package com.example.nflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    public ArrayList<Info> mInfo = new ArrayList<>(); //first story data
    public ArrayList<Info> mInfo2 = new ArrayList<>();// 2nd


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setLocalDataset2(); //2nd story

        long unixTime = System.currentTimeMillis();
        Info p1 = new Info("Rod Main Screen","99", Long.toString(unixTime));
        mInfo.add(p1);
        initRecyclerView(mInfo);

        Log.d(TAG,mInfo.get(0).toString());

    }

    //For the random button screen--------------------------------------
    //------------------------------------------------------------------

    public void rButton(View view){
        Bundle b = new Bundle();
        ArrayList<Info> bundleList = randomInfo();

        b.putString("name",bundleList.get(0).name);
        b.putString("date",bundleList.get(0).date);
        b.putString("score",bundleList.get(0).score);

        Intent myIntent = new Intent(view.getContext(), ScoreScreenActivity.class);
        myIntent.putExtras(b);
        startActivityForResult(myIntent, 0);
    }

    private ArrayList<Info> randomInfo(){
        Log.d(TAG,"------------");
        Log.d(TAG,"------CLICKED------");
        Log.d(TAG,"------------");


        double roll = Math.random() * mInfo2.size();
        int randomIndex = (int) roll;

        ArrayList<Info> newList = new ArrayList<>();
        newList.add(mInfo2.get(randomIndex));

        Log.d(TAG,newList.get(0).name);
        Log.d(TAG,newList.get(0).date);
        Log.d(TAG,newList.get(0).score);

        return newList;
    }

    // Third Story Local Dataset Setup------------------------------------------------------------------
    //------------------------------------------------------------------

    public void buttonForLocal(View view){
        Intent myIntent = new Intent(view.getContext(), LOCALDATAactivity.class);
        startActivityForResult(myIntent, 0);
    }

    // Fourth Story URL set------------------------------------------------------------------
    //------------------------------------------------------------------

    public void buttonForJSON(View view){
        Intent myIntent = new Intent(view.getContext(), JSONactivity.class);
        startActivityForResult(myIntent, 0);
    }


    //------------------------------------------------------------------
    //------------------------------------------------------------------

    private void setLocalDataset2(){ //this sets the dataset for the 2nd story
        mInfo2.add(new Info("Tory","100", "1536341851000"));
        mInfo2.add(new Info("Ryan","90", "1537777851000"));
        mInfo2.add(new Info("Andres","80", "1999341851000"));
    }
    private void initRecyclerView(ArrayList<Info> info){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(info,this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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


