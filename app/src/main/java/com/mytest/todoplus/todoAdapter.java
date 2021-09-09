package com.mytest.todoplus;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder> implements OnToDoItemClickListener, ItemTouchHelperListener {

    static ArrayList<todo_object> items = new ArrayList<todo_object>();

    OnToDoItemClickListener listener;

    //db 선언
    public static SQLiteHelper helper;
    SQLiteDatabase db;


    //배열 리스트 items에 새로운 item 객체 추가하기기
    public final void addItem(todo_object item) {
        items.add(item);
        MainFragment.refresh();
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);

        //db선언
        helper = new SQLiteHelper(itemView.getContext(), null,3);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        return new ViewHolder(itemView, this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public todo_object getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnToDoItemClickListener listener) {
        this.listener = listener;
    }

    public final void removeItem(int position) {
        //position으로 어떤 아이템인지 정보 얻기
        todo_object itemInfo=getItem(position);
        String title=itemInfo.getItemTitle();

        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
        helper.delete_todortn(title);
        MainFragment.refresh();
    }

    public final void editItem(int position, todo_object td_o, boolean isChecked) {
        int checkedState;

        if (isChecked == true){
            checkedState = 1;
        }else{
            checkedState = 0;
        }

        //바꾸기 전의 아이템에서 title 뽑아내기
        todo_object origin_item=getItem(position);
        String origin_title=origin_item.getItemTitle();

        //기존 자리에 새로운 객체를 생성해서 넣는 것
        items.set(position, td_o);
        td_o.setSelected(isChecked);
        
        String title=td_o.getItemTitle();
        String time=td_o.getItemTime();
        String place=td_o.getItemPlace();
        String day=td_o.getItemDay();

        //기존의 title 기준으로 데이터 찾아서 db 내용 변경
        helper.update_Query(title,time,place,day,origin_title);
        //checkbox 상태여부 변경될 때마다 db 정보 update
        helper.update_checkbox_Qurey(checkedState,origin_title);

        notifyItemChanged(position);
        MainFragment.refresh();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull todoAdapter.ViewHolder holder, int position) {
        final todo_object item = items.get(position);

        //checkbox 상태변화있을시 불리는 리스너 초기화 **
        holder.checkBox.setOnCheckedChangeListener(null);

        //현재 item의 체크 상태 여부를 읽어 체크박스값을 초기화해준다.
        //모델 클래스의 getter로 체크 상태값을 가져온 다음, setter를 통해 이 값을 아이템 안의 체크박스에 set한다
        holder.checkBox.setChecked(item.isSelected());

        //체크상태가 달라질 때 불려지는 리스너
        //사용자가 체크할 값에 대해 체크 이벤트를 달아서 이전에 저장된 isChecked 값을 불러온다
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                item.setSelected(isChecked);
                //checkbox state db에 저장
                boolean selected = item.isSelected();
                String itemTitle=item.getItemTitle();

                //update_Query 에 넣을 최종 체크상태
                int saveChecked;

                if (selected == true){
                    saveChecked = 1;
                }else{
                    saveChecked = 0;
                }

                //checkbox 상태여부 변경될 때마다 db 정보 update
                helper.update_checkbox_Qurey(saveChecked,itemTitle);
            }
        });

        //뷰 재사용을 막고, 계속 새로운 아이템을 생성하여 데이터 꼬임 문제 해결.
        holder.setIsRecyclable(false);
        holder.onBind(items.get(position), position);

    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        todo_object item = items.get(from_position);

        //현재 위치와 움직일 위치 입력받아서 이동하기
        //현재 위치의 아이템 삭제
        items.remove(from_position);
        Log.d("from_hahaha", String.valueOf(from_position));
        //이동할 위치에 아까 그 아이템을 다시 추가시킨다
        items.add(to_position, item);
        Log.d("to_hahaha", String.valueOf(to_position));
        item.setNumber(to_position);

        notifyItemMoved(from_position, to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        //position값으로 title 알아내서 해당 아이템 삭제
        todo_object itemInfo=getItem(position);
        String title=itemInfo.getItemTitle();

        items.remove(position);
        Log.d("position", String.valueOf(position));
        helper.delete_todortn(title);
        notifyItemRemoved(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        static TextView item_title;
        static TextView item_time;
        static TextView item_place;
        static ImageView item_line;
        static TextView item_type;
        static TextView item_day;

        static CheckBox checkBox;

        public ViewHolder(View itemView, OnToDoItemClickListener listener) {
            super(itemView);

            item_title = itemView.findViewById(R.id.item_title);
            item_time = itemView.findViewById(R.id.item_time);
            item_place = itemView.findViewById(R.id.item_place);
            item_line = itemView.findViewById(R.id.item_line);
            item_type = itemView.findViewById(R.id.item_type);
            item_day = itemView.findViewById(R.id.item_day);

            checkBox = itemView.findViewById(R.id.checkbox);

            //viewholder 안에서 전달받은 뷰를 클릭했을 때~ listener 쪽으로 전달할 수 있다.***
            //각각의 item 뷰가 클릭되었을 때~ 인터페이스로 만든 함수 호출
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //아이템 position값 반환
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }

        public static void setItem(todo_object item) {
            item_title.setText(item.getItemTitle());
            item_time.setText(item.getItemTime());
            item_place.setText(item.getItemPlace());
            item_line.setImageResource(item.getItemLine());
            item_type.setText(item.getItemType());
            item_day.setText(item.getItemDay());
        }

        public void onBind(todo_object item, int position) {
            item_title.setText(item.getItemTitle());
            item_time.setText(item.getItemTime());
            item_place.setText(item.getItemPlace());
            item_line.setImageResource(item.getItemLine());
            item_type.setText(item.getItemType());
            item_day.setText(item.getItemDay());


            item.setNumber(position);
        }

        public int itemPosition() {
            int itemPosition = getAdapterPosition();

            return itemPosition;
        }
    }

}