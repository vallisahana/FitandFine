package com.example.fitandfine.Ex_fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitandfine.R;

public class Ex_Tipsfragment extends Fragment {
    String[] content;
    int[] no = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int pos;
    TextView tipdetail;
    TextView tipno;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.exec_tipsfragment, viewGroup, false);
        this.tipno = (TextView) inflate.findViewById(R.id.tipsserialno);
        this.tipdetail = (TextView) inflate.findViewById(R.id.tipsdescription);
        FragmentActivity activity = getActivity();
        activity.getClass();
        this.content = activity.getResources().getStringArray(R.array.tips);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.pos = arguments.getInt("pos");
        }
        TextView textView = this.tipno;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tip ");
        stringBuilder.append(this.no[this.pos]);
        textView.setText(String.valueOf(stringBuilder.toString()));
        this.tipdetail.setText(this.content[this.pos]);
        return inflate;
    }
}
