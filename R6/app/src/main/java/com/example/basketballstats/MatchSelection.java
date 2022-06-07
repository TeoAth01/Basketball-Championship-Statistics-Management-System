package com.example.basketballstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class MatchSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_selection);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TextView home = (TextView) findViewById(R.id.homeTeam);
        TextView away = (TextView) findViewById(R.id.awayTeam);
        Button button = (Button) findViewById(R.id.b);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = home.getText().toString();
                String str2 = away.getText().toString();
                Intent intent = new Intent(getApplicationContext(), FinishedMatchStats.class);
                intent.putExtra("String 1", str1);
                intent.putExtra("String 2", str2);
                startActivity(intent);
            }
        });

    }
}