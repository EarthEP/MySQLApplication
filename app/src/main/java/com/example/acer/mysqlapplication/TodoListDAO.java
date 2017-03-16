package com.example.acer.mysqlapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Acer on 10/2/2560.
 */

public class TodoListDAO {

    public SQLiteDatabase database;
    public DbHelper dbHelper;

    public TodoListDAO (Context context){
        dbHelper = new DbHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }
    public ArrayList<TodoList> getAlltodoList(){
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM tbtodo_list;",null);
        cursor.moveToFirst();
        TodoList todoList1;
        //todoList1 = new TodoList();
        while(!cursor.isAfterLast()){
            todoList1 = new TodoList();
            todoList1.setTaskid(cursor.getInt(0));
            todoList1.setTaskname(cursor.getString(1));
            todoList.add(todoList1);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }

    public  void add(TodoList todoList) {

        ContentValues values = new ContentValues();
        values.put("taskname", todoList.getTaskname());
        this.database.insert("tbtodo_list",null,values);
        Log.d("Todo List :::","add ok");
    }

    public void update(TodoList todoList){
        TodoList updateTodoList = todoList;
        ContentValues values = new ContentValues();
        values.put("taskname", updateTodoList.getTaskname());
        values.put("taskid", updateTodoList.getTaskid());
        String where = "taskid=" + updateTodoList.getTaskid();

        this.database.update("tbtodo_list", values, where, null);
        Log.d("Todo List :::","Update OK !!!");
    }

    public void  delete(TodoList todoList){
        TodoList delTodoList = todoList;
        String sqlText = "DELETE FROM tbtodo_list WHERE taskid=" + delTodoList.getTaskid();
        this.database.execSQL(sqlText);
        Log.d("Todo List :::","DELETE OK !!!");
    }
}
