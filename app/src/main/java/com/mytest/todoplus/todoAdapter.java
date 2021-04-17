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
        static TextView todo_title;
        static TextView todo_time;
        static TextView todo_place;

        public ViewHolder(final View itemView) {
            super(itemView);

            todo_title = itemView.findViewById(R.id.todo_title);
            todo_time = itemView.findViewById(R.id.todo_time);
            todo_place = itemView.findViewById(R.id.todo_place);

            //viewholder 안에서 전달받은 뷰를 클릭했을 때~ listener 쪽으로 전달할 수 있다.
            //각각의 item 뷰가 클릭되었을 때~ 인터페이스로 만든 함수 호출
        }

        public static void setItem(todo_object item) {
            todo_title.setText(item.getTodoTitle());
            todo_time.setText(item.getTodoTime());
            todo_place.setText(item.getTodoPlace());
        }

    }
}