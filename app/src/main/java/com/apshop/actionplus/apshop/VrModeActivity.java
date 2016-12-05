package com.apshop.actionplus.apshop;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VrModeActivity extends Activity {

    WebView myWebView;
    int productNum;
    Button contBtn;
    RelativeLayout introLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vr_mode);

        myWebView = (WebView) findViewById(R.id.vrWebView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        productNum = Integer.parseInt(getIntent().getExtras().getString("productNum"));

        contBtn = (Button)findViewById(R.id.vrmodeContBtn);
        introLay = (RelativeLayout)findViewById(R.id.vrmodeIntroLay);

        myWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            switch (productNum) {
                case 2:
                    //Impact
                    myWebView.loadUrl("https://sketchfab.com/models/f0a4132d2f3446bf8c365c7cc270ffaf/embed?cardboard=1");
                    break;
                case 3:
                    //Everest
                    myWebView.loadUrl("https://sketchfab.com/models/00c8a4f6ba3244ca82a73bf37f8063a7/embed?cardboard=1");
                    break;
                case 4:
                    //Continental
                    myWebView.loadUrl("https://sketchfab.com/models/8a9f5f7f2cfa420281d91458c8046770/embed?cardboard=1");
                    break;
                case 5:
                    //Airtext Pen
                    myWebView.loadUrl("https://sketchfab.com/models/2035063665ed430f849c9cdc6df98b2d/embed?cardboard=1");
                    break;
                default:
                    myWebView.loadUrl("https://sketchfab.com/models/00c8a4f6ba3244ca82a73bf37f8063a7/embed?cardboard=1");
                    break;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Connect to Data or WIFI network and try again.", Toast.LENGTH_LONG).show();
        }

        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.setVisibility(View.VISIBLE);
                introLay.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
               // myWebView.callOnClick();
                //myWebView.loadUrl("javascript:document.getElementsByClassName('viewer started model-loaded transparent-pattern').click();");
                Toast.makeText(getApplicationContext(), "Volume UP", Toast.LENGTH_SHORT).show();
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
