package com.example.adapter.app.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.adapter.app.R;

/**
 * Created by Олег on 28.05.2014.
 */
public class MainActivityFragment2 extends Fragment {
    public static final String COLOR = "color";
    public static MainActivityFragment2 newInstance(int color) {

        Bundle bundle = new Bundle();
        bundle.putInt(COLOR, color);
        MainActivityFragment2 mainActivityFragment2 = new MainActivityFragment2();
        mainActivityFragment2.setArguments(bundle);



        return mainActivityFragment2;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView2 = inflater.inflate(R.layout.layout_2_main_fragment, container, false);

        ////
        int bgc = getArguments().getInt(COLOR);
        fragmentView2.setBackgroundColor(bgc);
        return fragmentView2;
    }
}
