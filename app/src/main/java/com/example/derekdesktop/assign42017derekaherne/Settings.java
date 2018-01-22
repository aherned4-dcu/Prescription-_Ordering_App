package com.example.derekdesktop.assign42017derekaherne;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class Settings extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        /*
        * Gather preferences from xml resource .
        *
        * @param sharedPreferences
        * @param s a string value
        * */

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }


}