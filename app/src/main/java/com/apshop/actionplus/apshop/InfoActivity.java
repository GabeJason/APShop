package com.apshop.actionplus.apshop;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InfoActivity extends AppCompatActivity {

    Button phoneBtn, emailBtn, webBtn, addressBtn;
    ImageButton exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        phoneBtn = (Button)findViewById(R.id.infoPhoneNumBtn);
        emailBtn = (Button)findViewById(R.id.infoEmailBtn);
        webBtn = (Button)findViewById(R.id.infoWebBtn);
        addressBtn = (Button)findViewById(R.id.infoAddressBtn);
        exitBtn = (ImageButton)findViewById(R.id.exitInfoBtn);


        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent dial = new Intent(Intent.ACTION_DIAL);
                    dial.setData(Uri.parse("tel:+17045368337"));
                    startActivity(dial);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setType("plain/text");
                    email.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@actionplusideas.com" });
                    email.putExtra(Intent.EXTRA_SUBJECT, "Action Plus Ideas Inquiry");
                    email.putExtra(Intent.EXTRA_TEXT, "Hi! I would like to know more about...");
                    startActivity(Intent.createChooser(email, ""));
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Sending a Email", "Email failed", activityException);
                }
            }
        });

        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse("http://www.actionplusideas.com");
                    Intent web = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(web);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Opening Website", "Web failed", activityException);
                }
            }
        });

        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri gmmIntentUri = Uri.parse("geo:35.1901726,-80.7768071?q=Action+Plus+Ideas");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Opening Website", "Web failed", activityException);
                }
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
