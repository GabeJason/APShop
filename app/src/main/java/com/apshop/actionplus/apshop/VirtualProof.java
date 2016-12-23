package com.apshop.actionplus.apshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.IOException;


public class VirtualProof extends AppCompatActivity {

    CheckBox whiteSpace;
    Button uploadBtn, requestProof;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_proof);
        ProductNum = Integer.parseInt(getIntent().getExtras().getString("Item"));
        opts.inMutable = true;

        uploadBtn = (Button) findViewById(R.id.VPuploadImage);
        whiteSpace = (CheckBox) findViewById(R.id.VPdeleteWhite);

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
            back = BitmapFactory.decodeResource(getResources(), images[0], opts);
            loaded = vpcanvas.setBack(back);
            while (!loaded){}
            loaded =false;
            canvasVP.setBackground(vpcanvas.getBack());
        }

        colorOps[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[0], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[1], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[2], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[3], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[4], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[5], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[6], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[7], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[8], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });


        colorOps[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = BitmapFactory.decodeResource(getResources(), images[9], opts);
                loaded = vpcanvas.setBack(back);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picker = new Intent();
                picker.setType("image/*");
                picker.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(picker, "Select Image"), image_request);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == image_request && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                logoBit = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                loaded = vpcanvas.setLogo(logoBit);
                while (!loaded){}
                loaded =false;
                canvasVP.setBackground(vpcanvas.getBack());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
