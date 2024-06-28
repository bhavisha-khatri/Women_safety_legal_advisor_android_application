package com.example.womensafetylegaladvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UserProblemDetailsActivity extends AppCompatActivity {
    ListView lv;
    SimpleAdapter sa;
    ArrayList lists;
    Button btnSubmit;
    HashMap<String, String> item;
    TextView tv_des, tv_cre,tv_com, tv_case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_problem_details);
        tv_des = findViewById(R.id.description);
        tv_cre = findViewById(R.id.created_at);
        tv_case = findViewById(R.id.case_type);
        tv_com = findViewById(R.id.comments);
        btnSubmit = findViewById(R.id.c_submit);
        String Comments[][] = CommentsData.COMMENTS;
        Intent it = getIntent();
        String description = it.getStringExtra("description");
        String created_at = it.getStringExtra("created_at");
        String case_type = it.getStringExtra("case_type");
        String comments = it.getStringExtra("comments");
        tv_des.setText(description);
        tv_cre.setText("Created At: " + created_at);
        tv_com.setText("Case Type: " + comments);
        tv_case.setText("Comments: " + case_type);
        lists = new ArrayList();
        for (int i=0; i < Comments.length; i++) {
            item = new HashMap<String, String>();
            item.put("comment", Comments[i][0]);
            item.put("name", Comments[i][1]);

            lists.add(item);
        }
        sa = new SimpleAdapter(this, lists,
                R.layout.user_problem_details_list,
                new String[]{"comment", "name"},
                new int[]{R.id.comment,R.id.c_name}
        );
        lv = (ListView)findViewById(R.id.ListViewUPD);
        lv.setAdapter(sa);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comment added successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}