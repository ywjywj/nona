package com.example.cap3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cap3.fragment.FragmentShare;
import com.example.cap3.fragment.FragmentWith;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Animation fab_open, fab_close,fab_oanim,fab_canim;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private TextView nona_tx,with_tx;
    private FragmentShare fragmentShare;
    private FragmentWith fragmentWith;

   /* private WebView webView;*/
   /* private String url = "https://m.naver.com";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*fragmentShare = new FragmentShare();
        fragmentWith = new FragmentWith();*/

        nona_tx=findViewById(R.id.nona_tx);
        with_tx = findViewById(R.id.with_tx);

        nona_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShareActivity.class);
                //건너갈 액티비티를 정해둔 인텐트 객체 선언

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(nona_tx,"nonatxTransition");
                //액티비티에서 움직일 뷰와 트랜지션이름을 Pair배열에 담아둔다.

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                //액티비티 옵션을 적용하기 위해 ActivityOptions객체를 만들고 트랜지션 에니메이션에 대한 설정을 넣는다

                startActivity(intent,options.toBundle());
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit);
                /*FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.fragmentFrame, fragmentShare);
                ft1.commit();*/
            }
        });
        with_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WithActivity.class);
                //건너갈 액티비티를 정해둔 인텐트 객체 선언

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(with_tx,"withtxTransition");
                //액티비티에서 움직일 뷰와 트랜지션이름을 Pair배열에 담아둔다.

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                //액티비티 옵션을 적용하기 위해 ActivityOptions객체를 만들고 트랜지션 에니메이션에 대한 설정을 넣는다

                startActivity(intent,options.toBundle());
                overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit);
               /* FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.fragmentFrame, fragmentWith);
                ft1.commit();*/
            }
        });

        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.)*/



       /* webView = findViewById(R.id.webView);*/

        /*webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());//크롬에 세팅
        webView.setWebViewClient(new WebViewClient());//웹 뷰에 대한 클라이언트*/
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

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //onkeydown 특정 안드로이드 key 값을 입력하게 되면 어떠한 동작을 하라
        if((keyCode== KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }//뒤로가기 동작*/



    /*private class WebViewClientClass extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/

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
        Intent intent = new Intent(MainActivity.this,userActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_enter,R.anim.slide_right_exit);
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
                Toast t= Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast t= Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT);
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
