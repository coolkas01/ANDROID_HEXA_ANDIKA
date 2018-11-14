package com.coolkas01.hexavara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText eUsername = (EditText) findViewById(R.id.edt_username);
        EditText ePassword = (EditText) findViewById(R.id.edt_password);
        Button bLogin = (Button) findViewById(R.id.btn_login);
    }
}
