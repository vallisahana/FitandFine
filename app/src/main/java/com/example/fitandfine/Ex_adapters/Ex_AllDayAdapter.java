package com.example.fitandfine.Ex_adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.fitandfine.R;

import java.util.List;

public class Ex_AllDayAdapter extends Adapter<Ex_AllDayAdapter.ViewHolder> {
    List<Ex_WorkoutData> exWorkoutData;
    Context context1;
    class ViewHolder extends RecyclerView.ViewHolder {
     //   CardView cv1;
       LinearLayout linearLayout;
       TextView changetext;
        NumberProgressBar progressbar;
        ImageView restImageView;
        TextView row_day;

        ViewHolder(View view) {
            super(view);
            this.row_day = (TextView) view.findViewById(R.id.row_day);
            this.linearLayout = (LinearLayout) view.findViewById(R.id.linear1);
        //    this.cv1 = (CardView) view.findViewById(R.id.cardviewone);
            this.restImageView = (ImageView) view.findViewById(R.id.restImageView);
            this.changetext = (TextView) view.findViewById(R.id.changetext);
            this.progressbar = (NumberProgressBar) view.findViewById(R.id.progressbar);
        }
    }

    public Ex_AllDayAdapter(Context context, List<Ex_WorkoutData> list) {
        this.exWorkoutData = list;
        context1=context;
    }

    public int getItemCount() {
        return this.exWorkoutData == null ? 0 : this.exWorkoutData.size();
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.progressbar.setMax(100);
        if ((i + 1) % 4 != 0 || i > 27) {
            viewHolder.restImageView.setVisibility(View.GONE);
            viewHolder.row_day.setText(((Ex_WorkoutData) this.exWorkoutData.get(i)).getDay());
            viewHolder.progressbar.setVisibility(View.VISIBLE);
            if (((int) ((Ex_WorkoutData) this.exWorkoutData.get(i)).getProgress()) > 99) {
                viewHolder.progressbar.setProgress(100);
                viewHolder.row_day.setTextColor(context1.getResources().getColor(R.color.white));
               //viewHolder.cv1.setBackgroundColor(context1.getResources().getColor(R.color.activeColor));
                viewHolder.changetext.setVisibility(View.VISIBLE);
                viewHolder.changetext.setText("completed");

                viewHolder.linearLayout.setBackground(context1.getResources().getDrawable(R.drawable.dayshape));
           //    viewHolder.linearLayout.setBackgroundColor(context1.getResources().getColor(R.color.activeColor));
                return;
            } else if(((int) ((Ex_WorkoutData) this.exWorkoutData.get(i)).getProgress()) > 0 && ((int) ((Ex_WorkoutData) this.exWorkoutData.get(i)).getProgress())<100  ){
                viewHolder.progressbar.setProgress((int) ((Ex_WorkoutData) this.exWorkoutData.get(i)).getProgress());
                viewHolder.row_day.setTextColor(context1.getResources().getColor(R.color.white));
                viewHolder.changetext.setVisibility(View.VISIBLE);
                viewHolder.changetext.setText("In Progress");
                // viewHolder.cv1.setBackgroundColor(context1.getResources().getColor(R.color.activeColor2));
                viewHolder.linearLayout.setBackground(context1.getResources().getDrawable(R.drawable.dayshapeyellow));
                return;
            }
            else {
                viewHolder.changetext.setVisibility(View.INVISIBLE);
                viewHolder.progressbar.setProgress((int) ((Ex_WorkoutData) this.exWorkoutData.get(i)).getProgress());
                viewHolder.row_day.setTextColor(context1.getResources().getColor(R.color.white));
              // viewHolder.cv1.setBackgroundColor(context1.getResources().getColor(R.color.white));
                viewHolder.linearLayout.setBackground(context1.getResources().getDrawable(R.drawable.dayshapewhite));
                return;

            }
        }
        viewHolder.progressbar.setVisibility(View.GONE);
        viewHolder.row_day.setText("Rest Day");
       // viewHolder.cv1.setBackgroundColor(context1.getResources().getColor(R.color.activeColor));
        viewHolder.row_day.setTextColor(context1.getResources().getColor(R.color.white));
      //  viewHolder.cv1.setBackgroundColor(context1.getResources().getColor(R.color.activeColor1));
        viewHolder.linearLayout.setBackground(context1.getResources().getDrawable(R.drawable.dayshapered));
//      viewHolder.linearLayout.setBackgroundColor(context1.getResources().getColor(R.color.activeColor1));

        viewHolder.restImageView.setVisibility(View.VISIBLE);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exec_all_days_rows1, viewGroup, false));
    }
}
