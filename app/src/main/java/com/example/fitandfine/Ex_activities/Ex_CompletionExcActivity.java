package com.example.fitandfine.Ex_activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fitandfine.Ex_adapters.Ex_MyAdapter;
import com.example.fitandfine.ThrowableExtension;
import com.example.fitandfine.utils.utils_LaunchDataModel;
import com.example.fitandfine.utils.utils_RecyclerItemClickListener;
import com.example.fitandfine.R;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Ex_CompletionExcActivity extends Activity {

    static LayoutParams layoutParams;
  //  private AdChoicesView adChoicesView;
    private LinearLayout adView;
    private boolean adloaded = false;

    private Context context;
    private int currentapiVersion;
    private ImageView imageViewShare;
    private LayoutInflater inflater;
    String jsonString;
    ArrayList<utils_LaunchDataModel> launchDataList;
    LinearLayoutManager manager;
    private int margin;
    Ex_MyAdapter exMyAdapter;
 //   private NativeAd nativeAd;
    private LinearLayout nativeAdContainer;
    RecyclerView recyclerView;
    private int screenheight;
    private int screenwidth;
    private int testHeight = 0;
    private TextView textViewTotExc;
    private TextView textViewTotaTime;
    private int totalExc;
    private int totalTime;

    /* renamed from: com.outthinking.abs.activities.Ex_CompletionExcActivity$1 */
    class C08501 implements OnClickListener {
        C08501() {
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("I have completed today's workout, you also workout \n");
            stringBuilder.append("https://play.google.com/store/apps/details?id=com.outthinking.abs");
            intent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
            intent.putExtra("android.intent.extra.SUBJECT", "Abs workout");
            intent.setType("text/plain");
            Ex_CompletionExcActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.outthinking.abs.activities.Ex_CompletionExcActivity$2 */
    class C08512 implements OnClickListener {
        C08512() {
        }

        public void onClick(View view) {
            Ex_CompletionExcActivity.this.onBackPressed();
        }
    }

    /* renamed from: com.outthinking.abs.activities.Ex_CompletionExcActivity$3 */
    class C12573 implements utils_RecyclerItemClickListener.onItemClickListener {
        C12573() {
        }

        public void OnItem(View view, int i) {
            String app_pakage = ((utils_LaunchDataModel) Ex_CompletionExcActivity.this.launchDataList.get(i)).getApp_pakage();
            Ex_CompletionExcActivity exCompletionExcActivity = Ex_CompletionExcActivity.this;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://play.google.com/store/apps/details?id=");
            stringBuilder.append(app_pakage);
            exCompletionExcActivity.actionView(stringBuilder.toString());
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

    public static int dpToPx() {
        return (int) (Resources.getSystem().getDisplayMetrics().density * 50.0f);
    }

    private void fetchJsonData(String str) {
        this.launchDataList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("item_array");
            for (int i = 0; i < jSONArray.length(); i++) {
                utils_LaunchDataModel utilsLaunchDataModel = new utils_LaunchDataModel();
                utilsLaunchDataModel.setApp_name(jSONArray.getJSONObject(i).getString("app_name"));
                utilsLaunchDataModel.setApp_pakage(jSONArray.getJSONObject(i).getString("app_package"));
                utilsLaunchDataModel.setIcon_url(jSONArray.getJSONObject(i).getString("icon_url"));
                if (!jSONArray.getJSONObject(i).getString("app_package").equals(getPackageName())) {
                    this.launchDataList.add(utilsLaunchDataModel);
                }
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void getScreenHeightWidth() {
        this.context = this;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenheight = displayMetrics.heightPixels;
        this.screenwidth = displayMetrics.widthPixels;
    }



    private boolean isConnectedToInternet() {
  Boolean val;
        ConnectionDetector connectionDetector=new ConnectionDetector(Ex_CompletionExcActivity.this);
       if( connectionDetector.isConnected())
        {
            val=true;
        }
        else{
            val=false;
        }

        return val;
      //  throw new UnsupportedOperationException("Method not decompiled: com.outthinking.abs.activities.Ex_CompletionExcActivity.isConnectedToInternet():boolean");
    }



    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(603979776);
        startActivity(intent);
        super.onBackPressed();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.exc_completion_layout);
        this.totalExc = getIntent().getIntExtra("totalExc", 0);
        this.totalTime = getIntent().getIntExtra("totalTime", 0);
        this.imageViewShare = (ImageView) findViewById(R.id.shareimage_Congrats);
        this.textViewTotaTime = (TextView) findViewById(R.id.congrts_duration);
        this.textViewTotExc = (TextView) findViewById(R.id.congrts_ExNo);
        TextView textView = this.textViewTotaTime;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.totalTime / 60);
        stringBuilder.append(":");
        stringBuilder.append(this.totalTime % 60);
        textView.setText(stringBuilder.toString());
        textView = this.textViewTotExc;
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(this.totalExc);
        textView.setText(stringBuilder.toString());
        this.jsonString = getSharedPreferences("string", 0).getString("jsonstring", "Not found!");
        fetchJsonData(this.jsonString);
        this.recyclerView = (RecyclerView) findViewById(R.id.rv_congrats);
        this.manager = new LinearLayoutManager(this, 0, false);
        this.exMyAdapter = new Ex_MyAdapter(this, this.launchDataList);
        this.recyclerView.setAdapter(this.exMyAdapter);
        this.recyclerView.setLayoutManager(this.manager);
        this.imageViewShare.setOnClickListener(new C08501());
        findViewById(R.id.closeimage_Congrats).setOnClickListener(new C08512());
        this.recyclerView.addOnItemTouchListener(new utils_RecyclerItemClickListener(this, new C12573()));
        getScreenHeightWidth();
        //displayNativeAd();
    }
}
