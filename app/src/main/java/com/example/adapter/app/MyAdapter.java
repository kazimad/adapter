package com.example.adapter.app;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.adapter.app.Fragments.MainActivityFragment;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KaZimad on 07.04.2014.
 */
public class MyAdapter extends BaseAdapter {
    Button myButton;
    Context myContext;
    //    int myResourceID;
    LayoutInflater myLayoutInflater;
    ArrayList<MyColor> myColorArrayList;

    public MyAdapter(Context context, ArrayList<MyColor> thisColorArrayList) {
        myContext = context;
        myColorArrayList = thisColorArrayList;
        myLayoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return myColorArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return myColorArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View thisView, ViewGroup parent) {
        View view = thisView;
        MyColor q = (MyColor) getItem(position);
        if (view == null) {
            view = myLayoutInflater.inflate(R.layout.layout_color, parent, false);

        }
        ((TextView) view.findViewById(R.id.textView)).setText(q.myText);
        (view.findViewById(R.id.mylayout)).setBackgroundColor(q.myColor);
        myButton = (Button) view.findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random myRandomOnClick = new Random();
                int r = myRandomOnClick.nextInt(MainActivityFragment.Colors.values().length);
                int colorOnClick = MainActivityFragment.Colors.values()[r].getColor();

                String myColorNameOnClick = MainActivityFragment.Colors.values()[r].name();
                MyColor nameColorOnClick = new MyColor();
                nameColorOnClick.myColor = colorOnClick;
                nameColorOnClick.myText = myColorNameOnClick;
                myColorArrayList.set(position ,nameColorOnClick);
                MyAdapter.this.notifyDataSetChanged();
            }
        });
        return view;


    }


}


