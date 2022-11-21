package com.example.cap3;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import com.example.cap3.fragment.FragmentShare;
import com.example.cap3.fragment.FragmentWith;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentShare fragmentShare;
    private FragmentWith fragmentWith;
    private Animation fab_open, fab_close,fab_oanim,fab_canim;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        fragmentShare = new FragmentShare();
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        ft1.replace(R.id.fragmentFrame, fragmentShare);
        ft1.commit();
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar(toolbar); //액티비티의 앱바(App Bar)로 지정

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_oanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_oanim);
        fab_canim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_canim);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);


        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_toolbar, menu);

        return true;
    }

    //앱바(App Bar)에 표시된 액션 또는 오버플로우 메뉴가 선택되면
    //액티비티의 onOptionsItemSelected() 메서드가 호출
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId ()) {
            case R.id.item1:
                startActivity (new Intent(this, MainActivity.class));
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit);
                return true;
            case R.id.item2:
                startActivity(new Intent(this,createparty.class));
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit);
                return true;
            default:
                return super.onOptionsItemSelected (item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();

                break;
            case R.id.fab1:
                anim();
                next();
                /*fram();*/
                break;
            case R.id.fab2:
                anim();
                alert();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit);
    }




    private void next() {
        Intent intent = new Intent(ShareActivity.this,userActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit);
    }

    private void fram() {
        fragmentWith = new FragmentWith();
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        ft1.replace(R.id.fragmentFrame, fragmentWith);
        ft1.commit();
    }

    private void alert() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater= getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_settingdialog, null);
        //view.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); 어찌쓸지 고민.
        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast t= Toast.makeText(ShareActivity.this, "OK", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast t= Toast.makeText(ShareActivity.this, "CANCEL", Toast.LENGTH_SHORT);
                t.show();
            }
        });
        AlertDialog dialog= builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }


    private void anim() {
        if (isFabOpen) {
            fab.startAnimation(fab_oanim);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab.startAnimation(fab_canim);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
}