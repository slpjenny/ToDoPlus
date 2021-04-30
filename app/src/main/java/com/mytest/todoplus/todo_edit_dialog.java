package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class todo_edit_dialog extends Dialog {

    private EditText todo_name_edit;
    private EditText todo_time_edit;
    private EditText todo_place_edit;

    private ImageView todoEdit_cancel;
    private Button todoEdit_ok;
    private Button todoEdit_remove;

    //각각 editText 부분에 변경되어서 적힐 내용
    static String todo_Ename_str;
    static String todo_Etime_str;
    static String todo_Eplace_str;
    private Bundle arguments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_edit_dialog);

        todo_name_edit=findViewById(R.id.todo_name_edit);
        todo_time_edit=findViewById(R.id.todo_time_edit);
        todo_place_edit=findViewById(R.id.todo_place_edit);

        todoEdit_cancel=findViewById(R.id.todoEdit_cancel);
        todoEdit_ok=findViewById(R.id.todoEdit_ok);
        todoEdit_remove=findViewById(R.id.todoEdit_remove);


        todoEdit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //'x'버튼 누르면 그냥 다이얼로그 사라지기
                dismiss();
            }
        });


        todoEdit_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리싸이클러뷰에서 해당 아이템 삭제시키기 기능
//                Bundle mArgs = getArguments();
//                todo_object item=(todo_object) mArgs.getSerializable("itemInfo");

                //이제 해당 아이템을 전달받았다!
//                items.remove(item);

                //다이얼로그 사라짐.
                dismiss();
            }
        });

        todoEdit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //원래 써있는 정보 수정해서 아이템 내용 바꾸기
            }
        });


    }

    public todo_edit_dialog(@NonNull Context context) {
        super(context);
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
    }

    public Bundle getArguments() {
        return arguments;
    }
}
