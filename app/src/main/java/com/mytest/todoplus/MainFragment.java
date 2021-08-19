package com.mytest.todoplus;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

    public static int isTwice;

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


        Log.d("호출","onCreateView_fragment");

        Log.d("호출", String.valueOf(isTwice) +"__fragmnet-onCreateView");


        if(isTwice == 0) {
            //db선언
            helper = new SQLiteHelper(getActivity(), null, 2);
            db = helper.getWritableDatabase();
            helper.onCreate(db);

            //db에서 데이터 가져와서 리싸이클러뷰 addItem -> 저장 내용 뿌려주기
            helper.exequte_Query();

        }

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

        //각각 아이템 클릭시 해당하는 '수정 다이얼로그' 팝업
        adapter.setOnItemClickListener(new OnToDoItemClickListener() {
            @Override
            public void onItemClick(todoAdapter.ViewHolder holder, View view, int position) {
                todo_object itemInfo=adapter.getItem(position);

                //아이템 종류에 따라서 다른 '수정 다이얼로그' 띄우기
                if(itemInfo.getItemType().equals("ToDo")) {

                    //다이얼로그에 item 정보 넘겨서, 이게 특정 item인 것을 알리기
                    todo_edit_dialog todo_edit_dialog = new todo_edit_dialog();

                    Bundle bundle=new Bundle();
                    bundle.putString("itemTitle",itemInfo.itemTitle);
                    bundle.putString("itemPlace",itemInfo.itemPlace);
                    bundle.putString("itemTime",itemInfo.itemTime);
                    bundle.putInt("itemPosition",position);
                    //checkbox state data 전송
                    bundle.putBoolean("itemChecked",itemInfo.isSelected());

                    todo_edit_dialog.setArguments(bundle);

                    todo_edit_dialog.show(getActivity().getFragmentManager(), "show");

                }else if (itemInfo.getItemType().equals("Routine")){
                    routine_edit_dialog routine_edit_dialog2 = new routine_edit_dialog();

                    Bundle bundle=new Bundle();
                    bundle.putString("itemTitle",itemInfo.itemTitle);
//                    bundle.putString("itemDay",itemInfo.itemDay);
                    bundle.putString("itemPlace",itemInfo.itemPlace);
                    bundle.putString("itemTime",itemInfo.itemTime);
                    bundle.putInt("itemPosition",position);
                    //checkbox state data 전송
                    bundle.putBoolean("itemChecked",itemInfo.isSelected());
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

    @Override
    public void onPause() {
        super.onPause();

        Log.d("호출","onPause_fragment");

        isTwice=1;

        SharedPreferences pref = getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("isTwice",isTwice);

        Log.d("호출", String.valueOf(isTwice)+"__fragment_onPause");

        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("호출","onResume_fragment");

        SharedPreferences pref= getActivity().getSharedPreferences("pref",Activity.MODE_PRIVATE);
        if((pref.contains("isTwice"))){
            int isTwice2 = pref.getInt("isTwice",isTwice);
            isTwice=isTwice2;
        }else if((pref != null)){
            isTwice=0;
        }

        Log.d("호출", String.valueOf(isTwice));
    }
}

