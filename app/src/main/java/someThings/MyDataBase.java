package someThings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
* Created by Олег on 03.05.2014.
*/
public class MyDataBase extends SQLiteOpenHelper {
    public static final String BASE_NAME = "colors";
    public  static final int BASE_VERSION = 5;
    public MyDataBase(Context context) {
        super(context, BASE_NAME, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(("DROP TABLE IF EXISTS mytable"));
        db.execSQL("create table mytable("
                +"name text,"
                +"color integer"+");");

        Log.d("myLog", "myDataBaseClassOnCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.delete("mytable",null,null);
        Log.d("myLog", "onUPDATE");
        onCreate(db);
    }



}
