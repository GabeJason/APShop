package com.apshop.actionplus.apshop;

import android.content.Intent;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ProductFullView extends AppCompatActivity {

    int ProductNum = 0;
    ImageButton exitBtn;
    ImageButton wishBtn, vrmodeBtn;
    Button vpBtn;
    int pImg;
    String pTitle;
    String pSrtDesc;
    String sysProdNum;
    String pCategory;
    Product wishItems[];
    int itemIndex = 0;
    boolean pVrmode = false;
    boolean pVrproof = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_view);
        ProductNum = Integer.parseInt(getIntent().getExtras().getString("item"));

        exitBtn = (ImageButton) findViewById(R.id.exitItemBtn);
        wishBtn = (ImageButton) findViewById(R.id.wishPFVBTN);
        wishBtn.setTag("1");
        vrmodeBtn = (ImageButton) findViewById(R.id.vrmodePFVBTN);
        vpBtn = (Button)findViewById(R.id.virtualProofPFVBTN);

        ImageView productImg = (ImageView) findViewById(R.id.imagePFV);
        TextView productTitle = (TextView) findViewById(R.id.titlePFVTV);
        TextView productDesc = (TextView) findViewById(R.id.descPFVTV);
        //TextView productABTitle = (TextView) findViewById(R.id.itemNameABTV);
        TextView prodNumTV = (TextView) findViewById(R.id.prodNumPFVTV);
        TextView prodCatTV = (TextView) findViewById(R.id.prodCatPFVTV);

        checkWishList();

        String filename = "ProductData.txt";
        File dataFile = new File(getApplicationContext().getFilesDir(), filename);

        FileInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            int lines = 0;

            for (int c = 1; c < 3; c++) {
                if (c == 1) {
                    inputStream = new FileInputStream(dataFile);
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = reader.readLine();
                    while (line != null) {
                        lines = lines + 1;
                        line = reader.readLine();
                    }
                    lines = lines / 7 ;

                } else {
                    inputStream = new FileInputStream(dataFile);
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = reader.readLine();
                    pImg = 0;
                    pTitle = "";
                    pSrtDesc = "";
                    sysProdNum = "";
                    pCategory = "";
                    int pCount = 0;
                    int iCount = 0;
                    while (line != null) {

                        if (iCount == ProductNum) {
                            switch (pCount) {
                                case 0:
                                    pImg = Integer.parseInt(line);
                                    break;
                                case 1:
                                    pTitle = line;
                                    break;
                                case 2:
                                    pSrtDesc = line;
                                    break;
                                case 3:
                                    sysProdNum = line;
                                    break;
                                case 4:
                                    pCategory = line;
                                    break;
                                case 5:
                                    pVrmode = Boolean.parseBoolean(line);
                                    break;
                                case 6:
                                    pVrproof = Boolean.parseBoolean(line);
                                    break;
                            }
                        }
                        if (pCount == 6) {
                            iCount = iCount + 1;
                            pCount = 0;

                        } else {
                            pCount = pCount + 1;
                        }

                        line = reader.readLine();

                    }

                    //
                    // set elements to img, title, desc
                    //
                    productImg.setImageResource(pImg);
                    productTitle.setText(pTitle);
                    //productABTitle.setText(pTitle);
                    productDesc.setText(pSrtDesc);
                    prodNumTV.setText(sysProdNum);
                    prodCatTV.setText(pCategory);
                    if (pVrmode){
                        vrmodeBtn.setVisibility(View.VISIBLE);
                    }
                    if (pVrproof){
                        vpBtn.setVisibility(View.VISIBLE);
                    }
                }
            }


            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(wishItems != null) {
            for (int i = 0; i < wishItems.length; i++) {
                if (productTitle.getText().equals(wishItems[i].title)) {
                    wishBtn.setTag("2");
                    wishBtn.setBackgroundResource(R.drawable.subwishlist);
                    itemIndex = i;
                    break;
                }

            }
        }

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        vrmodeBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vrmodeBtn.setBackgroundResource(R.drawable.googlecardboardc);
                        break;
                    case MotionEvent.ACTION_UP:
                        vrmodeBtn.setBackgroundResource(R.drawable.googlecardboard);
                        Intent vrmode = new Intent(getApplicationContext(), VrModeActivity.class);
                        vrmode.putExtra("productNum",Integer.toString(ProductNum));
                        startActivity(vrmode);
                        break;
                }
                return false;
            }
        });

        wishBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(wishBtn.getTag().equals("1")) {
                            wishBtn.setBackgroundResource(R.drawable.addwishlistc);
                        }else{
                            wishBtn.setBackgroundResource(R.drawable.subwishlistc);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(wishBtn.getTag().equals("1")) {
                            wishBtn.setBackgroundResource(R.drawable.subwishlist);
                        }else{
                            wishBtn.setBackgroundResource(R.drawable.addwishlist);
                        }
                        break;
                }
                return false;
            }
        });

        wishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wishBtn.getTag().equals("1")) {
                    String filename = "wishList.txt";
                    File wishFile = new File(getApplicationContext().getFilesDir().getPath(), filename);
                    Log.i("FilePath", wishFile.getAbsolutePath());

                    if (!wishFile.exists()) {
                        try {
                            wishFile.createNewFile();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    FileOutputStream outputStream = null;
                    String writeOut = "";
                    try {
                        outputStream = new FileOutputStream(wishFile, true);
                        for (int p = 0; p < 7; p++) {
                            switch (p) {
                                case 0:
                                    writeOut = Integer.toString(pImg) + "\n";
                                    break;
                                case 1:
                                    writeOut = pTitle + "\n";
                                    break;
                                case 2:
                                    writeOut = pSrtDesc + "\n";
                                    break;
                                case 3:
                                    writeOut = sysProdNum + "\n";
                                    break;
                                case 4:
                                    writeOut = pCategory + "\n";
                                    break;
                                case 5:
                                    writeOut = Boolean.toString(pVrmode) + "\n";
                                    break;
                                case 6:
                                    writeOut = Boolean.toString(pVrproof) + "\n";
                                    break;
                            }
                            outputStream.write(writeOut.getBytes());
                        }
                        outputStream.close();

                        int newIndex = wishItems.length;
                        Product tempList[] = new Product[wishItems.length+1];
                        for (int i = 0; i < newIndex; i++){
                            tempList[i] = wishItems[i];
                        }

                        tempList[newIndex] = new Product(pImg, pTitle, pSrtDesc,
                                sysProdNum, pCategory, pVrmode, pVrproof);

                        wishItems = tempList;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    wishBtn.setTag("2");

                }else{
                    wishBtn.setTag("1");

                    Product tempList[] = new Product[wishItems.length -1];
                    for (int i = 0; i < tempList.length; i++){
                        if(i<itemIndex) {
                            tempList[i] = wishItems[i];
                        }else{
                            tempList[i] = wishItems[i + 1];
                        }
                    }
                    wishItems = tempList;

                    String filename = "wishList.txt";
                    File wishFile = new File(getApplicationContext().getFilesDir().getPath(), filename);
                    Log.i("FilePath", wishFile.getAbsolutePath());

                    if (!wishFile.exists()) {
                        try {
                            wishFile.createNewFile();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    FileOutputStream outputStream = null;
                    String writeOut = "";
                    int lineCount = 0;
                    try {
                        outputStream = new FileOutputStream(wishFile);
                        for (int i = 0; i < wishItems.length; i++) {
                            for (int p = 0; p < 7; p++) {
                                switch (p){
                                    case 0:
                                        writeOut = Integer.toString(wishItems[i].image) + "\n";
                                        break;
                                    case 1:
                                        writeOut = wishItems[i].title + "\n";
                                        break;
                                    case 2:
                                        writeOut = wishItems[i].shortDesc + "\n";
                                        break;
                                    case 3:
                                        writeOut = wishItems[i].productSystNum + "\n";
                                        break;
                                    case 4:
                                        writeOut = wishItems[i].category + "\n";
                                        break;
                                    case 5:
                                        writeOut = Boolean.toString(wishItems[i].vrmode) + "\n";
                                        break;
                                    case 6:
                                        writeOut = Boolean.toString(wishItems[i].vrproof) + "\n";
                                        break;
                                }
                                lineCount = lineCount + 1;
                                outputStream.write(writeOut.getBytes());
                            }
                        }
                        lineCount = lineCount / 7;
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        vpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vpIntent = new Intent(getApplicationContext(), VirtualProof.class);
                vpIntent.putExtra("Item",Integer.toString(ProductNum));
                vpIntent.putExtra("ProductTitle",pTitle);
                startActivity(vpIntent);
            }
        });
    }

    private void checkWishList() {
        String filename = "wishList.txt";
        File wishFile = new File(getApplicationContext().getFilesDir().getPath(), filename);
        Log.i("FilePath", wishFile.getAbsolutePath());

        if (!wishFile.exists()) {
            try {
                wishFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {


            FileInputStream inputStream = null;
            BufferedReader reader = null;
            try {
                int lines = 0;

                for (int c = 1; c < 3; c++) {
                    if (c == 1) {
                        inputStream = new FileInputStream(wishFile);
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = reader.readLine();
                        while (line != null) {
                            lines = lines + 1;
                            line = reader.readLine();
                        }
                        lines = lines / 7;

                    } else {
                        wishItems = new Product[lines];
                        inputStream = new FileInputStream(wishFile);
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = reader.readLine();
                        int pImg = 0;
                        String pTitle = "";
                        String pSrtDesc = "";
                        String pProductNum = "";
                        String pCategory = "";
                        int pCount = 0;
                        int iCount = 0;
                        while (line != null) {
                            switch (pCount) {
                                case 0:
                                    pImg = Integer.parseInt(line);
                                    break;
                                case 1:
                                    pTitle = line;
                                    break;
                                case 2:
                                    pSrtDesc = line;
                                    break;
                                case 3:
                                    pProductNum = line;
                                    break;
                                case 4:
                                    pCategory = line;
                                    break;
                                case 5:
                                    pVrmode = Boolean.parseBoolean(line);
                                    break;
                                case 6:
                                    pVrproof = Boolean.parseBoolean(line);
                                    break;
                            }
                            if (pCount == 6) {
                                wishItems[iCount] = new Product(pImg, pTitle, pSrtDesc,
                                        pProductNum, pCategory, pVrmode, pVrproof);
                                iCount = iCount + 1;
                                pCount = 0;
                            } else {
                                pCount = pCount + 1;
                            }

                            line = reader.readLine();

                        }
                    }
                }

                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
