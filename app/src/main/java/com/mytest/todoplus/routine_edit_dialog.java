package com.mytest.todoplus;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class routine_edit_dialog extends DialogFragment {

    //new
    public static todoAdapter adapter=new todoAdapter();

    private EditText rtn_title_edit;
    private EditText rtn_day_edit;
    private EditText rtn_time_edit;
    private EditText rtn_place_edit;

    private ImageView rtnEdit_cancel;
    private Button rtnEdit_ok;
    private Button rtnEdit_remove;

    //각각 editText 부분에 변경되어서 적힐 내용
    String rtn_Ename_str;
    String rtn_Eday_Str;
    String rtn_Etime_str;
    String rtn_Eplace_str;

    int position;

    public routine_edit_dialog() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_routine_edit_dialog, container, false);

        rtn_title_edit=v.findViewById(R.id.rtn_title_edit);
        rtn_day_edit=v.findViewById(R.id.rtn_day_edit);
        rtn_time_edit=v.findViewById(R.id.rtn_time_edit);
        rtn_place_edit=v.findViewById(R.id.rtn_place_edit);

        rtnEdit_cancel=v.findViewById(R.id.rtnEdit_cancel);
        rtnEdit_ok=v.findViewById(R.id.rtnEdit_ok);
        rtnEdit_remove=v.findViewById(R.id.rtnEdit_remove);

        //MainFragment로부터 받은 item 정보 꺼내서 내용수정 전 hint로 알려주기
        if(getArguments() != null) {
            rtn_Ename_str = getArguments().getString("itemTitle");
            rtn_Eday_Str = getArguments().getString("itemDay");
            rtn_Etime_str = getArguments().getString("itemTime");
            rtn_Eplace_str = getArguments().getString("itemPlace");
            position=getArguments().getInt("itemPosition");

        }

        rtn_title_edit.setText(rtn_Ename_str);
        rtn_day_edit.setText(rtn_Eday_Str);
        rtn_time_edit.setText(rtn_Etime_str);
        rtn_place_edit.setText(rtn_Eplace_str);

        rtnEdit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //'x'버튼 누르면 그냥 다이얼로그 사라지기
                dismiss();
            }
        });

        rtnEdit_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리싸이클러뷰에서 해당 아이템 삭제시키기 기능
                adapter.removeItem(position);

                //다이얼로그 사라짐.
                dismiss();
            }
        });

        rtnEdit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //원래 써있는 정보 수정해서 아이템 내용 바꾸기

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        //DialogFragment 의 넓이와 높이를 사용자 지정으로 바꾼다.
        int width=getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height=getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        super.onResume();
    }
}