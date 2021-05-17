package com.mytest.todoplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder>implements OnToDoItemClickListener{

    static ArrayList<todo_object> items=new ArrayList<todo_object>();
    OnToDoItemClickListener listener;

    //배열 리스트 items에 새로운 item 객체 추가하기기
   public static void addItem(todo_object item){items.add(item);}
//   public static void

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public todo_object getItem(int position){
       return items.get(position);
    }

    public void setOnItemClickListener(OnToDoItemClickListener listener){
       this.listener=listener;
    }

    public final void removeItem(int position){
        items.remove(position); //여기까지는 오케이. 리스트에서는 삭제된 것 확실. ui에서 다른게 삭제되고있는 것.
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,items.size());
//       notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener!=null){
            listener.onItemClick(holder,view,position);
        }
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
        static TextView item_day;

        public ViewHolder(View itemView, OnToDoItemClickListener listener) {
            super(itemView);

            item_title = itemView.findViewById(R.id.item_title);
            item_time = itemView.findViewById(R.id.item_time);
            item_place = itemView.findViewById(R.id.item_place);
            item_line= itemView.findViewById(R.id.item_line);
            item_type=itemView.findViewById(R.id.item_type);
            item_day=itemView.findViewById(R.id.item_day);


            //viewholder 안에서 전달받은 뷰를 클릭했을 때~ listener 쪽으로 전달할 수 있다.***
            //각각의 item 뷰가 클릭되었을 때~ 인터페이스로 만든 함수 호출
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,position);
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
    }
}