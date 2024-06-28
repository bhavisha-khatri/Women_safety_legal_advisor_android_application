package com.example.womensafetylegaladvisor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class FindAdvocateActivity extends AppCompatActivity {
    TextView tv;
    ArrayList list;
    SimpleAdapter sa;
    ListView lv;
    HashMap<String, String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_advocate);
        String imagePath= "http://192.168.31.6/legal_advisor/";
        String advocates[][] = AdvocateData.ADVOCATES;
        list = new ArrayList();
        for (int i=0; i < advocates.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", advocates[i][0]);
            item.put("line2", advocates[i][3] + ", " + advocates[i][4]);
            item.put("line3", advocates[i][8]);
            item.put("line4", advocates[i][9]);
            item.put("line5", advocates[i][6]);
            item.put("profileImage", imagePath + advocates[i][14]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.advocate_list,
                new String[]{"line1", "line2", "line3", "line4", "line5","profileImage"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e, R.id.profileImage}
        );
        sa.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view.getId() == R.id.profileImage) {
                    ImageView imageView = (ImageView) view;
                    String imageUrl = (String) data;
                    Glide.with(FindAdvocateActivity.this)
                            .load(imageUrl)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imageView);
                    return true;
                }
                return false;
            }
        });
        lv = (ListView)findViewById(R.id.ListViewAD);
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(FindAdvocateActivity.this, AdvocateDetailsActivity.class);
                it.putExtra("advocate_name", advocates[position][0]);
                it.putExtra("street", advocates[position][1]);
                it.putExtra("pincode", advocates[position][2]);
                it.putExtra("city", advocates[position][3]);
                it.putExtra("state", advocates[position][4]);
                it.putExtra("licence_no", advocates[position][5]);
                it.putExtra("court", advocates[position][6]);
                it.putExtra("practice_area", advocates[position][7]);
                it.putExtra("language", advocates[position][8]);
                it.putExtra("specialization", advocates[position][9]);
                it.putExtra("experience_years", advocates[position][10]);
                it.putExtra("mobile_no", advocates[position][11]);
                it.putExtra("email_id", advocates[position][12]);
                it.putExtra("about_us", advocates[position][13]);
                it.putExtra("photo", imagePath + advocates[position][14]);
                startActivity(it);
            }
        });
    }
}