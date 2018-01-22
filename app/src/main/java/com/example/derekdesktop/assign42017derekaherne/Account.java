package com.example.derekdesktop.assign42017derekaherne;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Account extends Fragment {
    /**
     * Info class extends Fragment
     *
     * @return the info layout is returned as a view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account, container, false);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Settings settings= new Settings();

        fragmentTransaction.add(R.id.preferencesactivity,settings,"settings_fragment");
        fragmentTransaction.commit();




        return rootView;
    }






}