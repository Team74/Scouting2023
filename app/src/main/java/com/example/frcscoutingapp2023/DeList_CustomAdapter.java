package com.example.frcscoutingapp2023;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeList_CustomAdapter extends RecyclerView.Adapter<DeList_CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList teamNum;
    Activity activity;
    ArrayList<String> teamNumsArray = new ArrayList<>();
    String delistTeams;


    DeList_CustomAdapter(Activity activity, Context context,
                         ArrayList teamNum)
    {
        this.activity = activity;
        this.context = context;
        this.teamNum = teamNum;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.delist_team_row, parent, false);
        SharedPreferences load = activity.getSharedPreferences("TeamsToDelist", 0);
        delistTeams = load.getString("delistTeams", "0");
        Log.d("path123", delistTeams + " 123");
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.teamNum_txt.setText(String.valueOf(teamNum.get(position)));

        Log.d("path123", holder.teamNum_txt.getText().toString());
        if(delistTeams.contains(holder.teamNum_txt.getText().toString()))
        {
            holder.delist_box.setChecked(true);
            teamNumsArray.add(holder.teamNum_txt.getText().toString());
        }else{
            holder.delist_box.setChecked(false);
            if(teamNumsArray.contains(holder.teamNum_txt.getText().toString()))
                teamNumsArray.remove(holder.teamNum_txt.getText().toString());
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.teamNum_txt.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.delist_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.delist_box.isChecked())
                {
                    teamNumsArray.add(holder.teamNum_txt.getText().toString());
                } else if (!holder.delist_box.isChecked()) {
                    if(teamNumsArray.contains(holder.teamNum_txt.getText().toString()))
                        teamNumsArray.remove(holder.teamNum_txt.getText().toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return teamNum.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView teamNum_txt;
        LinearLayout mainLayout;
        CheckBox delist_box;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            teamNum_txt = itemView.findViewById(R.id.teamNum_txt);
            delist_box = itemView.findViewById(R.id.delist_cb);
        }
    }

    public String[] getDelistedTeams()
    {
        Log.d("path123", teamNumsArray.toString());
        String[] teamNumsAsStringArray = teamNumsArray.toArray(new String[0]);
        return teamNumsAsStringArray;
    }

}
