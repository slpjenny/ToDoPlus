package com.mytest.todoplus;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
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

import java.util.Calendar;

public class routine_edit_dialog extends DialogFragment {

    //new
    public static todoAdapter adapter = new todoAdapter();

    private EditText rtn_title_edit;
//    private EditText rtn_day_edit;
    public static TextView rtn_time_edit;
    private EditText rtn_place_edit;
    private Boolean isChecked;

    private ImageView rtnEdit_cancel;
    private Button rtnEdit_ok;
    private Button rtnEdit_remove;

    //각각 editText 부분에 변경되어서 적힐 내용
    String rtn_Ename_str;
    String rtn_Eday_Str;
    String rtn_Etime_str;
    String rtn_Eplace_str;

    int position;

    CheckBox checkM;
    CheckBox checkTu;
    CheckBox checkW;
    CheckBox checkTh;
    CheckBox checkF;
    CheckBox checkSa;
    CheckBox checkSu;

    boolean[] checkList;
    String resultDay = ""; //최종 변경된 요일 변수

    public routine_edit_dialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_routine_edit_dialog, container, false);

        rtn_title_edit = v.findViewById(R.id.rtn_title_edit);
//        rtn_day_edit = v.findViewById(R.id.rtn_day_edit);
        rtn_time_edit = v.findViewById(R.id.rtn_time_edit);
        rtn_place_edit = v.findViewById(R.id.rtn_place_edit);

        rtnEdit_cancel = v.findViewById(R.id.rtnEdit_cancel);
        rtnEdit_ok = v.findViewById(R.id.rtnEdit_ok);
        rtnEdit_remove = v.findViewById(R.id.rtnEdit_remove);

        //요일 체크박스
        checkM = v.findViewById(R.id.checkM);
        checkTu = v.findViewById(R.id.checkTu);
        checkW = v.findViewById(R.id.checkW);
        checkTh = v.findViewById(R.id.checkTh);
        checkF = v.findViewById(R.id.checkF);
        checkSa = v.findViewById(R.id.checkSa);
        checkSu = v.findViewById(R.id.checkSu);

        //MainFragment 에서 받은 data bundle
        if (getArguments() != null) {
            rtn_Ename_str = getArguments().getString("itemTitle");
//            rtn_Eday_Str = getArguments().getString("itemDay");
            rtn_Etime_str = getArguments().getString("itemTime");
            rtn_Eplace_str = getArguments().getString("itemPlace");
            position = getArguments().getInt("itemPosition");
            isChecked = getArguments().getBoolean("itemChecked");
        }

        //원래 써있는 아이템 정보 editText창에 불러오기
        rtn_title_edit.setText(rtn_Ename_str);
        rtn_time_edit.setText(rtn_Etime_str);
        rtn_place_edit.setText(rtn_Eplace_str);

        rtnEdit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //'x'버튼 누르면 그냥 다이얼로그 사라지기
                dismiss();
            }
        });

        rtnEdit_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리싸이클러뷰에서 해당 아이템 삭제시키기 기능
                adapter.removeItem(position);
                //다이얼로그 사라짐.
                dismiss();
            }
        });

        rtnEdit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkList = new boolean[]{checkM.isChecked(), checkTu.isChecked(), checkW.isChecked(), checkTh.isChecked(), checkF.isChecked(), checkSa.isChecked(), checkSu.isChecked()};

                //원래 써있는 정보 수정해서 아이템 내용 바꾸기
                String rt_name_e = rtn_title_edit.getText().toString();
//                String rt_day_e = rtn_day_edit.getText().toString();
                String rt_time_e = rtn_time_edit.getText().toString();
                String rt_place_e = rtn_place_edit.getText().toString();

                if (!checkM.isChecked() && !checkTu.isChecked() && !checkW.isChecked() && !checkTh.isChecked() && !checkF.isChecked() && !checkSa.isChecked() && !checkSu.isChecked()) {
                    Toast.makeText(getContext(), "요일을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                } else if (rtn_title_edit.getText().toString().isEmpty()) {  //왜 이건 적용 안되지?
                    Toast.makeText(getContext(), "제목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
                    //체크된 요일 출력
                    checkDay();

                    //원래 써있는 정보 수정해서 아이템 내용 바꾸기
                todo_object rt_o = new todo_object(rt_name_e, rt_time_e, rt_place_e, R.drawable.yellow_vertical_line, "Routine", resultDay);
                adapter.editItem(position, rt_o, isChecked);

                Toast.makeText(getContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();

                dismiss();

            }
        });

        rtn_time_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment2 tpk2=new TimePickerFragment2();
                tpk2.show(getFragmentManager(), "tpk2");

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        //DialogFragment 의 넓이와 높이를 사용자 지정으로 바꾼다.
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        super.onResume();
    }

    //시간 설정
    public static class TimePickerFragment2 extends DialogFragment
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
            rtn_time_edit.setText(hourOfDay + ":" + minute);
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