package com.example.idealhome;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.StringTokenizer;

import es.dmoral.toasty.Toasty;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="IdealHome.db";

    //Authentication VariableName
    public static final String TABLE_NAME="Auth";
    public static final String ID="_id";
    public static final String EMAIL="email";
    public static final String USER_NAME="username";
    public static final String PASSWORD="password";
    public static final String MOBILE_NUMBER="mobilenumber";
    public static final int VERSION_NUMBER=2;

    //EntryMill Variable
    public static final String TABLE_MILL="MillEntry";
    public static final String YEARS="year";
    public static final String MONTHS="months";
    public static final String DAYS="days";
    public static final String COUNT="millCount";

    public static final String TABLE_MEMBER="member";
    public static final String MEMBER_IMAGE="memberImage";
    public static final String MEMBER_NAME="memberName";


    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+EMAIL+" VARCHAR(20),"+USER_NAME+" VARCHAR(50),"+PASSWORD+" VARCHAR(20),"+MOBILE_NUMBER+" VARCHAR(20)); ";
    public static final String CREATE_MEMBER = "CREATE TABLE "+TABLE_MEMBER+
            "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            ""+MEMBER_IMAGE+" VARCHAR(20)," +
            ""+MEMBER_NAME+" VARCHAR(50)); ";

    public static final String SHOW_USER = "SELECT * FROM "+TABLE_NAME;
    public static final String CREATE_MILLENTRY = "CREATE TABLE "+TABLE_MILL+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+YEARS+" VARCHAR(20),"+MONTHS+" VARCHAR(50),"+DAYS+" VARCHAR(20),"+COUNT+" VARCHAR(20)); ";
    public static final String SHOW_MILLENTRY = "SELECT * FROM "+TABLE_NAME;

    public Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
//            db.execSQL(CREATE_MILLENTRY);
//            db.execSQL(CREATE_MEMBER);

            Toast.makeText(context,"Table Is Created ",Toast.LENGTH_SHORT).show();
        }catch (Exception e ){
            Toast.makeText(context,"Database Error"+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
