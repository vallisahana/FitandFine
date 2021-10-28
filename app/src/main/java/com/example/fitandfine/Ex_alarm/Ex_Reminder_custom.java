package com.example.fitandfine.Ex_alarm;

public class Ex_Reminder_custom {
    private boolean fri;
    private boolean mon;
    private boolean onoff;
    private boolean sat;
    private boolean sun;
    private boolean thr;
    private String time;
    private boolean tue;
    private boolean wen;

    public boolean getFri() {
        return this.fri;
    }

    public boolean getMon() {
        return this.mon;
    }

    public boolean getOnoff() {
        return this.onoff;
    }

    public boolean getSat() {
        return this.sat;
    }

    public boolean getSun() {
        return this.sun;
    }

    public boolean getThr() {
        return this.thr;
    }

    public boolean getTue() {
        return this.tue;
    }

    public boolean getWen() {
        return this.wen;
    }

    public String gettime() {
        return this.time;
    }

    public void setFri(boolean z) {
        this.fri = z;
    }

    public void setMon(boolean z) {
        this.mon = z;
    }

    public void setOnoff(boolean z) {
        this.onoff = z;
    }

    public void setSat(boolean z) {
        this.sat = z;
    }

    public void setSun(boolean z) {
        this.sun = z;
    }

    public void setThr(boolean z) {
        this.thr = z;
    }

    public void setTue(boolean z) {
        this.tue = z;
    }

    public void setWen(boolean z) {
        this.wen = z;
    }

    public void settime(String str) {
        this.time = str;
    }
}
