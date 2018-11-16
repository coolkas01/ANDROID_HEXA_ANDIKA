package com.coolkas01.hexavara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText eUsername;
    private EditText ePassword;
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AndroidNetworking.initialize(this);

        eUsername = findViewById(R.id.edt_username);
        ePassword = findViewById(R.id.edt_password);
        bLogin = findViewById(R.id.btn_login);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesLogin();
            }
        });
    }

    private void prosesLogin() {
        final User user = new User();

        String username = eUsername.getText().toString();
        String password = ePassword.getText().toString();

        AndroidNetworking.post("http://hexavara.ip-dynamic.com/androidrec/public/api/login")
                .addBodyParameter("username", "hexavara")
                .addBodyParameter("password", "6hexavara6")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            user.setToken(response.getString("token"));
                            user.setUsername(response.getString("username"));
                            user.setEmail(response.getString("email"));
                            user.setFullname(response.getString("fullname"));
                            user.setAddress(response.getString("address"));
                            user.setPhoto(response.getString("photo"));
                        } catch (JSONException e) {
                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                        }

                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("token", user.getToken());
                        intent.putExtra("username", user.getUsername());
                        intent.putExtra("email", user.getEmail());
                        intent.putExtra("fullname", user.getFullname());
                        intent.putExtra("address", user.getAddress());
                        intent.putExtra("photo", user.getPhoto());
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
