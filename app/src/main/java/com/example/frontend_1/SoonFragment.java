package com.example.frontend_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.fragment.app.ListFragment;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SoonFragment extends ListFragment {

    private static final String URL = "jdbc:mariadb://192.168.1.3:3306/omada18";
    private static final String USER = "root5";
    private static final String PASSWORD = "";

    ArrayList<String> soonmatch = new ArrayList<>();
    ArrayAdapter sm;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new SoonMatchesAsyncTask().execute();


        sm = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, soonmatch){
            @Override

            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);

                TextView textView= view.findViewById(android.R.id.text1);

                textView.setTextColor(Color.WHITE);

                return view;
            }
        };


        setListAdapter(sm);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String HomeAway = getListAdapter().getItem(position).toString();
        String[] separate =  HomeAway.split("\n");
        Intent intent = new Intent(SoonFragment.this.getActivity(),Players.class);
        intent.putExtra("HomeString",separate[0]);
        intent.putExtra("AwayString",separate[1]);
        startActivity(intent);

    }

    @SuppressLint("StaticFieldLeak")
    public class SoonMatchesAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {


            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT HOME, AWAY, HPoints, APoints FROM matches  WHERE State='SOON'";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    soonmatch.add(resultSet.getString("HOME") + "\n" + resultSet.getString("AWAY"));
                }

                resultSet.close();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading omada18 information", e);
            }

            return soonmatch;

        }

    }


}