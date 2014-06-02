package com.example.adapter.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * Created by Олег on 17.04.2014.
 */
public class MyAdapter_Spinner extends ArrayAdapter<String> {

    //private int[] myEnumColors;
    Context myContextSpinner;
    String[] myNameSpinner;
    int[] myColorSpinner;
    LayoutInflater myInflater;
            

    public MyAdapter_Spinner(Context context, String[] name, int []color) {
        super(context, android.R.layout.simple_dropdown_item_1line, name);
        myContextSpinner = context;
        myNameSpinner = name;
        myColorSpinner = color;
        myInflater = (LayoutInflater) myContextSpinner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
        /*
        * возвращает название цвета из масива
        * */
    @Override   
    public String getItem(int position) {
        return myNameSpinner[position];
    }

    @Override
    public long getItemId(int position) {
        return myColorSpinner[position];
    }

    @Override
    public int getPosition(String item) {
        return getPosition(item);
    }

    @Override
    public View getDropDownView(int position, View thisView, ViewGroup parent) {
        return getCustomView(position,thisView,parent);
    }

    @Override
    public View getView(int position, View thisView, ViewGroup parent) {
        return getCustomView(position, thisView, parent);




    }
    public View getCustomView(int position,View thisView,ViewGroup parent){
        View row = myInflater.inflate(R.layout.add_color_spinner_layout, parent, false);
        row.setBackgroundColor(myColorSpinner[position]);
        TextView label = (TextView) row.findViewById(R.id.textView_Spinner);
        label.setText(myNameSpinner[position]);


        return row;
    }

}





