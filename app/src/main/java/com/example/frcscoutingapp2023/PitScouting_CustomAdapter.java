package com.example.frcscoutingapp2023;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PitScouting_CustomAdapter extends RecyclerView.Adapter<PitScouting_CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList pit_id, teamNum;
    Activity activity;


    PitScouting_CustomAdapter(Activity activity, Context context, ArrayList pit_id,
                              ArrayList teamNum)
    {
        this.activity = activity;
        this.context = context;
        this.pit_id = pit_id;
        this.teamNum = teamNum;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pit_scouting_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Log.d("path123", "hi");
        holder.match_id_txt.setText(String.valueOf(pit_id.get(position)));
        holder.teamNum_txt.setText(String.valueOf(teamNum.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdatePreMatchData.class);
                intent.putExtra("id", Integer.parseInt(pit_id.get(position).toString()));
                intent.putExtra("teamNum", String.valueOf(teamNum.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pit_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView match_id_txt, teamNum_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            match_id_txt = itemView.findViewById(R.id.match_id_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            teamNum_txt = itemView.findViewById(R.id.teamNum_txt);
        }
    }
}
