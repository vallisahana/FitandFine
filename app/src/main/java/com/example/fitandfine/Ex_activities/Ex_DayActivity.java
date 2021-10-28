package com.example.fitandfine.Ex_activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.example.fitandfine.Ex_adapters.Ex_WorkoutData;
import com.example.fitandfine.R;
import com.example.fitandfine.Ex_adapters.Ex_IndividualDayAdapter;
import com.example.fitandfine.Ex_database.Ex_DatabaseOperations;
import com.example.fitandfine.utils.utils_RecyclerItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Ex_DayActivity extends AppCompatActivity {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private AdRequest adRequest;
    private AdView mAdView;
    private int[] allDays = new int[]{R.array.day1, R.array.day2, R.array.day3, R.array.day4, R.array.day5, R.array.day6, R.array.day7, R.array.day8, R.array.day9, R.array.day10, R.array.day11, R.array.day12, R.array.day13, R.array.day14, R.array.day15, R.array.day16, R.array.day17, R.array.day18, R.array.day19, R.array.day20, R.array.day21, R.array.day22, R.array.day23, R.array.day24, R.array.day25, R.array.day26, R.array.day27, R.array.day28, R.array.day29, R.array.day30};
    private int[] allDays_cycles = new int[]{R.array.day1_cycles, R.array.day2_cycles, R.array.day3_cycles, R.array.day4_cycles, R.array.day5_cycles, R.array.day6_cycles, R.array.day7_cycles, R.array.day8_cycles, R.array.day9_cycles, R.array.day10_cycles, R.array.day11_cycles, R.array.day12_cycles, R.array.day13_cycles, R.array.day14_cycles, R.array.day15_cycles, R.array.day16_cycles, R.array.day17_cycles, R.array.day18_cycles, R.array.day19_cycles, R.array.day20_cycles, R.array.day21_cycles, R.array.day22_cycles, R.array.day23_cycles, R.array.day24_cycles, R.array.day25_cycles, R.array.day26_cycles, R.array.day27_cycles, R.array.day28_cycles, R.array.day29_cycles, R.array.day30_cycles};
    private String day;
    private int day_num = -1;
    private HashMap<String, Integer> hashMapExcAnimResIds;
    private HashMap<String, Integer> hashMapExcDescription;
    private Intent intentMainExcerciseActivity;
    private float progress;
    private ArrayList<Ex_WorkoutData> exWorkoutDataList;




    void getExcDescription() {
        this.hashMapExcDescription = new HashMap();
        this.hashMapExcDescription.put("trunk_rotation", Integer.valueOf(R.string.trunk_rotation_desc));
        this.hashMapExcDescription.put("mountain_climber", Integer.valueOf(R.string.mountain_climber_desc));
        this.hashMapExcDescription.put("clapping_crunches", Integer.valueOf(R.string.clapping_crunches_desc));
        this.hashMapExcDescription.put("swimming_and_superman", Integer.valueOf(R.string.swimming_and_superman_desc));
        this.hashMapExcDescription.put("butt_bridge", Integer.valueOf(R.string.butt_bridge_desc));
        this.hashMapExcDescription.put("flutter_kicks", Integer.valueOf(R.string.flutter_kicks_desc));
        this.hashMapExcDescription.put("plank", Integer.valueOf(R.string.plank_desc));
        this.hashMapExcDescription.put("reverse_crunches", Integer.valueOf(R.string.reverse_crunches_desc));
        this.hashMapExcDescription.put("bent_leg_twist", Integer.valueOf(R.string.bent_leg_twist_desc));
        this.hashMapExcDescription.put("bicycle_crunches", Integer.valueOf(R.string.bicycle_crunches_desc));
        this.hashMapExcDescription.put("russian_twist", Integer.valueOf(R.string.russian_twist_desc));
        this.hashMapExcDescription.put("reclined_oblique_twist", Integer.valueOf(R.string.reclined_oblique_twist_desc));
        this.hashMapExcDescription.put("cross_arm_crunches", Integer.valueOf(R.string.cross_arm_crunches_desc));
        this.hashMapExcDescription.put("standing_bicycle", Integer.valueOf(R.string.standing_bicycle_desc));
        this.hashMapExcDescription.put("leg_drops", Integer.valueOf(R.string.leg_drops_desc));
        this.hashMapExcDescription.put("side_leg_rise_left", Integer.valueOf(R.string.side_leg_rise_left_desc));
        this.hashMapExcDescription.put("side_leg_rise_right", Integer.valueOf(R.string.side_leg_rise_right_desc));
        this.hashMapExcDescription.put("long_arm_crunches", Integer.valueOf(R.string.long_arm_crunches_desc));
        this.hashMapExcDescription.put("dead_bug", Integer.valueOf(R.string.dead_bug_desc));
    }

    final /* synthetic */ void lambda$onCreate$0$DayActivity(View view, int i) {
        if (i < this.exWorkoutDataList.size()) {
            Intent intent = new Intent(this, ExcDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putIntArray("framesIdArray", ((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getImageIdList());
            bundle.putString("excName", ((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getExcName());
            bundle.putInt("excNameDescResId", ((Integer) this.hashMapExcDescription.get(((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getExcName())).intValue());
            bundle.putInt("excCycle", ((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getExcCycles());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    final /* synthetic */ void lambda$onCreate$1$DayActivity(View view) {
        this.intentMainExcerciseActivity = new Intent(this, MainExcerciseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("exWorkoutDataList", this.exWorkoutDataList);
        this.intentMainExcerciseActivity.putExtras(bundle);
        this.intentMainExcerciseActivity.putExtra("day", this.day);
        this.progress = new Ex_DatabaseOperations(this).getExcDayProgress(this.day);
        this.intentMainExcerciseActivity.putExtra(NotificationCompat.CATEGORY_PROGRESS, this.progress);
            startActivity(this.intentMainExcerciseActivity);

    }

    void mapAnimResIds() {
        this.hashMapExcAnimResIds = new HashMap();
        this.hashMapExcAnimResIds.put("trunk_rotation", Integer.valueOf(R.array.trunk_rotation));
        this.hashMapExcAnimResIds.put("mountain_climber", Integer.valueOf(R.array.mountain_climber));
        this.hashMapExcAnimResIds.put("clapping_crunches", Integer.valueOf(R.array.clapping_crunches));
        this.hashMapExcAnimResIds.put("swimming_and_superman", Integer.valueOf(R.array.swimming_and_superman));
        this.hashMapExcAnimResIds.put("butt_bridge", Integer.valueOf(R.array.butt_bridge));
        this.hashMapExcAnimResIds.put("flutter_kicks", Integer.valueOf(R.array.flutter_kicks));
        this.hashMapExcAnimResIds.put("plank", Integer.valueOf(R.array.plank));
        this.hashMapExcAnimResIds.put("reverse_crunches", Integer.valueOf(R.array.reverse_crunches));
        this.hashMapExcAnimResIds.put("bent_leg_twist", Integer.valueOf(R.array.bent_leg_twist));
        this.hashMapExcAnimResIds.put("bicycle_crunches", Integer.valueOf(R.array.bicycle_crunches));
        this.hashMapExcAnimResIds.put("russian_twist", Integer.valueOf(R.array.russian_twist));
        this.hashMapExcAnimResIds.put("reclined_oblique_twist", Integer.valueOf(R.array.reclined_oblique_twist));
        this.hashMapExcAnimResIds.put("cross_arm_crunches", Integer.valueOf(R.array.cross_arm_crunches));
        this.hashMapExcAnimResIds.put("standing_bicycle", Integer.valueOf(R.array.standing_bicycle));
        this.hashMapExcAnimResIds.put("leg_drops", Integer.valueOf(R.array.leg_drops));
        this.hashMapExcAnimResIds.put("side_leg_rise_left", Integer.valueOf(R.array.side_leg_rise_left));
        this.hashMapExcAnimResIds.put("side_leg_rise_right", Integer.valueOf(R.array.side_leg_rise_right));
        this.hashMapExcAnimResIds.put("long_arm_crunches", Integer.valueOf(R.array.long_arm_crunches));
        this.hashMapExcAnimResIds.put("dead_bug", Integer.valueOf(R.array.dead_bug));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.exec_day_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerAllDaysList);
        Button button = (Button) findViewById(R.id.buttonTwo);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
      //  mAdView = findViewById(R.id.adView);
       /* AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
        mapAnimResIds();
        getExcDescription();
        Bundle extras = getIntent().getExtras();
        this.day = extras.getString("day");
        this.day_num = extras.getInt("day_num");
        this.progress = extras.getFloat(NotificationCompat.CATEGORY_PROGRESS);
        ActionBar supportActionBar = getSupportActionBar();
       supportActionBar.getClass();
        supportActionBar.setTitle(this.day);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator ( R.drawable.back );
        supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        this.exWorkoutDataList = prepareAdapterData();
        Adapter individualDayAdapter = new Ex_IndividualDayAdapter(this, this.day, this.exWorkoutDataList, Callback.DEFAULT_DRAG_ANIMATION_DURATION);
       recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(individualDayAdapter);
        recyclerView.addOnItemTouchListener(new utils_RecyclerItemClickListener(this, new Ex_DayActivity_buttonOn_Click(this)));
        button.setOnClickListener(new Ex_DayActivity_Button_Click(this));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    @SuppressLint({"Recycle"})
    ArrayList<Ex_WorkoutData> prepareAdapterData() {
        ArrayList<Ex_WorkoutData> arrayList = new ArrayList();
        String[] stringArray = getResources().getStringArray(this.allDays[this.day_num]);
        int[] intArray = getResources().getIntArray(this.allDays_cycles[this.day_num]);
        for (int i = 0; i < stringArray.length; i++) {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(((Integer) this.hashMapExcAnimResIds.get(stringArray[i])).intValue());
            int length = obtainTypedArray.length();
            int[] iArr = new int[length];
            Ex_WorkoutData exWorkoutData = new Ex_WorkoutData();
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = obtainTypedArray.getResourceId(i2, -1);
            }
            exWorkoutData.setExcName(stringArray[i]);
            exWorkoutData.setExcDescResId(((Integer) this.hashMapExcDescription.get(stringArray[i])).intValue());
            exWorkoutData.setExcCycles(intArray[i]);
            exWorkoutData.setPosition(i);
            exWorkoutData.setImageIdList(iArr);
            arrayList.add(exWorkoutData);
        }
        return arrayList;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
