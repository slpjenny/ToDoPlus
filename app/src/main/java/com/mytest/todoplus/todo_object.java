package com.mytest.todoplus;

public class todo_object {
    String todoTitle;
    String todoTime;
    String todoPlace;

    public todo_object(String todoTitle,String todoTime,String todoPlace){
        this.todoTitle=todoTitle;
        this.todoTime=todoTime;
        this.todoPlace=todoPlace;
    }

    public String getTodoTitle() {return todoTitle;}
    public void setTodoTitle(String todoTitle) {this.todoTitle = todoTitle;}

    public String getTodoTime() {return todoTime;}
    public void setTodoTime(String todoTime) {this.todoTime = todoTime;}

    public String getTodoPlace() {return todoPlace;}
    public void setTodoPlace(String todoPlace) {this.todoPlace = todoPlace;}
}
