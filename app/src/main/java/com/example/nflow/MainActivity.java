package com.example.nflow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    public ArrayList<Info> mInfo = new ArrayList<>();
    public ArrayList<Info> mInfo2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initInfo(); //local data set

        long unixTime = System.currentTimeMillis();
        Info p1 = new Info("Rod Main Screen",99, unixTime);
        mInfo2.add(p1);
        initRecyclerView(mInfo2);

        Log.d(TAG,mInfo2.get(0).toString());



    }



    //For the random button screen

    private void randomInfo(){
        double roll = Math.random() * mInfo.size();
        int randomIndex = (int) roll;


        ArrayList<Info> newList = new ArrayList<>();
        newList.add(mInfo.get(randomIndex));
        initRecyclerView(newList);

    }
    private void initInfo(){
        mInfo.add(new Info("Tory",100, 1536341851000L));
        mInfo.add(new Info("Ryan",90, 1537777851000L));
        mInfo.add(new Info("Andres",80, 1999341851000L));
        //initRecyclerView();
    }
    private void initRecyclerView(ArrayList<Info> info){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(info,this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void randomButton(View view){
        randomInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}


