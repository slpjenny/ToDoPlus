package com.mytest.todoplus;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 현재 날짜 받아오기

public class MainFragment extends Fragment {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static todoAdapter adapter=new todoAdapter();
    private ItemTouchHelper mItemTouchHelper;

    private List<todo_object> todo_objects;

    //db 선언
    public static SQLiteHelper helper;
    public static SQLiteDatabase db;

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

//        //db선언
//        helper = new SQLiteHelper(getContext(), null,1);
//        db = helper.getWritableDatabase();
//        helper.onCreate(db);

        //데이터 조회 함수 호출
//        helper.exequte_Query();

        RecyclerView recyclerView=rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //이거 없으면 리싸이클러 뷰 안나타남
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


        mItemTouchHelper=new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        // 오늘 날짜 표시
        TextView textView=rootView.findViewById(R.id.showDate);
        textView.setText(getTime());

        //루틴 추가 버튼 누르면 해당 다이얼로그 팝업
        Button routine_add_btn=rootView.findViewById(R.id.routine_add_btn);
        routine_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routine_add_dialog rtn_dlg = new routine_add_dialog();
                rtn_dlg.show(getActivity().getFragmentManager(),"show");
//                rtn_dlg.show(getFragmentManager(),"show");
            }
        });

        //할일 추가 버튼 누르면 해당 다이얼로그 팝업
        Button todo_add_btn=rootView.findViewById(R.id.todo_add_btn);
        todo_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todo_add_dialog todo_add_dlg = new todo_add_dialog();
                todo_add_dlg.show(getActivity().getFragmentManager(), "show");
//                todo_add_dlg.show(getFragmentManager(),"show");

            }
        });

        adapter.setOnItemClickListener(new OnToDoItemClickListener() {
            @Override
            public void onItemClick(todoAdapter.ViewHolder holder, View view, int position) {
                todo_object itemInfo=adapter.getItem(position);

                //아이템 종류에 따라서 다른 '수정 다이얼로그' 띄우기
                if(itemInfo.itemType=="ToDo") {

                    //다이얼로그에 item 정보 넘겨서, 이게 특정 item인 것을 알리기
                    todo_edit_dialog todo_edit_dialog = new todo_edit_dialog();

                    Bundle bundle=new Bundle();
                    bundle.putString("itemTitle",itemInfo.itemTitle);
                    bundle.putString("itemPlace",itemInfo.itemPlace);
                    bundle.putString("itemTime",itemInfo.itemTime);
                    bundle.putInt("itemPosition",position);
                    todo_edit_dialog.setArguments(bundle);

                    todo_edit_dialog.show(getActivity().getFragmentManager(), "show");
//                    todo_edit_dialog.show(getFragmentManager(),"show");

                }else if (itemInfo.itemType=="Routine"){
                    routine_edit_dialog routine_edit_dialog2 = new routine_edit_dialog();

                    Bundle bundle=new Bundle();
                    bundle.putString("itemTitle",itemInfo.itemTitle);
//                    bundle.putString("itemDay",itemInfo.itemDay);
                    bundle.putString("itemPlace",itemInfo.itemPlace);
                    bundle.putString("itemTime",itemInfo.itemTime);
                    bundle.putInt("itemPosition",position);
                    routine_edit_dialog2.setArguments(bundle);

                    routine_edit_dialog2.show(getActivity().getFragmentManager(), "show");
//                    routine_edit_dialog2.show(getFragmentManager(),"show");
                }
            }
        });
        return rootView;
    }

    static public void refresh(){
        adapter.notifyDataSetChanged();
    }

}