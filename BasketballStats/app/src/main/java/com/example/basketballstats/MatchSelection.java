package com.example.basketballstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MatchSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_selection);

        TextView home = (TextView) findViewById(R.id.homeTeam);
        TextView away = (TextView) findViewById(R.id.awayTeam);
        BottomAppBar button = (BottomAppBar)  findViewById(R.id.matchButton);
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