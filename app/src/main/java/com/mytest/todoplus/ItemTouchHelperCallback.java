package com.mytest.todoplus;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperListener listener;
    //listener 받는 callback 생성자
    public ItemTouchHelperCallback(ItemTouchHelperListener listener){this.listener=listener;}

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag_flags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipe_flags=ItemTouchHelper.START|ItemTouchHelper.END;

        //결국 현재 위치값은 int로 반환
        return makeMovementFlags(drag_flags,swipe_flags);
    }

    @Override
    //target-> viewHolder 중 선택된 아이템
    //아이템이 움직이고 있는가? boolean 값을 반환
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return listener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
    }

    @Override
    //뷰홀더, 움직힐 방향 입력받는다.
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //움직일 방향 입력해서 swipe
        listener.onItemSwipe(viewHolder.getAdapterPosition());
    }

    //길게 누르기 감지
    public boolean isLongPressDragEnabled(){
        return true;
    }
}
