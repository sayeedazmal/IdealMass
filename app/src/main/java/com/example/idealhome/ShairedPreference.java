package com.example.idealhome;

import android.content.Context;
import android.content.SharedPreferences;

public class ShairedPreference {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String IS_LOGGED_IN = "is_logged_in";

    public ShairedPreference(Context context) {
        preferences = context.getSharedPreferences("login",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLoginStatus(boolean status){
        editor.putBoolean(IS_LOGGED_IN,status);
        editor.commit();
    }
    public  boolean getLoginstatus(){
        return preferences.getBoolean(IS_LOGGED_IN,false);
    }
}
