package com.example.basketballchampionshipstatisticsmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_team_statistics);

        ArrayList<String> teams = new ArrayList<>();


        //TO-DO get data from database

        teams.add("aris");
        teams.add("paok");
        teams.add("pao");
        teams.add("osfp");
        teams.add("aek");
        teams.add("hraklhs");
        teams.add("giannena");

        TextView tvTeams = findViewById(R.id.teams);
        TextView tvStandings =  findViewById(R.id.STANDINGS);
        TextView tvFta =  findViewById(R.id.FTA);
        TextView tvFtm =  findViewById(R.id.FTM);
        TextView tv2pa =  findViewById(R.id.P2A);
        //tv.append(teams.get(0)+"\n");//String.valueOf(teams.size())

//        tvStandings.append("\n"+teams.get(6));
//        tvFta.append("\n"+teams.get(6));
//        tvFtm.append("\n"+teams.get(6));
//        tv2pa.append("\n"+teams.get(6));
        for (int i = 0; i< teams.size();i++){
            tvTeams.append("\n"+teams.get(i));
        }




    }
}