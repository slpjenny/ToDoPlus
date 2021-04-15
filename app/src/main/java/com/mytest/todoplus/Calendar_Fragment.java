package com.mytest.todoplus;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Calendar_Fragment extends Fragment {

    public Calendar_Fragment() {
        // Required empty public constructor
    }

    public static Calendar_Fragment newInstance(String param1, String param2) {
        Calendar_Fragment fragment = new Calendar_Fragment();
        Bundle args = new Bundle();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_calendar_, container, false);

        ImageView plusMemo=rootView.findViewById(R.id.plusMemo);
        plusMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //커스텀 다이얼로그 띄우기
                memo_add_dialog memoDlg = new memo_add_dialog(getContext());
                memoDlg.show();
            }
        });


        return rootView;
    }
}