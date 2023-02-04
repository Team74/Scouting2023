package com.example.frcscoutingapp2023;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList match_id, matchNum, teamNum, autonTotal, numCones, numCubes, balance;
    Activity activity;


    CustomAdapter(Activity activity, Context context, ArrayList match_id, ArrayList matchNum,
                  ArrayList teamNum, ArrayList autonTotal, ArrayList numCones, ArrayList numCubes, ArrayList balance)
    {
        this.activity = activity;
        this.context = context;
        this.match_id = match_id;
        this.matchNum = matchNum;
        this.teamNum = teamNum;
        this.autonTotal = autonTotal;
        this.numCones = numCones;
        this.numCubes = numCubes;
        this.balance = balance;

        //Log.d("test123",String.valueOf(numCones.get(1)) + " " + String.valueOf(numCubes.get(1)) + " " + String.valueOf(matchNum.get(1)) + " " + String.valueOf(teamNum.get(1)));

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.match_id_txt.setText(String.valueOf(match_id.get(position)));
        holder.autonTotal_txt.setText(String.valueOf(autonTotal.get(position)));
        holder.numCones_txt.setText(String.valueOf(numCones.get(position)));
        holder.numCubes_txt.setText(String.valueOf(numCubes.get(position)));
        holder.matchNum_txt.setText(String.valueOf(matchNum.get(position)));
        holder.teamNum_txt.setText(String.valueOf(teamNum.get(position)));

        //Not clean, but I just spent 3 hours trying to display true or false and not 0 or 1, just to learn that == does not compare string values
        //very well, and that I needed to use .equals() . An entire day wasted do to that
        String canBalance = String.valueOf(balance.get(position));
        switch(canBalance){
            case "0":
                holder.balance_txt.setText("Did Not Attempt");
                break;
            case "1":
                holder.balance_txt.setText("Failed Attempt");
                break;
            case "2":
                holder.balance_txt.setText("Engaged");
                break;
            case "3":
                holder.balance_txt.setText("Docked");
                break;
            default:
                holder.balance_txt.setText("Error");
        }


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdatePreMatchData.class);
                intent.putExtra("id", Integer.parseInt(match_id.get(position).toString()));
                intent.putExtra("matchNum", String.valueOf(matchNum.get(position)));
                intent.putExtra("teamNum", String.valueOf(teamNum.get(position)));
                intent.putExtra("cones", String.valueOf(numCones.get(position)));
                intent.putExtra("cubes", String.valueOf(numCubes.get(position)));
                intent.putExtra("balance", String.valueOf(balance.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return match_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView match_id_txt, matchNum_txt, teamNum_txt, autonTotal_txt, numCones_txt, numCubes_txt, balance_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            match_id_txt = itemView.findViewById(R.id.match_id_txt);

            autonTotal_txt = itemView.findViewById(R.id.autonTotal_txt);
            numCones_txt = itemView.findViewById(R.id.cones_txt);
            numCubes_txt = itemView.findViewById(R.id.cubes_txt);
            balance_txt = itemView.findViewById(R.id.balance_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            matchNum_txt = itemView.findViewById(R.id.matchNum_txt);
            teamNum_txt = itemView.findViewById(R.id.teamNum_txt);
        }
    }
}
