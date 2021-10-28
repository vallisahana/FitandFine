package com.example.fitandfine.Ex_adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.fitandfine.R;
import com.example.fitandfine.utils.utils_LaunchDataModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Ex_MyAdapter extends Adapter<Ex_MyAdapter.ViewHolder> {
    private ArrayList<utils_LaunchDataModel> arrayList;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView nameEx;

        ViewHolder(View view) {
            super(view);
            this.nameEx = (TextView) view.findViewById(R.id.tv1);
            this.iv = (ImageView) view.findViewById(R.id.absW);
        }
    }

    public Ex_MyAdapter(Context context, ArrayList<utils_LaunchDataModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public int getItemCount() {
        return this.arrayList.size();
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        utils_LaunchDataModel utilsLaunchDataModel = (utils_LaunchDataModel) this.arrayList.get(i);
        CharSequence app_name = utilsLaunchDataModel.getApp_name();
        String icon_url = utilsLaunchDataModel.getIcon_url();
        viewHolder.nameEx.setText(app_name);
        Picasso.get().load(icon_url).resize(Callback.DEFAULT_SWIPE_ANIMATION_DURATION, Callback.DEFAULT_SWIPE_ANIMATION_DURATION).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(viewHolder.iv);
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exec_row, viewGroup, false));
    }
}
