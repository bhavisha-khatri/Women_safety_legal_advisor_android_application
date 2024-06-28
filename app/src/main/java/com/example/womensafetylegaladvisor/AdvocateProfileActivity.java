package com.example.womensafetylegaladvisor;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.widget.EditText;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class AdvocateProfileActivity extends AppCompatActivity {

    EditText edUsername, edName, edEmail, edMobile, edStreet, edCity, edPincode, edState, edLicenceno, edAboutus, edCourt, edPracticeArea, edExperienceYear, edLanguage, edSpecialization;
    ImageView img;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private final String profileUrl = "http://192.168.31.6:80/legal_advisor/api/advocatedetails.php";
    private final String imagePath= "http://192.168.31.6/legal_advisor/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advocate_profile);
        edUsername = findViewById(R.id.editTextProAUsername);
        edName = findViewById(R.id.editTextProAName);
        edEmail = findViewById(R.id.editTextProAEmail);
        edMobile = findViewById(R.id.editTextProAMobile);
        edStreet = findViewById(R.id.editTextProAStreet);
        edCity = findViewById(R.id.editTextProACity);
        edPincode = findViewById(R.id.editTextProAPincode);
        edState = findViewById(R.id.editTextProAState);
        edLicenceno = findViewById(R.id.editTextProALicenceNo);
        edAboutus = findViewById(R.id.editTextProAAboutUs);
        edCourt = findViewById(R.id.editTextProACourt);
        edPracticeArea = findViewById(R.id.editTextProAPracticeArea);
        edExperienceYear = findViewById(R.id.editTextProAExperienceYear);
        edLanguage = findViewById(R.id.editTextProALanguage);
        edSpecialization = findViewById(R.id.editTextProASpecialization);
        img = findViewById(R.id.profileImage);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        if (!username.isEmpty()) {
            getAdvocateProfile(username);
        } else {
            Toast.makeText(this, "No Advocate logged in!", Toast.LENGTH_SHORT).show();
        }
    }
    private void getAdvocateProfile(String username) {

        mRequestQueue = Volley.newRequestQueue(this);
        String urlWithParams = profileUrl + "?username=" + username;
        mStringRequest = new StringRequest(Request.Method.GET, urlWithParams, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String status = json.getString("status");
                    if (status.equals("success")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        JSONObject data = json.getJSONObject("data");
                        edUsername.setText(data.getString("username"));
                        edName.setText(data.getString("name"));
                        edEmail.setText(data.getString("email_id"));
                        edMobile.setText(data.getString("mobile"));
                        edStreet.setText(data.getString("street"));
                        edCity.setText(data.getString("city_name"));
                        edPincode.setText(data.getString("pincode"));
                        edState.setText(data.getString("state_name"));
                        edLicenceno.setText(data.getString("licence_no"));
                        edAboutus.setText(data.getString("about_us"));
                        edCourt.setText(data.getString("court"));
                        edPracticeArea.setText(data.getString("practice_area"));
                        edExperienceYear.setText(data.getString("experiance_year"));
                        edLanguage.setText(data.getString("language"));
                        edSpecialization.setText(data.getString("specialization"));
                        String imageURL = imagePath + data.getString("photo");
                        Glide.with(AdvocateProfileActivity.this)
                                .load(imageURL)
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(img);
                        editor.putString("username", data.getString("username"));
                        editor.apply();
                    } else {
                        Toast.makeText(getApplicationContext(), json.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error :" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(mStringRequest);
    }
}