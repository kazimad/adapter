package com.example.adapter.app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.adapter.app.Fragments.Add_ColorFragment;
import com.example.adapter.app.Fragments.MainActivityFragment;
import com.example.adapter.app.Fragments.MainActivityFragment2;


/**
 * Created by KaZimad on 06.04.2014.
 */
public class SecondActivity extends Activity implements MainActivityFragment.changeSecondFragment, Add_ColorFragment.ColorAddListener {
    MainActivityFragment mainActivityFragment;
    MainActivityFragment2 mainActivityFragment2;
    FragmentTransaction ft;
    FragmentTransaction ft2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_layout);

        mainActivityFragment = new MainActivityFragment();
        ft = getFragmentManager()
                .beginTransaction()
                .add(R.id.frame1, mainActivityFragment);
        ft.commit();


    }

    @Override
    public void changeColor(int color) {
        mainActivityFragment2 = MainActivityFragment2.newInstance(color);
        ft2 = getFragmentManager().beginTransaction().add(R.id.frame2, mainActivityFragment2);
        ft2.commit();

//        mainActivityFragment2 = (MainActivityFragment2) getFragmentManager().findFragmentById(R.id.frame2);


    }


    @Override
    public void toSAsetResult(int mColor, String mNameColor) {
        MyColor nameColorCallBack = new MyColor();
        nameColorCallBack.myText = mNameColor;
        nameColorCallBack.myColor = mColor;
        mainActivityFragment.addMyColor(nameColorCallBack);


    }
}