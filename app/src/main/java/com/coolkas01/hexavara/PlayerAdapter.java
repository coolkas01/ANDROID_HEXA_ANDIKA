package com.coolkas01.hexavara;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private List<Player> playerList;
    private Context mContext;

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        public TextView tId, tName, tSalary;
        public ImageView iPlayer;

        public PlayerViewHolder(View view) {
            super(view);
            tId = view.findViewById(R.id.player_id);
            tName = view.findViewById(R.id.player_name);
            tSalary = view.findViewById(R.id.player_salary);
            iPlayer = view.findViewById(R.id.player_image);
        }
    }

    public PlayerAdapter(Context context, List<Player> players) {
        mContext = context;
        this.playerList = players;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_player, parent, false);

        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.tId.setText(player.getId());
        holder.tName.setText(player.getName());
        holder.tSalary.setText(player.getSalary());

        GlideApp.with(mContext)
                .load("http://"+player.getImage())
                .placeholder(R.color.colorAccent)
                .dontAnimate()
                .into(holder.iPlayer);
    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }
}
