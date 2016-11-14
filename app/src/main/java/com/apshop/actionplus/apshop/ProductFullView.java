package com.apshop.actionplus.apshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Button wishBtn, vrmodeBtn;
    int pImg;
    String pTitle;
    String pSrtDesc;
    String sysProdNum;
    String pCategory;
    Product wishItems[];
    int itemIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_view);
        ProductNum = Integer.parseInt(getIntent().getExtras().getString("item"));

        exitBtn = (ImageButton) findViewById(R.id.exitItemBtn);
        wishBtn = (Button) findViewById(R.id.wishPFVBTN);
        wishBtn.setTag("1");
        vrmodeBtn = (Button) findViewById(R.id.vrmodePFVBTN);

        ImageView productImg = (ImageView) findViewById(R.id.imagePFV);
        TextView productTitle = (TextView) findViewById(R.id.titlePFVTV);
        TextView productDesc = (TextView) findViewById(R.id.descPFVTV);
        TextView productABTitle = (TextView) findViewById(R.id.itemNameABTV);
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
                    lines = lines / 5;

                    Log.i("Lines", Integer.toString(lines));
                } else {
                    Log.i("Running", "Yes");
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
                        Log.i("Line not Null", "True");

                        if (iCount == ProductNum) {
                            switch (pCount) {
                                case 0:
                                    pImg = Integer.parseInt(line);
                                    Log.i("pImg", Integer.toString(pImg));
                                    break;
                                case 1:
                                    pTitle = line;
                                    Log.i("pTitle", pTitle);
                                    break;
                                case 2:
                                    pSrtDesc = line;
                                    Log.i("pSrtDesc", pSrtDesc);
                                    break;
                                case 3:
                                    sysProdNum = line;
                                    Log.i("pProdNum", sysProdNum);
                                    break;
                                case 4:
                                    pCategory = line;
                                    Log.i("pCategory", pCategory);
                                    break;
                            }
                        }
                        Log.i("pCount", Integer.toString(pCount));
                        Log.i("iCount", Integer.toString(iCount));
                        if (pCount == 4) {
                            Log.i("ProNum", Integer.toString(iCount));
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
                    productABTitle.setText(pTitle);
                    productDesc.setText(pSrtDesc);
                    prodNumTV.setText("Item Number: " + sysProdNum);
                    prodCatTV.setText(pCategory);
                }
            }


            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < wishItems.length; i++) {
            if (productTitle.getText().equals(wishItems[i].title)) {
                wishBtn.setText("Remove From List");
                wishBtn.setTag("2");
                itemIndex = i;
                break;
            }

        }

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
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
                        for (int p = 0; p < 5; p++) {
                            switch (p) {
                                case 0:
                                    writeOut = Integer.toString(pImg) + "\n";
                                    Log.i("WriteOut", writeOut);
                                    break;
                                case 1:
                                    writeOut = pTitle + "\n";
                                    Log.i("WriteOut", writeOut);
                                    break;
                                case 2:
                                    writeOut = pSrtDesc + "\n";
                                    Log.i("WriteOut", writeOut);
                                    break;
                                case 3:
                                    writeOut = sysProdNum + "\n";
                                    Log.i("WriteOut", writeOut);
                                    break;
                                case 4:
                                    writeOut = pCategory + "\n";
                                    Log.i("WriteOut", writeOut);
                                    break;
                            }
                            outputStream.write(writeOut.getBytes());
                        }
                        outputStream.close();

                        int newIndex = wishItems.length;
                        wishItems[newIndex] = new Product(pImg, pTitle, pSrtDesc,
                                sysProdNum, pCategory);
                        Log.i("ProNum", Integer.toString(newIndex));
                        Log.i("Image", Integer.toString(wishItems[newIndex].image));
                        Log.i("Title", wishItems[newIndex].title);
                        Log.i("ShortDesc", wishItems[newIndex].shortDesc);
                        Log.i("Category", wishItems[newIndex].category);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    wishBtn.setTag("2");
                    wishBtn.setText("Remove from List");

                }else{
                    wishBtn.setTag("1");
                    wishBtn.setText("Add to Wishlist");

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
                            for (int p = 0; p < 5; p++) {
                                switch (p){
                                    case 0:
                                        writeOut = Integer.toString(wishItems[i].image) + "\n";
                                        Log.i("WriteOut", writeOut);
                                        break;
                                    case 1:
                                        writeOut = wishItems[i].title + "\n";
                                        Log.i("WriteOut", writeOut);
                                        break;
                                    case 2:
                                        writeOut = wishItems[i].shortDesc + "\n";
                                        Log.i("WriteOut", writeOut);
                                        break;
                                    case 3:
                                        writeOut = wishItems[i].productSystNum + "\n";
                                        Log.i("WriteOut", writeOut);
                                        break;
                                    case 4:
                                        writeOut = wishItems[i].category + "\n";
                                        Log.i("WriteOut", writeOut);
                                        break;
                                }
                                lineCount = lineCount + 1;
                                outputStream.write(writeOut.getBytes());
                            }
                        }
                        lineCount = lineCount / 5;
                        outputStream.close();
                        Log.i("LineCount", Integer.toString(lineCount));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

            Log.i("File Exists", "true");

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
                        lines = lines / 5;

                        Log.i("Lines", Integer.toString(lines));
                    } else {
                        Log.i("Running", "Yes");
                        wishItems = new Product[lines];
                        Log.i("Num of Items", Integer.toString(wishItems.length));
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
                            Log.i("Line not Null", "True");
                            switch (pCount) {
                                case 0:
                                    pImg = Integer.parseInt(line);
                                    Log.i("pImg", Integer.toString(pImg));
                                    break;
                                case 1:
                                    pTitle = line;
                                    Log.i("pTitle", pTitle);
                                    break;
                                case 2:
                                    pSrtDesc = line;
                                    Log.i("pSrtDesc", pSrtDesc);
                                    break;
                                case 3:
                                    pProductNum = line;
                                    Log.i("pProductNum", pProductNum);
                                    break;
                                case 4:
                                    pCategory = line;
                                    Log.i("pCategory", pCategory);
                            }
                            Log.i("pCount", Integer.toString(pCount));
                            Log.i("iCount", Integer.toString(iCount));
                            if (pCount == 4) {
                                wishItems[iCount] = new Product(pImg, pTitle, pSrtDesc,
                                        pProductNum, pCategory);
                                Log.i("ProNum", Integer.toString(iCount));
                                Log.i("Image", Integer.toString(wishItems[iCount].image));
                                Log.i("Title", wishItems[iCount].title);
                                Log.i("ShortDesc", wishItems[iCount].shortDesc);
                                Log.i("Category", wishItems[iCount].category);
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
