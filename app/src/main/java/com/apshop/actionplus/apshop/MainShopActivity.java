package com.apshop.actionplus.apshop;

import android.app.ActionBar;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainShopActivity extends AppCompatActivity {


    private ListView productListV;
    private Product product_data[];
    ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);

        menuBtn = (ImageButton)findViewById(R.id.menuBtn);
        menuBtn.setTag("1");


        String filename = "ProductData.txt";
        File dataFile = new File(getApplicationContext().getFilesDir().getPath(), filename);
        Log.i("FilePath",dataFile.getAbsolutePath());

        if (!dataFile.exists()){

            Log.i("File Exists","false");

            product_data = new Product[]



                    {
                            new Product(R.drawable.viking20tumbler, "20 Oz. Viking Tumbler", "20 Oz. double wall vacuum stainless steel bottom. Press-in lid. Keeps liquid hot for 5 1/2 hours. Keeps liquid cold for 24 hours. Silver, Black, Maroon Red, Navy Blue.", "YIDJI-KWXUS"),
                            new Product(R.drawable.hugocopper30tumbler, "Mega Hugo Copper Vacuum Insulated Tumbler 30 Oz.", "Do not miss the most trendy tumbler of the year. Durable, double-wall stainless steel vacuum construction with copper insulation, which allows your cold beverage to stay cold for 24 hours and at least 6 hours for hot beverages. The construction also prevents condensation on the outside of the tumbler. Easy sipping, push-on lid. Wide opening for comfortable filling and pouring. Design features the spinning, geometric bottom. 30oz. 8.86\" H x 3.86\" Diameter. Silver, Black", "QIHJB-KWYJV"),
                            new Product(R.drawable.impacttumbler26, "Impact 26 oz H2O to Go Tumbler", "26 oz double wall 18/8 stainless steel thermal bottle with copper vacuum insulation and threaded stainless steel lid. Hot 12 hours/Cold 24 hours. Gloss white, neon pink, neon orange, neon yellow, neon green, neon blue, matte gray, matte black.", "SKDBH-JUYCR"),
                            new Product(R.drawable.himylayiantumbler20, "Everest 20 oz Tumbler", "Stainless Steel Outer And Inner. Double Wall Construction For Insulation Of Hot Or Cold Liquids. Spill-Resistant Slide Action Lid With Rubber Gasket. Due To Vacuum Insulation Technology, Capacity Is 18 Oz. With Lid On. Meets FDA Requirements. BPA Free. Hand Wash Recommended. Keeps Drinks Hot Or Cold Up To 6 Hours. Silver or White, both with Clear Lid. Matte: Black with Charcoal Lid. Metallic: Blue or Red with Charcoal Lid. Metallic: Fuchsia, Green, Orange, Purple or Teal, all with Clear Lid.", "GKHCE-KWVNS"),
                            new Product(R.drawable.continental30tumbler, "Continental 30 oz Tumbler", "30 ounces. Double wall stainless steel. Copper vacuum insulation. Push on, sip through lid. Perfect for hot and cold. Silver, Matte Black.", "NCGEB-KXHER"),
                            new Product(R.drawable.airtextstyluspen, "Airtext Stylus Pen", "Designed for advanced connectivity with all touchscreen devices, this promotional stylus pen hits all the right buttons! Shimmering jewel tones are set off by striking silver tone accents while the elegant diamond etched grip provides writing comfort. Each ballpoint pen contains a smooth-writing  ink cartridge. Silver engraved with your logo or personalized message, this pen is a perfect gift for valued clients, customers, and executives. For your convenience, each pen is individually cellophane wrapped.", "AJGHE-JKTRE"),
                            new Product(R.drawable.palmbeachumbrella, "Palm Beach 60\" Steel Golf Umbrella", "Solid colors: 190T polyester, two tone colors: half polyester/half nylon. Full size vented golf umbrella. 60\" arc. Manual opening. Large polyester canopy. Sturdy metal shaft with wooden handle. 39\" L. Black, Khaki Beige, Navy Blue, Navy Blue/Khaki Beige, Red, Royal Blue, White/Black, White/Royal Blue.", "CDFBJ-JJKCT")
                    };

            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream outputStream = null;

            String writeOut = "";

            int lineCount = 0;

            try {

                outputStream = new FileOutputStream(dataFile);

                for (int i = 0; i < product_data.length; i++) {

                    for (int p = 0; p < 4; p++) {

                        switch (p){
                            case 0:
                                writeOut = Integer.toString(product_data[i].image) + "\n";
                                Log.i("WriteOut", writeOut);
                                break;
                            case 1:
                                writeOut = product_data[i].title + "\n";
                                Log.i("WriteOut", writeOut);
                                break;
                            case 2:
                                writeOut = product_data[i].shortDesc + "\n";
                                Log.i("WriteOut", writeOut);
                                break;
                            case 3:
                                writeOut = product_data[i].productSystNum + "\n";
                                Log.i("WriteOut", writeOut);
                                break;
                        }

                        lineCount = lineCount + 1;

                        outputStream.write(writeOut.getBytes());


                    }
                }
                lineCount = lineCount / 4;
                outputStream.close();
                Log.i("LineCount", Integer.toString(lineCount));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

            Log.i("File Exists","true");

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
                        lines = lines / 4;

                        Log.i("Lines", Integer.toString(lines));
                    } else {
                        Log.i("Running", "Yes");
                        product_data = new Product[lines];
                        Log.i("Num of Items",Integer.toString(product_data.length));
                        inputStream = new FileInputStream(dataFile);
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = reader.readLine();
                        int pImg = 0;
                        String pTitle = "";
                        String pSrtDesc = "";
                        String pProductNum = "";
                        int pCount = 0;
                        int iCount = 0;
                        while (line != null) {
                            Log.i("Line not Null", "True");
                            switch (pCount) {
                                case 0:
                                    pImg = Integer.parseInt(line);
                                    Log.i("pImg",Integer.toString(pImg));
                                    break;
                                case 1:
                                    pTitle = line;
                                    Log.i("pTitle",pTitle);
                                    break;
                                case 2:
                                    pSrtDesc = line;
                                    Log.i("pSrtDesc",pSrtDesc);
                                    break;
                                case 3:
                                    pProductNum = line;
                                    Log.i("pProductNum", pProductNum);
                                    break;
                            }
                            Log.i("pCount",Integer.toString(pCount));
                            Log.i("iCount",Integer.toString(iCount));
                            if (pCount == 3) {
                                product_data[iCount] = new Product(pImg, pTitle, pSrtDesc, pProductNum);
                                Log.i("ProNum", Integer.toString(iCount));
                                Log.i("Image", Integer.toString(product_data[iCount].image));
                                Log.i("Title", product_data[iCount].title);
                                Log.i("ShortDesc", product_data[iCount].shortDesc);
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
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        ProductAdapter adapter = new ProductAdapter(this, R.layout.row, product_data);

        productListV = (ListView)findViewById(R.id.mainProductLV);

        productListV.setAdapter(adapter);

        productListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductFullView.class);
                intent.putExtra("item",Integer.toString(position));
                startActivity(intent);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(menuBtn.getTag()==("1")) {
                    menuBtn.setBackgroundResource(R.drawable.exitbtnimg);
                    menuBtn.setTag("2");
                }else{
                    menuBtn.setBackgroundResource(R.drawable.menubtnimg);
                    menuBtn.setTag("1");
                }
            }
        });

    }
}
