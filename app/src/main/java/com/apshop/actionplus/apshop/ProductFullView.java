package com.apshop.actionplus.apshop;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ProductFullView extends AppCompatActivity {

    int ProductNum = 0;
    ImageButton exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_full_view);
        ProductNum = Integer.parseInt(getIntent().getExtras().getString("item"));

        exitBtn = (ImageButton)findViewById(R.id.exitItemBtn);

        ImageView productImg = (ImageView)findViewById(R.id.imagePFV);
        TextView productTitle = (TextView)findViewById(R.id.titlePFVTV);
        TextView productDesc = (TextView)findViewById(R.id.descPFVTV);
        TextView productABTitle = (TextView)findViewById(R.id.itemNameABTV);
        TextView prodNumTV = (TextView)findViewById(R.id.prodNumPFVTV);

        String filename = "ProductData.txt";
        File dataFile = new File(getApplicationContext().getFilesDir(), filename);

        FileInputStream inputStream = null;
        BufferedReader reader = null;
        try{
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
                    int pImg = 0;
                    String pTitle = "";
                    String pSrtDesc = "";
                    String sysProdNum = "";
                    String pCategory = "";
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
                        Log.i("pCount",Integer.toString(pCount));
                        Log.i("iCount",Integer.toString(iCount));
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
                }
            }


            inputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
