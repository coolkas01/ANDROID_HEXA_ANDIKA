package com.coolkas01.hexavara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private Intent intent;
    private Bundle bundle;

    private TextView tFullname;
    private TextView tUsername;
    private TextView tEmail;
    private TextView tAddress;
    private ImageView iProfile;

    private List<Player> playerList;
    private PlayerAdapter aPlayer;
    private RecyclerView rPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AndroidNetworking.initialize(this);

        playerList = new ArrayList<>();

        tFullname = findViewById(R.id.text_fullname);
        tUsername = findViewById(R.id.text_username);
        tEmail = findViewById(R.id.text_email);
        tAddress = findViewById(R.id.text_address);
        iProfile = findViewById(R.id.profile_image);

        rPlayer = findViewById(R.id.recycler_love);

        aPlayer = new PlayerAdapter(getApplicationContext(), playerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rPlayer.setLayoutManager(mLayoutManager);
        rPlayer.setAdapter(aPlayer);

        intent = getIntent();
        bundle = intent.getExtras();
        initProfile();
        loadPlayerList();
    }

    private void initProfile() {
        if (bundle != null) {
            tFullname.setText(bundle.getString("fullname"));
            tUsername.setText(bundle.getString("username"));
            tEmail.setText(bundle.getString("email"));
            tAddress.setText(bundle.getString("address"));

            String url = "http://"+bundle.getString("photo");

            GlideApp.with(this)
                    .load(url)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(iProfile);
        }
    }

    private void loadPlayerList() {
        AndroidNetworking.get("http://hexavara.ip-dynamic.com/androidrec/public/api/mylist")
                .addHeaders("Authorization", "jldh946rgbc0983vxcu341bvj123plgh99dnxu42232")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        playerList.clear();

                        for (int i=0; i<response.length(); i++) {
                            JSONObject jsonObject = response.optJSONObject(i);
                            try {
                                Player player = new Player();
                                player.setId(jsonObject.getString("id"));
                                player.setName(jsonObject.getString("name"));
                                player.setSalary(jsonObject.getString("salary"));
                                player.setImage(jsonObject.getString("image"));
                                playerList.add(player);

                                aPlayer.notifyDataSetChanged();

                            } catch (JSONException e) {

                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
