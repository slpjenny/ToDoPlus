package com.mytest.todoplus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Calendar_Fragment extends Fragment implements CalendarAdapter.OnItemListener {

    private TextView monthText;
    private TextView yearText;

    private RecyclerView calenderRecyclerView;
    private LocalDate selectedDate;
    private Context mcontext; // fragment에서 토스트 사용하려고 추가함

    //날짜 가져올 변수
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    ImageView dot;

    public Calendar_Fragment() {
        // Required empty public constructor
    }

    public static Calendar_Fragment newInstance(String param1, String param2) {
        Calendar_Fragment fragment = new Calendar_Fragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_, container, false);

        ImageView plusMemo = rootView.findViewById(R.id.plusMemo);
        plusMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //커스텀 다이얼로그 띄우기
                memo_add_dialog memoDlg = new memo_add_dialog(getContext());
                memoDlg.show();
            }
        });

        // DB 생성코드
        SQLiteHelper helper;
        SQLiteDatabase db;
        helper = new SQLiteHelper(getContext(), null, 3);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        Cursor c = db.query("mymemo", null, null, null, null, null, null, null);
        c.moveToFirst();

        ViewGroup imageview = (ViewGroup) inflater.inflate(R.layout.calendar_cell, container, false);
        dot = imageview.findViewById(R.id.dot);
//        if(dot.getDrawable() != null){
//            dot.setImageDrawable(null);
//        }
        dot.setVisibility(View.INVISIBLE);

        for (int i=0; i<c.getCount(); i++)
        {
            // db에 메모 데이터가 있을 때 dot 이미지뷰가 보여야함
            if(c.getCount()>0){
                // (1) 커서가 가리키는 데이터의 날짜를 알아와서
                // (2) 그 날짜가 캘린더 리싸이클러뷰의 어디 위치에 있는지 알아내고
                // (3) 그 위치의 리싸이클러뷰 아이템의 이미지뷰를 보이게 한다.

                // (1)
                String date = c.getString(c.getColumnIndex("date"));


                // (2)
                //LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

               // int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
                // dayOfweek-2를 한 값을 날짜에서 더하면 그 아이템 위치(포지션)임

                // (3)
                //dot.setImageResource(R.drawable.calendar_smalldot);

            } else {}
        }



        Button backButton = (Button) rootView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonthAction(v);
            }
        });

        Button nextButton = (Button) rootView.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonthAction(v);
            }
        });


        // initWidgets()
        // XML에 있는 리사이클러뷰랑 연결
        calenderRecyclerView = rootView.findViewById(R.id.calendarRecyclerView);
        monthText = rootView.findViewById(R.id.month);
        yearText = rootView.findViewById(R.id.year);

        selectedDate = LocalDate.now();
        setMonthView();

        //토스트 때문에 추가함
        mcontext = container.getContext();

//        // 이전, 이후 달력 스와이프
//        calenderRecyclerView.setOnTouchListener(new OnSwipeTouchListener(context) {
//            public void onSwipeTop() {
//            }
//            public void onSwipeRight() {
//                selectedDate = selectedDate.minusMonths(1);
//                setMonthView();
//            }
//            public void onSwipeLeft() {
//                selectedDate = selectedDate.plusMonths(1);
//                setMonthView();
//            }
//            public void onSwipeBottom() {
//            }
//        });



        return rootView;
    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    private void setMonthView() {
        // month, year text에 맞는 데이터로 설정하기
        monthText.setText(monthFromDate(selectedDate));
        yearText.setText(yearFromDate(selectedDate));

        // 그 달의 날짜들을 담고 있는 배열 daysInMonth
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);

        // calenderRecyclerView에 레이아웃 매니저와 어댑터 설정
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calendarAdapter);

    }

    // 그 달의 날짜들을 담고 있는 배열을 만들어주는 함수
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();

        // 현재 날짜의 년도와 달을 yearMonth 변수에 저장
        YearMonth yearMonth = YearMonth.from(date);

        // 현재 달의 길이를 daysInMonth 변수에 저장
        int daysInMonth = yearMonth.lengthOfMonth();

        // withDayofMonth(int n) 그 달의 n번째 날을 가져옴
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

        // getDayofWeek: 요일(문자)을 반환하는 함수(각 요일은 int값이 있음 from 1 (Monday) to 7 (Sunday))
        // dayOfWeek 변수에 그 달의 첫 번째 날의 요일의 int값을 저장
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        // 반복문으로 그 달의 날짜 배열 만들기
        for (int i = 2; i <= 42; i++) {
            // 
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        // 배열 반환
        return daysInMonthArray;
    }

    // 월 형식으로 보여주는 함수
    private String monthFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return date.format(formatter);
    }

    // 년도 형식으로 보여주는 함수
    private String yearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return date.format(formatter);
    }

    // 뒤로 가기 버튼을 눌렀을 때 이전 달력 보여주는 함수
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    // 앞으로 가기 버튼을 눌렀을 때 이전 달력 보여주는 함수
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {

        if (dayText.equals("")) {

        } else {
            String message = "Selected Date" + dayText + " " + monthFromDate(selectedDate);
            Toast.makeText(mcontext.getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext=context;
    }
}