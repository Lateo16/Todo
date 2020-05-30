package com.lateofrederick.todoish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lateofrederick.todoish.R;
import com.lateofrederick.todoish.TodoActivity;

public class AddTodoActivity extends AppCompatActivity {
    private EditText mFname;
    private EditText mLname;
    private EditText mTele;
    private TodoReaderHelper todoReaderHelper;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        mFname = findViewById(R.id.add_firstname);
        mLname = findViewById(R.id.add_lastname);
        mTele = findViewById(R.id.add_telephone);
        mButton = findViewById(R.id.add_button);

        todoReaderHelper = new TodoReaderHelper(this);
        addTodo();

    }

    public void addTodo() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newFname = mFname.getText().toString();
                String newLname = mLname.getText().toString();
                String newTele = mTele.getText().toString();

                if(!newFname.isEmpty() && !newLname.isEmpty() && !newTele.isEmpty()){
                    try{
                        Todos todos = new Todos(newFname, newLname, newTele);
                        todoReaderHelper.save(todos);
                        finish();
                        Intent main = new Intent(getApplicationContext(), TodoActivity.class);
                        startActivity(main);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AddTodoActivity.this,"Data not added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
