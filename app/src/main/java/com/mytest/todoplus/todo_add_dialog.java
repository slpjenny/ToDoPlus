package com.mytest.todoplus;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;

public class todo_add_dialog extends DialogFragment {

    //new
    public static todoAdapter adapter=new todoAdapter();

    private ImageView todoAdd_cancel;
    private EditText todo_title;
    public static TextView todo_time;
    private EditText todo_place;
    private Button todoAdd_ok;

    static String todo_title_str;
    static String todo_time_str;
    static String todo_place_str;

    public todo_add_dialog(){}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.todo_add_dialog, container, false);

        todo_title=v.findViewById(R.id.todo_title);
        todo_time=v.findViewById(R.id.todo_time);
        todo_place=v.findViewById(R.id.todo_place);

        todoAdd_cancel=v.findViewById(R.id.todoAdd_cancel);
        todoAdd_ok=v.findViewById(R.id.todoAdd_ok);

        SQLiteHelper helper;
        SQLiteDatabase db;
        helper = new SQLiteHelper(getContext(), null,3);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        todoAdd_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        todoAdd_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo_title_str=todo_title.getText().toString();
                todo_time_str=todo_time.getText().toString();
                todo_place_str=todo_place.getText().toString();

                todo_object todo_item = new todo_object(todo_title_str,todo_time_str,todo_place_str, R.drawable.green_vertical_line,"ToDo","");
                adapter.addItem(todo_item);

                //db에 저장
                helper.insert_Toroutine("ToDo",todo_title_str,todo_time_str,todo_place_str,"",0);
                Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

        todo_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment3 tpk3=new TimePickerFragment3();
                tpk3.show(getFragmentManager(),"tpk3");
            }
        });
        return v;
    }

    public void onResume() {
        //DialogFragment 의 넓이와 높이를 사용자 지정으로 바꾼다.
        int width=getResources().getDimensionPixelSize(R.dimen.todo_dialog_width);
        int height=getResources().getDimensionPixelSize(R.dimen.todo_dialog_height);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        super.onResume();
    }

    public static class TimePickerFragment3 extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            todo_time.setText(hourOfDay+":"+minute);
        }
    }
}
