package com.mytest.todoplus;

import android.app.Dialog;
import android.app.TimePickerDialog;
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
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class routine_add_dialog extends DialogFragment {

    //new
    public static todoAdapter adapter=new todoAdapter();

    private EditText rtn_title;
    private static TextView rtn_time;
    private EditText rtn_place;

    private ImageView rtn_cancel;
    private Button rtn_add_ok;

    static String rtn_title_str;
    static String rtn_day_str;
    static String rtn_time_str;
    static String rtn_place_str;

    public routine_add_dialog(){}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.routine_add_dialog, container, false);

        rtn_title = v.findViewById(R.id.rtn_title);
        rtn_time = v.findViewById(R.id.rtn_time);
        rtn_place = v.findViewById(R.id.rtn_place);

        rtn_cancel = v.findViewById(R.id.rtn_cancel);
        rtn_add_ok = v.findViewById(R.id.rtn_add_ok);

        rtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        rtn_add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rtn_title_str = rtn_title.getText().toString();
                //체크된 요일 출력
//                rtn_day_str = rtn_day.getText().toString();
                rtn_time_str = rtn_time.getText().toString();
                rtn_place_str = rtn_place.getText().toString();

                todo_object todo_item = new todo_object(rtn_title_str, rtn_time_str, rtn_place_str, R.drawable.yellow_vertical_line, "Routine", "rtnDay");
                adapter.addItem(todo_item);

                Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

        rtn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment tpk=new TimePickerFragment();
                tpk.show(getFragmentManager(),"tpk");
            }
        });

        return v;
    }

    public void onResume() {
        //DialogFragment 의 넓이와 높이를 사용자 지정으로 바꾼다.
        int width=getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height=getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        super.onResume();
    }

    //시간 설정
    public static class TimePickerFragment extends DialogFragment
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
            rtn_time.setText(hourOfDay+":"+minute);
        }
    }
}
