package com.example.idealhome;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.idealhome.Pojo.User;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;



    private EditText mail,usrusr,pswrdd,mobphone;
    TextView sup;
    TextView signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

            mail      =     findViewById(R.id.mail);
            usrusr    =     findViewById(R.id.usrusr);
            pswrdd    =     findViewById(R.id.pswrdd);
            mobphone  =     findViewById(R.id.mobphone);
            signup    =     findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });

        sup  =     findViewById(R.id.sup);


        sup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email    = mail.getText().toString().trim();
                    String username = usrusr.getText().toString();
                    String password = pswrdd.getText().toString();
                    String mobile   = mobphone.getText().toString();

                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    if (!email.matches(emailPattern))
                    {
                        mail.setError("email is not validate");
                    }else{
                        if (email.isEmpty()){
                            mail.setError("email is required");
                        }else if (username.isEmpty()){
                            usrusr.setError("user name  is required");
                        }else if (password.isEmpty()){
                            pswrdd.setError("password name  is required");
                        }else if(mobile.isEmpty()){
                            mobphone.setError("password name  is required");
                        }else {
                            User user = new User(email,username,password,mobile);
                            DatabaseQuery dabaseQuery = new DatabaseQuery(SignupActivity.this);
                            boolean status = dabaseQuery.userinsert(user);

                            if (status){
                                Toasty.success(SignupActivity.this,"inserted Successfully",Toasty.LENGTH_SHORT).show();
                                mail.setText("");
                                usrusr.setText("");
                                pswrdd.setText("");
                                mobphone.setText("");

                            }else{
                                Toasty.success(SignupActivity.this,"inserted Not Successfully",Toasty.LENGTH_SHORT).show();
                            }
                        }
                    }




                    }

            });




    }
}
