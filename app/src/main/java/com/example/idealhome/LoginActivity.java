package com.example.idealhome;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idealhome.Pojo.User;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {


    TextView lin;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    DatabaseQuery databaseQuery;
    EditText usrusr,pswrdd;
    List<User> userList = new ArrayList<User>();
    TextView sup;
    ShairedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DatabaseQuery query= new DatabaseQuery(this);
        preference = new ShairedPreference(this);

        usrusr = findViewById(R.id.usrusr);
        pswrdd = findViewById(R.id.pswrdd);
        sup = findViewById(R.id.sup);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });



        lin = findViewById(R.id.lin);
        boolean status = preference.getLoginstatus();
        if (!status){
            lin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {

//                        DatabaseQuery dq = new DatabaseQuery(LoginActivity.this);
                        String uname = usrusr.getText().toString();
                        String pass = pswrdd.getText().toString();

                        Boolean result= query.passWordCheck(uname,pass);


                        if (result==true){
                            preference.setLoginStatus(true);
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toasty.success(LoginActivity.this,"Login Successfully",Toasty.LENGTH_SHORT).show();
                        }else {
                            Toasty.success(LoginActivity.this,"Login Failed Please Try Again",Toasty.LENGTH_SHORT).show();
                        }

//                    Toasty.success(LoginActivity.this,"Error"+result,Toasty.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toasty.success(LoginActivity.this,"Error"+e,Toasty.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}