package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHandler extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //may change

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    String url;

                    //Teams creation
                        url = "http://192.168.1.5/Android/getdata.php";
                        OkHttpClient client = new OkHttpClient().newBuilder().build();
                        MediaType mediaType = MediaType.parse("application/json");
                        RequestBody body = RequestBody.create(mediaType, "");
                        Request request = new Request.Builder()
                                .url(url)
                                .method("GET", null)
                                .addHeader("Content-Type", "application/json")
                                .build();

                        Response response = client.newCall(request).execute();
                        String data = response.body().string();

                        JSONObject jsonObject = new JSONObject(data);

                        String name = jsonObject.getString("Name");
                        int standings = jsonObject.getInt("Standings");
                        int wins = jsonObject.getInt("Wins");
                        int loses = jsonObject.getInt("Loses");

                        Team team = new Team(name, standings, wins, loses);

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                //   } catch (IOException e;) {
                //      e.printStackTrace();
                //    } catch (
                //    JSONException e;) {
                //       e.printStackTrace();
            }
        }
        );
        // try {
        // Thread thread = null;
        // thread.start();
        //  thread.join();
        // } catch (InterruptedException e) {
        //       e.printStackTrace();
        }
    }
// }
