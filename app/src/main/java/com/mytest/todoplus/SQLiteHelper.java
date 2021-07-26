package com.mytest.todoplus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {

    public static todoAdapter adapter = new todoAdapter();

    public static final String DATABASE_NAME = "Todo_Plus.db";

    public static final String TABLE_MYMEMO = "MYMEMO";
    public static final String TABLE_TOROUTINE = "TABLE_TOROUTINE";

    public SQLiteHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    // myMemo라는 테이블 생성 후 title과 content라는 column 생성
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MYMEMO);
        db.execSQL(CREATE_TOROUTINE);
    }

    public static final String CREATE_MYMEMO = "CREATE TABLE if not exists MYMEMO"
            + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT,"
            + "content TEXT,"
            + "date TEXT,"
            + "acheivement INTEGER);";

    //line은 저장이 아니라 type으로 구분해서 다시 불러와야하나?
    //position 값 못불러오는 중
    public static final String CREATE_TOROUTINE = "CREATE TABLE if not exists TABLE_TOROUTINE"
            +
            "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "type TEXT,"
            + "title TEXT,"
            + "time TEXT,"
            + "place TEXT,"
            + "day TEXT);";


    //----------------------------------------------------------------------
    // 데이터 삽입 함수
    public void insertMemo(String title, String content, String date, int achv) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MYMEMO VALUES(null, '" + title + "', '" + content + "','" + date + "','" + achv + "');");
        db.close();
    }

    //todo 항목은 day가 없다
    public void insert_Toroutine(String title, String time, String place, String type, String day) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TABLE_TOROUTINE VALUES(null, '" + type + "', '" + title + "', '" + time + "', '" + place + "', '" + day + "');");
        db.close();
        ;
    }

    //----------------------------------------------------------------

    // 데이터 삭제 함수
    public void delete_mymemo(String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 현재 메모 제목과 일치하는 행 삭제
        db.execSQL("DELETE FROM MYMEMO WHERE name='" + name + "';");
        db.close();
    }

    public void delete_todortn(int position) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("position", String.valueOf(position));
        position=position+1;
        db.execSQL("DELETE FROM TABLE_TOROUTINE WHERE _id="+position);
    }

    //-----------------------------------------------------------------
    //데이터 조회
    //원하는 항목만 조회 가능 : getColumnIndex() p.562
    public void exequte_Query() {
        SQLiteDatabase db = getWritableDatabase();

        String[] dbData = new String[]{};
        //Cursor-> 여러 레코드를 한 개씩 넘어가며 접근하는 객체
        //rawQurery-> 결과 값을 Cursor 객체로 받을 수 있는 SQL 실행방법
        Cursor cursor = db.rawQuery("select _id, type, title, time, place, day from " + TABLE_TOROUTINE, null);
        int recordCount = cursor.getCount(); //레코드 개수 세기

        //들어있는 투두/루틴 개수만큼 반복해서 데이터 조회
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext(); //다음 결과 레코드로 넘어가기
            //데이터 불러오기
            int id = cursor.getInt(0);
            String type = cursor.getString(1);
            String title = cursor.getString(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);
            String day = cursor.getString(5);

            Log.d("type", type);

            //저장했던 아이템들을 adapter에 다시 올리기
            if (type.equals("Routine")) {
                todo_object todo_item = new todo_object(title, time, place, R.drawable.yellow_vertical_line, type, day);
                adapter.addItem(todo_item);
            } else {
                todo_object todo_item = new todo_object(title, time, place, R.drawable.green_vertical_line, type, day);
                adapter.addItem(todo_item);
            }
        }

        cursor.close();
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
