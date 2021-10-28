package com.example.fitandfine.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class utils_MyScrollView extends ScrollView {
    private boolean enableScrolling = true;

    public utils_MyScrollView(Context context) {
        super(context);
    }

    public utils_MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public utils_MyScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private boolean scrollingEnabled() {
        return this.enableScrolling;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return scrollingEnabled() ? super.onInterceptTouchEvent(motionEvent) : false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return scrollingEnabled() ? super.onTouchEvent(motionEvent) : false;
    }

    public void setScrolling(boolean z) {
        this.enableScrolling = z;
    }
}
