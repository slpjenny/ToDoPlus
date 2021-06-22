package com.mytest.todoplus;

import android.content.Context;
import android.content.Intent;

public class BroadcastReceiver extends android.content.BroadcastReceiver {
    public static todoAdapter adapter = new todoAdapter();

    @Override
    public void onReceive(Context context, Intent intent) {
        //다음날(자정) 시간 변경 감지
        if(Intent.ACTION_DATE_CHANGED==intent.getAction()){
            //do sth
            //할일 아이템 전체 삭제
            //데이터베이스에서 todo로 저장된 아이템들만 불러오게?
            //recylerview 전체 아이템 훑어서 todo만 골라내기 되나


        }
    }
}
