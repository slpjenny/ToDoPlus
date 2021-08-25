package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Date;
import java.text.SimpleDateFormat;


public class memo_add_dialog extends Dialog {

    private EditText memoName;
    private EditText memoContent;
    private Button memoAdd_ok;
    private ImageView memo_cancel;

    //날짜 가져올 변수
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_add_dialog);

        // DB 생성코드
        SQLiteHelper helper;
        SQLiteDatabase db;
        helper = new SQLiteHelper(getContext(), null, 2);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        Cursor c = db.query("mymemo", null, null, null, null, null, null, null);

        memoName = findViewById(R.id.memoName);
        memoContent = findViewById(R.id.memoContent);
        memoAdd_ok = findViewById(R.id.memoAdd_ok);
        memo_cancel = findViewById(R.id.memo_cancel);

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

                // 제목 or 내용 입력하지 않았을 때
                if (name == null || name.equals("")) {
                    Toast.makeText(getContext(), "제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();

                } else if (content == null || content.equals("")) {
                    Toast.makeText(getContext(), "메모를 입력해 주세요.", Toast.LENGTH_SHORT).show();

                // 제목과 내용 모두 입력했을 때
                } else {
                    c.moveToLast();
                    // 같은 날짜에 해당하는 데이터가 있으면 저장 안 되게
                    if(c.getString(c.getColumnIndex("date")).equals(getTime())){
                        Toast.makeText(getContext(), "이미 저장된 메모가 있어요!", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                    // 같은 날짜에 해당하는 데이터가 없으면 저장 되게
                    else if (!c.getString(c.getColumnIndex("date")).equals(getTime())){
                        // 오늘 저장한 메모 데이터가 없으면 db에 저장
                        helper.insertMemo(name,content,getTime(),100);
                        Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                }
            }
        });

    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    public memo_add_dialog(@NonNull Context context) {
        super(context);
    }
}
