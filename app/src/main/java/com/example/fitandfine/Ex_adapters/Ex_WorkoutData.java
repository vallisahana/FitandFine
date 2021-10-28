package com.example.fitandfine.Ex_adapters;

import java.io.Serializable;

public class Ex_WorkoutData implements Serializable {
    private String day;
    private int excCycles;
    private int excDexcResId;
    private String excName;
    private String[] excNameList;
    private int position;
    private float progress;
    private int[] workoutList;

    public String getDay() {
        return this.day;
    }

    public int getExcCycles() {
        return this.excCycles;
    }

    public int getExcDescResId() {
        return this.excDexcResId;
    }

    public String getExcName() {
        return this.excName;
    }

    public String[] getExcNameList() {
        return this.excNameList;
    }

    public int[] getImageIdList() {
        return this.workoutList;
    }

    public int getPosition() {
        return this.position;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public void setExcCycles(int i) {
        this.excCycles = i;
    }

    public void setExcDescResId(int i) {
        this.excDexcResId = i;
    }

    public void setExcName(String str) {
        this.excName = str;
    }

    public void setExcNameList(String[] strArr) {
        this.excNameList = strArr;
    }

    public void setImageIdList(int[] iArr) {
        this.workoutList = iArr;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setProgress(float f) {
        this.progress = f;
    }
}
