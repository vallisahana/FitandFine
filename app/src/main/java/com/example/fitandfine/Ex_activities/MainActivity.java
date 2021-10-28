package com.example.fitandfine.Ex_activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.example.fitandfine.R;
import com.example.fitandfine.ThrowableExtension;
import com.example.fitandfine.Ex_adapters.Ex_AllDayAdapter;
import com.example.fitandfine.Ex_adapters.Ex_MyAdapter;
import com.example.fitandfine.Ex_adapters.Ex_WorkoutData;
import com.example.fitandfine.Ex_alarm.Ex_AlarmMainActivity;
import com.example.fitandfine.Ex_database.Ex_DatabaseOperations;
import com.example.fitandfine.utils.utils_AppUtils;
import com.example.fitandfine.utils.utils_Constants;
import com.example.fitandfine.utils.utils_LaunchDataModel;
import com.example.fitandfine.utils.utils_RecyclerItemClickListener;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    AdRequest adRequest;
    private PublisherInterstitialAd interstitialAd;
    Ex_AllDayAdapter adapter;
    ArrayList<String> arr;
    boolean doubleBackToExitPressedOnce = false;
    private Context context;
    DonutProgress circularProgress;
    Ex_DatabaseOperations exDatabaseOperations;
    TextView dayleft;
    int daysCompletionConter = 0;
    ArrayList<utils_LaunchDataModel> final_data;
    public int height;
    InterstitialAd interstitial;
    private SharedPreferences launchDataPreferences;
    InterstitialAd mInterstitialAdAtLaunch;
    private GridLayoutManager manager;
    LinearLayoutManager managerAdd;
    Ex_MyAdapter exMyAdapter;
    TextView percentScore1;
    ProgressBar progressBar;
    private BroadcastReceiver progressReceiver = new C08532();
 //   private RateThisApp rateThisAppObj;
    private int rateUsCount;
    RecyclerView recyclerView;
    RecyclerView recyclerViewAdd;
    public int squareSize;
    double total_progress = 0.0d;
    public int width;
    ActionBarDrawerToggle toggle;
    private List<Ex_WorkoutData> exWorkoutDataList;
    private int workoutPosition = -1;

    final private String defaultGameId = "2111224";
    private String interstitialPlacementId;
    private String incentivizedPlacementId;



    class C08532 extends BroadcastReceiver {
        C08532() {
        }

        public void onReceive(Context context, Intent intent) {
            double doubleExtra = intent.getDoubleExtra(utils_AppUtils.KEY_PROGRESS, 0.0d);
            try {
                ((Ex_WorkoutData) MainActivity.this.exWorkoutDataList.get(MainActivity.this.workoutPosition)).setProgress((float) doubleExtra);
                MainActivity.this.total_progress = 0.0d;
                int i = 0;
                MainActivity.this.daysCompletionConter = 0;
                while (i < utils_Constants.TOTAL_DAYS) {
                    MainActivity.this.total_progress = (double) ((float) (MainActivity.this.total_progress + ((((double) ((Ex_WorkoutData) MainActivity.this.exWorkoutDataList.get(i)).getProgress()) * 4.348d) / 100.0d)));
                    if (((Ex_WorkoutData) MainActivity.this.exWorkoutDataList.get(i)).getProgress() >= 99.0f) {
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.daysCompletionConter++;
                    }
                    i++;
                }
                MainActivity.this.daysCompletionConter += MainActivity.this.daysCompletionConter / 3;
                MainActivity.this.progressBar.setProgress((int) MainActivity.this.total_progress);
                TextView textView = MainActivity.this.percentScore1;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append((int) MainActivity.this.total_progress);
                stringBuilder.append("%");
                textView.setText(stringBuilder.toString());

                circularProgress.setVisibility(View.VISIBLE);
                //circularProgress.setText(stringBuilder.toString()+ "%");
                String convertto=(String.valueOf((int) MainActivity.this.total_progress));
                //  circularProgress.setText(stringBuilder.toString()+ "%");
                circularProgress.setDonut_progress(convertto+"");
                ///circularProgress.setDonut_progress(stringBuilder.toString() + "");
                textView = MainActivity.this.dayleft;
                stringBuilder = new StringBuilder();
                stringBuilder.append(utils_Constants.TOTAL_DAYS - MainActivity.this.daysCompletionConter);
                stringBuilder.append(" Days left");
                textView.setText(stringBuilder.toString());
                MainActivity.this.adapter.notifyDataSetChanged();
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(doubleExtra);
                Log.i("progress broadcast", stringBuilder.toString());
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    public class LoadCrossPromotionAdd extends AsyncTask<Void, Void, ArrayList<utils_LaunchDataModel>> {
        private ArrayList<utils_LaunchDataModel> launchDataList = new ArrayList();

        private void fetchJsonData(String str) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("item_array");
                for (int i = 0; i < jSONArray.length(); i++) {
                    utils_LaunchDataModel utilsLaunchDataModel = new utils_LaunchDataModel();
                    utilsLaunchDataModel.setApp_name(jSONArray.getJSONObject(i).getString("app_name"));
                    utilsLaunchDataModel.setApp_pakage(jSONArray.getJSONObject(i).getString("app_package"));
                    utilsLaunchDataModel.setIcon_url(jSONArray.getJSONObject(i).getString("icon_url"));
                    if (!jSONArray.getJSONObject(i).getString("app_package").equals(MainActivity.this.getPackageName())) {
                        this.launchDataList.add(utilsLaunchDataModel);
                    }
                }
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
        }

        private void loadDataFromJson() {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://www.mediafire.com/file/ky2d413b4vvf9vf/gymdatabse.json").openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    } else {
                        String stringBuilder2 = stringBuilder.toString();
                        Editor edit = MainActivity.this.getSharedPreferences("string", 0).edit();
                        edit.putString("jsonstring", stringBuilder2);
                        edit.apply();
                        fetchJsonData(stringBuilder2);
                        return;
                    }
                }
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
        }

        protected ArrayList<utils_LaunchDataModel> doInBackground(Void... voidArr) {
            loadDataFromJson();
            return this.launchDataList;
        }

        protected void onPostExecute(ArrayList<utils_LaunchDataModel> arrayList) {
            super.onPostExecute(arrayList);
            if (arrayList != null && arrayList.size() > 0) {
                MainActivity.this.final_data = arrayList;
            }
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    /* renamed from: com.outthinking.abs.activities.MainActivity$1 */
    class C12611 implements utils_RecyclerItemClickListener.onItemClickListener {
        C12611() {
        }

        public void OnItem(View view, int i) {
           // Toast.makeText(MainActivity.this, "Yeas", Toast.LENGTH_SHORT).show();
            Intent intent;
            MainActivity.this.workoutPosition = i;
            if ((MainActivity.this.workoutPosition + 1) % 4 == 0) {
                intent = new Intent(MainActivity.this, Ex_RestDayActivity.class);
            } else if (((Ex_WorkoutData) MainActivity.this.exWorkoutDataList.get(i)).getProgress() < 99.0f) {
                intent = new Intent(MainActivity.this, Ex_DayActivity.class);
                intent.putExtra("day", (String) MainActivity.this.arr.get(i));
                intent.putExtra("day_num", i);
                intent.putExtra(NotificationCompat.CATEGORY_PROGRESS, ((Ex_WorkoutData) MainActivity.this.exWorkoutDataList.get(i)).getProgress());
            } else {
                MainActivity.this.excRepeatConfirmDialog(i);
                return;
            }
            MainActivity.this.startActivity(intent);
        }
    }

    private void actionView(String str) {
        if (isConnectedToInternet()) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                startActivity(intent);
                return;
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
                return;
            }
        }
        Toast.makeText(this, "Please Check Internet Connection..", Toast.LENGTH_SHORT).show();
    }

    private boolean isConnectedToInternet() {
            ConnectionDetector connectionDetector=new ConnectionDetector(MainActivity.this);
            Boolean val;
            if(connectionDetector.isConnected())
            {
                val=true;
            }
            else{
                val=false;
            }
            return val;
    }



    void excRepeatConfirmDialog(int i) {
        new Builder(this).title((CharSequence) "Confirm!").titleColor(ContextCompat.getColor(this, R.color.textColor)).content((CharSequence) "Would you like to repeat this workout?").contentColor(ContextCompat.getColor(this, R.color.textColor)).positiveText((CharSequence)
                "Yes").positiveColor(ContextCompat.getColor(this, R.color.activeColor))
                .onPositive(new MainActivity_excRepeatConfirmDialog_positivebutton(this, i))
                .negativeText((CharSequence) "No").negativeColor(ContextCompat.getColor(this, R.color.activeColor))
                .onNegative(MainActivity_excRepeatConfirmDialog_nagativebutton.$instance).show();
    }

    void exitConfirmDialog(final ArrayList<utils_LaunchDataModel> arrayList) {
        final Dialog dialog = new Dialog(this, R.style.AppTheme);
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        dialog.getWindow().setLayout(-1, -1);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setFlags(1024, 1024);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.exec_exit_confirm_dialog_layout);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(-1, -1);
        dialog.setCancelable(true);
        this.recyclerViewAdd = (RecyclerView) dialog.findViewById(R.id.recyclerAdd);
        this.managerAdd = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        this.exMyAdapter = new Ex_MyAdapter(this, arrayList);
        this.recyclerViewAdd.setAdapter(this.exMyAdapter);
        this.recyclerViewAdd.setLayoutManager(this.managerAdd);
        ((Button) dialog.findViewById(R.id.btnYes)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
             //  super.onBackPressed();
            }
        });
        this.recyclerViewAdd.addOnItemTouchListener(new utils_RecyclerItemClickListener(this, new utils_RecyclerItemClickListener.onItemClickListener() {
            public void OnItem(View view, int i) {
                String app_pakage = ((utils_LaunchDataModel) arrayList.get(i)).getApp_pakage();
                MainActivity mainActivity = MainActivity.this;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(app_pakage);
                mainActivity.actionView(stringBuilder.toString());
            }
        }));
        dialog.show();
    }

    public void imageclick(View view) {
        startActivity(new Intent(this, Ex_TipsActivity.class));
    }

    public boolean isRestDay(int i) {
        return i == 4;
    }

    final /* synthetic */ void lambda$excRepeatConfirmDialog$0$MainActivity(int i, MaterialDialog materialDialog, DialogAction dialogAction) {
        try {
            Toast.makeText(MainActivity.this, "YEs", Toast.LENGTH_SHORT).show();
            CharSequence stringBuilder = null;
            Intent intent;
            materialDialog.dismiss();
            String str = (String) this.arr.get(i);
            this.exDatabaseOperations.insertExcDayData(str, 0.0f);
            this.exDatabaseOperations.insertExcCounter(str, 0);
            this.exWorkoutDataList = this.exDatabaseOperations.getAllDaysProgress();
            this.adapter = new Ex_AllDayAdapter(this, this.exWorkoutDataList);
            this.recyclerView.setAdapter(this.adapter);
            this.daysCompletionConter--;
            TextView textView = this.dayleft;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(utils_Constants.TOTAL_DAYS - this.daysCompletionConter);
            stringBuilder2.append(" Days left");
            textView.setText(stringBuilder2.toString());
            if (this.daysCompletionConter > 0) {
                this.progressBar.setProgress((int) (this.total_progress - 4.348d));
                textView = this.percentScore1;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append((int) (this.total_progress - 4.348d));
                stringBuilder3.append("%");
                stringBuilder = stringBuilder3.toString();
            } else {
                if (this.daysCompletionConter == 0) {
                    this.progressBar.setProgress(0);
                    textView = this.percentScore1;
                    stringBuilder = "0%";
                }
                intent = new Intent(this, Ex_DayActivity.class);
                intent.putExtra("day", str);
                intent.putExtra("day_num", i);
                intent.putExtra(NotificationCompat.CATEGORY_PROGRESS, ((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getProgress());
                startActivity(intent);
            }
            textView.setText(stringBuilder);
            intent = new Intent(this, Ex_DayActivity.class);
            intent.putExtra("day", str);
            intent.putExtra("day_num", i);
            intent.putExtra(NotificationCompat.CATEGORY_PROGRESS, ((Ex_WorkoutData) this.exWorkoutDataList.get(i)).getProgress());
            startActivity(intent);
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            drawerLayout.closeDrawer((int) GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                System.gc();
                finish();
                System.exit(0);
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            //Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(this,"Tap again to exit", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 1500);


           /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage((CharSequence) "Are you sure you want to exit?").
                    setCancelable(false).setPositiveButton((CharSequence)
                    "Ok", new okbutton()).setNeutralButton((CharSequence) "No",
                    new nobutton()).setNegativeButton((CharSequence) "Rate us", new ratebutton());
            builder.create().show();
           // super.onBackPressed();*/
        }

     /*   try {
            interstitialAd = new PublisherInterstitialAd(MainActivity.this);
            interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_id));
        }
        catch (Exception ex)
        {}
        try {
            interstitialAd.loadAd(new PublisherAdRequest.Builder().build());
        } catch (Exception e) {
        }
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {
            try {
                interstitialAd.show();
                interstitialAd = null;
            } catch (Exception e) {
            }
        }
    }

    public void onAdClosed() {
        interstitialAd = null;
    }
});*/

    }

    class okbutton implements DialogInterface.OnClickListener {
        okbutton() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            MainActivity.this.finish();
        }
    }


    class nobutton implements DialogInterface.OnClickListener {
        nobutton() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            //    MainActivity.this.finish();
        }
    }

    class ratebutton implements DialogInterface.OnClickListener {
        ratebutton() {
        }

        public void onClick(DialogInterface r2, int r3) {
            try{
                Uri marketUri = Uri.parse("market://details?id=" + getPackageName());
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }catch(ActivityNotFoundException e) {
                Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                startActivity(marketIntent);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = 1;

        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.activity_main);

        this.context = this;
        this.final_data = new ArrayList();
        int i2 = 0;
        new LoadCrossPromotionAdd().execute(new Void[0]);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            this.percentScore1 = (TextView) findViewById(R.id.percentScore);
            this.circularProgress = (DonutProgress) findViewById(R.id.donut_progress);
            this.dayleft = (TextView) findViewById(R.id.daysLeft);
            this.launchDataPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            this.exDatabaseOperations = new Ex_DatabaseOperations(this);
            boolean z = this.launchDataPreferences.getBoolean("daysInserted", false);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.width = displayMetrics.widthPixels;
            this.height = displayMetrics.heightPixels;
            if (!z && this.exDatabaseOperations.CheckDBEmpty() == 0) {
                this.exDatabaseOperations.insertExcALLDayData();
                Editor edit = this.launchDataPreferences.edit();
                edit.putBoolean("daysInserted", true);
                edit.apply();
        }
        this.exWorkoutDataList = this.exDatabaseOperations.getAllDaysProgress();
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        this.progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.neo_launch_progressbar));
        this.exDatabaseOperations.getExcDayProgress("Day 1");
        this.exDatabaseOperations.getDayExcCounter("Day 1");
        while (i2 < utils_Constants.TOTAL_DAYS) {
            this.total_progress = (double) ((float) (this.total_progress + ((((double) ((Ex_WorkoutData) this.exWorkoutDataList.get(i2)).getProgress()) * 4.348d) / 100.0d)));
            if (((Ex_WorkoutData) this.exWorkoutDataList.get(i2)).getProgress() >= 99.0f) {
                this.daysCompletionConter++;
            }
            i2++;
        }
        this.daysCompletionConter += this.daysCompletionConter / 3;
        this.progressBar.setProgress((int) this.total_progress);
        TextView textView = this.percentScore1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((int) this.total_progress);
        stringBuilder.append("%");
        textView.setText(stringBuilder.toString());

        circularProgress.setVisibility(View.VISIBLE);
        //circularProgress.setText(stringBuilder.toString()+ "%");
          String convertto=(String.valueOf((int)this.total_progress));
      //  circularProgress.setText(stringBuilder.toString()+ "%");
        circularProgress.setDonut_progress(convertto+"");
        ///circularProgress.setDonut_progress(stringBuilder.toString() + "");

        circularProgress.setMax(100);
        textView = this.dayleft;
        stringBuilder = new StringBuilder();
        stringBuilder.append(utils_Constants.TOTAL_DAYS - this.daysCompletionConter);
        stringBuilder.append(" Days left");
        textView.setText(stringBuilder.toString());
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler);
        this.adapter = new Ex_AllDayAdapter(this, this.exWorkoutDataList);
        this.manager = new GridLayoutManager(this, 3);
        this.adapter = new Ex_AllDayAdapter(this, this.exWorkoutDataList);
        this.arr = new ArrayList();
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.manager);
        this.recyclerView.addOnItemTouchListener(new utils_RecyclerItemClickListener(this, new C12611()));
        while (i <= utils_Constants.TOTAL_DAYS) {
            ArrayList arrayList = this.arr;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Day ");
            stringBuilder.append(i);
            arrayList.add(stringBuilder.toString());
            i++;
        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {

                TextView textView1=(TextView)findViewById(R.id.time);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
             //   textView1.setText(currentDateandTime);



                super.onDrawerOpened(drawerView);
            }
        };
      /*  drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();*/
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        registerReceiver(this.progressReceiver, new IntentFilter(utils_AppUtils.WORKOUT_BROADCAST_FILTER));
        if (this.daysCompletionConter > 4) {
            createAppRatingDialog("Rate App","Please rate our gym_workout").show();

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.progressReceiver);
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.trainingplan) {
            if (itemId == R.id.reminder) {
                startActivity(new Intent(this, Ex_AlarmMainActivity.class));
            }
            if (itemId == R.id.setting) {
                startActivity(new Intent(this, ActivityAbout.class));
            }

            else if (itemId == R.id.restartprogress) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("Are you sure you want to restart progress");
                    alertDialogBuilder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    restart();
                                }
                            });

                    alertDialogBuilder.setNegativeButton("cancel",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }

            if(itemId==R.id.privacy){
                Intent emailIntent = new Intent();
                emailIntent.setAction(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharing_text)+getApplicationContext().getPackageName());
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                emailIntent.setType("message/rfc822");

                PackageManager pm = getPackageManager();
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");

                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filePath = app.sourceDir;

                Intent openInChooser = Intent.createChooser(emailIntent,"Share via:");

                List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                for (int i = 0; i < resInfo.size(); i++)
                {
                    // Extract the label, append it, and repackage it in a LabeledIntent
                    ResolveInfo ri = resInfo.get(i);
                    String packageName = ri.activityInfo.packageName;
                    if(packageName.contains("android.email"))
                    {
                        emailIntent.setPackage(packageName);
                    }
                    else if( packageName.contains("anyshare") ||  packageName.contains("android.bluetooth")
                            || packageName.contains("hangouts") || packageName.contains("hike") ||
                            packageName.contains("twitter") || packageName.contains("facebook") ||
                            packageName.contains("mms") || packageName.contains("android.gm") ||
                            packageName.contains("whatsapp"))
                    {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT,  getString(R.string.sharing_text)+getApplicationContext().getPackageName());

                        if(packageName.contains("android.gm"))
                        {
                            intent.putExtra(Intent.EXTRA_SUBJECT,  getString(R.string.app_name));
                            intent.setType("message/rfc822");
                        }
                        if ( (packageName.contains("android.bluetooth") || packageName.contains("anyshare")) && filePath!=null )
                        {
                            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                            intent.setType("*/*");
                        }
                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                    }
                }

                // convert intentList to array
                LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

                openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                startActivity(openInChooser);

            }
            if(itemId==R.id.terms){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName()));
                startActivity(intent);
               /* Intent intent=new Intent(getApplicationContext(),Terms.class);
                startActivity(intent);
                finish();*/

            }
            if(itemId==R.id.logout){

                SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                editor.putString("password", "");
                editor.putString("email", "");
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
               /* Intent intent=new Intent(getApplicationContext(),Terms.class);
                startActivity(intent);
                finish();*/
            }
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return menuItem.getItemId() == R.id.action_settings ? true : super.onOptionsItemSelected(menuItem);
    }

    public void restart()
    {
        this.exDatabaseOperations.deleteTable();
        Editor edit = this.launchDataPreferences.edit();
        edit.putBoolean("daysInserted", false);
        edit.apply();
        this.exDatabaseOperations.insertExcALLDayData();
        edit.putBoolean("daysInserted", true);
        edit.apply();
        this.exWorkoutDataList = this.exDatabaseOperations.getAllDaysProgress();
        this.adapter = new Ex_AllDayAdapter(this, this.exWorkoutDataList);
        this.recyclerView.setAdapter(this.adapter);
        this.progressBar.setProgress(0);
        this.percentScore1.setText("0%");
        circularProgress.setVisibility(View.VISIBLE);
        circularProgress.setDonut_progress("0");
        ///circularProgress.setDonut_progress(stringBuilder.toString() + "");

        circularProgress.setMax(100);
        TextView textView = this.dayleft;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(utils_Constants.TOTAL_DAYS);
        stringBuilder.append(" Days left");
        textView.setText(stringBuilder.toString());
    }




    private AlertDialog createAppRatingDialog(String rateAppTitle, String rateAppMessage) {
        AlertDialog dialog = new AlertDialog.Builder(this).setPositiveButton("Rate this App", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                openAppInPlayStore(MainActivity.this);
              //  AppPreferences.getInstance(MainActivity.this.getApplicationContext()).setAppRate(false);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
               paramAnonymousDialogInterface.dismiss();
                // openFeedback(MainActivity.this);
                //AppPreferences.getInstance(MainActivity.this.getApplicationContext()).setAppRate(false);
            }
        }).setMessage(rateAppMessage).setTitle(rateAppTitle).create();
        return dialog;
    }



    public  void openAppInPlayStore(Context paramContext) {
        paramContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
    }



    public void onResume() {
        super.onResume();

    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_get_ready);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layout.setOrientation(LinearLayout.HORIZONTAL);

        } else {
            layout.setOrientation(LinearLayout.VERTICAL);
        }
    }






    public boolean ifhasInternet(Context context)
    {
    Boolean value=false;
    ConnectionDetector connectionDetector;
    connectionDetector=new ConnectionDetector(this.getApplicationContext());
    if(connectionDetector.isConnected())
    {
        value=true;
    }
    else {
        value=false;
    }


    return value;
    }





}
