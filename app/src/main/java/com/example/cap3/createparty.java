package com.example.cap3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class createparty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createparty);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit);
    }
}