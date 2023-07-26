package com.example.nutritrack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritrack.R;
import com.example.nutritrack.models.ExerciseModel;

import java.util.ArrayList;

public class ExerciseRecyclerviewAdapter extends RecyclerView.Adapter<ExerciseRecyclerviewAdapter.ViewHolder> {

    private ArrayList<ExerciseModel> exerciseList;
    private Context context;
    @NonNull
    @Override
    public ExerciseRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_exercise, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerviewAdapter.ViewHolder holder, int position) {

        ExerciseModel exerciseModel = exerciseList.get(position);
        holder.exerciseCalorie.setText(exerciseModel.getCalorieBurnedHourly());
        holder.exerciseName.setText(exerciseModel.getExerciseName().toString());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseName;
        TextView exerciseCalorie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.txt_exerciseName);
            exerciseCalorie = itemView.findViewById(R.id.txt_exerciseName);
        }
    }
}
