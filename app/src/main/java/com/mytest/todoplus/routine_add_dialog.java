package com.mytest.todoplus;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;

public class routine_add_dialog extends DialogFragment {
    //메인페이지 리사이클러뷰 어댑터
    public static todoAdapter adapter = new todoAdapter();
    //3번째 페이지 리사이클러뷰 어댑터
    public static routineAdapter adapter2 = new routineAdapter();

    private EditText rtn_title;
    private static TextView rtn_time;
    private EditText rtn_place;

    private ImageView rtn_cancel;
    private Button rtn_add_ok;

    static String rtn_title_str;
    static String rtn_day_str;
    static String rtn_time_str;
    static String rtn_place_str;

    CheckBox checkM;
    CheckBox checkTu;
    CheckBox checkW;
    CheckBox checkTh;
    CheckBox checkF;
    CheckBox checkSa;
    CheckBox checkSu;

    boolean[] checkList;
    String resultDay = ""; //최종 요일 변수

    public routine_add_dialog() {
    }

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

        //요일 체크박스
        checkM = v.findViewById(R.id.checkM);
        checkTu = v.findViewById(R.id.checkTu);
        checkW = v.findViewById(R.id.checkW);
        checkTh = v.findViewById(R.id.checkTh);
        checkF = v.findViewById(R.id.checkF);
        checkSa = v.findViewById(R.id.checkSa);
        checkSu = v.findViewById(R.id.checkSu);

        SQLiteHelper helper;
        SQLiteDatabase db;
        helper = new SQLiteHelper(getContext(), null,3);
        db = helper.getWritableDatabase();
        helper.onCreate(db);


        rtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        rtn_add_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkList = new boolean[]{checkM.isChecked(), checkTu.isChecked(), checkW.isChecked(), checkTh.isChecked(), checkF.isChecked(), checkSa.isChecked(), checkSu.isChecked()};

                if (!checkM.isChecked() && !checkTu.isChecked() && !checkW.isChecked() && !checkTh.isChecked() && !checkF.isChecked() && !checkSa.isChecked() && !checkSu.isChecked()) {
                    Toast.makeText(getContext(), "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                } else if (rtn_title.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    rtn_title_str = rtn_title.getText().toString();

                    //체크된 요일 출력
                    checkDay();

                    rtn_time_str = rtn_time.getText().toString();
                    rtn_place_str = rtn_place.getText().toString();

                    //리싸이클러뷰 아이템으로 추가
                    todo_object todo_item = new todo_object(rtn_title_str, rtn_time_str, rtn_place_str, R.drawable.yellow_vertical_line, "Routine", resultDay);
                    adapter.addItem(todo_item);


                    //db에 저장
                    helper.insert_Toroutine("Routine",rtn_title_str,rtn_time_str,rtn_place_str,resultDay,0);
//                    helper.insert_Toroutine(rtn_title_str,rtn_time_str,rtn_place_str,"Routine",resultDay);
                    Toast.makeText(getContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();


                    //세번째 페이지 리싸이클러뷰 아이템으로 추가
                    routine_object routine_item = new routine_object(rtn_title_str);
                    adapter2.addItem(routine_item);

                    int count=adapter2.getItemCount();
                    Log.d("count", String.valueOf(count));

                    //다이얼로그 끝내기
                    dismiss();
                }
            }
        });

        rtn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment tpk = new TimePickerFragment();
                tpk.show(getFragmentManager(), "tpk");
            }
        });
        return v;
    }

    public void onResume() {
        //DialogFragment 의 넓이와 높이를 사용자 지정으로 바꾼다.
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
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
            rtn_time.setText(hourOfDay + ":" + minute);
        }
    }

    public void checkDay() {
        for (int i = 0; i < 7; i++) {
            if (checkList[i] == true) {
                resultDay = resultDay + printDay(i);
            }
        }
    }

    //인덱스에 따라 최종요일 변수에 넣을 요일 반환
    public char printDay(int index) {
        if (index == 0) return '월';
        else if (index == 1) return '화';
        else if (index == 2) return '수';
        else if (index == 3) return '목';
        else if (index == 4) return '금';
        else if (index == 5) return '토';
        else if (index == 6) return '일';

        else return '0';
    }
}
