package com.example.idealhome;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity implements
        HomeFragment.MillListener,
        ProfileFragment.ProfileListener{

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    BottomNavigationView bottomnav;
    private ShairedPreference preference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawerlayout);
        bottomnav = findViewById(R.id.bottomnav);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        preference = new ShairedPreference(this);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new HomeFragment()).commit();
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;


                if (item.getItemId() == R.id.nav_home) {
                    fragment = new HomeFragment();

                }
                if (item.getItemId() == R.id.nav_gallery) {
                    fragment = new ProfileFragment();

                }
                if (item.getItemId() == R.id.nav_slideshow) {
                    fragment = new ProfileFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

                return false;
            }
        });
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.preview:
                        fragment = new AllmembersFragment();
                        break;
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
                return false;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem logIn = menu.findItem(R.id.login);
        MenuItem logOut = menu.findItem(R.id.logout);
        MenuItem setting = menu.findItem(R.id.setting);

        if (preference.getLoginstatus()) {
            logIn.setVisible(false);
            logOut.setVisible(true);
        } else {
            logIn.setVisible(true);
            logOut.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.login:
                preference.setLoginStatus(true);
                break;
            case R.id.logout:
                preference.setLoginStatus(false);
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProfilemethod() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ProfileFragment()).commit();

    }

    @Override
    public void onMillmethod() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MillEntryFragment()).commit();
    }

    @Override
    public void onMembermethod() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MemberFragment()).commit();
    }


//
//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Do you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }


}
