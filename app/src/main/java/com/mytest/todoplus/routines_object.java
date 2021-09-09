package com.mytest.todoplus;

public class routines_object {
    String routine_item_title;

    boolean isSelected;

    public routines_object(String routine_item_title){
        this.routine_item_title=routine_item_title;
    }

    public String getRoutine_item_title(){return routine_item_title;}
    public void setRoutine_item_title(String routine_item_title){this.routine_item_title=routine_item_title;}
}
