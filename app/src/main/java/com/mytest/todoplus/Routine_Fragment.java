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

    public static routineAdapter adapter2 = new routineAdapter();

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

        RecyclerView routineRecyclerView = rootView.findViewById(R.id.routines_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        routineRecyclerView.setLayoutManager(layoutManager);
//        routineAdapter adapter = new routineAdapter();

        adapter2.notifyDataSetChanged();
        routineRecyclerView.setAdapter(adapter2);

        return rootView;
    }

    static public void refresh2(){ adapter2.notifyDataSetChanged();}

}