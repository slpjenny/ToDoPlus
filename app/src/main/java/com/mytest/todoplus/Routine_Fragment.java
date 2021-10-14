package com.mytest.todoplus;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Routine_Fragment extends androidx.fragment.app.Fragment {

    //db 선언
    public static SQLiteHelper helper;
    public static SQLiteDatabase db;

    public static int isTwice_forRoutine;

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

        //데이터 저장되어있던것 불러오기
//        if(isTwice_forRoutine == 0){
            helper = new SQLiteHelper(getActivity(),null,3);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            // 루틴 타입인 데이터만 조회하기
        helper.exequte_RoutineQuery();
//        }

        RecyclerView routineRecyclerView = rootView.findViewById(R.id.routines_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        routineRecyclerView.setLayoutManager(layoutManager);

        adapter2.notifyDataSetChanged();
        routineRecyclerView.setAdapter(adapter2);

        return rootView;
    }

    static public void refresh2(){
        adapter2.notifyDataSetChanged();
        }

    @Override
    //데이터를 저장
    public void onPause() {
        super.onPause();

    }

    @Override
    //잠시 저장해둔 데이터를 불러옴
    public void onResume() {
        super.onResume();

        //다시 이 화면으로 돌아오면, adapter 초기화하고 루틴만 불러오는거 다시하기
        adapter2.removeItemAll();
        helper.exequte_RoutineQuery();

    }
}