package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class routine_add_dialog extends Dialog {

    private EditText rtn_title;
    private EditText rtn_day;
    private EditText rtn_time;
    private EditText rtn_place;

    private ImageView rtn_cancel;
    private Button rtn_add_ok;

    static String rtn_title_str;
    static String rtn_day_str;
    static String rtn_time_str;
    static String rtn_place_str;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_add_dialog);

        rtn_title=findViewById(R.id.rtn_title);
        rtn_day=findViewById(R.id.rtn_day);
        rtn_time=findViewById(R.id.rtn_time);
        rtn_place=findViewById(R.id.rtn_place);

        rtn_cancel=findViewById(R.id.rtn_cancel);
        rtn_add_ok=findViewById(R.id.rtn_add_ok);

        rtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        rtn_add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                rtn_title_str=rtn_title.getText().toString();
                rtn_day_str=rtn_day.getText().toString();
                rtn_time_str=rtn_time.getText().toString();
                rtn_place_str=rtn_place.getText().toString();

                todo_object todo_item = new todo_object(rtn_title_str,rtn_time_str,rtn_place_str, R.drawable.yellow_vertical_line,"Routine",rtn_day_str);
                todoAdapter.addItem(todo_item);

                Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });
    }
    public routine_add_dialog(@NonNull Context context) {
        super(context);
    }
}
