package com.mytest.todoplus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

//------------


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    public static LocalDate selectedDate;
//    public static RecyclerView calenderRecyclerView;

    public static SQLiteHelper helper;
    SQLiteDatabase db;
    ImageView dot;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);

        // DB 생성코드
        helper = new SQLiteHelper(view.getContext(), null, 3);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        //
        ViewGroup calender_view = (ViewGroup) inflater.inflate(R.layout.fragment_calendar_, parent, false);
        Cursor c = db.query("mymemo", null, null, null, null, null, null, null);
        c.moveToFirst();

//        dot = calender_view.findViewById(R.id.dot);
//        if(dot != null){
//            dot.setImageBitmap(null);
//        }
//
//        for (int i=0; i<c.getCount(); i++)
//        {
//            // db에 메모 데이터가 있을 때 dot 이미지뷰가 보여야함
//            if(c.getCount()>0){
//                // (1) 커서가 가리키는 데이터의 날짜를 알아와서
//                // (2) 그 날짜가 캘린더 리싸이클러뷰의 어디 위치에 있는지 알아내고
//                // (3) 그 위치의 리싸이클러뷰 아이템의 이미지뷰를 보이게 한다.
//
//                // (1)
//                String date = c.getString(c.getColumnIndex("date"));
//
//
//                // (2)
//                LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
//
//                int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
//                // dayOfweek-2를 한 값을 날짜에서 더하면 그 아이템 위치(포지션)임
//
//                // (3)
//                dot.setImageResource(R.drawable.calendar_smalldot);
//
//            }
//        }

        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        holder.dayOfMonth.setText(daysOfMonth.get(position));


    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface OnItemListener
    {
        void onItemClick(int position, String dayText);
    }


    public void onSwipeRight(){
        selectedDate=selectedDate.minusMonths(1);

    }

    public void setMonthView(){

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        static ImageView dot;

        public ViewHolder(View itemView, OnToDoItemClickListener listener) {
            super(itemView);


        }
    }

}

