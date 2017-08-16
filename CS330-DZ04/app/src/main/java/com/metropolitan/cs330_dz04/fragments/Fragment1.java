package com.metropolitan.cs330_dz04.fragments;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.metropolitan.cs330_dz04.R;

/**
 * Created by mare on 6/8/17.
 */

public class Fragment1 extends PreferenceFragment {

    private ListPreference test;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        test = (ListPreference) findPreference("odgovori");

        test.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                resolveAnswer((String) newValue);
                return false;
            }
        });
    }

    private void resolveAnswer(String answer) {
        if (answer.equals("Bern")) {
            Toast.makeText(getActivity(), "Vas odgovor je tačan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Uneli ste netačan odgovor", Toast.LENGTH_SHORT).show();
        }
    }
}


