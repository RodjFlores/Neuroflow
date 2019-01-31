package com.example.nflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public ArrayList<Info> infoList = new ArrayList<Info>();
    public Context mContext;

    public RecyclerViewAdapter(ArrayList<Info> infoList, Context mContext) {
        this.infoList = infoList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBind: Called");

        holder.tvName.setText(infoList.get(position).name);
        holder.tvScore.setText(infoList.get(position).score + "%");
        holder.tvDate.setText(infoList.get(position).date);

        holder.parentLayout.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG,"Clicked: " + infoList.get(position));
                Toast.makeText(mContext,infoList.get(position).name +" "+ infoList.get(position).score + infoList.get(position).date ,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvScore;
        TextView tvDate;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.Name);
            tvScore = itemView.findViewById(R.id.Score);
            tvDate = itemView.findViewById(R.id.Date);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
