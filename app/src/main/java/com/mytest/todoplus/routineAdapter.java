package com.mytest.todoplus;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class routineAdapter extends RecyclerView.Adapter<routineAdapter.ViewHolder> {

    static ArrayList<routine_object> routines = new ArrayList<routine_object>();
    //db 선언
    public static SQLiteHelper helper;
    SQLiteDatabase db;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.routines_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        routine_object item= routines.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public void addItem(routine_object item){
        routines.add(item);
        Routine_Fragment.refresh2();
    }


//    메인에 있는 리사이클러뷰랑 순서 다르게 세니까, position으로 삭제하면 순서 뒤바뀜.
//    제목 비교 후 삭제 방법?
    public final void removeItem2(int position){
        routine_object itemInfo = getItem(position);
        String title=itemInfo.getRoutine_title();

        routines.remove(position);
//        helper.delete_myroutine(title);
        notifyItemRemoved(position);

        notifyItemRangeChanged(position, routines.size());
//        Routine_Fragment.refresh2();

    }

    public final void removeItemAll(){
        routines.clear();
    }

    public void setItems(ArrayList<routine_object> items){
        this.routines = items;
    }

    public routine_object getItem(int position){
        return routines.get(position);
    }

    public void setItem(int position, routine_object item){
        routines.set(position,item);
    }


    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView routine_item_title;

        public ViewHolder(View itemView){
            super(itemView);

            routine_item_title = itemView.findViewById(R.id.routine_item_title);
        }

        public void setItem(routine_object item){
            routine_item_title.setText(item.getRoutine_title());
        }
    }
}
