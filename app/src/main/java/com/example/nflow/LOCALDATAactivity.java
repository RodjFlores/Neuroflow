package com.example.nflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class LOCALDATAactivity extends AppCompatActivity {

    public ArrayList<Info> maleData = new ArrayList<>();
    public ArrayList<Info> femaleData = new ArrayList<>();

    public ArrayList<Info> maleInfoList = new ArrayList<>();
    public ArrayList<Info> femaleInfoList = new ArrayList<>();

    public HashMap<String,ArrayList<Info>> localDataset = new HashMap<>();
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        setLocalData();
    }

    public void setLocalData(){
        maleData.add(new Info("Ryan","63", "1546341851000"));
        maleData.add(new Info("Sam","86", "1536442851000"));
        maleData.add(new Info("Joey","78", "1546442992000"));

        femaleData.add(new Info("Melissa","91", "1540341851000"));
        femaleData.add(new Info("Jess","93", "1540341751000"));
        femaleData.add(new Info("Carly","89", "1540341651000"));

        setLocalData2();
    }

    public void setLocalData2(){  // Sets all the data in a single list as required

        localDataset.put("male", maleData);
        localDataset.put("female", femaleData);

        useLocalData();
    }

    public void useLocalData(){
        for(String key:localDataset.keySet()){
            int count = localDataset.get(key).size();
            Log.d(TAG,"------------");

            Log.d(TAG,key);

            if(key.matches("male")){
                for(int x = 0; x <= count - 1; x++){
                    String name = localDataset.get(key).get(x).name;
                    String score = localDataset.get(key).get(x).score;
                    Long date = localDataset.get(key).get(x).dateLong;

                    maleInfoList.add(new Info(name,score, Long.toString(date)));
                    Log.d(TAG,name + " " + score + " " + date);

                }
            }else {
                for(int x = 0; x <= count - 1; x++){
                    String name = localDataset.get(key).get(x).name;
                    String score = localDataset.get(key).get(x).score;
                    Long date = localDataset.get(key).get(x).dateLong;
                    Log.d(TAG,name + " " + score + " " + date);

                    femaleInfoList.add(new Info(name,score, Long.toString(date)));

                }
            }
        }
        Collections.sort(maleInfoList);
        Collections.sort(femaleInfoList);

        initRecyclerView(maleInfoList);
        initRecyclerView2(femaleInfoList);
    }

    public void backButton(View view){
        this.finish();
    }

    private void initRecyclerView(ArrayList<Info> info){

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(info,this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initRecyclerView2(ArrayList<Info> info){
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view2);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(info,this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
