package com.mytest.todoplus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {

    public static todoAdapter adapter = new todoAdapter();
    public static routineAdapter adapter2 = new routineAdapter();

    public static final String DATABASE_NAME = "Todo_Plus.db";

    public static final String TABLE_MYMEMO = "MYMEMO";
    public static final String TABLE_TOROUTINE = "TABLE_TOROUTINE";
    public static final String TABLE_MYROUTINE ="MYROUTINE";

    public static final int VERSION=4;

    public SQLiteHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    // myMemo라는 테이블 생성 후 title과 content라는 column 생성
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MYMEMO);
        db.execSQL(CREATE_TOROUTINE);
//        db.execSQL(CREATE_MYROUTINE);
    }

    public static final String CREATE_MYMEMO = "CREATE TABLE if not exists MYMEMO"
            + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT,"
            + "content TEXT,"
            + "date TEXT,"
            + "acheivement INTEGER);";

    public static final String CREATE_TOROUTINE = "CREATE TABLE if not exists TABLE_TOROUTINE"
            +
            "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "type TEXT,"
            + "title TEXT,"
            + "time TEXT,"
            + "place TEXT,"
            + "day TEXT,"
            + "checked INTEGER);";

    //루틴만 있는 테이블 만들기
//    public static final String CREATE_MYROUTINE = "CREATE TABLE if not exists MYROUTINE"
//            +
//            "("
//            + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + "title TEXT,"
//            + "routineposition INTEGER);";

    //----------------------------------------------------------------------
    // 데이터 삽입 함수
    public void insertMemo(String title, String content, String date, int achv) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MYMEMO VALUES(null, '" + title + "', '" + content + "','" + date + "','" + achv + "');");
        db.close();
    }

    //todo 아이템은 'day' 칼럼 없음
    public void insert_Toroutine(String type, String title, String time, String place, String day, int checked) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TABLE_TOROUTINE VALUES(null, '" + type + "', '" + title + "', '" + time + "', '" + place + "', '" + day + "','" + checked + "');");
        db.close();
    }

//    public void insert_routine(String title,int routineposition){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("INSERT INTO MYROUTINE VALUES(null, '" + title + "','" + routineposition +"');");
//        db.close();
//    }

    //----------------------------------------------------------------

    // 데이터 삭제 함수
    public void delete_mymemo(String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 현재 메모 제목과 일치하는 행 삭제
        db.execSQL("DELETE FROM MYMEMO WHERE name='" + name + "';");
        db.close();
    }

    public void delete_todortn(String title) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("title", title);
        db.execSQL("DELETE FROM TABLE_TOROUTINE WHERE title='" + title + "';");
    }

//    public void delete_myroutine(String title){
//        SQLiteDatabase db = getWritableDatabase();
//        Log.d("title", title);
//        db.execSQL("DELETE FROM MYROUTINE WHERE title='" + title + "';");
//    }

    //-----------------------------------------------------------------
    //데이터 변경
    public void update_Query(String title, String time, String place, String day, String originTitle) {
        SQLiteDatabase db = getWritableDatabase();
        //어떤 아이템의 내용을 바꿀건지 안적었네..ㅋ
        db.execSQL("UPDATE TABLE_TOROUTINE SET title='" + title + "', time='" + time + "', place='" + place + "', day='" + day + "' WHERE title='" + originTitle + "';");
    }

    //checkbox 상태여부 업데이트
    public void update_checkbox_Qurey(int checked, String ortitle) {
        SQLiteDatabase db = getWritableDatabase();
        //기준을 position으로 해야할 것 같은데?ㅠ
        db.execSQL("UPDATE TABLE_TOROUTINE SET checked='" + checked + "' WHERE title = '" + ortitle + "';");
    }

