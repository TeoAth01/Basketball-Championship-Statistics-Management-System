package com.example.tutorialspoint7.r4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        new InfoAsyncTask().execute();
        new InfoAsyncTask1().execute();
        new InfoAsyncTask2().execute();
        new InfoAsyncTask3().execute();
        new InfoAsyncTask4().execute();

    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... String) {
            TextView txt = findViewById(R.id.hTeam_Name);
            String home = txt.getText().toString();
            TextView txt2 = findViewById(R.id.aTeam_Name);
            String away = txt2.getText().toString();

            ArrayList<String> info = new ArrayList<String>();

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.1.3/omada18", "root5", "")) {

                //Getting Minute From database
                String sql = "SELECT Minute FROM livematch WHERE Team='" + home + "'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    info.add(resultSet.getString("Minute"));
                }
                resultSet.close();

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading Match Information", e);
            }
            return info;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            ListView lv = findViewById(R.id.minute);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_list, result);
            lv.setAdapter(arrayAdapter);

            lv.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask1 extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... String) {
            TextView txt = findViewById(R.id.hTeam_Name);
            String home = txt.getText().toString();
            TextView txt2 = findViewById(R.id.aTeam_Name);
            String away = txt2.getText().toString();

            ArrayList<String> info1 = new ArrayList<String>();

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.1.3/omada18", "root5", "")) {

                //Getting Event,Player From database
                String sql1 = "SELECT Event,Player FROM livematch WHERE Team='" + home + "'";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                ResultSet resultSet1 = statement1.executeQuery();
                while (resultSet1.next()) {
                    info1.add(resultSet1.getString("Event") + " " + resultSet1.getString("Player"));
                }
                resultSet1.close();

            } catch (Exception e) {
                Log.e("InfoAsyncTask1", "Error reading Match Information", e);
            }
            return info1;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            ListView lv1 = findViewById(R.id.event_player);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_list, result);
            lv1.setAdapter(arrayAdapter);

            lv1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask2 extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... String) {
            TextView txt = findViewById(R.id.hTeam_Name);
            String home = txt.getText().toString();
            TextView txt2 = findViewById(R.id.aTeam_Name);
            String away = txt2.getText().toString();

            ArrayList<String> info2 = new ArrayList<String>();

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.1.3/omada18", "root5", "")) {

                //Getting Event,Player From database
                String sql1 = "SELECT Event,Player FROM livematch WHERE Team='" + away + "'";
                PreparedStatement statement2 = connection.prepareStatement(sql1);
                ResultSet resultSet2 = statement2.executeQuery();
                while (resultSet2.next()) {
                    info2.add(resultSet2.getString("Event") + " " + resultSet2.getString("Player"));
                }
                resultSet2.close();

            } catch (Exception e) {
                Log.e("InfoAsyncTask1", "Error reading Match Information", e);
            }
            return info2;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            ListView lv2 = findViewById(R.id.event_player1);


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.my_list, result);
            lv2.setAdapter(arrayAdapter);

            lv2.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        }
    }


    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask3 extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... String) {
            TextView txt = findViewById(R.id.hTeam_Name);
            String home = txt.getText().toString();
            TextView txt2 = findViewById(R.id.aTeam_Name);
            String away = txt2.getText().toString();

            ArrayList<String> info3 = new ArrayList<String>();

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.1.3/omada18", "root5", "")) {

                //Getting Event,Player From database
                String sql3 = "SELECT HPoints,APoints FROM matches WHERE HOME='" + home + "' AND AWAY='" + away + "'";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                ResultSet resultSet3 = statement3.executeQuery();
                if (resultSet3.next()) {
                    info3.add(resultSet3.getString("HPoints"));
                    info3.add(resultSet3.getString("APoints"));
                }
                //resultSet3.close();

            } catch (Exception e) {
                Log.e("InfoAsyncTask3", "Error reading Match Information", e);
            }
            return info3;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            TextView txt3 = findViewById(R.id.hPoints);
            TextView txt4 = findViewById(R.id.aPoints);
            for (int i = 0; i < result.size(); i++) {
                txt3.setText(result.get(0));
                txt4.setText(result.get(1));
            }

        }
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask4 extends AsyncTask<String, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... String) {
            TextView txt = findViewById(R.id.hTeam_Name);
            String home = txt.getText().toString();
            TextView txt2 = findViewById(R.id.aTeam_Name);
            String away = txt2.getText().toString();

            ArrayList<String> logo = new ArrayList<String>();

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.1.3/omada18", "root5", "")) {

                //Getting Event,Player From database
                String sql3 = "SELECT Logo FROM teams WHERE Name='"+home+"'";
                PreparedStatement statement3 = connection.prepareStatement(sql3);
                ResultSet resultSet3 = statement3.executeQuery();
                String sql4 = "SELECT Logo FROM teams WHERE Name='"+away+"'";
                PreparedStatement statement4 = connection.prepareStatement(sql4);
                ResultSet resultSet4 = statement4.executeQuery();
                if (resultSet3.next()) {
                    logo.add(resultSet3.getString("Logo"));
                }
                if (resultSet4.next()) {
                    logo.add(resultSet4.getString("Logo"));
                }

            } catch (Exception e) {
                Log.e("InfoAsyncTask3", "Error reading Match Information", e);
            }
            return logo;
        }
        @Override
        protected void onPostExecute(ArrayList<String> result) {
            ImageView homeImage = (ImageView) findViewById(R.id.hTeam_Logo);
            ImageView awayImage = (ImageView) findViewById(R.id.aTeam_Logo);


            Picasso.with(getApplicationContext()).load(result.get(0)).into(homeImage);
            Picasso.with(getApplicationContext()).load(result.get(1)).into(awayImage);


            }

        }
}