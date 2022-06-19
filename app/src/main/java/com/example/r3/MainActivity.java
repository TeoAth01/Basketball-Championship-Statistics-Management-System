package com.example.r3;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new InfoAsyncTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<String, Void, ArrayList<ArrayList<String>>> {
        @Override
        protected ArrayList<ArrayList<String>> doInBackground(String... String) {

            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try (Connection con = DriverManager.getConnection("jdbc:mariadb://192.168.1.102/omada 18", "username", "password")) {

                Statement s = con.createStatement();

                String sql0 ="SELECT Event,Player,MatchID INTO @event,@player,@id FROM livematch";
                String sql1 ="UPDATE players SET FTA=FTA+1 WHERE Name=@player AND @event='FTA'";
                String sql2 ="UPDATE players SET FTM=FTM+1 WHERE Name=@player AND @event='FTM'";
                String sql3 ="UPDATE players SET 2PA=2PA+1 WHERE Name=@player AND @event='2PA'";
                String sql4 ="UPDATE players SET 2PM=2PM+1 WHERE Name=@player AND @event='2PM'";
                String sql5 ="UPDATE players SET 3PA=3PA+1 WHERE Name=@player AND @event='3PA'";
                String sql6 ="UPDATE players SET 3PM=3PM+1 WHERE Name=@player AND @event='3PM'";
                String sql7 = "UPDATE players SET Assists=Assists+1 WHERE Name=@player AND @event = 'ASSIST'";
                String sql8 ="UPDATE players SET Rebounds=Rebounds+1 WHERE Name=@player AND @event = 'REBOUND'";
                String sql9 ="UPDATE players SET Blocks=Blocks+1 WHERE Name=@player AND @event = 'BLOCK'";
                String sql10 ="UPDATE players SET Steals=Steals+1 WHERE Name=@player AND @event = 'STEAL'";
                String sql11="UPDATE players SET Turnovers=Turnovers+1 WHERE Name=@player AND @event = 'STEAL'";
                String sql12="UPDATE players SET Fouls=Fouls+1 WHERE Name=@player AND @event = 'FOUL'";
                String sql13="UPDATE players SET Points=FTM+2PM*2+3PM*3 WHERE Name=@player";

                String sql0i = "SELECT TEAM INTO @team FROM players WHERE Name=@player";
                String sql1i ="UPDATE matches SET HFTA=HFTA+1 WHERE HOME=@team AND @event='FTA' AND @id=id";
                String sql2i ="UPDATE matches SET HFTM=HFTM+1 WHERE HOME=@team AND @event='FTM'AND @id=id";
                String sql3i ="UPDATE matches SET H2PA=H2PA+1 WHERE HOME=@team AND @event='2PA'AND @id=id";
                String sql4i ="UPDATE matches SET H2PM=H2PM+1 WHERE HOME=@team AND @event='2PM'AND @id=id";
                String sql5i ="UPDATE matches SET H3PA=H3PA+1 WHERE HOME=@team AND @event='3PA'AND @id=id";
                String sql6i ="UPDATE matches SET H3PM=H3PM+1 WHERE HOME=@team AND @event='3PM'AND @id=id";
                String sql7i = "UPDATE matches SET HAssists=HAssists+1 WHERE HOME=@team AND @event = 'ASSIST'AND @id=id";
                String sql8i ="UPDATE matches SET HRebounds=HRebounds+1 WHERE HOME=@team AND @event = 'REBOUND'AND @id=id";
                String sql9i ="UPDATE matches SET HBlocks=HBlocks+1 WHERE HOME=@team AND @event = 'BLOCK'AND @id=id";
                String sql10i ="UPDATE matches SET HSteals=HSteals+1 WHERE HOME=@team AND @event = 'STEAL'AND @id=id";
                String sql11i="UPDATE matches SET HTurnovers=HTurnovers+1 WHERE HOME=@team AND @event = 'TURNOVER'AND @id=id";
                String sql12i="UPDATE matches SET HFouls=HFouls+1 WHERE HOME=@team AND @event = 'FOUL'AND @id=id";
                String sql13i="UPDATE matches SET HPoints=HFTM+H2PM*2+H3PM*3 WHERE HOME=@team AND @id=id";

                String sql1ii ="UPDATE matches SET AFTA=AFTA+1 WHERE AWAY=@team AND @event='FTA'AND @id=id";
                String sql2ii ="UPDATE matches SET AFTM=AFTM+1 WHERE AWAY=@team AND @event='FTM'AND @id=id";
                String sql3ii ="UPDATE matches SET A2PA=A2PA+1 WHERE AWAY=@team AND @event='2PA'AND @id=id";
                String sql4ii ="UPDATE matches SET A2PM=A2PM+1 WHERE AWAY=@team AND @event='2PM'AND @id=id";
                String sql5ii ="UPDATE matches SET A3PA=A3PA+1 WHERE AWAY=@team AND @event='3PA'AND @id=id";
                String sql6ii ="UPDATE matches SET A3PM=A3PM+1 WHERE AWAY=@team AND @event='3PM'AND @id=id";
                String sql7ii = "UPDATE matches SET AAssists=AAssists+1 WHERE AWAY=@team AND @event = 'ASSIST'AND @id=id";
                String sql8ii ="UPDATE matches SET ARebounds=ARebounds+1 WHERE AWAY=@team AND @event = 'REBOUND'AND @id=id";
                String sql9ii ="UPDATE matches SET ABlocks=ABlocks+1 WHERE AWAY=@team AND @event = 'BLOCK'AND @id=id";
                String sql10ii ="UPDATE matches SET ASteals=ASteals+1 WHERE AWAY=@team AND @event = 'STEAL'AND @id=id";
                String sql11ii="UPDATE matches SET ATurnovers=ATurnovers+1 WHERE AWAY=@team AND @event = 'TURNOVER'AND @id=id";
                String sql12ii="UPDATE matches SET AFouls=AFouls+1 WHERE AWAY=@team AND @event = 'FOUL'AND @id=id";
                String sql13ii="UPDATE matches SET APoints=AFTM+A2PM*2+A3PM*3 WHERE AWAY=@team  AND @id=id";



                String sql1t ="UPDATE teams SET FTA=FTA+1 WHERE Name=@team AND @event='FTA'";
                String sql2t ="UPDATE teams SET FTM=FTM+1 WHERE Name=@team AND @event='FTM'";
                String sql3t ="UPDATE teams SET 2PA=2PA+1 WHERE Name=@team AND @event='2PA'";
                String sql4t ="UPDATE teams SET 2PM=2PM+1 WHERE Name=@team AND @event='2PM'";
                String sql5t ="UPDATE teams SET 3PA=3PA+1 WHERE Name=@team AND @event='3PA'";
                String sql6t ="UPDATE teams SET 3PM=3PM+1 WHERE Name=@team AND @event='3PM'";
                String sql7t = "UPDATE teams SET Assists=Assists+1 WHERE Name=@team AND @event = 'ASSIST'";
                String sql8t ="UPDATE teams SET Rebounds=Rebounds+1 WHERE Name=@team AND @event = 'REBOUND'";
                String sql9t ="UPDATE teams SET Blocks=Blocks+1 WHERE Name=@team AND @event = 'BLOCK'";
                String sql10t ="UPDATE teams SET Steals=Steals+1 WHERE Name=@team AND @event = 'STEAL'";
                String sql11t="UPDATE teams SET Turnovers=Turnovers+1 WHERE Name=@team AND @event = 'STEAL'";
                String sql12t="UPDATE teams SET Fouls=Fouls+1 WHERE Name=@team AND @event = 'FOUL'";
                String sql13t="UPDATE teams SET Points=FTM+2PM*2+3PM*3 WHERE Name=@team";
                String sql14t="SELECT HOME,AWAY INTO @home,@away FROM matches WHERE State='COMPLETED' AND id=@id AND HPoints>APoints";
                String sql15t="UPDATE teams SET Wins=Wins+1, Standings=Standings+2 WHERE Name=@home";
                String sql16t="UPDATE teams SET Loses=Loses+1 WHERE Name=@away";
                String sql14t1="SELECT HOME,AWAY INTO @home1,@away1 FROM matches WHERE State='COMPLETED' AND id=@id AND HPoints<APoints";
                String sql15t1="UPDATE teams SET Wins=Wins+1, Standings=Standings+2 WHERE Name=@away1";
                String sql16t1="UPDATE teams SET Loses=Loses+1 WHERE Name=@home1";

                String sqlDEL = "DELETE FROM livematch LIMIT 1";


                s.addBatch(sql0);
                s.addBatch(sql1);
                s.addBatch(sql2);
                s.addBatch(sql3);
                s.addBatch(sql4);
                s.addBatch(sql5);
                s.addBatch(sql6);
                s.addBatch(sql7);
                s.addBatch(sql8);
                s.addBatch(sql9);
                s.addBatch(sql10);
                s.addBatch(sql11);
                s.addBatch(sql12);
                s.addBatch(sql13);

                s.addBatch(sql0i);
                s.addBatch(sql1i);
                s.addBatch(sql2i);
                s.addBatch(sql3i);
                s.addBatch(sql4i);
                s.addBatch(sql5i);
                s.addBatch(sql6i);
                s.addBatch(sql7i);
                s.addBatch(sql8i);
                s.addBatch(sql9i);
                s.addBatch(sql10i);
                s.addBatch(sql11i);
                s.addBatch(sql12i);
                s.addBatch(sql13i);

                s.addBatch(sql1ii);
                s.addBatch(sql2ii);
                s.addBatch(sql3ii);
                s.addBatch(sql4ii);
                s.addBatch(sql5ii);
                s.addBatch(sql6ii);
                s.addBatch(sql7ii);
                s.addBatch(sql8ii);
                s.addBatch(sql9ii);
                s.addBatch(sql10ii);
                s.addBatch(sql11ii);
                s.addBatch(sql12ii);
                s.addBatch(sql13ii);

                s.addBatch(sql1t);
                s.addBatch(sql2t);
                s.addBatch(sql3t);
                s.addBatch(sql4t);
                s.addBatch(sql5t);
                s.addBatch(sql6t);
                s.addBatch(sql7t);
                s.addBatch(sql8t);
                s.addBatch(sql9t);
                s.addBatch(sql10t);
                s.addBatch(sql11t);
                s.addBatch(sql12t);
                s.addBatch(sql13t);
                s.addBatch(sql14t);
                s.addBatch(sql15t);
                s.addBatch(sql16t);
                s.addBatch(sql14t1);
                s.addBatch(sql15t1);
                s.addBatch(sql16t1);

                s.addBatch(sqlDEL);

                s.executeBatch();
                con.commit();

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading Information from database", e);
            }
            return null;
        }
    }
}
