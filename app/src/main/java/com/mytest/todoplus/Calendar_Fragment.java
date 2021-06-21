package com.mytest.todoplus;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;


public class Calendar_Fragment extends Fragment implements CalendarAdapter.OnItemListener{

    private static TextView monthText;
    private static TextView yearText;

    public static RecyclerView calenderRecyclerView;
    public static LocalDate selectedDate;
    private Context context; // fragment에서 토스트 사용하려고 추가함

    public Calendar_Fragment() {
        // Required empty public constructor
    }

    public static Calendar_Fragment newInstance(String param1, String param2) {
        Calendar_Fragment fragment = new Calendar_Fragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_calendar_, container, false);

        ImageView plusMemo=rootView.findViewById(R.id.plusMemo);
        plusMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //커스텀 다이얼로그 띄우기
                memo_add_dialog memoDlg = new memo_add_dialog(getContext());
                memoDlg.show();
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
        context = container.getContext();

        // 이전, 이후 달력 스와이프
        calenderRecyclerView.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
                Log.d("swipe","success swipeRight");
            }
            public void onSwipeRight() {
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
                Log.d("swipe","success swipeRight");
            }
            public void onSwipeLeft() {
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
            }
            public void onSwipeBottom() {
            }

        });

        return rootView;
    }

    public void setMonthView() {
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
    private static ArrayList<String> daysInMonthArray(LocalDate date)
    {
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
        for (int i=2 ; i <=42; i++)
        {
            // 
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }
        // 배열 반환
        return daysInMonthArray;
    }

    // 월 형식으로 보여주는 함수
    private static String monthFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return date.format(formatter);
    }

    // 년도 형식으로 보여주는 함수
    private static String yearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return date.format(formatter);
    }

    // 뒤로 가기 버튼을 눌렀을 때 이전 달력 보여주는 함수
    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    // 앞으로 가기 버튼을 눌렀을 때 이전 달력 보여주는 함수
    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    //이건 무슨 함수야? 클릭하면 뭐 나와?
    public void onItemClick(int position, String dayText) {

        if(dayText.equals(""))
        {
            String message = "Selected Date" +dayText + " " + monthFromDate(selectedDate);
            Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}