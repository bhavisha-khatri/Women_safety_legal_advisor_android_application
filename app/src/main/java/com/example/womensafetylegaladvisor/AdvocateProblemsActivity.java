package com.example.womensafetylegaladvisor;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
public class AdvocateProblemsActivity extends AppCompatActivity {
    TextView tv;
    ArrayList list;
    SimpleAdapter sa;
    ListView lv;
    HashMap<String, String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advocate_problems);
        String problems[][] = AdvocateProblems.PROBLEMS;
        list = new ArrayList();
        for (int i=0; i < problems.length; i++) {
            item = new HashMap<String, String>();
            item.put("description", problems[i][1]);
            item.put("created_at", "Created at: " + problems[i][3]);
            item.put("case_type", "Case Type: " + problems[i][0]);
            item.put("comments", "Comments: " + problems[i][2]);
            item.put("created_by", problems[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.advocate_problem_details_list,
                new String[]{"description", "created_at", "case_type", "comments", "created_by"},
                new int[]{R.id.description,R.id.created_at,R.id.case_type,R.id.comments,R.id.created_by}
        );
        lv = (ListView)findViewById(R.id.ListViewUP);
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(AdvocateProblemsActivity.this, AdvocateProblemDetailsActivity.class);
                it.putExtra("description", problems[position][1]);
                it.putExtra("created_at", problems[position][3]);
                it.putExtra("case_type", problems[position][2]);
                it.putExtra("comments", problems[position][0]);
                it.putExtra("created_by", problems[position][4]);
                startActivity(it);
            }
        });
    }
}