package com.lateofrederick.todoish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lateofrederick.todoish.R;
import com.lateofrederick.todoish.TodoActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

    }

    public void login(View view) {
        Intent intent = new Intent(this, TodoActivity.class);
        startActivity(intent);
    }
}