//    //원래 제목을 새로운 제목으로 바꾸기
//    public void update_myroutine(String ortitle, String newtitle) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("UPDATE MYROUTINE SET title='" + newtitle + "' WHERE title = '" + ortitle + "';");
//    }


    //-----------------------------------------------------------------
    //데이터 조회
    //원하는 항목만 조회 가능 : getColumnIndex() p.562
    public void exequte_Query() {
        SQLiteDatabase db = getWritableDatabase();

        String[] dbData = new String[]{};
        //Cursor-> 여러 레코드를 한 개씩 넘어가며 접근하는 객체
        //rawQurery-> 결과 값을 Cursor 객체로 받을 수 있는 SQL 실행방법
        Cursor cursor = db.rawQuery("select _id, type, title, time, place, day, checked from " + TABLE_TOROUTINE, null);
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
            int checked = cursor.getInt(6);

            //저장했던 아이템들을 adapter에 다시 올리기
            if (type.equals("Routine")) {
                todo_object todo_item = new todo_object(title, time, place, R.drawable.yellow_vertical_line, type, day);
                adapter.addItem(todo_item);

                if (checked == 0) {
                    todo_item.setSelected(false);
                } else if (checked == 1) {
                    todo_item.setSelected(true);
                }

            } else {
                todo_object todo_item = new todo_object(title, time, place, R.drawable.green_vertical_line, type, day);
                adapter.addItem(todo_item);

                if (checked == 0) {
                    todo_item.setSelected(false);
                } else if (checked == 1) {
                    todo_item.setSelected(true);
                }
            }
        }
        cursor.close();
    }

//    //routine 들만 모여있는 테이블 레코드 조회하기
//    public void exqute_myRoutineQuery(){
//        SQLiteDatabase db = getWritableDatabase();
//
//        String[] dbData = new String[]{"title"};
//
//        //type이 Routine 인 데이터만 추리기 위한 sql문
//        String sqlSelect = "SELECT TITLE FROM MYROUTINE";
//
//        //Cursor-> 여러 레코드를 한 개씩 넘어가며 접근하는 객체
//        //rawQurery-> 결과 값을 Cursor 객체로 받을 수 있는 SQL 실행방법
//        Cursor cursor2 = db.rawQuery(sqlSelect,null);
//        int recordCount = cursor2.getCount(); //레코드 개수 세기
//
//        //들어있는 루틴 개수만큼 반복해서 데이터 조회
//        for (int i = 0; i < recordCount; i++) {
//            cursor2.moveToNext(); //다음 결과 레코드로 넘어가기
//            //데이터 불러오기 (제목만 필요함)
//            int id = cursor2.getInt(0);
//            String title = cursor2.getString(1);
//
//            //저장했던 아이템들을 adapter에 다시 올리기
//            routine_object rtn_item = new routine_object(title);
//            adapter2.addItem(rtn_item);
//        }
//        cursor2.close();
//    }


    //루틴 타입인 데이터만 조회하기
    public void exequte_RoutineQuery() {

        SQLiteDatabase db = getWritableDatabase();

        String[] dbData = new String[]{"type", "title"};

        //type이 Routine 인 데이터만 추리기 위한 sql문
        String sqlSelect = "SELECT * FROM TABLE_TOROUTINE WHERE TYPE= 'Routine'";

        //Cursor-> 여러 레코드를 한 개씩 넘어가며 접근하는 객체
        //rawQurery-> 결과 값을 Cursor 객체로 받을 수 있는 SQL 실행방법
        Cursor cursor2 = db.rawQuery(sqlSelect,null);
        int recordCount = cursor2.getCount(); //레코드 개수 세기

        //들어있는 루틴 개수만큼 반복해서 데이터 조회
        for (int i = 0; i < recordCount; i++) {
            cursor2.moveToNext(); //다음 결과 레코드로 넘어가기
            //데이터 불러오기 (제목만 필요함)
            String title = cursor2.getString(2);

            //저장했던 아이템들을 adapter에 다시 올리기
            routine_object rtn_item = new routine_object(title);
            adapter2.addItem(rtn_item);
        }
        cursor2.close();
    }


// -----------------------------------------------------------
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
