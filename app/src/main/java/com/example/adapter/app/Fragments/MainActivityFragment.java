package com.example.adapter.app.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.app.Add_Color;
import com.example.adapter.app.MyAdapter;
import com.example.adapter.app.MyColor;
import com.example.adapter.app.R;

import java.util.ArrayList;
import java.util.Random;

import someThings.MyDataBase;
import someThings.Request_Codes;

/**
 * Created by Олег on 26.05.2014.
 */
public class MainActivityFragment extends Fragment {
    public static final String MY_LOG = "myLog";

    ArrayList<MyColor> myColorArrayList = new ArrayList<MyColor>();
    Button addColorButton;
    Button refresh;
    MyDataBase myHelper;
    public MyAdapter myAdapter;

    public boolean Ar = false;

    public enum Colors {
        blue(Color.BLUE), red(Color.RED), green(Color.GREEN), yellow(Color.YELLOW), gray(Color.GRAY);
        private int color;


        Colors(int color) {
            this.color = color;
        }

        public int getColor() {
            return color;
        }
    }

    // нужен для того что бы передавать выбранный из списка цвет в родное активити
    public interface changeSecondFragment {
        public void changeColor(int color);
    }

    changeSecondFragment toSecondFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        toSecondFragment = (changeSecondFragment) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.layout_main_fragment, container, false);


        refresh = (Button) fragmentView.findViewById(R.id.btnRefresh);             // нужна только для реализации спиннера, Бог его знает почему...
        addColorButton = (Button) fragmentView.findViewById(R.id.btnAddColor);


         /*
        * связано с БД
        */
        myHelper = new MyDataBase(getActivity());
        Log.d(MY_LOG, "mainActivity DB created");
        ContentValues myCV = new ContentValues();
        SQLiteDatabase db = myHelper.getWritableDatabase();
        Cursor checkCursor = db.query("mytable", null, null, null, null, null, null);

        Log.d(MY_LOG, "checkCursor exists" + checkCursor.getCount());
        if (checkCursor.getCount() < 100) {
            Log.d(MY_LOG, "while is running");

            // генерируем наши цвета
            Random myRandom = new Random();
            int count = 0;
            while (count != 100) {
                String dbpath = String.valueOf(getActivity().getDatabasePath("mytable"));
//                Log.d(MY_LOG, "dbpath is in " + dbpath);
                int r = myRandom.nextInt(Colors.values().length);
                int color = Colors.values()[r].getColor();
                String myColorName = Colors.values()[r].name();


                //помещаем их в БД
                myCV.put("name", myColorName);
                myCV.put("color", color);

//                Log.d(MY_LOG, "MainActivity after put");
                //магия
                long rowID = db.insert("mytable", null, myCV);
//                Log.d(MY_LOG, "MainActivity row ID");
//                Log.d(MY_LOG, "MainActivity myCursor");
//                Log.d(MY_LOG, "MainActivity myCursor close");
                count++;
//                Log.d(MY_LOG, "Mainactivity count ++" + count);
            }
        }
        Cursor myCursor = db.query("mytable", null, null, null, null, null, null);
        if (myCursor.moveToFirst()) {
            myCursor.getCount();
//            Log.d(MY_LOG, "myCursor is " + myCursor.getCount());
//            Log.d(MY_LOG, "Cursor move to first");
            int nameIndex = myCursor.getColumnIndex("name");
//            Log.d(MY_LOG, "nameIndex" + nameIndex);
            int colorIndex = myCursor.getColumnIndex("color");
//            Log.d(MY_LOG, "colorIndex" + colorIndex);

            do {
                MyColor nameColor = new MyColor();

                nameColor.myColor = myCursor.getInt(colorIndex);
                nameColor.myText = myCursor.getString(nameIndex);
                myColorArrayList.add(nameColor);
//                Log.d(MY_LOG, "MainActivity_name color create");

            } while (myCursor.moveToNext());
//            Log.d(MY_LOG, "Mainactivity move to next");
        } else myCursor.close();
        myHelper.close();
//        Random myRandom = new Random();
//            int count = 0;
//            while (count != 100) {
//
//                int r = myRandom.nextInt(Colors.values().length);
//                int color = Colors.values()[r].getColor();
//                String myColorName = Colors.values()[r].name();
//
//                MyColor nameColor = new MyColor();
//
//                nameColor.myColor = color;
//                nameColor.myText = myColorName;
//                myColorArrayList.add(nameColor);
//
//                count++;
//            }

        ListView myList = (ListView) fragmentView.findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(getActivity(), myColorArrayList);
        myList.setAdapter(myAdapter);
/*
//      метод для изменения цвета по нажатию елемента
*/
//        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Random myRandomOnClick = new Random();
//                int r = myRandomOnClick.nextInt(Colors.values().length);
//                int colorOnClick = Colors.values()[r].getColor();
//
//                String myColorNameOnClick = Colors.values()[r].name();
//                MyColor nameColorOnClick = new MyColor();
//                nameColorOnClick.myColor = colorOnClick;
//                nameColorOnClick.myText = myColorNameOnClick;
//                myColorArrayList.set(i ,nameColorOnClick);
//                myAdapter.notifyDataSetChanged();
//
//
//            }
//
//        });
//

        /*
        //      метод для вывода тоста про цвет елемента
        */


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

       //       getActivity().findViewById(R.id.frame2).setBackgroundColor(myColorArrayList.get(i).myColor);


                toSecondFragment.changeColor(myColorArrayList.get(i).myColor);


//                Toast.makeText(getActivity(), myColorArrayList.get(i).myText, Toast.LENGTH_SHORT).show();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.notifyDataSetChanged();
            }
        });

        addColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_ColorFragment add_colorFragment;
                add_colorFragment = new Add_ColorFragment();
                add_colorFragment.show(getActivity().getFragmentManager(), Add_ColorFragment.TAG);
            }


        });
        return fragmentView;
    }

    public void addMyColor(MyColor myColor) {
        myColorArrayList.add(myColor);

    }

}



