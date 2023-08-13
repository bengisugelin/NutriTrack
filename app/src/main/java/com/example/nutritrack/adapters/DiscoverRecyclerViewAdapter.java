package com.example.nutritrack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritrack.R;
import com.example.nutritrack.models.RecipeModel;

import java.util.ArrayList;

public class DiscoverRecyclerViewAdapter extends RecyclerView.Adapter<DiscoverRecyclerViewAdapter.MyViewHolder>{

    Context context;

    ArrayList<RecipeModel> recipeModelList;

    public DiscoverRecyclerViewAdapter(Context context, ArrayList<RecipeModel> recipeModelList) {
        this.context = context;
        this.recipeModelList = recipeModelList;
    }

    @NonNull
    @Override
    public DiscoverRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.discover_recyclerview_content,parent,false);
        return new DiscoverRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.discoverAdapter_recipetitle.setText(recipeModelList.get(position).getTitle());
        holder.discoverAdapter_recipeservings.setText(recipeModelList.get(position).getServings());
        holder.discoverAdapter_recipeingredients.setText(recipeModelList.get(position).getIngredients());
        holder.discoverAdapter_recipeinstructions.setText(recipeModelList.get(position).getInstructions());
    }

    @Override
    public int getItemCount() {
        return recipeModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView discoverAdapter_recipetitle, discoverAdapter_recipeservings,discoverAdapter_recipeingredients,discoverAdapter_recipeinstructions;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            discoverAdapter_recipetitle = itemView.findViewById(R.id.discover_recipeTitle);
            discoverAdapter_recipeservings = itemView.findViewById(R.id.discover_recipeServings );
            discoverAdapter_recipeingredients = itemView.findViewById(R.id.discover_recipeIngredients);
            discoverAdapter_recipeinstructions = itemView.findViewById(R.id.discover_recipeinstructions);


        }
    }
}
