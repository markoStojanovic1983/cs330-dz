package com.metropolitan.cs330_dz04.fragments;

import android.content.Context;
import android.icu.util.Calendar;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by mare on 6/8/17.
 */

public class DatePreference extends DialogPreference {

    private int year = 2016;
    private int month = 1;
    private int day = 1;
    private DatePicker picker;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    protected View onCreateDialogView() {
        picker = new DatePicker(getContext());
        mDate = getPersistedLong(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDate);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        picker.init(year, month, day, null);
        return picker;
    }

    @Override
    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(String.valueOf((new Date(String.valueOf(defaultValue))).getTime()));
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            if (isPersistent()) {
                persistLong(mDate);
            }
            callChangeListener(String.valueOf(mDate));
        }
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
        Date now = new Date();
        picker.init(now.getYear(), now.getMonth(), now.getDay(), null);
    }

    public void init() {
        setPersistent(true);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        String date = null;
        if (restoreValue) {
            if (defaultValue == null) {
                date = getPersistedString("01/01/16");
            } else {
                date = getPersistedString(defaultValue.toString());
            }
        } else {
            date = defaultValue.toString();
        }
    }


    public void setDate(Date date) {
        mDate = date.getTime();
    }

    public void setDate(long milisseconds) {
        mDate = milisseconds;
    }

    public long getDate() {
        return mDate;
    }

    private long mDate;


}
