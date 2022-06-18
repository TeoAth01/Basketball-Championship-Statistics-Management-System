package com.example.frontend_1;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Players extends AppCompatActivity {
    private static final String URL = "jdbc:mariadb://192.168.1.3:3306/omada18";
    private static final String USER = "root5";
    private static final String PASSWORD ="";

    String HomeString, AwayString;
    private Button change;

    AutoCompleteTextView autoCompleteHBPl, autoCompleteHCPl, autoCompleteABPl, autoCompleteACPl;
    TextView HomeTeam, AwayTeam;
    ImageView homelogo, awaylogo;


    ArrayAdapter<String> Hbp, Hcp, Abp, Acp;
    ArrayList<String> HbasicTeam = new ArrayList<>();
    ArrayList<String> HChanges = new ArrayList<>();
    ArrayList<String> AbasicTeam = new ArrayList<>();
    ArrayList<String> AChanges = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        getSupportActionBar().setTitle("Παίκτες");

        autoCompleteHBPl = findViewById(R.id.auto_complete_txt_HBPl);
        autoCompleteHCPl = findViewById(R.id.auto_complete_txt_HCPl);
        autoCompleteABPl = findViewById(R.id.auto_complete_txt_ABPl);
        autoCompleteACPl = findViewById(R.id.auto_complete_txt_ACPl);

        HomeTeam = findViewById(R.id.HomeText);
        AwayTeam = findViewById(R.id.AwayText);


        HomeString = (String) getIntent().getExtras().get("HomeString");
        AwayString = (String) getIntent().getExtras().get("AwayString");
        HomeTeam.setText(HomeString);
        AwayTeam.setText(AwayString);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));


        new BasicHAsyncTask().execute();
        new ChangesHAsyncTask().execute();
        new BasicAAsyncTask().execute();
        new ChangesAAsyncTask().execute();


        Hbp = new ArrayAdapter(this, R.layout.list_item, HbasicTeam);
        Hcp = new ArrayAdapter(this, R.layout.list_item, HChanges);
        Abp = new ArrayAdapter(this, R.layout.list_item, AbasicTeam);
        Acp = new ArrayAdapter(this, R.layout.list_item, AChanges);


        autoCompleteHBPl.setAdapter(Hbp);
        autoCompleteHCPl.setAdapter(Hcp);
        autoCompleteABPl.setAdapter(Abp);
        autoCompleteACPl.setAdapter(Acp);

        change = findViewById(R.id.Change_Button);

        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String Hbasicplayer = autoCompleteHBPl.getText().toString();
                String Hchangeplayer = autoCompleteHCPl.getText().toString();
                String Abasicplayer = autoCompleteABPl.getText().toString();
                String Achangeplayer = autoCompleteACPl.getText().toString();


                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    String sql = "UPDATE players SET Is5='0' WHERE Name='"+Hbasicplayer+"'";
                    String sql1 = "UPDATE players SET Is5='1' WHERE Name='"+Hchangeplayer+"'";
                    String sql2 = "UPDATE players SET Is5='0' WHERE Name='"+Abasicplayer+"'";
                    String sql3 = "UPDATE players SET Is5='1' WHERE Name='"+Achangeplayer+"'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    PreparedStatement statement1 = connection.prepareStatement(sql1);
                    PreparedStatement statement2 = connection.prepareStatement(sql2);
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    ResultSet resultSet = statement.executeQuery();
                    ResultSet resultSet1 = statement1.executeQuery();
                    ResultSet resultSet2 = statement2.executeQuery();
                    ResultSet resultSet3 = statement3.executeQuery();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                HbasicTeam.clear();
                HChanges.clear();
                AbasicTeam.clear();
                AChanges.clear();

                new BasicHAsyncTask().execute();
                new ChangesHAsyncTask().execute();
                new BasicAAsyncTask().execute();
                new ChangesAAsyncTask().execute();

                Toast.makeText(Players.this, "Επιτυχής αλλαγή", Toast.LENGTH_SHORT).show();
            }

        });



        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT Logo FROM teams  WHERE Name='"+HomeString+"'";
            String sql1 = "SELECT Logo FROM teams  WHERE Name='"+AwayString+"'";
            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            ResultSet resultSet = statement.executeQuery();
            ResultSet resultSet1 = statement1.executeQuery();

            if (resultSet.next()) {
                String image = resultSet.getString("Logo");
                homelogo = findViewById(R.id.H_Logo);
                Picasso.with(getApplicationContext()).load(image).into(homelogo);

            }
            if (resultSet1.next()) {
                String image1 = resultSet1.getString("Logo");
                awaylogo = findViewById(R.id.A_Logo);
                Picasso.with(getApplicationContext()).load(image1).into(awaylogo);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @SuppressLint("StaticFieldLeak")
    public class BasicHAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT Name, Team, Is5 FROM players  WHERE Is5='1'and Team='"+HomeString+"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HbasicTeam.add(resultSet.getString("Name"));
                }

                resultSet.close();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading omada18 information", e);
            }

            return HbasicTeam;

        }

    }

    @SuppressLint("StaticFieldLeak")
    public class ChangesHAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

                String sql = "SELECT Name, Team, Is5 FROM players  WHERE Is5='0' AND Team='"+HomeString+"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    HChanges.add(resultSet.getString("Name"));
                }

                resultSet.close();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading omada18 information", e);
            }

            return HChanges;

        }

    }

    @SuppressLint("StaticFieldLeak")
    public class BasicAAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT Name, Team, Is5 FROM players  WHERE Is5='1'and Team='"+AwayString+"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    AbasicTeam.add(resultSet.getString("Name"));
                }

                resultSet.close();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading omada18 information", e);
            }

            return AbasicTeam;

        }

    }

    @SuppressLint("StaticFieldLeak")
    public class ChangesAAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

                String sql = "SELECT Name, Team, Is5 FROM players  WHERE Is5='0' AND Team='"+AwayString+"'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AChanges.add(resultSet.getString("Name"));
                }

                resultSet.close();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading omada18 information", e);
            }

            return AChanges;

        }

    }


}