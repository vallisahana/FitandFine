package com.example.fitandfine.Ex_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitandfine.ThrowableExtension;

public class Ex_DatabaseHelper extends SQLiteOpenHelper {
    static String COL_DAY = "day";
    static String COL_EXC_COUNTER = "counter";
    static String COL_PROGRESS = "progress";
    static String DB_NAME = "FitDB";
    static int DB_VERSION = 2;
    static String EXC_DAY_TABLE = "exc_day";
    String CREATE_EXC_DAY_TABLE_TABLE;

    public Ex_DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE ");
        stringBuilder.append(EXC_DAY_TABLE);
        stringBuilder.append(" (");
        stringBuilder.append(COL_DAY);
        stringBuilder.append(" TEXT, ");
        stringBuilder.append(COL_PROGRESS);
        stringBuilder.append(" REAL, ");
        stringBuilder.append(COL_EXC_COUNTER);
        stringBuilder.append(" INTEGER)");
        this.CREATE_EXC_DAY_TABLE_TABLE = stringBuilder.toString();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(this.CREATE_EXC_DAY_TABLE_TABLE);
        } catch (Throwable e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 > i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(EXC_DAY_TABLE);
            stringBuilder.append(" ADD COLUMN ");
            stringBuilder.append(COL_EXC_COUNTER);
            stringBuilder.append(" INTEGER");
            try {
                sQLiteDatabase.execSQL(stringBuilder.toString());
            } catch (Throwable e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }
}
