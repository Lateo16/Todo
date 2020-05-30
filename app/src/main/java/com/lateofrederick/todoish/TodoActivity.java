package com.lateofrederick.todoish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lateofrederick.todoish.EditTodoActivity;
import com.lateofrederick.todoish.R;

import java.util.ArrayList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TodoReaderHelper mTodoReaderHelper;

    ArrayList<Todos> mTodosArrayList = new ArrayList<>();
    Todos mTodos;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        mRecyclerView = findViewById(R.id.todo_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TodoAdapter(generateTodos());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.HORIZONTAL));

    }


    private ArrayList<Todos> generateTodos(){

        TodoReaderHelper todoReaderHelper = new TodoReaderHelper(this);
        ArrayList<ArrayList<Object>> todoData = todoReaderHelper.renderingTodos();

        for(int i=0; i < todoData.size(); i++) {
            mTodos = new Todos();
            ArrayList<Object> todo = todoData.get(i);
            mTodos.setFirstname(todo.get(0).toString());
            mTodos.setLastname(todo.get(1).toString());
            mTodos.setTelephone(todo.get(2).toString());
            mTodosArrayList.add(mTodos);
        }
        return mTodosArrayList;
    }


    public void editTodo(View view) {
        Intent intent = new Intent(this, EditTodoActivity.class);
        startActivity(intent);
    }

    public void addTodo(View view) {
        Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);
        startActivity(intent);
    }

    public void deleteTodo(View view) {

    }
}
