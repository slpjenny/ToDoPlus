package com.mytest.todoplus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Todo_Plus.db";

    public static final String TABLE_MYMEMO = "MYMEMO";
    public static final String TABLE_TOROUTINE = "TABLE_TOROUTINE";


    public SQLiteHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    // myMemo라는 테이블 생성 후 title과 content라는 column 생성
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE if not exists MYMEMO"
//                + "("
//                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + "title TEXT,"
//                + "content TEXT);";

//        db.execSQL(CREATE_MYMEMO);
        db.execSQL(CREATE_TOROUTINE);
    }

    public static final String CREATE_MYMEMO = "CREATE TABLE if not exists MYMEMO"
            + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT,"
            + "content TEXT);";

    public static final String CREATE_TOROUTINE = "CREATE TABLE if not exists TABLE_TOROUTINE"
            +
            "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "type TEXT,"
            + "title TEXT,"
            + "time TEXT,"
            + "place TEXT,"
            + "day TEXT,"
            + "position INT);";


    // 데이터 삽입 함수
    public void insertMemo(String title, String content) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MYMEMO VALUES(null, '" + title + "', '" + content + "');");
        db.close();
    }

    public void insert_Toroutine(String title, String time, String place, String type, String day, int position) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TABLE_TOROUTINE VALUES(null, '" + type + "', '" + title + "', '" + time + "', '" + place + "', '" + day + "', '" + position + "');");
        db.close();
        ;
    }


    // 데이터 삭제 함수
    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 현재 메모 제목과 일치하는 행 삭제
        db.execSQL("DELETE FROM MYMEMO WHERE name='" + name + "';");
        db.close();
    }


    @Override
    //설정 버전 올렸을때
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //기존 table Drop
        String sql = "DROP TABLE if exists TABLE_MYMEMO";
        db.execSQL(sql);
        //onCreate 호출해서 table 다시 생성
        onCreate(db);

    }
}
