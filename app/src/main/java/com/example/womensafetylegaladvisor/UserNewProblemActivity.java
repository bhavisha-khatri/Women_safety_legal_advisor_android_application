package com.example.womensafetylegaladvisor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserNewProblemActivity extends AppCompatActivity {
    TextView tv_que;
    Button btnSubmit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_new_problem);
        tv_que = findViewById(R.id.askquestion);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Question submitted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
