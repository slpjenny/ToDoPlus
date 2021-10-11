package com.mytest.todoplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class routineAdapter extends RecyclerView.Adapter<routineAdapter.ViewHolder> {

    static ArrayList<routine_object> routines = new ArrayList<routine_object>();

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
