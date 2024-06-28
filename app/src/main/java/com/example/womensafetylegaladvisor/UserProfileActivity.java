package com.example.womensafetylegaladvisor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class UserProfileActivity extends AppCompatActivity {

    EditText edUsername, edName, edEmail, edMobile, edStreet, edCity, edPincode, edState;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private final String profileUrl = "http://192.168.31.6:80/legal_advisor/api/userdetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        edUsername = findViewById(R.id.editTextProUUsername);
        edName = findViewById(R.id.editTextProUName);
        edEmail = findViewById(R.id.editTextProUEmail);
        edMobile = findViewById(R.id.editTextProUMobile);
        edStreet = findViewById(R.id.editTextProUStreet);
        edCity = findViewById(R.id.editTextProUCity);
        edPincode = findViewById(R.id.editTextProUPincode);
        edState = findViewById(R.id.editTextProUState);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        if (!username.isEmpty()) {
            getUserProfile(username);
        } else {
            Toast.makeText(this, "No user logged in!", Toast.LENGTH_SHORT).show();
        }
    }
    private void getUserProfile(String username) {
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
