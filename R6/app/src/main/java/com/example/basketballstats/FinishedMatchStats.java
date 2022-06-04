package com.example.basketballstats;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FinishedMatchStats extends AppCompatActivity {
    private boolean isLeftListEnabled = true;
    private boolean isRightListEnabled = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        new InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<String, Void, ArrayList<ArrayList<String>>>  {
        @Override
        protected ArrayList<ArrayList<String>>  doInBackground(String... String) {
            Intent intent = getIntent();
            String str1 = intent.getStringExtra("String 1");
            String str2 = intent.getStringExtra("String 2");

            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            ArrayList<String> namesAndPoints = new ArrayList<String>();
            ArrayList<String> home = new ArrayList<String>();
            ArrayList<String> away = new ArrayList<String>();
            ArrayList<String> logos = new ArrayList<String>();


            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.2.8/omada 18","username","password")) {
                //Getting Home Teams Stats From Database
                String sql = "SELECT HFTA,HFTM,H2PA,H2PM,H3PA,H3PM,HBLOCK,HTURNOVER,HFOUL,HSTEAL,HREBOUND,HASSIST FROM matches WHERE HOME='"+str1+"' AND AWAY='"+str2+"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                int databaseColumnCount = resultSet.getMetaData().getColumnCount();
                if (resultSet.next()) {
                    for (int j = 0; j < databaseColumnCount; j++) {
                        home.add(resultSet.getString(j+1));
                    }
                    info.add(home);
                }

                //Getting Home Teams Stats From Databse
                String sql2 = "SELECT AFTA,AFTM,A2PA,A2PM,A3PA,A3PM,ABLOCK,ATURNOVER,AFOUL,ASTEAL,AREBOUND,AASSIST FROM matches WHERE HOME='"+str1+"' AND AWAY='"+str2+"'";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                ResultSet resultSet2 = statement2.executeQuery();
                int databaseColumnCount1 = resultSet2.getMetaData().getColumnCount();
                if (resultSet2.next()) {
                    for (int j = 0; j < databaseColumnCount1; j++) {
                        away.add(resultSet2.getString(j + 1));
                    }
                    info.add(away);
                }

                //Getting Points and Names for both home and away teams from database
                String sql3 = "SELECT HOME,HPOINTS,AWAY,APOINTS FROM matches WHERE HOME='"+str1+"' AND AWAY='"+str2+"'";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                ResultSet resultSet3 = statement3.executeQuery();
                int databaseColumnCount2 = resultSet3.getMetaData().getColumnCount();
                if (resultSet3.next()) {
                    for (int j = 0; j < databaseColumnCount2; j++) {
                        namesAndPoints.add(resultSet3.getString(j + 1));
                    }
                    info.add(namesAndPoints);
                }

                //Getting both teams logos from database
                if(!info.isEmpty()){
                    String sql4 = "SELECT Logo FROM teams WHERE name = '"+str1+"'";
                    PreparedStatement statement4 = connection.prepareStatement(sql4);
                    ResultSet resultSet4 = statement4.executeQuery();
                    int databaseColumnCount4 = resultSet4.getMetaData().getColumnCount();
                    if (resultSet4.next()) {
                        for (int j = 0; j < databaseColumnCount4; j++) {
                            logos.add(resultSet4.getString(j + 1));
                        }
                    }

                    String sql5 = "SELECT Logo FROM teams WHERE name = '"+str2+"'";
                    PreparedStatement statement5 = connection.prepareStatement(sql5);
                    ResultSet resultSet5 = statement5.executeQuery();
                    int databaseColumnCount5 = resultSet5.getMetaData().getColumnCount();
                    if (resultSet5.next()) {
                        for (int j = 0; j < databaseColumnCount5; j++) {
                            logos.add(resultSet5.getString(j + 1));
                        }
                        info.add(logos);
                    }
                }




            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }
            return info;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<String>> result) {
            TextView homeName = (TextView) findViewById(R.id.homeTeam_Name);
            TextView awayName = (TextView) findViewById(R.id.awayTeam_Name);
            ImageView homeImage = (ImageView) findViewById(R.id.homeTeam_Logo);
            ImageView awayImage = (ImageView) findViewById(R.id.awayTeam_Logo);
            TextView homePoints = (TextView) findViewById(R.id.homePoints);
            TextView awayPoints = (TextView) findViewById(R.id.awayPoints);
            ListView lv = (ListView) findViewById(R.id.homeList);
            ListView lv1 = (ListView) findViewById(R.id.awayList);
            ListView lv2 = findViewById(R.id.list);


            if (!result.isEmpty()) {
                //Filling ListViews
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, result.get(0));
                lv.setAdapter(arrayAdapter);


                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, result.get(1));
                lv1.setAdapter(arrayAdapter1);

                ArrayList<String> a = new ArrayList<String>();
                a.add("FTA");
                a.add("FTM");
                a.add("2PA");
                a.add("2PM");
                a.add("3PA");
                a.add("3PM");
                a.add("BLOCKS");
                a.add("TURNOVERS");
                a.add("FOULS");
                a.add("STEALS");
                a.add("REBOUNDS");
                a.add("ASSISTS");

                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, a);
                lv2.setAdapter(arrayAdapter2);


                homeName.setText(result.get(2).get(0));
                homePoints.setText(result.get(2).get(1));
                awayName.setText(result.get(2).get(2));
                awayPoints.setText(result.get(2).get(3));
                Picasso.with(getApplicationContext()).load(result.get(3).get(0)).into(homeImage);
                Picasso.with(getApplicationContext()).load(result.get(3).get(1)).into(awayImage);
            }

            //Managing Scrolling On ListViews
            lv.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
            lv1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
            lv2.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

            lv.setOnScrollListener(new AbsListView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    // onScroll will be called and there will be an infinite loop.
                    // That's why i set a boolean value
                    if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                        isRightListEnabled = false;
                    } else if (scrollState == SCROLL_STATE_IDLE) {
                        isRightListEnabled = true;
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                     int totalItemCount) {
                    View c = view.getChildAt(0);
                    if (c != null && isLeftListEnabled) {
                        lv1.setSelectionFromTop(firstVisibleItem, c.getTop());
                        lv2.setSelectionFromTop(firstVisibleItem,c.getTop());
                    }
                }
            });

            lv1.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                        isLeftListEnabled = false;
                    } else if (scrollState == SCROLL_STATE_IDLE) {
                        isLeftListEnabled = true;
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                     int totalItemCount) {
                    View c = view.getChildAt(0);
                    if (c != null && isRightListEnabled) {
                        lv.setSelectionFromTop(firstVisibleItem, c.getTop());
                        lv2.setSelectionFromTop(firstVisibleItem,c.getTop());
                    }
                }
            });

            lv2.setOnScrollListener(new AbsListView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    // onScroll will be called and there will be an infinite loop.
                    // That's why i set a boolean value
                    if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                        isRightListEnabled = false;
                    } else if (scrollState == SCROLL_STATE_IDLE) {
                        isRightListEnabled = true;
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                     int totalItemCount) {
                    View c = view.getChildAt(0);
                    if (c != null && isLeftListEnabled) {
                        lv.setSelectionFromTop(firstVisibleItem, c.getTop());
                        lv1.setSelectionFromTop(firstVisibleItem, c.getTop());
                    }
                }
            });
        }
    }
}