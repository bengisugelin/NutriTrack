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
import com.example.nutritrack.models.ConsumptionModel;

import java.util.ArrayList;

public class C_RecyclerViewAdapter extends RecyclerView.Adapter<C_RecyclerViewAdapter.MyViewHolder> {

    private final C_RecyclerViewInterface c_recyclerViewInterface;
    Context context;
    ArrayList<ConsumptionModel> consumptionModels;

    public C_RecyclerViewAdapter(Context context, ArrayList<ConsumptionModel> consumptionModels , C_RecyclerViewInterface c_recyclerViewInterface){

        this.context = context;
        this.consumptionModels = consumptionModels;
        this.c_recyclerViewInterface=c_recyclerViewInterface;
    }
    @NonNull
    @Override
    public C_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyler_view_consumption_content, parent, false);
        return new C_RecyclerViewAdapter.MyViewHolder(view, c_recyclerViewInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull C_RecyclerViewAdapter.MyViewHolder holder, int position) {

        //binding process, assigning values
        holder.tvConsumptionName.setText(consumptionModels.get(position).getConsumptionName());
        holder.imageView.setImageResource(consumptionModels.get(position).getImage());
    }
    @Override
    public int getItemCount() {

        return consumptionModels.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvConsumptionName;
        public MyViewHolder(@NonNull View itemView, C_RecyclerViewInterface c_recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tvConsumptionName = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(c_recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            c_recyclerViewInterface.onItemClicked(pos);
                        }
                    }
                }
            });
        }
    }


}
