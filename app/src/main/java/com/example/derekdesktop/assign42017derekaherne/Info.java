package com.example.derekdesktop.assign42017derekaherne;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Info extends Fragment {
    /**
     * Info class extends Fragment
     *
     * @return the info layout is returned as a view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info, container, false);


        return rootView;
    }
}