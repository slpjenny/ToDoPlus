package com.mytest.todoplus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {
    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    // myMemo라는 테이블 생성 후 title과 content라는 column 생성
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists MYMEMO ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "title TEXT,"
                + "content TEXT);";
        db.execSQL(sql);

    }

    // 데이터 삽입 함수
    public void insert(String name, String content) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MYMEMO VALUES(null, '" + name + "', '" + content + "');");
        db.close();
    }

    // 데이터 삭제 함수
    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 현재 메모 제목과 일치하는 행 삭제
        db.execSQL("DELETE FROM MYMEMO WHERE name='" + name + "';");
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE if exists mytable";

        db.execSQL(sql);
        onCreate(db);

    }
}
