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

    public static routinesAdapter adapter2=new routinesAdapter();

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


        RecyclerView recyclerView=rootView.findViewById(R.id.routines_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter2);
        Log.d("실행","3");


        String title = "gjfgjf";
        routines_object routine_item=new routines_object(title);

        adapter2.addItem(routine_item);
        Log.d("실행","4");


        //아이템은 잘 들어옴
//        Log.d("ItemCount", String.valueOf(num));


        return rootView;
    }

    static public void refresh(){
        adapter2.notifyDataSetChanged();
    }
}