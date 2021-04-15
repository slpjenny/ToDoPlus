package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class memo_add_dialog extends Dialog {

    private EditText memoName;
    private EditText memoContent;
    private Button memoAdd_ok;
    private ImageView memo_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_add_dialog);

        memoName=findViewById(R.id.memoName);
        memoContent=findViewById(R.id.memoContent);
        memoAdd_ok=findViewById(R.id.memoAdd_ok);
        memo_cancel=findViewById(R.id.memo_cancel);

        memo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public memo_add_dialog(@NonNull Context context) {
        super(context);
    }
}
