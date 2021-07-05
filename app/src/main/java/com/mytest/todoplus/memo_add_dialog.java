package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

        // DB 생성코드
        SQLiteHelper helper;
        SQLiteDatabase db;
        helper = new SQLiteHelper(getContext(), null,1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

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

        // 확인 눌렀을 때 DB에 데이터 추가
        memoAdd_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = memoName.getText().toString();
                String content = memoContent.getText().toString();

                helper.insertMemo(name, content);
                dismiss();
            }
        });

    }

    public memo_add_dialog(@NonNull Context context) {
        super(context);
    }
}
