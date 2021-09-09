package com.mytest.todoplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class routinesAdapter extends RecyclerView.Adapter<routinesAdapter.ViewHolder> {

    static ArrayList<routines_object> routines = new ArrayList<routines_object>();

    public final void addItem(routines_object routine){
        routines.add(routine);
        //데이터 refresh 필
        Routine_Fragment.refresh();

    }
    @Override
    public int getItemCount() {
        return routines.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView= inflater.inflate(R.layout.routines_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        static TextView routine_item_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            routine_item_title=itemView.findViewById(R.id.routine_item_title);

        }

        public static void setItem(routines_object routines){
            routine_item_title.setText(routines.getRoutine_item_title());
        }
        public void onBind(routines_object routines,int position){
            routine_item_title.setText(routines.getRoutine_item_title());

        }
    }
}
