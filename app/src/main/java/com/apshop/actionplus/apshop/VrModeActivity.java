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
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VrModeActivity extends Activity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vr_mode);

        myWebView = (WebView) findViewById(R.id.vrWebView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            myWebView.loadUrl("https://sketchfab.com/models/c210137d7106440f974cad684819c3bf/embed?cardboard=1");
        }else{
            Toast.makeText(getApplicationContext(), "Connect to Data or WIFI network and try again.", Toast.LENGTH_LONG).show();
        }
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
