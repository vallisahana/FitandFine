package com.example.fitandfine.Ex_alarm;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.gson.Gson;
import com.example.fitandfine.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ex_ReminderAdapter extends Adapter<Ex_ReminderAdapter.ProductViewHolder> {
    private Ex_AlarmHelper exAlarmHelper;
    private Gson gson;
    private Context mCtx;
    private long mLastClickTime = 0;
    private SharedPreferences mSharedPreferences;
    private Editor prefsEditor;
    private List<Ex_Reminder_custom> productList;
    private Ex_Reminder_custom reminderproduct;
    SimpleDateFormat startTimeFormat;

    /* renamed from: com.outthinking.abs.alarm.alarm_adapter.Ex_ReminderAdapter$7 */
    class C08667 implements OnClickListener {
        C08667() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    class ProductViewHolder extends ViewHolder {
        ImageView mDelete;
        TextView mFri;
        TextView mMon;
        Switch mOnoff;
        TextView mSat;
        TextView mSun;
        TextView mThru;
        TextView mTime;
        TextView mTue;
        TextView mWed;

        public ProductViewHolder(View view) {
            super(view);
            this.mTime = (TextView) view.findViewById(R.id.time);
            this.mMon = (TextView) view.findViewById(R.id.day1);
            this.mTue = (TextView) view.findViewById(R.id.day2);
            this.mWed = (TextView) view.findViewById(R.id.day3);
            this.mThru = (TextView) view.findViewById(R.id.day4);
            this.mFri = (TextView) view.findViewById(R.id.day5);
            this.mSat = (TextView) view.findViewById(R.id.day6);
            this.mSun = (TextView) view.findViewById(R.id.day7);
            this.mDelete = (ImageView) view.findViewById(R.id.timedelete);
            this.mOnoff = (Switch) view.findViewById(R.id.timeswitch);
        }
    }

    public Ex_ReminderAdapter(Context context, List<Ex_Reminder_custom> list, Gson gson, SharedPreferences sharedPreferences, Editor editor, Ex_AlarmHelper exAlarmHelper) {
        this.mCtx = context;
        this.productList = list;
        this.mSharedPreferences = sharedPreferences;
        this.prefsEditor = editor;
        this.gson = gson;
        this.exAlarmHelper = exAlarmHelper;
    }

    private void showTimePickerDialog(final Ex_Reminder_custom exReminder_custom, final int i) {
        Calendar instance = Calendar.getInstance();
        new TimePickerDialog(this.mCtx, new OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                if (timePicker.isShown()) {
                    Calendar instance = Calendar.getInstance();
                    instance.set(11, i);
                    instance.set(12, i2);
                    Ex_ReminderAdapter.this.showDialog(instance, exReminder_custom, i);
                }
            }
        }, instance.get(11), instance.get(12), false).show();
    }

    public SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public int getItemCount() {
        return this.productList.size();
    }

    public SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    @SuppressLint({"SetTextI18n"})
    public void onBindViewHolder(final ProductViewHolder productViewHolder, int i) {
        this.reminderproduct = (Ex_Reminder_custom) this.productList.get(i);
        productViewHolder.mTime.setText(this.reminderproduct.gettime());
        productViewHolder.mTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Ex_ReminderAdapter.this.reminderproduct = (Ex_Reminder_custom) Ex_ReminderAdapter.this.productList.get(productViewHolder.getAdapterPosition());
                Ex_ReminderAdapter.this.showTimePickerDialog(Ex_ReminderAdapter.this.reminderproduct, productViewHolder.getAdapterPosition());
            }
        });
        productViewHolder.mMon.setVisibility(View.VISIBLE);
        productViewHolder.mTue.setVisibility(View.VISIBLE);
        productViewHolder.mWed.setVisibility(View.VISIBLE);
        productViewHolder.mThru.setVisibility(View.VISIBLE);
        productViewHolder.mFri.setVisibility(View.VISIBLE);
        productViewHolder.mSat.setVisibility(View.VISIBLE);
        productViewHolder.mSun.setVisibility(View.VISIBLE);
        if (this.reminderproduct.getMon()) {
            productViewHolder.mMon.setText("Mon");
        } else {
            productViewHolder.mMon.setVisibility(View.GONE);
        }
        if (this.reminderproduct.getTue()) {
            productViewHolder.mTue.setText("Tue");
        } else {
            productViewHolder.mTue.setVisibility(View.GONE);
        }
        if (this.reminderproduct.getWen()) {
            productViewHolder.mWed.setText("Wed");
        } else {
            productViewHolder.mWed.setVisibility(View.GONE);
        }
        if (this.reminderproduct.getThr()) {
            productViewHolder.mThru.setText("Thu");
        } else {
            productViewHolder.mThru.setVisibility(View.GONE);
        }
        if (this.reminderproduct.getFri()) {
            productViewHolder.mFri.setText("Fri");
        } else {
            productViewHolder.mFri.setVisibility(View.GONE);
        }
        if (this.reminderproduct.getSat()) {
            productViewHolder.mSat.setText("Sat");
        } else {
            productViewHolder.mSat.setVisibility(View.GONE);
        }
        if (this.reminderproduct.getSun()) {
            productViewHolder.mSun.setText("Sun");
        } else {
            productViewHolder.mSun.setVisibility(View.GONE);
        }
        productViewHolder.mOnoff.setChecked(this.reminderproduct.getOnoff());
        productViewHolder.mOnoff.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Ex_ReminderAdapter.this.reminderproduct = (Ex_Reminder_custom) Ex_ReminderAdapter.this.productList.get(productViewHolder.getAdapterPosition());
                Ex_ReminderAdapter.this.reminderproduct.setOnoff(z);
                String toJson = Ex_ReminderAdapter.this.gson.toJson(Ex_ReminderAdapter.this.productList);
                Ex_ReminderAdapter.this.prefsEditor = Ex_ReminderAdapter.this.mSharedPreferences.edit();
                Ex_ReminderAdapter.this.prefsEditor.putString("Reminder_customObjectList", toJson);
                Ex_ReminderAdapter.this.prefsEditor.apply();
            }
        });
        productViewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - Ex_ReminderAdapter.this.mLastClickTime >= 1000) {
                    Ex_ReminderAdapter.this.mLastClickTime = SystemClock.elapsedRealtime();
                    Ex_ReminderAdapter.this.removeAt(productViewHolder.getAdapterPosition());
                }
            }
        });
    }

    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.mCtx).inflate(R.layout.exec_layout_remindercustom_row, null));
    }

    public void removeAt(int i) {
        this.productList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.productList.size());
        String toJson = this.gson.toJson(this.productList);
        this.prefsEditor = this.mSharedPreferences.edit();
        this.prefsEditor.putString("Reminder_customObjectList", toJson);
        this.prefsEditor.apply();
    }

    void setTimeHrAndMin(Ex_AlarmHelper r3, Calendar r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:6:0x005b in {2, 4, 5} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/775386112.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.startTimeFormat();
        r1 = r4.getTime();
        r0 = r0.format(r1);
        r1 = "AM";
        r0 = r0.endsWith(r1);
        if (r0 == 0) goto L_0x0039;
    L_0x0014:
        r0 = r2.getHourFormat();
        r1 = r4.getTime();
        r0 = r0.format(r1);
        r0 = java.lang.Integer.parseInt(r0);
        r1 = r2.getMinuteFormat();
        r4 = r4.getTime();
        r4 = r1.format(r4);
        r4 = java.lang.Integer.parseInt(r4);
        r1 = 0;
    L_0x0035:
        r3.schedulePendingIntent(r0, r4, r1);
        return;
    L_0x0039:
        r0 = r2.getHourFormat();
        r1 = r4.getTime();
        r0 = r0.format(r1);
        r0 = java.lang.Integer.parseInt(r0);
        r1 = r2.getMinuteFormat();
        r4 = r4.getTime();
        r4 = r1.format(r4);
        r4 = java.lang.Integer.parseInt(r4);
        r1 = 1;
        goto L_0x0035;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.outthinking.abs.alarm.alarm_adapter.Ex_ReminderAdapter.setTimeHrAndMin(com.outthinking.abs.alarm.alarmmanagerdemo.Ex_AlarmHelper, java.util.Calendar):void");
    }

    public void showDialog(Calendar calendar, Ex_Reminder_custom exReminder_custom, int i) {
        Builder builder = new Builder(this.mCtx);
        builder.setTitle("Days");
        final ArrayList arrayList = new ArrayList();
        builder.setMultiChoiceItems(this.mCtx.getResources().getStringArray(R.array.day_list), null, new OnMultiChoiceClickListener() {
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
        final Ex_Reminder_custom exReminder_custom2 = exReminder_custom;
        final Calendar calendar2 = calendar;
        final int i2 = i;
        builder.setPositiveButton(this.mCtx.getString(17039370), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (arrayList.size() > 0) {
                    dialogInterface.dismiss();
                    exReminder_custom2.settime(Ex_ReminderAdapter.this.startTimeFormat().format(calendar2.getTime()));
                    exReminder_custom2.setMon(false);
                    exReminder_custom2.setTue(false);
                    exReminder_custom2.setWen(false);
                    exReminder_custom2.setThr(false);
                    exReminder_custom2.setFri(false);
                    exReminder_custom2.setSat(false);
                    exReminder_custom2.setSun(false);
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(0))) {
                            exReminder_custom2.setMon(true);
                        } else if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(1))) {
                            exReminder_custom2.setTue(true);
                        } else if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(2))) {
                            exReminder_custom2.setWen(true);
                        } else if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(3))) {
                            exReminder_custom2.setThr(true);
                        } else if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(4))) {
                            exReminder_custom2.setFri(true);
                        } else if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(5))) {
                            exReminder_custom2.setSat(true);
                        } else if (((Integer) arrayList.get(i2)).equals(Integer.valueOf(6))) {
                            exReminder_custom2.setSun(true);
                        }
                    }
                    exReminder_custom2.setOnoff(true);
                    Ex_ReminderAdapter.this.setTimeHrAndMin(Ex_ReminderAdapter.this.exAlarmHelper, calendar2);
                    String toJson = Ex_ReminderAdapter.this.gson.toJson(Ex_ReminderAdapter.this.productList);
                    Ex_ReminderAdapter.this.prefsEditor = Ex_ReminderAdapter.this.mSharedPreferences.edit();
                    Ex_ReminderAdapter.this.prefsEditor.putString("Reminder_customObjectList", toJson);
                    Ex_ReminderAdapter.this.prefsEditor.apply();
                    Ex_ReminderAdapter.this.notifyItemChanged(i2);
                    Ex_ReminderAdapter.this.notifyItemRangeChanged(i2, Ex_ReminderAdapter.this.productList.size());
                    return;
                }
                Toast.makeText(Ex_ReminderAdapter.this.mCtx, "Please select at-least one day", 0).show();
            }
        });
        builder.setNegativeButton(this.mCtx.getString(17039360), new C08667());
        builder.create().show();
    }

    public SimpleDateFormat startTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }
}
