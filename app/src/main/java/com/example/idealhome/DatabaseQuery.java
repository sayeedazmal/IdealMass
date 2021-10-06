package com.example.idealhome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.idealhome.Pojo.Member;
import com.example.idealhome.Pojo.MillInfo;
import com.example.idealhome.Pojo.User;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class DatabaseQuery {

    private DatabaseHelper db;
    private SQLiteDatabase sqLiteDatabase;
    private User users;
    Context context;
    List<User> usersList=new ArrayList<>();


    public DatabaseQuery(Context context) {
        db  = new DatabaseHelper(context);
    }


    public void open(){
        sqLiteDatabase = db.getWritableDatabase();
    }


    public void close(){
        sqLiteDatabase.close();
    }


    public boolean  userinsert(User user){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EMAIL,user.getEmail());
        contentValues.put(DatabaseHelper.USER_NAME,user.getUsername());
        contentValues.put(DatabaseHelper.PASSWORD,user.getPassword());
        contentValues.put(DatabaseHelper.MOBILE_NUMBER,user.getMobilenumber());
        long insertrow = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        this.close();
        if (insertrow > 0){
            return true;
        }else {
            return false;
        }

    }

    public boolean millInsert(MillInfo memberMill){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.YEARS,memberMill.getYear());
        contentValues.put(DatabaseHelper.MONTHS,memberMill.getMonths());
        contentValues.put(DatabaseHelper.DAYS,memberMill.getDays());
        contentValues.put(DatabaseHelper.COUNT,memberMill.getDays());

        long insertrow = sqLiteDatabase.insert(DatabaseHelper.TABLE_MILL,null,contentValues);
        this.close();

        if (insertrow > 0){
            return  true;
        }
        return false;
    }

    public Boolean passWordCheck(String uname, String pass){

            this.open();
            Cursor cursor = sqLiteDatabase.rawQuery(DatabaseHelper.SHOW_USER,null);
            Boolean result = false;

            if (cursor.getCount() == 0){
                Toasty.success(context,"No data is found",Toasty.LENGTH_SHORT).show();
            }else{

                while (cursor.moveToNext()){
                    String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                    String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PASSWORD));


                    if(username.equals(uname) && password.equals(pass)){
                        result = true;
                        break;
                    }
                }
            }
            cursor.close();
            this.close();

        return result;
    }
    public boolean addMember(Member member){

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MEMBER_NAME,member.getMemberName());
        contentValues.put(DatabaseHelper.MEMBER_IMAGE, member.getMemberImage());

        long insertrow = sqLiteDatabase.insert(DatabaseHelper.TABLE_MEMBER,null,contentValues);
        this.close();
        if (insertrow > 0){
            return true;
        }else {
            return false;
        }

    }

}
