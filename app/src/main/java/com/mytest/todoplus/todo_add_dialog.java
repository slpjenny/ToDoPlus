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
import androidx.fragment.app.FragmentTransaction;

public class todo_add_dialog extends Dialog {

    private ImageView todoAdd_cancel;
    private EditText todo_title;
    private EditText todo_time;
    private EditText todo_place;
    private Button todoAdd_ok;

    static String todo_title_str;
    static String todo_time_str;
    static String todo_place_str;

    static todoAdapter adapter=new todoAdapter();

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
                todo_title_str=todo_title.getText().toString();
                todo_time_str=todo_time.getText().toString();
                todo_place_str=todo_place.getText().toString();

                todo_object todo_item = new todo_object(todo_title_str,todo_time_str,todo_place_str, R.drawable.green_vertical_line,"ToDo");
                adapter.addItem(todo_item);
                adapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

    }

    public todo_add_dialog(@NonNull Context context) {
        super(context);
    }
}
