package com.apshop.actionplus.apshop;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import java.io.IOException;


public class VirtualProof extends AppCompatActivity {

    CheckBox whiteSpace;
    Button uploadBtn, requestProof;
    ImageButton exitBtn;
    int ProductNum;
    String[] colors;
    int[] images;
    Product prod;
    Button[] colorOps = new Button[10];
    View canvasVP;
    VPCanvas vpcanvas;
    int image_request;
    Bitmap logoBit;
    Bitmap back;
    Bitmap.Config config = Bitmap.Config.ARGB_8888;
    BitmapFactory.Options opts = new BitmapFactory.Options();
    boolean loaded = false;
    int imgNum;
    String productTitle;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_proof);
        ProductNum = Integer.parseInt(getIntent().getExtras().getString("Item"));
        productTitle = getIntent().getExtras().getString("ProductTitle");
        opts.inMutable = true;

        exitBtn = (ImageButton)  findViewById(R.id.exitItemBtnVP);
        uploadBtn = (Button) findViewById(R.id.VPuploadImage);
        whiteSpace = (CheckBox) findViewById(R.id.VPdeleteWhite);
        requestProof = (Button) findViewById(R.id.VPreqBetProof);

        colorOps[0] = (Button) findViewById(R.id.VPcolorC1);
        colorOps[1] = (Button) findViewById(R.id.VPcolorC2);
        colorOps[2] = (Button) findViewById(R.id.VPcolorC3);
        colorOps[3] = (Button) findViewById(R.id.VPcolorC4);
        colorOps[4] = (Button) findViewById(R.id.VPcolorC5);
        colorOps[5] = (Button) findViewById(R.id.VPcolorC6);
        colorOps[6] = (Button) findViewById(R.id.VPcolorC7);
        colorOps[7] = (Button) findViewById(R.id.VPcolorC8);
        colorOps[8] = (Button) findViewById(R.id.VPcolorC9);
        colorOps[9] = (Button) findViewById(R.id.VPcolorC10);

        canvasVP = findViewById(R.id.VPCanvasView);

        vpcanvas = new VPCanvas(this, ProductNum);

        prod = new Product(0,"","","","",false,false);

        colors = prod.getColors(ProductNum);
        images = prod.getImages(ProductNum);

        if (!colors[0].equals("NO")){
            for (int i = 0; i < colors.length; i++){
                colorOps[i].setBackgroundColor(Color.parseColor(colors[i]));
                colorOps[i].setVisibility(View.VISIBLE);
            }
            colorPickBtns(0);
        }

        colorOps[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(0);
            }
        });


        colorOps[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(1);
            }
        });


        colorOps[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(2);
            }
        });


        colorOps[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(3);
            }
        });


        colorOps[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(4);
            }
        });


        colorOps[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(5);
            }
        });


        colorOps[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(6);
            }
        });


        colorOps[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(7);
            }
        });


        colorOps[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(8);
            }
        });


        colorOps[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickBtns(9);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picker = new Intent();
                picker.setType("image/*");
                picker.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(picker, "Select Image"), image_request);
                isEnabledBtn(false);
            }
        });

        whiteSpace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(whiteSpace.isChecked()){
                    vpcanvas.whiteSpace = true;
                    vpcanvas.first = false;
                    loaded = vpcanvas.setBack(back, imgNum);
                    isEnabledBtn(false);
                    while (!loaded){}
                    loaded =false;
                    isEnabledBtn(true);
                    canvasVP.setBackground(vpcanvas.getBack());
                }else{
                    vpcanvas.whiteSpace = false;
                    vpcanvas.first = false;
                    loaded = vpcanvas.setBack(back, imgNum);
                    isEnabledBtn(false);
                    while (!loaded){}
                    loaded =false;
                    isEnabledBtn(true);
                    canvasVP.setBackground(vpcanvas.getBack());
                }
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        requestProof.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        requestProof.setTextColor(Color.parseColor("#003b5d"));
                        break;
                    case MotionEvent.ACTION_UP:
                        requestProof.setTextColor(Color.parseColor("#5cac00"));
                        try {
                            String emailContent = "I would like to see a proof of " + productTitle + " in the color    with this image";
                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.setType("plain/text");
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@actionplusideas.com"});
                            email.putExtra(Intent.EXTRA_SUBJECT, "Proof Request");
                            email.putExtra(Intent.EXTRA_TEXT, emailContent);
                            if (imgUri != null) {
                                email.putExtra(Intent.EXTRA_STREAM, imgUri);
                            }
                            startActivity(Intent.createChooser(email, "Send email..."));
                        } catch (ActivityNotFoundException activityException) {
                            Log.e("Sending a Email", "Email failed", activityException);
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == image_request && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            imgUri = uri;

            try {
                logoBit = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                vpcanvas.logoLoaded = true;
                vpcanvas.logoBitmapOrg = logoBit;
                loaded = vpcanvas.setBack(back, imgNum);
                while (!loaded){}
                loaded =false;
                isEnabledBtn(true);
                canvasVP.setBackground(vpcanvas.getBack());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void colorPickBtns(int i){
        imgNum = i;
        back = BitmapFactory.decodeResource(getResources(), images[i], opts);
        loaded = vpcanvas.setBack(back, imgNum);
        isEnabledBtn(false);
        while (!loaded){}
        loaded =false;
        isEnabledBtn(true);
        canvasVP.setBackground(vpcanvas.getBack());
    }

    public void isEnabledBtn(boolean is){
        for (int i = 0; i < colorOps.length; i++){
            colorOps[i].setEnabled(is);
        }
        uploadBtn.setEnabled(is);
        whiteSpace.setEnabled(is);
    }
}
