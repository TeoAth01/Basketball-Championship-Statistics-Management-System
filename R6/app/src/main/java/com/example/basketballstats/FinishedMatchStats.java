package com.example.basketballstats;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


public class FinishedMatchStats extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        TextView homeName = (TextView) findViewById(R.id.homeTeam_Name);
//        TextView awayName = (TextView) findViewById(R.id.awayTeam_Name);
//        ImageView homeImage = (ImageView) findViewById(R.id.homeTeam_Logo);
//        ImageView awayImage = (ImageView) findViewById(R.id.awayTeam_Logo);
//        TextView homePoints = (TextView) findViewById(R.id.homePoints);
//        TextView awayPoints = (TextView) findViewById(R.id.awayPoints);
//        TextView homeAssists = (TextView) findViewById(R.id.homeAssists);
//        TextView awayAssists = (TextView) findViewById(R.id.awayAssists);
//        TextView homeRebounds = (TextView) findViewById(R.id.homeRebounds);
//        TextView awayRebounds = (TextView) findViewById(R.id.awayRebounds);
//        TextView homeBlocks = (TextView) findViewById(R.id.homeBlocks);
//        TextView awayBlocks = (TextView) findViewById(R.id.awayBlocks);
//        TextView homeSteals = (TextView) findViewById(R.id.homeSteals);
//        TextView awaySteals = (TextView) findViewById(R.id.awaySteals);
//        TextView homeTurnovers = (TextView) findViewById(R.id.homeTurnovers);
//        TextView awayTurnovers = (TextView) findViewById(R.id.awayTurnovers);
//        TextView homeFouls = (TextView) findViewById(R.id.homeFouls);
//        TextView awayFouls = (TextView) findViewById(R.id.awayFouls);
//        TextView homeFT = (TextView) findViewById(R.id.homeFT);
//        TextView awayFT = (TextView) findViewById(R.id.awayFT);
//        TextView home2pt = (TextView) findViewById(R.id.home2PTS);
//        TextView away2pt = (TextView) findViewById(R.id.away2PTS);
//        TextView home3pt = (TextView) findViewById(R.id.home3PTS);
//        TextView away3pt = (TextView) findViewById(R.id.away3PTS);
//
//
//        awayName.setText(str2);
//        homeName.setText(str1);
//        Picasso.with(this).load("https://www.larisabasket.gr/images/newlogo1.png").into(homeImage);
//        Picasso.with(this).load("https://www.gazzetta.gr/sites/default/files/styles/default/public/2021-01/aris-bc-min.png?itok=N7nFnxEb").into(awayImage);
//        homePoints.setText("300");
//        awayPoints.setText("0");
//        homeAssists.setText("500");
//        awayAssists.setText("0");
//        homeRebounds.setText("500");
//        awayRebounds.setText("0");
//        homeBlocks.setText("500");
//        awayBlocks.setText("0");
//        homeSteals.setText("500");
//        awaySteals.setText("0");
//        homeTurnovers.setText("500");
//        awayTurnovers.setText("0");
//        homeFouls.setText("500");
//        awayFouls.setText("0");
//        homeFT.setText("500/500");
//        awayFT.setText("0/500");
//        home2pt.setText("500/500");
//        away2pt.setText("0/500");
//        home3pt.setText("500/500");
//        away3pt.setText("0/500");


