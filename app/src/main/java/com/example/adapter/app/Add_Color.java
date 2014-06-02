package com.example.adapter.app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.app.Fragments.Add_ColorFragment;

import java.util.HashMap;

/**
 * Created by Олег on 14.04.14.
 */
public class Add_Color extends Activity {
    final Intent toSecondActivity = new Intent();
    Add_ColorFragment add_colorFragment;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_color_layout);

        add_colorFragment = new Add_ColorFragment();
        ft = getFragmentManager()
                .beginTransaction()
                .add(R.id.frame_add, add_colorFragment);
        ft.commit();


    }


}

