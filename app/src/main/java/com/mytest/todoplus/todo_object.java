package com.mytest.todoplus;

public class todo_object {
    String itemTitle;
    String itemTime;
    String itemPlace;
    int itemLine;
    String itemType;
    int number;

    //체크박스 체크 상태
    //boolean 의 초기화 하기 전 값-> false
    boolean isSelected;

    //루틴에만 들어가는 요소
    String itemDay;

    public todo_object(String itemTitle,String itemTime,String itemPlace,int itemLine,String itemType,String itemDay){
        this.itemTitle=itemTitle;
        this.itemTime=itemTime;
        this.itemPlace=itemPlace;
        this.itemLine=itemLine;
        this.itemType=itemType;
        this.itemDay=itemDay;
        this.number=number;
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

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSelected() {return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}