//        Button b1 = (Button) findViewById(R.id.button);
//        b1.setOnClickListener(new View.OnClickListener() {
//            int i = 0;
//            @Override
//            public void onClick(View view) {
//                 TextView homePoint = (TextView) findViewById(R.id.homePoints);
//                 i++;
//                 homePoint.setText(String.valueOf(i));
//            }
//        });
        new InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Intent intent = getIntent();
            String str1 = intent.getStringExtra("String 1");
            String str2 = intent.getStringExtra("String 2");
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.2.8/omada 18","username","password")) {

                String sql = "SELECT * FROM matches WHERE HOME='"+str1+"' AND AWAY='"+str2+"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    info.put("HOME", resultSet.getString("HOME"));
                    info.put("AWAY", resultSet.getString("AWAY"));
                    info.put("HOME FTA", resultSet.getString("HOME FTA"));
                    info.put("AWAY FTA", resultSet.getString("AWAY FTA"));
                    info.put("HOME FTM", resultSet.getString("HOME FTM"));
                    info.put("AWAY FTM", resultSet.getString("AWAY FTM"));
                    info.put("HOME 2PA", resultSet.getString("HOME 2PA"));
                    info.put("AWAY 2PA", resultSet.getString("AWAY 2PA"));
                    info.put("HOME 2PM", resultSet.getString("HOME 2PM"));
                    info.put("AWAY 2PM", resultSet.getString("AWAY 2PM"));
                    info.put("HOME 3PA", resultSet.getString("HOME 3PA"));
                    info.put("AWAY 3PA", resultSet.getString("AWAY 3PA"));
                    info.put("HOME 3PM", resultSet.getString("HOME 3PM"));
                    info.put("AWAY 3PM", resultSet.getString("AWAY 3PM"));
                    info.put("HOME ASSIST", resultSet.getString("HOME ASSIST"));
                    info.put("AWAY ASSIST", resultSet.getString("AWAY ASSIST"));
                    info.put("HOME REBOUND", resultSet.getString("HOME REBOUND"));
                    info.put("AWAY REBOUND", resultSet.getString("AWAY REBOUND"));
                    info.put("HOME BLOCK", resultSet.getString("HOME BLOCK"));
                    info.put("AWAY BLOCK", resultSet.getString("AWAY BLOCK"));
                    info.put("HOME STEAL", resultSet.getString("HOME STEAL"));
                    info.put("AWAY STEAL", resultSet.getString("AWAY STEAL"));
                    info.put("HOME TURNOVER", resultSet.getString("HOME TURNOVER"));
                    info.put("AWAY TURNOVER", resultSet.getString("AWAY TURNOVER"));
                    info.put("HOME FOUL", resultSet.getString("HOME FOUL"));
                    info.put("AWAY FOUL", resultSet.getString("AWAY FOUL"));
                    info.put("HOME POINTS", resultSet.getString("HOME POINTS"));
                    info.put("AWAY POINTS", resultSet.getString("AWAY POINTS"));
                }

                String sql2 = "SELECT Logo FROM teams WHERE name = '"+str1+"'";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet resultSet2 = statement2.executeQuery();

                if (resultSet2.next()) {
                    info.put("homeLogo", resultSet2.getString("Logo"));

                }

                String sql3 = "SELECT Logo FROM teams WHERE name = '"+str2+"'";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                ResultSet resultSet3 = statement3.executeQuery();

                if (resultSet3.next()) {
                    info.put("awayLogo", resultSet3.getString("Logo"));
                }

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (!result.isEmpty()) {

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

                Picasso.with(getApplicationContext()).load(result.get("homeLogo")).into(homeImage);
                Picasso.with(getApplicationContext()).load(result.get("awayLogo")).into(awayImage);
                homeName.setText(result.get("HOME"));
                awayName.setText(result.get("AWAY"));
                homePoints.setText(result.get("HOME POINTS"));
                awayPoints.setText(result.get("AWAY POINTS"));
                homeAssists.setText(result.get("HOME ASSIST"));
                awayAssists.setText(result.get("AWAY ASSIST"));
                homeRebounds.setText(result.get("HOME REBOUND"));
                awayRebounds.setText(result.get("AWAY REBOUND"));
                homeBlocks.setText(result.get("HOME BLOCK"));
                awayBlocks.setText(result.get("AWAY BLOCK"));
                homeSteals.setText(result.get("HOME STEAL"));
                awaySteals.setText(result.get("AWAY STEAL"));
                homeTurnovers.setText(result.get("HOME TURNOVER"));
                awayTurnovers.setText(result.get("AWAY TURNOVER"));
                homeFouls.setText(result.get("HOME FOUL"));
                awayFouls.setText(result.get("AWAY FOUL"));
                homeFT.setText(result.get("HOME FTM")+"/"+result.get("HOME FTA"));
                awayFT.setText(result.get("AWAY FTM")+"/"+result.get("AWAY FTA"));
                home2pt.setText(result.get("HOME 2PM")+"/"+result.get("HOME 2PA"));
                away2pt.setText(result.get("AWAY 2PM")+"/"+result.get("AWAY 2PA"));
                home3pt.setText(result.get("HOME 3PM")+"/"+result.get("HOME 3PA"));
                away3pt.setText(result.get("AWAY 3PM")+"/"+result.get("AWAY 3PA"));
            }
        }
    }
}