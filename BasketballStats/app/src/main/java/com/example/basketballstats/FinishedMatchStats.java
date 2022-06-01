package com.example.basketballstats;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FinishedMatchStats extends AppCompatActivity {

    ArrayList<String> cbList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("String 1");
        String str2 = intent.getStringExtra("String 2");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView homeName = (TextView) findViewById(R.id.homeTeam_Name);
        TextView awayName = (TextView) findViewById(R.id.awayTeam_Name);
        ImageView homeImage = (ImageView) findViewById(R.id.homeTeam_Logo);
        ImageView awayImage = (ImageView) findViewById(R.id.awayTeam_Logo);
        TextView homePoints = (TextView) findViewById(R.id.homePoints);
        TextView awayPoints = (TextView) findViewById(R.id.awayPoints);
        TextView homeAssists = (TextView) findViewById(R.id.homeAssists);
        TextView awayAssists = (TextView) findViewById(R.id.awayAssists);
        TextView homeRebounds = (TextView) findViewById(R.id.homeRebounds);
        TextView awayRebounds = (TextView) findViewById(R.id.awayRebounds);
        TextView homeBlocks = (TextView) findViewById(R.id.homeBlocks);
        TextView awayBlocks = (TextView) findViewById(R.id.awayBlocks);
        TextView homeSteals = (TextView) findViewById(R.id.homeSteals);
        TextView awaySteals = (TextView) findViewById(R.id.awaySteals);
        TextView homeTurnovers = (TextView) findViewById(R.id.homeTurnovers);
        TextView awayTurnovers = (TextView) findViewById(R.id.awayTurnovers);
        TextView homeFouls = (TextView) findViewById(R.id.homeFouls);
        TextView awayFouls = (TextView) findViewById(R.id.awayFouls);
        TextView homeFT = (TextView) findViewById(R.id.homeFT);
        TextView awayFT = (TextView) findViewById(R.id.awayFT);
        TextView home2pt = (TextView) findViewById(R.id.home2PTS);
        TextView away2pt = (TextView) findViewById(R.id.away2PTS);
        TextView home3pt = (TextView) findViewById(R.id.home3PTS);
        TextView away3pt = (TextView) findViewById(R.id.away3PTS);


        awayName.setText(str2);
        homeName.setText(str1);
        Picasso.with(this).load("https://upload.wikimedia.org/wikipedia/el/e/e7/PAOK_BC_logo.png").into(homeImage);
        Picasso.with(this).load("https://www.gazzetta.gr/sites/default/files/styles/default/public/2021-01/aris-bc-min.png?itok=N7nFnxEb").into(awayImage);
        homePoints.setText("300");
        awayPoints.setText("0");
        homeAssists.setText("500");
        awayAssists.setText("0");
        homeRebounds.setText("500");
        awayRebounds.setText("0");
        homeBlocks.setText("500");
        awayBlocks.setText("0");
        homeSteals.setText("500");
        awaySteals.setText("0");
        homeTurnovers.setText("500");
        awayTurnovers.setText("0");
        homeFouls.setText("500");
        awayFouls.setText("0");
        homeFT.setText("500/500");
        awayFT.setText("0/500");
        home2pt.setText("500/500");
        away2pt.setText("0/500");
        home3pt.setText("500/500");
        away3pt.setText("0/500");


        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                 TextView homePoint = (TextView) findViewById(R.id.homePoints);
                 i++;
                 homePoint.setText(String.valueOf(i));
            }
        });
    }
}