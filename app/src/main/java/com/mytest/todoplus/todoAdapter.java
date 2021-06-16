package com.mytest.todoplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder> implements OnToDoItemClickListener, ItemTouchHelperListener {

    static ArrayList<todo_object> items = new ArrayList<todo_object>();
    OnToDoItemClickListener listener;

    //배열 리스트 items에 새로운 item 객체 추가하기기
    public static void addItem(todo_object item) {
        items.add(item);
        MainFragment.refresh();
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);
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
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
        MainFragment.refresh();
    }

    public final void editItem(int position, todo_object td_o) {
        items.set(position, td_o);
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
        holder.checkBox.setChecked(item.isSelected());

        //체크상태가 달라질 때 불려지는 리스너
        //사용자가 체크할 값에 대해 체크 이벤트를 달아서 setSelected를 해줌으로써 체크를 할 수 있게 한다.
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //체크박스의 가장 마지막 상태 저장한 것을 불러온다
                item.setSelected(isChecked);
            }
        });

        //뷰 재사용을 막고, 계속 새로운 아이템을 생성하여 데이터 꼬임 문제 해결.
        holder.setIsRecyclable(false);
        holder.onBind(items.get(position),position);

    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        todo_object item = items.get(from_position);

        //현재 위치와 움직일 위치 입력받아서 이동하기
        items.remove(from_position);
        items.add(to_position, item);
        item.setNumber(to_position);

        notifyItemMoved(from_position, to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        //position 값 입력받아서 해당 아이템 삭제
        items.remove(position);
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

            checkBox=itemView.findViewById(R.id.checkbox);


            //viewholder 안에서 전달받은 뷰를 클릭했을 때~ listener 쪽으로 전달할 수 있다.***
            //각각의 item 뷰가 클릭되었을 때~ 인터페이스로 만든 함수 호출
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

        public void onBind(todo_object item,int position){
            item_title.setText(item.getItemTitle());
            item_time.setText(item.getItemTime());
            item_place.setText(item.getItemPlace());
            item_line.setImageResource(item.getItemLine());
            item_type.setText(item.getItemType());
            item_day.setText(item.getItemDay());

            item.setNumber(position);

        }
    }

}