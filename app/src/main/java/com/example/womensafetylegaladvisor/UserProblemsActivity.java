package com.example.womensafetylegaladvisor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;;

public class UserProblemsActivity extends AppCompatActivity {

    TextView tv;
    ArrayList list;
    SimpleAdapter sa;
    ListView lv;
    Button btnNew;
    HashMap<String, String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_problems);
        btnNew = findViewById(R.id.btnNew);
        String user[][] = ProblemData.PROBLEMS;
        list = new ArrayList();
        for (int i=0; i < user.length; i++) {
            item = new HashMap<String, String>();
            item.put("description", user[i][1]);
            item.put("created_at", "Created at: " + user[i][3]);
            item.put("case_type", "Case Type: " + user[i][0]);
            item.put("comments", "Comments: " + user[i][2]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.user_problem_list,
                new String[]{"description", "created_at", "case_type", "comments"},
                new int[]{R.id.description,R.id.created_at,R.id.case_type,R.id.comments}
        );
        lv = (ListView)findViewById(R.id.ListViewUP);
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(UserProblemsActivity.this, UserProblemDetailsActivity.class);
                it.putExtra("description", user[position][1]);
                it.putExtra("created_at", user[position][3]);
                it.putExtra("case_type", user[position][2]);
                it.putExtra("comments", user[position][0]);
                startActivity(it);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProblemsActivity.this, UserNewProblemActivity.class));
            }
        });
    }
}