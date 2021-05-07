package com.mytest.todoplus;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

public class todo_edit_dlg2 extends DialogFragment {

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

    public todo_edit_dlg2() {}

    public static todo_edit_dlg2 getInstance() {
        todo_edit_dlg2 dlg2 = new todo_edit_dlg2();
        return dlg2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo_edit_dlg2, container, false);

        todo_name_edit=v.findViewById(R.id.todo_name_edit);
        todo_time_edit=v.findViewById(R.id.todo_time_edit);
        todo_place_edit=v.findViewById(R.id.todo_place_edit);

        todoEdit_cancel=v.findViewById(R.id.todoEdit_cancel);
        todoEdit_ok=v.findViewById(R.id.todoEdit_ok);
        todoEdit_remove=v.findViewById(R.id.todoEdit_remove);

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

                //이제 해당 아이템을 전달받았다!
                Bundle args=getArguments();
                if(args !=null){
                    todo_object item= (todo_object) args.getSerializable("itemInfo");
                    Log.d("d",item.itemDay);
                }


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
        return v;
    }
}