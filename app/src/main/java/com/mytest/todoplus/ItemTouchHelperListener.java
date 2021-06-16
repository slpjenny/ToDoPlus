package com.mytest.todoplus;

public interface ItemTouchHelperListener {
    boolean onItemMove(int from_position,int to_position);  //아이템 리스트 위치 수정
    void onItemSwipe(int position);  //아이템 swipe 시 사용
}
