package com.mytest.todoplus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

// 현재 날짜 받아오기
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFragment extends Fragment {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static todoAdapter adapter=new todoAdapter();


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 날짜,시간 가져오는 함수
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView item 예시
//        adapter.addItem(new todo_object("지영이랑 안드로이드","10:22","zoom", R.drawable.green_vertical_line,"Todo"));
//        adapter.addItem(new todo_object("이거는 루틴예시","03:19","595",R.drawable.yellow_vertical_line,"Routine"));

        //이거 없으면 리싸이클러 뷰 안나타남
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        // 오늘 날짜 표시
        TextView textView=rootView.findViewById(R.id.showDate);
        textView.setText(getTime());

        //루틴 추가 버튼 누르면 해당 다이얼로그 팝업
        Button routine_add_btn=rootView.findViewById(R.id.routine_add_btn);
        routine_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routine_add_dialog rtn_dlg = new routine_add_dialog(getContext());
                rtn_dlg.show();
            }
        });

        //할일 추가 버튼 누르면 해당 다이얼로그 팝업
        Button todo_add_btn=rootView.findViewById(R.id.todo_add_btn);
        todo_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo_add_dialog td_dlg = new todo_add_dialog(getContext());
                td_dlg.show();
            }
        });

        adapter.setOnItemClickListener(new OnToDoItemClickListener() {
            @Override
            public void onItemClick(todoAdapter.ViewHolder holder, View view, int position) {
                todo_object itemInfo=adapter.getItem(position);

                //아이템 종류에 따라서 다른 '수정 다이얼로그' 띄우기
                if(itemInfo.itemType=="ToDo") {

                    //다이얼로그에 item 정보 넘겨서, 이게 특정 item인 것을 알리기
                    todo_edit_dialog td_edit_dlg = new todo_edit_dialog(getContext());
                    String e_TodoTitle=itemInfo.itemTitle;
                    String e_TodoTime=itemInfo.itemTime;
                    String e_TodoPlace=itemInfo.itemPlace;

//                    todo_edit_dlg2 two=new todo_edit_dlg2();
//                    two.show(getFragmentManager().beginTransaction());


//                    Intent editTodoIntent= new Intent(getActivity(),todo_edit_dialog.class);
//                    editTodoIntent.putExtra("dataInfo_title",e_TodoTitle);
//                    editTodoIntent.putExtra("dataInfo_time",e_TodoTime);
//                    editTodoIntent.putExtra("dataInfo_place",e_TodoPlace);

                    td_edit_dlg.show();

                }else if(itemInfo.itemType=="Routine"){
                    routine_edit_dialog rtn_edit_dlg = new routine_edit_dialog(getContext());
                    rtn_edit_dlg.show();
                }
            }
        });

        return rootView;
    }

}