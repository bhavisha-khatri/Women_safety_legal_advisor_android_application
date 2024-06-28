package com.example.womensafetylegaladvisor;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;

public class AdvocateDetailsActivity extends AppCompatActivity {
    ImageView img_profile;
    TextView tv_name, tv_exp, tv_spec, tv_phone, tv_email, tv_about, tv_lang, tv_court, tv_prac ,tv_licence, tv_addre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advocate_details);

        tv_name = findViewById(R.id.advocateName);
        tv_exp = findViewById(R.id.experience);
        tv_spec = findViewById(R.id.specialization);
        tv_phone = findViewById(R.id.mobile);
        tv_email = findViewById(R.id.email_id);
        tv_lang = findViewById(R.id.language);
        tv_court = findViewById(R.id.court);
        tv_prac = findViewById(R.id.practice_area);
        tv_licence = findViewById(R.id.licence_no);
        tv_addre = findViewById(R.id.address);
        tv_about = findViewById(R.id.about_us);
        img_profile = findViewById(R.id.profileImage);
        Intent it = getIntent();
        String name = it.getStringExtra("advocate_name");
        String exp = it.getStringExtra("experience_years");
        String spec = it.getStringExtra("specialization");
        String phone = it.getStringExtra("mobile_no");
        String email = it.getStringExtra("email_id");
        String lang = it.getStringExtra("language");
        String court = it.getStringExtra("court");
        String prac = it.getStringExtra("practice_area");
        String licence = it.getStringExtra("licence_no");
        String addre = it.getStringExtra("street") + ", " + it.getStringExtra("city") + ", " + it.getStringExtra("state") + "-" + it.getStringExtra("pincode");
        String about = it.getStringExtra("about_us");
        String photo = it.getStringExtra("photo");
        tv_name.setText("Name: "+name);
        tv_exp.setText("Expereince: " + exp);
        tv_spec.setText("Specialization: " + spec);
        tv_phone.setText("Mobile No: " + phone);
        tv_email.setText("Email ID: " + email);
        tv_lang.setText("Language: " + lang);
        tv_court.setText("Court: " + court);
        tv_prac.setText("Practice Area: " + prac);
        tv_licence.setText("Licence No: " + licence);
        tv_addre.setText("Address: " + addre);
        tv_about.setText("Abot Us: " + about);

        Glide.with(AdvocateDetailsActivity.this)
                .load(photo)
                .placeholder(R.drawable.ic_launcher_background)
                .into(img_profile);

    }
}