package com.mytest.todoplus;

import android.widget.ImageView;

public class todo_object {
    String itemTitle;
    String itemTime;
    String itemPlace;
    int itemLine;
    String itemType;

    //루틴에만 들어가는 요소
    String itemDay;

    public todo_object(String itemTitle,String itemTime,String itemPlace,int itemLine,String itemType,String itemDay){
        this.itemTitle=itemTitle;
        this.itemTime=itemTime;
        this.itemPlace=itemPlace;
        this.itemLine=itemLine;
        this.itemType=itemType;
        this.itemDay=itemDay;
    }

    public String getItemTitle() {return itemTitle;}
    public void setItemTitle(String itemTitle) {this.itemTitle = itemTitle;}

    public String getItemTime() {return itemTime;}
    public void setItemTime(String itemTime) {this.itemTime = itemTime;}

    public String getItemPlace() {return itemPlace;}
    public void setItemPlace(String itemPlace) {this.itemPlace = itemPlace;}

    public int getItemLine() {return itemLine;}
    public void setItemLine(int itemLine) {this.itemLine = itemLine;}

    public String getItemType() {return itemType;}
    public void setItemType(String itemType) {this.itemType = itemType;}

    public String getItemDay() {return itemDay;}
    public void setItemDay(String itemDay) {this.itemDay = itemDay;}
}
