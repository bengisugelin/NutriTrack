package com.example.nutritrack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritrack.R;
import com.example.nutritrack.models.logModel;

import java.util.ArrayList;

public class LogRecyclerViewAdapter extends RecyclerView.Adapter<LogRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<logModel> logmodellist;
    private final LogRecyclerViewInterface recyclerViewInterface;

    public LogRecyclerViewAdapter(Context context, ArrayList<logModel> logmodellist, LogRecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.logmodellist = logmodellist;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public LogRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.log_recyclerview_content,parent,false);
        return new LogRecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull LogRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.foodname.setText(logmodellist.get(position).getFoodName());
        holder.foodcalorie.setText(logmodellist.get(position).getFoodCalorie().toString());
    }

    @Override
    public int getItemCount() {
        return logmodellist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView foodname, foodcalorie;
        ImageView delete;
        public MyViewHolder(@NonNull View itemView,LogRecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            foodname = itemView.findViewById(R.id.log_txt_foodName);
            foodcalorie=itemView.findViewById(R.id.log_txt_foodCalorie);
            delete = itemView.findViewById(R.id.log_delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){

                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
