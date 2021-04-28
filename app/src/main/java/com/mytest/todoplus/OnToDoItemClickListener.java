package com.mytest.todoplus;

import android.view.View;

public interface OnToDoItemClickListener {
    public void onItemClick(todoAdapter.ViewHolder holder,View view,int position);
}
