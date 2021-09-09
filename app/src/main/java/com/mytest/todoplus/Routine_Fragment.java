package com.mytest.todoplus;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Routine_Fragment extends androidx.fragment.app.Fragment {

    public static routinesAdapter adapter=new routinesAdapter();

    public Routine_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_routine_, container, false);

        routines_object routines_object=new routines_object("헐크 쓰다듬기");
        adapter.addItem(routines_object);

        RecyclerView recyclerView=rootView.findViewById(R.id.routines_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return rootView;

    }

    static public void refresh(){
        adapter.notifyDataSetChanged();
    }
}