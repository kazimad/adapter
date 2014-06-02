package com.example.adapter.app.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.app.MyAdapter_Spinner;
import com.example.adapter.app.R;

import java.util.HashMap;

/**
 * Created by Олег on 27.05.2014.
 */




public class Add_ColorFragment extends DialogFragment{

    public static final String COLOR = "add";
    public static final String NAME_COLOR = "nameColor";

    public TextView myAddText;
    public TextView myNameText;
    public TextView myColorText;
    public Button addBotton;
    public Button makeChoose;
    public static EditText myNameAdd;
    public EditText myColorAdd;
    public static Integer value;
    public static int myColorBySpinner;
    private int mColor;
    private String mNameColor;
    public final static String TAG = Add_ColorFragment.class.getSimpleName();
    private static HashMap<String, Integer> q = new HashMap<String, Integer>();
    static {
        q.put("blue", Color.BLUE);
        q.put("red", Color.RED);
        q.put("yellow", Color.YELLOW);
        q.put("gray", Color.GRAY);
        q.put("green", Color.GREEN);
    };


    int[] EnumColors = {Color.BLUE, Color.YELLOW, Color.GREEN, Color.GRAY, Color.RED};
    String[] EnumColorsName = {"blue", "yellow", "green", "gray", "red"};
    private boolean colorOk = false;

    public Spinner mySpinner;



    public interface ColorAddListener {

        public void toSAsetResult(int mColor, String mNameColor);

    }

    ColorAddListener secondActItrListener;
    @Override
     public void onAttach(Activity activity) {
        super.onAttach(activity);
        secondActItrListener = (ColorAddListener) activity;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View addColorFragmentView = inflater.inflate(R.layout.add_color_layout_fragment, container, false);



        //        setContentView(R.layout.add_color_layout_fragment);

        myAddText = (TextView) addColorFragmentView.findViewById(R.id.addText);
        myNameText = (TextView) addColorFragmentView.findViewById(R.id.nameText);
        myColorText = (TextView) addColorFragmentView.findViewById(R.id.colorText);
        myNameAdd = (EditText) addColorFragmentView.findViewById(R.id.nameAdd);
        myColorAdd = (EditText) addColorFragmentView.findViewById(R.id.colorAdd);
        addBotton = (Button) addColorFragmentView.findViewById(R.id.addButton);

        mySpinner = (Spinner) addColorFragmentView.findViewById(R.id.spinner);
        makeChoose = (Button) addColorFragmentView.findViewById(R.id.btnFromSpinner);

//        Context myAddColorContext = this;


        MyAdapter_Spinner myAdapter_Spinner = new MyAdapter_Spinner(getActivity(), EnumColorsName, EnumColors);
        myAdapter_Spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter_Spinner);



//        mySpinner.setPrompt("Select Color");
//        Log.d("myLog", "in addColor_Spinner");
//        final Intent mySpinnerIntent = new Intent();




        /*
        * часть про спиннер
        * */


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                mColor = EnumColors[mySpinner.getSelectedItemPosition()] ;
                mNameColor = mySpinner.getSelectedItem().toString();

                colorOk = true;
//                Log.d("myLog", "put_Extra" + mySpinner.getSelectedItem().toString());
//                Log.d("myLog", "put_Extra" + myColorBySpinner);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });
        makeChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorOk != false) {
                    secondActItrListener.toSAsetResult(mColor, mNameColor);
                    dismiss();

                }
            }
        });

        /*
        * часть про добавление со строки
        * */
        addBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newColor();
                secondActItrListener.toSAsetResult(mColor,mNameColor);
                dismiss();
            }
        });
        return addColorFragmentView;

    }



    public void newColor() {

        mNameColor =myNameAdd.getText().toString();
//        setResult(Add_Color.RESULT_OK, toSecondActivity);
        String valueOfColor = myColorAdd.getText().toString().toLowerCase();
        Log.d("myLog", "inputcolorIs" + valueOfColor);


       value = q.get(valueOfColor);
        if (value != null) {
//            toSecondActivity.putExtra(COLOR, value);
            mColor = value;
//            finish();
        } else {
            myColorAdd.setText(null);
            Toast.makeText(getActivity(), "Your color is unavailable", Toast.LENGTH_SHORT).show();
        }

    }

}
