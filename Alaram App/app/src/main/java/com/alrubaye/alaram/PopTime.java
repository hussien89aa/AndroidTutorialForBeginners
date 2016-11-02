package com.alrubaye.alaram;

/**
 * Created by hussienalrubaye on 10/31/16.
 */

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by hussienalrubaye on 10/9/16.
 */

public class PopTime extends DialogFragment implements View.OnClickListener {

    View view;
    TimePicker tp;
    Button buDome;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.pop_time,container,false);
        tp=(TimePicker)view.findViewById(R.id.tp1);
        buDome=(Button)view.findViewById(R.id.buDome);
        buDome.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        this.dismiss();
        MainActivity ma=(MainActivity)getActivity();
        if ((int) Build.VERSION.SDK_INT >= 23)
            ma.SetTime(tp.getHour(),tp.getMinute());
        else
            ma.SetTime(tp.getCurrentHour(),tp.getCurrentMinute());
    }
}
