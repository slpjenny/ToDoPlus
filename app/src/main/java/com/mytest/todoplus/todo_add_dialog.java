package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class todo_add_dialog extends Dialog {

    private ImageView todoAdd_cancel;
    private EditText todo_title;
    private EditText todo_time;
    private EditText todo_place;
    private Button todoAdd_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_add_dialog);

        todo_title=findViewById(R.id.todo_title);
        todo_time=findViewById(R.id.todo_time);
        todo_place=findViewById(R.id.todo_place);

        todoAdd_cancel=findViewById(R.id.todoAdd_cancel);
        todoAdd_ok=findViewById(R.id.todoAdd_ok);

        todoAdd_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        todoAdd_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }

    public todo_add_dialog(@NonNull Context context) {
        super(context);
    }
}
