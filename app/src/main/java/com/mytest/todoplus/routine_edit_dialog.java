package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
public class routine_edit_dialog extends Dialog {

    private EditText rtn_title_edit;
    private EditText rtn_day_edit;
    private EditText rtn_time_edit;
    private EditText rtn_place_edit;

    private ImageView rtnEdit_cancel;
    private Button rtnEdit_ok;
    private Button rtnEdit_remove;

    //각각 editText 부분에 변경되어서 적힐 내용
    static String rtn_Ename_str;
    static String rtn_Etime_str;
    static String rtn_Eplace_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_edit_dialog);

        rtn_title_edit=findViewById(R.id.rtn_title_edit);
        rtn_day_edit=findViewById(R.id.rtn_day_edit);
        rtn_time_edit=findViewById(R.id.rtn_time_edit);
        rtn_place_edit=findViewById(R.id.rtn_place_edit);

        rtnEdit_cancel=findViewById(R.id.rtnEdit_cancel);
        rtnEdit_ok=findViewById(R.id.rtnEdit_ok);
        rtnEdit_remove=findViewById(R.id.rtnEdit_remove);

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


    }

    public routine_edit_dialog(@NonNull Context context) {
        super(context);
    }
}
