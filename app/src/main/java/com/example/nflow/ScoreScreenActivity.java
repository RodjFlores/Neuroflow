package com.example.nflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreScreenActivity extends AppCompatActivity {

    public TextView tvName,tvScore,tvDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        setText();
    }

    public void setText(){
        Bundle b = getIntent().getExtras();


        tvName = findViewById(R.id.ssName);
        tvDate = findViewById(R.id.ssDate);
        tvScore = findViewById(R.id.ssScore);

        tvName.setText(b.getString("name"));
        tvScore.setText(b.getString("score") + "%");
        tvDate.setText(b.getString("date"));
        b.clear();
    }

    public void backButton(View view){
        //Intent myIntent = new Intent(view.getContext(), MainActivity.class);
        //startActivityForResult(myIntent, 0);
        this.finish();
    }
}
