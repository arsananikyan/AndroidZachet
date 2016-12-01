package com.example.gtx660ti.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtx660ti.myapplication.R;

/**
 * Created by GTX660TI on 01.12.2016.
 */

public class PlayerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.player_fragment_layout, container, false);

        return content;
    }
}
