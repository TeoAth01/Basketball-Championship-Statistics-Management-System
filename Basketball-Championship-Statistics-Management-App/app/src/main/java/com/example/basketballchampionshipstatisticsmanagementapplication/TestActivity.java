package com.example.basketballchampionshipstatisticsmanagementapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.mariadb.jdbc.internal.com.read.resultset.SelectResultSet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private static final String URL = "jdbc:mariadb://192.168.1.11/omada 18";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_team_statistics);

        Resources resource = this.getResources();
        TableLayout tableLayout =  findViewById(R.id.tableLayout);
        tableLayout.setBackgroundColor(resource.getColor(R.color.orange));
        TableLayout buttonTable = findViewById(R.id.tableLayout2);
        buttonTable.setBackgroundColor(resource.getColor(R.color.orange));
        context = this;

        new InfoAsyncTask().execute();
    }

    public class InfoAsyncTask extends AsyncTask<String, Void, ArrayList<ArrayList<String>>> {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        protected ArrayList<ArrayList<String>> doInBackground(String... String) {
            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM teams";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                int databaseColumnCount = resultSet.getMetaData().getColumnCount(); //how many tags
                int databaseRowCount = ((SelectResultSet) resultSet).getDataSize(); //how many teams

                if (resultSet.next()) {
                    info.add(new ArrayList<String>());
                    for (int j = 0; j < databaseColumnCount; j++) {
                        info.get(0).add(resultSet.getMetaData().getColumnName(j+1));
                    }
                    for (int i = 0; i<databaseRowCount; i++) {
                        info.add(new ArrayList<String>());
                        for (int j = 0; j < databaseColumnCount; j++) {
                                info.get(i+1).add(resultSet.getString(j+1));
                        }
                        resultSet.next();
                    }

                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading information", e);
            }
            return info;
        }
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        protected void onPostExecute(ArrayList<ArrayList<String>> result) {
            Resources resource = context.getResources();
            if (!result.isEmpty()) {
                int cols = result.get(0).size();
                for(int i=0; i<result.size(); i++) {
                    TableRow tempRow = new TableRow(context);
                    tempRow.setPadding(0,5,0,5);
                    TextView forBorderNext = new TextView(context);
                    forBorderNext.setPadding(5,5,5,5);
                    tempRow.addView(forBorderNext);
                    for(int j=0; j<cols; j++) {
                        if (j!=2) {
                            TextView tempView = new TextView(context);
                            tempView.setText(result.get(i).get(j));
                            tempView.setPadding(40, 40, 40, 40);
                            tempView.setTextColor(resource.getColor(R.color.white));
                            tempView.setBackgroundColor(resource.getColor(R.color.gray));
                            tempView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            tempRow.addView(tempView);

                            TextView forBorder = new TextView(context);
                            forBorder.setPadding(5,5,5,5);
                            tempRow.addView(forBorder);
                            }
                    }
                    TableLayout tableLayout =  findViewById(R.id.tableLayout);
                    tableLayout.addView(tempRow);
                }
            }
        }
    }
}
