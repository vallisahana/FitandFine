package com.example.fitandfine.Ex_alarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PorterDuff.Mode;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.fitandfine.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.ALARM_SERVICE;

public class Ex_ReminderFragment extends Fragment {
    private static final String TAG = "Ex_ReminderFragment";
    private Ex_AlarmHelper exAlarmHelper;
    private Gson gson;
     private  AdView mAdView;
    private Ex_ReminderAdapter mAdapter;
    private List<Ex_Reminder_custom> mReCu;
    private RecyclerView mRecyclerView;
    private SharedPreferences mSharedPreferences;
    private TextView noreminders;
    private Editor prefsEditor;
    SimpleDateFormat startTimeFormat;

    /* renamed from: com.outthinking.abs.alarm.alarm_fragments.Ex_ReminderFragment$2 */
    class C08672 implements OnClickListener {
        C08672() {
        }

        public void onClick(View view) {
            Ex_ReminderFragment.this.showTimePickerDialog();
        }
    }

    /* renamed from: com.outthinking.abs.alarm.alarm_fragments.Ex_ReminderFragment$3 */
    class C08683 implements OnTimeSetListener {
        C08683() {
        }

        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            if (timePicker.isShown()) {
                Calendar instance = Calendar.getInstance();
                instance.set(11, i);
                instance.set(12, i2);
                String str = Ex_ReminderFragment.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onTimeSet: ");
                stringBuilder.append(Ex_ReminderFragment.this.startTimeFormat().format(instance.getTime()));
                Log.d(str, stringBuilder.toString());
                Ex_ReminderFragment.this.showDialog(instance);
            }
        }
    }

    /* renamed from: com.outthinking.abs.alarm.alarm_fragments.Ex_ReminderFragment$6 */
    class C08716 implements DialogInterface.OnClickListener {
        C08716() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    /* renamed from: com.outthinking.abs.alarm.alarm_fragments.Ex_ReminderFragment$1 */
    class C12661 extends TypeToken<List<Ex_Reminder_custom>> {
        C12661() {
        }
    }

    private void showTimePickerDialog() {
        Calendar instance = Calendar.getInstance();
        new TimePickerDialog(getActivity(), new C08683(), instance.get(11), instance.get(12), false).show();
    }

    public SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.exec_layout_reminderfragment, viewGroup, false);
        inflate.setTag(TAG);
        Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
         ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator ( R.drawable.back );
        //((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().mutate().setColorFilter(getResources().getColor(R.color.black), Mode.SRC_IN);
        setHasOptionsMenu(true);
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

      //  mAdView = inflate.findViewById(R.id.adView);
       /* AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
        this.exAlarmHelper = new Ex_AlarmHelper(getActivity());
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.reminderlist);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.noreminders = (TextView) inflate.findViewById(R.id.noreminder);
        this.gson = new Gson();
        this.mReCu = (List) this.gson.fromJson(this.mSharedPreferences.getString("Reminder_customObjectList", null), new C12661().getType());
        if (this.mReCu == null || this.mReCu.size() <= 0) {
            this.mRecyclerView.setVisibility(View.GONE);
            this.noreminders.setVisibility(View.VISIBLE);
        } else {
            this.mRecyclerView.setVisibility(View.VISIBLE);
            this.mAdapter = new Ex_ReminderAdapter(getActivity(), this.mReCu, this.gson, this.mSharedPreferences, this.prefsEditor, this.exAlarmHelper);
            this.mRecyclerView.setAdapter(this.mAdapter);
            this.noreminders.setVisibility(View.GONE);
        }
        ((FloatingActionButton) inflate.findViewById(R.id.addreminder)).setOnClickListener(new C08672());
        return inflate;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            getActivity().finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    void setTimeHrAndMin(Ex_AlarmHelper r3, Calendar r4) {
        AlarmManager objAlarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, r4.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, r4.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, r4.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, r4.get(Calendar.MILLISECOND));

      //  Intent alamShowIntent = new Intent(getActivity(),MainActivity.class);
       // PendingIntent alarmPendingIntent = PendingIntent.getActivity(getActivity(), 0,alamShowIntent,0 );
       // objAlarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), alarmPendingIntent);
        // objAlarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alamShowIntent);
       // Toast.makeText(getActivity(), "alarm is set", Toast.LENGTH_SHORT).show();


        Ex_NotificationSchedular.setReminder(getActivity(),
                Ex_AlarmReciever.class, r4.get(Calendar.HOUR_OF_DAY), r4.get(Calendar.MINUTE));



    }






    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }

    public void showDialog(final Calendar calendar) {
        Builder builder = new Builder(getActivity());
        builder.setTitle("Days");
        final ArrayList arrayList = new ArrayList();
        builder.setMultiChoiceItems(getResources().getStringArray(R.array.day_list), null, new OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                if (z) {
                    arrayList.add(Integer.valueOf(i));
                    return;
                }
                if (arrayList.contains(Integer.valueOf(i))) {
                    arrayList.remove(Integer.valueOf(i));
                }
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (arrayList.size() > 0) {
                    dialogInterface.dismiss();
                    Ex_Reminder_custom exReminder_custom = new Ex_Reminder_custom();
                    exReminder_custom.settime(Ex_ReminderFragment.this.startTimeFormat().format(calendar.getTime()));
                    for (i = 0; i < arrayList.size(); i++) {
                        if (((Integer) arrayList.get(i)).equals(Integer.valueOf(0))) {
                            exReminder_custom.setMon(true);
                        } else if (((Integer) arrayList.get(i)).equals(Integer.valueOf(1))) {
                            exReminder_custom.setTue(true);
                        } else if (((Integer) arrayList.get(i)).equals(Integer.valueOf(2))) {
                            exReminder_custom.setWen(true);
                        } else if (((Integer) arrayList.get(i)).equals(Integer.valueOf(3))) {
                            exReminder_custom.setThr(true);
                        } else if (((Integer) arrayList.get(i)).equals(Integer.valueOf(4))) {
                            exReminder_custom.setFri(true);
                        } else if (((Integer) arrayList.get(i)).equals(Integer.valueOf(5))) {
                            exReminder_custom.setSat(true);
                        } else if (((Integer) arrayList.get(i)).equals(Integer.valueOf(6))) {
                            exReminder_custom.setSun(true);
                        }
                    }
                    Ex_ReminderFragment.this.setTimeHrAndMin(Ex_ReminderFragment.this.exAlarmHelper, calendar);
                    exReminder_custom.setOnoff(true);
                    if (Ex_ReminderFragment.this.mReCu == null || Ex_ReminderFragment.this.mReCu.size() <= 0) {
                        Ex_ReminderFragment.this.mReCu = new ArrayList();
                    }
                    Ex_ReminderFragment.this.mReCu.add(exReminder_custom);
                    String toJson = Ex_ReminderFragment.this.gson.toJson(Ex_ReminderFragment.this.mReCu);
                    Ex_ReminderFragment.this.prefsEditor = Ex_ReminderFragment.this.mSharedPreferences.edit();
                    Ex_ReminderFragment.this.prefsEditor.putString("Reminder_customObjectList", toJson);
                    Ex_ReminderFragment.this.prefsEditor.apply();
                    Ex_ReminderFragment.this.mRecyclerView.setVisibility(View.VISIBLE);
                    Ex_ReminderFragment.this.mAdapter = new Ex_ReminderAdapter(Ex_ReminderFragment.this.getActivity(), Ex_ReminderFragment.this.mReCu, Ex_ReminderFragment.this.gson, Ex_ReminderFragment.this.mSharedPreferences, Ex_ReminderFragment.this.prefsEditor, Ex_ReminderFragment.this.exAlarmHelper);
                    Ex_ReminderFragment.this.mRecyclerView.setAdapter(Ex_ReminderFragment.this.mAdapter);
                    Ex_ReminderFragment.this.noreminders.setVisibility(View.GONE);
                    return;
                }
                Toast.makeText(Ex_ReminderFragment.this.getActivity(), "Please select at-least one day", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new C08716());
        builder.create().show();
    }

    public SimpleDateFormat startTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }
}
