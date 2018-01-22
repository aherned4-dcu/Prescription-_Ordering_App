package com.example.derekdesktop.assign42017derekaherne;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.content.SharedPreferences;



public class Account extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account, container, false);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Settings settings= new Settings();
        fragmentTransaction.add(android.R.id.content,settings,"SETTINGS_FRAGMENT");
        fragmentTransaction.commit();

        return rootView;
    }

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




}