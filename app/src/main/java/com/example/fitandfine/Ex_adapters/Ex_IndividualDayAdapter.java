package com.example.fitandfine.Ex_adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitandfine.R;

import java.util.ArrayList;
import java.util.HashMap;
import kr.pe.burt.android.lib.faimageview.FAImageView;

public class Ex_IndividualDayAdapter extends Adapter<Ex_IndividualDayAdapter.ViewHolder> {
    private HashMap<String, ArrayList<Integer>> arrayListHashMap = this.arrayListHashMap;
    private Context context;
    private String day;
    private int screenWidth;
    private ArrayList<Ex_WorkoutData> exWorkoutDataList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        FAImageView animImage;
        CardView cardViewInRecycler;
        TextView tv_cycles;
        TextView tv_exerciseName;

        public ViewHolder(View view) {
            super(view);
            this.tv_exerciseName = (TextView) view.findViewById(R.id.exerciseName);
            this.tv_cycles = (TextView) view.findViewById(R.id.rotation);
            this.animImage = (FAImageView) view.findViewById(R.id.animation);
            this.cardViewInRecycler = (CardView) view.findViewById(R.id.cardViewInRecycler);
        }
    }

    public Ex_IndividualDayAdapter(Context context, String str, ArrayList<Ex_WorkoutData> arrayList, int i) {
        this.context = context;
        this.screenWidth = i;
        this.day = str;
        this.exWorkoutDataList = arrayList;
    }

    public int getItemCount() {
        return this.exWorkoutDataList.size() + 1;
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i < this.exWorkoutDataList.size()) {
            viewHolder.animImage.setInterval(1000);
            viewHolder.animImage.setLoop(true);
            viewHolder.animImage.reset();
            int i2 = 0;
            viewHolder.cardViewInRecycler.setVisibility(View.VISIBLE);
            int[] imageIdList = ((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getImageIdList();
            int length = imageIdList.length;
            while (i2 < length) {
                viewHolder.animImage.addImageFrame(imageIdList[i2]);
                i2++;
            }
            viewHolder.animImage.startAnimation();
            viewHolder.tv_exerciseName.setText(((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getExcName().replace("_", " ").toUpperCase());
            TextView textView = viewHolder.tv_cycles;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("x");
            stringBuilder.append(((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getExcCycles());
            textView.setText(stringBuilder.toString());
            return;
        }
        viewHolder.cardViewInRecycler.setVisibility(View.GONE);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exec_row_days, viewGroup, false));
    }
}
