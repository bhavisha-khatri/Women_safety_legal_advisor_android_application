package com.example.womensafetylegaladvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    TextView tv, tva;
    RadioGroup radioGroupUserType;
    RadioButton radioButtonUser, radioButtonAdvocate;
    private int isAdvocate = 0;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private final String userUrl = "http://192.168.31.6:80/legal_advisor/api/user.php";
    private final String advocateUrl = "http://192.168.31.6:80/legal_advisor/api/advocate.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        radioGroupUserType = findViewById(R.id.user_type);
        radioButtonUser = findViewById(R.id.radioButtonUser);
        radioButtonAdvocate = findViewById(R.id.radioButtonAdvocate);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);
        tva = findViewById(R.id.textViewNewAdvocate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                int selectedUserId = radioGroupUserType.getCheckedRadioButtonId();
                String URL = userUrl;
                if (selectedUserId == radioButtonAdvocate.getId()) {
                    URL = advocateUrl;
                    isAdvocate = 1;
                } else if (selectedUserId == radioButtonUser.getId()) {
                    isAdvocate = 0;
                }
                if (username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all Details",Toast.LENGTH_SHORT).show();
                }else {
                    getData(username, password, URL);
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, UserRegisterActivity.class));
            }
        });
        tva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, AdvocateRegisterActivity.class));
            }
        });

    }
    private void getData(String username, String password, String URL) {
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    String status = json.getString("status");
                    String message = json.getString("message");
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    if (status.equals("success")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username); editor.apply();
                        if (isAdvocate == 0) {
                            startActivity(new Intent(LoginActivity.this, UserHomeActivity.class));
                        } else {
                            startActivity(new Intent(LoginActivity.this, AdvocateHomeActivity.class));
                        }
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        mRequestQueue.add(mStringRequest);
    }
}