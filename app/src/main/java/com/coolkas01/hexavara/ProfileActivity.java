package com.coolkas01.hexavara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tFullname;
    private TextView tUsername;
    private TextView tEmail;
    private TextView tAddress;
    private CircleImageView iProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tFullname = (TextView) findViewById(R.id.text_fullname);
        tUsername = (TextView) findViewById(R.id.text_username);
        tEmail = (TextView) findViewById(R.id.text_email);
        tAddress = (TextView) findViewById(R.id.text_address);
        iProfile = (CircleImageView) findViewById(R.id.profile_image);

        initProfile();
    }

    private void initProfile() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            tFullname.setText(bundle.getString("fullname"));
            tUsername.setText(bundle.getString("username"));
            tEmail.setText(bundle.getString("email"));
            tAddress.setText(bundle.getString("address"));
            
            GlideApp.with(this)
                    .load(bundle.getString("photo"))
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(iProfile);
        }
    }
}
