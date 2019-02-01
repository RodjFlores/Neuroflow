package com.example.nflow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JSONactivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    String url = "https://gist.githubusercontent.com/ryanneuroflow/370d19311602c091928300edd7a40f66/raw/1865ae6004142553d8a6c6ba79ccb511028a2cba/names.json";
    ProgressDialog dialog;

    public ArrayList<Info> maleInfoList = new ArrayList<>();
    public ArrayList<Info> femaleInfoList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                Log.d(TAG,string);

                parseJsonData(string);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(JSONactivity.this);
        rQueue.add(request);
        //sortInfoListByDate(maleInfoList);

    }

    public void parseJsonData(String jsonString) {
        try {
            Log.d(TAG,"------Try------");

            JSONArray object = new JSONArray(jsonString);
            Log.d(TAG,"------------");
            Log.d(TAG,object.getString(0));// logs the JSON Male data
            Log.d(TAG,object.getString(1));// logs females
            Log.d(TAG,"------------");

            JSONObject maleArrayString = new JSONObject(object.getString(0));
            JSONObject femaleArrayString = new JSONObject(object.getString(1));

            JSONArray maleArray = maleArrayString.getJSONArray("males");
            JSONArray femaleArray = femaleArrayString.getJSONArray("females");

            Log.d(TAG,"------------");
            Log.d(TAG,"-----FOR LOOP-------");


            for(int x = 0; x <= maleArray.length() - 1; x++){
                JSONObject finalMaleData = new JSONObject(maleArray.get(x).toString());

                String name = finalMaleData.get("name").toString();
                String score = finalMaleData.get("score").toString();
                String date = finalMaleData.get("date_created").toString();

                maleInfoList.add(new Info(name,score, date));
                Log.d(TAG,name + " " + score + " " + date);
                sortInfoListByDate(maleInfoList);
                if(x == 2){
                    initRecyclerView(maleInfoList);
                }
            }


            for(int x = 0; x <= femaleArray.length() - 1; x++){
                JSONObject finalFemaleData = new JSONObject(femaleArray.get(x).toString());

                String name = finalFemaleData.get("name").toString();
                String score = finalFemaleData.get("score").toString();
                String date = finalFemaleData.get("date_created").toString();

                femaleInfoList.add(new Info(name,score, date));
                Log.d(TAG,name + " " + score + " " + date);
                sortInfoListByDate(femaleInfoList);
                if(x==2){
                    initRecyclerView2(femaleInfoList);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG,"------FAIL------");

        }
        dialog.dismiss();

    }

    public void sortInfoListByDate(ArrayList<Info> infoList){
        Collections.sort(infoList);

        if(infoList.size() == 3){
            Log.d(TAG,infoList.get(0).date + " " + infoList.get(1).date + " " + infoList.get(2).date + " ");

        }
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


    public void backButton(View view){
        Intent myIntent = new Intent(view.getContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
    }

    public void test(View view){
        Collections.sort(maleInfoList);
        Log.d(TAG,maleInfoList.get(0).date + " " + maleInfoList.get(1).date + " " + maleInfoList.get(2).date + " ");

    }
}
