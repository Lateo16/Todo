package com.lateofrederick.todoish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lateofrederick.todoish.R;
import com.lateofrederick.todoish.TodoActivity;
import com.lateofrederick.todoish.TodoReaderHelper;
import com.lateofrederick.todoish.Todos;

public class EditTodoActivity extends AppCompatActivity {

    private TextView mFname;
    private TextView mLname;
    private TextView mTele;
    private Button mEditButton;

    TodoReaderHelper todoReaderHelper = new TodoReaderHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        mFname= findViewById(R.id.edit_firstname);
        mLname= findViewById(R.id.edit_lastname);
        mTele= findViewById(R.id.edit_telephone);
        mEditButton = findViewById(R.id.edit_button);

        editData();
    }

    public void editData() {
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mfname = mFname.getText().toString();
                String mlname = mLname.getText().toString();
                String mtele = mTele.getText().toString();

                if(!mfname.isEmpty() && !mlname.isEmpty() && !mtele.isEmpty()) {
                    try{
                        Todos todo = new Todos(mfname,mlname,mtele);
                        int i = todoReaderHelper.update(todo);
                        Log.d("Row affected", " " + i);

                        Toast.makeText(EditTodoActivity.this, "to do updated", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }
}
