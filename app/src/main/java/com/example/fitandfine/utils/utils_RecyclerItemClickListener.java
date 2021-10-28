package com.example.fitandfine.utils;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class utils_RecyclerItemClickListener implements OnItemTouchListener {
    GestureDetector mGestureDetector;
    private onItemClickListener mListener;

    /* renamed from: com.outthinking.abs.utils.utils_RecyclerItemClickListener$1 */
    class C08781 extends SimpleOnGestureListener {
        C08781() {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return true;
        }
    }

    public interface onItemClickListener {
        void OnItem(View view, int i);
    }

    public utils_RecyclerItemClickListener(Context context, onItemClickListener onitemclicklistener) {
        this.mListener = onitemclicklistener;
        this.mGestureDetector = new GestureDetector(context, new C08781());
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (!(findChildViewUnder == null || this.mListener == null || !this.mGestureDetector.onTouchEvent(motionEvent))) {
            this.mListener.OnItem(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
        }
        return false;
    }

    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }
}
