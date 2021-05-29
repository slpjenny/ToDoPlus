package com.mytest.todoplus;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.util.ArrayList;



public class Calendar_Fragment extends Fragment implements CalendarAdapter.OnItemListener{

    private TextView monthText;
    private TextView yearText;

    private RecyclerView calenderRecyclerView;
    private LocalDate selectedDate;
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


        return rootView;
    }

    private void setMonthView() {
        monthText.setText(monthFromDate(selectedDate));
        yearText.setText(yearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i=1 ; i <=42; i++)
        {
            if(i <= dayOfWeek || i>daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i-dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    // 월 형식으로 보여주는 함수
    private String monthFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return date.format(formatter);
    }

    // 년도 형식으로 보여주는 함수
    private String yearFromDate(LocalDate date)
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
    public void onItemClick(int position, String dayText) {

        if(dayText.equals(""))
        {
            String message = "Selected Date" +dayText + " " + monthFromDate(selectedDate);
            Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}