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

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class todo_edit_dialog extends DialogFragment {

    //new
    public static todoAdapter adapter=new todoAdapter();

    private EditText todo_name_edit;
    public static TextView todo_time_edit;
    private EditText todo_place_edit;

    private ImageView todoEdit_cancel;
    private Button todoEdit_ok;
    private Button todoEdit_remove;

    //각각 editText 부분에 변경되어서 적힐 내용
    String todo_Ename_str;
    String todo_Etime_str;
    String todo_Eplace_str;

    int position;

    public todo_edit_dialog() {}

    public static todo_edit_dialog getInstance() {
        todo_edit_dialog dlg2 = new todo_edit_dialog();
        return dlg2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo_edit_dialog, container, false);

        todo_name_edit=v.findViewById(R.id.todo_name_edit);
        todo_time_edit=v.findViewById(R.id.todo_time_edit);
        todo_place_edit=v.findViewById(R.id.todo_place_edit);

        todoEdit_cancel=v.findViewById(R.id.todoEdit_cancel);
        todoEdit_ok=v.findViewById(R.id.todoEdit_ok);
        todoEdit_remove=v.findViewById(R.id.todoEdit_remove);


        if(getArguments() != null) {
            todo_Ename_str = getArguments().getString("itemTitle");
            todo_Etime_str = getArguments().getString("itemTime");
            todo_Eplace_str = getArguments().getString("itemPlace");
            position=getArguments().getInt("itemPosition");
        }

        //원래 써있는 아이템 정보 editText창에 불러오기
        todo_name_edit.setText(todo_Ename_str);
        todo_time_edit.setText(todo_Etime_str);
        todo_place_edit.setText(todo_Eplace_str);

        todoEdit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //'x'버튼 누르면 그냥 다이얼로그 사라지기
                dismiss();
            }
        });

        todoEdit_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //리싸이클러뷰에서 해당 아이템 삭제시키기 기능
                adapter.removeItem(position);
                //다이얼로그 사라짐.
                dismiss();
            }
        });

        todoEdit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText 창에 수정하여 작성한 것 문자열로 변환해놓기
                String td_name_e=todo_name_edit.getText().toString();
                String td_time_e=todo_time_edit.getText().toString();
                String td_place_e=todo_place_edit.getText().toString();
                //원래 써있는 정보 수정해서 아이템 내용 바꾸기
                todo_object td_o = new todo_object(td_name_e,td_time_e,td_place_e, R.drawable.green_vertical_line,"ToDo","");
                adapter.editItem(position,td_o);

                dismiss();
            }
        });

        todo_time_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment4 tpk4=new TimePickerFragment4();
                tpk4.show(getFragmentManager(),"tpk4");
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

    //시간 설정
    public static class TimePickerFragment4 extends DialogFragment
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
            todo_time_edit.setText(hourOfDay + ":" + minute);
        }

    }

}