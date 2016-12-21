package com.apshop.actionplus.apshop;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class VirtualProof extends AppCompatActivity {

    Button colorOp1, colorOp2, colorOp3, colorOp4, colorOp5, colorOp6, colorOp7, colorOp8, colorOp9, colorOp10;

    Button uploadBtn, requestProof;
    int ProductNum;
    String[] colors;
    int[] images;
    Product prod;
    Button[] colorOps = new Button[10];
    View canvasVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_proof);
        ProductNum = Integer.parseInt(getIntent().getExtras().getString("Item"));

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

        prod = new Product(0,"","","","",false,false);

        colors = prod.getColors(ProductNum);
        images = prod.getImages(ProductNum);

        if (!colors[0].equals("NO")){
            for (int i = 0; i < colors.length; i++){
                colorOps[i].setBackgroundColor(Color.parseColor(colors[i]));
                colorOps[i].setVisibility(View.VISIBLE);
                canvasVP.setBackgroundResource(images[0]);
            }
        }

        colorOps[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[0]);
            }
        });
        colorOps[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[1]);
            }
        });
        colorOps[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[2]);
            }
        });
        colorOps[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[3]);
            }
        });
        colorOps[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[4]);
            }
        });
        colorOps[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[5]);
            }
        });
        colorOps[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[6]);
            }
        });
        colorOps[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[7]);
            }
        });
        colorOps[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[8]);
            }
        });
        colorOps[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasVP.setBackgroundResource(images[9]);
            }
        });
    }
}
