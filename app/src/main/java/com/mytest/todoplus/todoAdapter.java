package com.mytest.todoplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder>{

    ArrayList<todo_object> items=new ArrayList<todo_object>();

    //배열 리스트 items에 새로운 item 객체 추가하기기
   public void addItem(todo_object item){items.add(item);}

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull todoAdapter.ViewHolder holder, int position) {
        todo_object item=items.get(position);
        ViewHolder.setItem(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        static TextView item_title;
        static TextView item_time;
        static TextView item_place;
        static ImageView item_line;
        static TextView item_type;

        public ViewHolder(final View itemView) {
            super(itemView);

            item_title = itemView.findViewById(R.id.item_title);
            item_time = itemView.findViewById(R.id.item_time);
            item_place = itemView.findViewById(R.id.item_place);
            item_line= itemView.findViewById(R.id.item_line);
            item_type=itemView.findViewById(R.id.item_type);

            //viewholder 안에서 전달받은 뷰를 클릭했을 때~ listener 쪽으로 전달할 수 있다.
            //각각의 item 뷰가 클릭되었을 때~ 인터페이스로 만든 함수 호출
        }

        public static void setItem(todo_object item) {
            item_title.setText(item.getItemTitle());
            item_time.setText(item.getItemTime());
            item_place.setText(item.getItemPlace());
            item_line.setImageResource(item.getItemLine());
            item_type.setText(item.getItemType());
        }
    }
}