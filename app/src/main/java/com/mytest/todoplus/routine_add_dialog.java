package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class routine_add_dialog extends Dialog {

    private EditText rtn_title;
    private EditText rtn_day;
    private EditText rtn_time;
    private EditText rtn_place;

    private ImageView rtn_cancel;
    private Button rtn_add_ok;

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
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    public routine_add_dialog(@NonNull Context context) {
        super(context);
    }
}
