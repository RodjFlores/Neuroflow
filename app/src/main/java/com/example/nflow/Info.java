package com.example.nflow;


import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import java.util.Date;

public class Info extends MainActivity {
    String name;
    String score;
    String date;


    Info(String n, int s, long d){
        this.name = n;
        this.score = Integer.toString(s);

        DateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date result = new Date(d);

        this.date = simple.format(result);
    }
}
