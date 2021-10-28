package com.example.fitandfine.Ex_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fitandfine.ThrowableExtension;
import com.example.fitandfine.Ex_adapters.Ex_WorkoutData;

import java.util.ArrayList;
import java.util.List;

public class Ex_DatabaseOperations {
    private Ex_DatabaseHelper dbHelper;
    private SQLiteDatabase sqlite;

    public Ex_DatabaseOperations(Context context) {
        this.dbHelper = new Ex_DatabaseHelper(context);
    }

    public int CheckDBEmpty() {
        this.sqlite = this.dbHelper.getReadableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT count(*) FROM ");
        stringBuilder.append(Ex_DatabaseHelper.EXC_DAY_TABLE);
        Cursor rawQuery = this.sqlite.rawQuery(stringBuilder.toString(), null);
        rawQuery.moveToFirst();
        return rawQuery.getInt(0) > 0 ? 1 : 0;
    }

    public int deleteTable() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(Ex_DatabaseHelper.EXC_DAY_TABLE, null, null);
        this.sqlite.close();
        return delete;
    }

    public List<Ex_WorkoutData> getAllDaysProgress() {
        List<Ex_WorkoutData> arrayList = new ArrayList();
        this.sqlite = this.dbHelper.getReadableDatabase();
        try {
            if (this.sqlite != null) {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("select * from ");
                stringBuilder.append(Ex_DatabaseHelper.EXC_DAY_TABLE);
                Cursor rawQuery = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        Ex_WorkoutData exWorkoutData = new Ex_WorkoutData();
                        exWorkoutData.setDay(rawQuery.getString(rawQuery.getColumnIndex(Ex_DatabaseHelper.COL_DAY)));
                        exWorkoutData.setProgress(rawQuery.getFloat(rawQuery.getColumnIndex(Ex_DatabaseHelper.COL_PROGRESS)));
                        rawQuery.moveToNext();
                        arrayList.add(exWorkoutData);
                    }
                }
                this.sqlite.close();
                return arrayList;
            }
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
        return arrayList;
    }

    public int getDayExcCounter(String str) {
        this.sqlite = this.dbHelper.getReadableDatabase();
        int i = 0;
        if (this.sqlite != null) {
            SQLiteDatabase sQLiteDatabase = this.sqlite;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ");
            stringBuilder.append(Ex_DatabaseHelper.EXC_DAY_TABLE);
            stringBuilder.append(" where ");
            stringBuilder.append(Ex_DatabaseHelper.COL_DAY);
            stringBuilder.append(" = '");
            stringBuilder.append(str);
            stringBuilder.append("'");
            Cursor rawQuery = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
            if (rawQuery.moveToFirst()) {
                i = rawQuery.getInt(rawQuery.getColumnIndex(Ex_DatabaseHelper.COL_EXC_COUNTER));
            }
            this.sqlite.close();
        }
        return i;
    }

    public float getExcDayProgress(String str) {
        this.sqlite = this.dbHelper.getReadableDatabase();
        float f = 0.0f;
        if (this.sqlite != null) {
            SQLiteDatabase sQLiteDatabase = this.sqlite;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ");
            stringBuilder.append(Ex_DatabaseHelper.EXC_DAY_TABLE);
            stringBuilder.append(" where ");
            stringBuilder.append(Ex_DatabaseHelper.COL_DAY);
            stringBuilder.append(" = '");
            stringBuilder.append(str);
            stringBuilder.append("'");
            Cursor rawQuery = sQLiteDatabase.rawQuery(stringBuilder.toString(), null);
            if (rawQuery.moveToFirst()) {
                f = rawQuery.getFloat(rawQuery.getColumnIndex(Ex_DatabaseHelper.COL_PROGRESS));
            }
            this.sqlite.close();
        }
        return f;
    }

    public long insertExcALLDayData() {
        long j = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            for (int i = 1; i <= 30; i++) {
                ContentValues contentValues = new ContentValues();
                String str = Ex_DatabaseHelper.COL_DAY;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Day ");
                stringBuilder.append(i);
                contentValues.put(str, stringBuilder.toString());
                contentValues.put(Ex_DatabaseHelper.COL_PROGRESS, Double.valueOf(0.0d));
                contentValues.put(Ex_DatabaseHelper.COL_EXC_COUNTER, Integer.valueOf(0));
                if (this.sqlite != null) {
                    try {
                        j = this.sqlite.insert(Ex_DatabaseHelper.EXC_DAY_TABLE, null, contentValues);
                    } catch (Throwable e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
            this.sqlite.close();
            return j;
        } catch (Throwable e2) {
            ThrowableExtension.printStackTrace(e2);
            this.sqlite.close();
            return j;
        }
    }

    public int insertExcCounter(String str, int i) {
        int i2 = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Ex_DatabaseHelper.COL_EXC_COUNTER, Integer.valueOf(i));
            if (this.sqlite != null) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UPDATE ");
                    stringBuilder.append(Ex_DatabaseHelper.EXC_DAY_TABLE);
                    stringBuilder.append(" SET ");
                    stringBuilder.append(Ex_DatabaseHelper.COL_EXC_COUNTER);
                    stringBuilder.append(" = ");
                    stringBuilder.append(i);
                    stringBuilder.append(" WHERE ");
                    stringBuilder.append(Ex_DatabaseHelper.COL_DAY);
                    stringBuilder.append(" = '");
                    stringBuilder.append(str);
                    stringBuilder.append("'");
                    stringBuilder.toString();
                    SQLiteDatabase sQLiteDatabase = this.sqlite;
                    String str2 = Ex_DatabaseHelper.EXC_DAY_TABLE;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(Ex_DatabaseHelper.COL_DAY);
                    stringBuilder2.append("='");
                    stringBuilder2.append(str);
                    stringBuilder2.append("'");
                    i2 = sQLiteDatabase.update(str2, contentValues, stringBuilder2.toString(), null);
                } catch (Throwable e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            this.sqlite.close();
            return i2;
        } catch (Throwable e2) {
            ThrowableExtension.printStackTrace(e2);
            this.sqlite.close();
            return 0;
        }
    }

    public int insertExcDayData(String str, float f) {
        int i = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Ex_DatabaseHelper.COL_PROGRESS, Float.valueOf(f));
            if (this.sqlite != null) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.sqlite;
                    String str2 = Ex_DatabaseHelper.EXC_DAY_TABLE;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Ex_DatabaseHelper.COL_DAY);
                    stringBuilder.append("='");
                    stringBuilder.append(str);
                    stringBuilder.append("'");
                    i = sQLiteDatabase.update(str2, contentValues, stringBuilder.toString(), null);
                } catch (Throwable e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            this.sqlite.close();
            return i;
        } catch (Throwable e2) {
            ThrowableExtension.printStackTrace(e2);
            this.sqlite.close();
            return 0;
        }
    }
}
