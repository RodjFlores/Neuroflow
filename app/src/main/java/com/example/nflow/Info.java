package com.example.nflow;


import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import java.util.Date;

public class Info extends MainActivity implements Comparable<Info> {
    String name;
    String score;
    String date;
    Long dateLong;


    Info(String n, String s, String d){
        this.name = n;
        this.score = s;

        DateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date result = new Date(Long.parseLong(d));
        this.dateLong = Long.parseLong(d);

        this.date = simple.format(result);
    }
    public Long getDateTime(){
        //Date result = new Date(Long.parseLong(date));
        return dateLong;
    }
    public int compareTo(Info i) {
        if(getDateTime() == null || i.getDateTime() == null){
            return 0;
        }
        return getDateTime().compareTo(i.getDateTime());
    }
}
