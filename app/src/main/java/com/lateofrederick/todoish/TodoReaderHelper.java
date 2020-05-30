package com.lateofrederick.todoish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lateofrederick.todoish.TodoContract;
import com.lateofrederick.todoish.Todos;

import java.util.ArrayList;
import java.util.List;

public class TodoReaderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Todo.db";

    private SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_DELETE_TODOS);
        sqLiteDatabase.execSQL(SQL_CREATE_TODOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TODOS);
        onCreate(sqLiteDatabase);
    }

    public TodoReaderHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TODOS =
            "CREATE TABLE " + TodoContract.TodoEntry.TABLE_NAME + " (" +
                    TodoContract.TodoEntry._ID + " INTEGER PRIMARY KEY," +
                    TodoContract.TodoEntry.COLUMN_NAME_FNAME + " TEXT," +
                    TodoContract.TodoEntry.COLUMN_NAME_LNAME + " TEXT," +
                    TodoContract.TodoEntry.COLUMN_NAME_TELE + " TEXT)";

    private static final String SQL_DELETE_TODOS =
            "DROP TABLE IF EXISTS " + TodoContract.TodoEntry.TABLE_NAME;

    //    Function to insert a new to do into database
    public void save(Todos todos) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoContract.TodoEntry.COLUMN_NAME_FNAME, todos.getFirstname());
        contentValues.put(TodoContract.TodoEntry.COLUMN_NAME_LNAME, todos.getLastname());
        contentValues.put(TodoContract.TodoEntry.COLUMN_NAME_TELE, todos.getTelephone());


        sqLiteDatabase.insert(TodoContract.TodoEntry.TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();
    }

    //    Function to update a to do
    public int update( Todos todos) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoContract.TodoEntry.COLUMN_NAME_FNAME, todos.getFirstname());
        contentValues.put(TodoContract.TodoEntry.COLUMN_NAME_LNAME, todos.getLastname());
        contentValues.put(TodoContract.TodoEntry.COLUMN_NAME_TELE, todos.getTelephone());
//        String selection = TodoContract.TodoEntry.COLUMN_NAME_TITLE + "LIKE " + previousTodo;

        int i = sqLiteDatabase.update(TodoContract.TodoEntry.TABLE_NAME, contentValues,
                TodoContract.TodoEntry._ID+"=?", new String[]{String.valueOf(todos.getId())});

        return i;
    }

    // Function to retrieve all todos from database
    public List<Todos> findAll(){
        List<Todos> todosList = new ArrayList<Todos>();
        String query = "SELECT * FROM " + TodoContract.TodoEntry.TABLE_NAME;

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        while (cursor.moveToNext()) {
            Todos todo = new Todos();
            todo.setId(Integer.valueOf(cursor.getString(0)));
            todo.setFirstname(cursor.getString(1));
            todo.setLastname(cursor.getString(2));
            todo.setTelephone(cursor.getString(3));
            todosList.add(todo);
        }
        cursor.close();
        return todosList;
    }

    public ArrayList<ArrayList<Object>> renderingTodos() {
        ArrayList<ArrayList<Object>> todoArray = new ArrayList<ArrayList<Object>>();
        Cursor cursor;
        try {
            sqLiteDatabase = this.getReadableDatabase();
            cursor = sqLiteDatabase.query(TodoContract.TodoEntry.TABLE_NAME,
                    new String[]{TodoContract.TodoEntry.COLUMN_NAME_FNAME,
                            TodoContract.TodoEntry.COLUMN_NAME_FNAME,
                            TodoContract.TodoEntry.COLUMN_NAME_TELE},
                    null,null,null,null,null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()){
                while (cursor.moveToNext()) {
                    ArrayList<Object> todoObjects = new ArrayList<Object>();
                    todoObjects.add(cursor.getString(0));
                    todoObjects.add(cursor.getString(1));
                    todoObjects.add(cursor.getString(2));
                    todoArray.add(todoObjects);
                }
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DB Error",e.toString());
        }
        return todoArray;
    }

    public void delete(Todos todos) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TodoContract.TodoEntry.TABLE_NAME, TodoContract.TodoEntry._ID+"=?",
                new String[]{String.valueOf(todos.getId())});
    }

}
