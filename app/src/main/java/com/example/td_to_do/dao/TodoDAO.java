package com.example.td_to_do.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.td_to_do.dao.DAO;
import com.example.td_to_do.dao.TodoDBHelper;
import com.example.td_to_do.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoDAO extends DAO {
    public TodoDAO(Context context) { super(new TodoDBHelper(context)); }

    public Todo find(Long id){
        Todo todo = null;

        Cursor cursor = db.rawQuery(
                "select * from "
                        + TodoDBHelper.TODO_TABLE_NAME + " where "
                        + TodoDBHelper.TODO_KEY_COLUMN_INDEX + " = ?"
                , new String[] {String.valueOf(id) }) ;
        if(cursor != null && cursor.moveToFirst() ) {
            todo = new Todo();
            todo.setId(Integer.parseInt(cursor.getString(0)));
            todo.setName(cursor.getString(1));
            todo.setUrgency(cursor.getString(2));

            cursor.close();
        }
        return todo;
    }

    public List<Todo> list() {
        open();

        List<Todo> todos = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "select * from " + TodoDBHelper.TODO_TABLE_NAME,null
        );

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Todo todo = new Todo();
                todo.setId(cursor.getLong(TodoDBHelper.TODO_KEY_COLUMN_INDEX));
                todo.setName(cursor.getString(TodoDBHelper.TODO_NAME_COLUMN_INDEX));
                todo.setUrgency(cursor.getString(TodoDBHelper.TODO_URGENCY_COLUMN_INDEX));

                todos.add(todo);

                cursor.moveToNext();
            }
        }

        cursor.close();

        return todos;
    }

    public void add(Todo todo){
        open();

        ContentValues value = new ContentValues();

        value.put(TodoDBHelper.TODO_NAME, todo.getName());
        value.put(TodoDBHelper.TODO_URGENCY, todo.getUrgency());

        db.insert(TodoDBHelper.TODO_TABLE_NAME, null, value);
    }


}
