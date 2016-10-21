package com.apshop.actionplus.apshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainShopActivity extends AppCompatActivity {


    private ListView productListV;
    private Product product_data[];
    ImageButton menuBtn, categorySwitch;
    RelativeLayout menuLay;
    LinearLayout menuBarView;
    EditText searchText;
    Button searchBtn, clearSearchBtn;
    int prodSearchCount;
    int checkProd[];
    ProductAdapter adapter;
    TextView noSearchResults;
    CheckBox drinkwareCK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);

        //Sets up hide keyboard on any click event
        setupUI(findViewById(R.id.activity_main_shop));

        ////////////////// Buttons ////////////////////////
        menuBtn = (ImageButton)findViewById(R.id.menuBtn);
        menuBtn.setTag("1");
        categorySwitch = (ImageButton)findViewById(R.id.categorySwitchBtn);
        categorySwitch.setTag("1");

        clearSearchBtn = (Button)findViewById(R.id.clearSearchBtn);
        searchBtn = (Button)findViewById(R.id.searchBtn);
        ///////////////////////////////////////////////////


        ////////////////// CheckBox ////////////////////////
        drinkwareCK = (CheckBox)findViewById(R.id.drinkwareCK);
        ///////////////////////////////////////////////////


        ////////////////// EditText ////////////////////////
        searchText =(EditText)findViewById(R.id.searchET);
        ///////////////////////////////////////////////////


        ////////////////// TextView ////////////////////////
        noSearchResults = (TextView)findViewById(R.id.noResultsTxt);
        ///////////////////////////////////////////////////


        ////////////////// Layouts ////////////////////////
        menuLay = (RelativeLayout)findViewById(R.id.menuBarLay);
        menuBarView = (LinearLayout)findViewById(R.id.actualMenuView);
        ///////////////////////////////////////////////////



        //////////////// Initial Data Creation ////////////////////////

        ////// Creates File //
        String filename = "ProductData.txt";
        File dataFile = new File(getApplicationContext().getFilesDir().getPath(), filename);
        Log.i("FilePath",dataFile.getAbsolutePath());
        dataFile.delete();
        /////////////////////


        if (!dataFile.exists()){

            Log.i("File Exists","false");

            //// Creates Array to Hold Products ////
            product_data = new Product[]

                    {
                            new Product(R.drawable.viking20tumbler, "20 Oz. Viking Tumbler", "20 Oz. double wall vacuum stainless steel bottom. Press-in lid. Keeps liquid hot for 5 1/2 hours. Keeps liquid cold for 24 hours. Silver, Black, Maroon Red, Navy Blue.", "YIDJI-KWXUS", "Drinkware"),
                            new Product(R.drawable.hugocopper30tumbler, "Mega Hugo Copper Vacuum Insulated Tumbler 30 Oz.", "Do not miss the most trendy tumbler of the year. Durable, double-wall stainless steel vacuum construction with copper insulation, which allows your cold beverage to stay cold for 24 hours and at least 6 hours for hot beverages. The construction also prevents condensation on the outside of the tumbler. Easy sipping, push-on lid. Wide opening for comfortable filling and pouring. Design features the spinning, geometric bottom. 30oz. 8.86\" H x 3.86\" Diameter. Silver, Black", "QIHJB-KWYJV", "Drinkware"),
                            new Product(R.drawable.impacttumbler26, "Impact 26 oz H2O to Go Tumbler", "26 oz double wall 18/8 stainless steel thermal bottle with copper vacuum insulation and threaded stainless steel lid. Hot 12 hours/Cold 24 hours. Gloss white, neon pink, neon orange, neon yellow, neon green, neon blue, matte gray, matte black.", "SKDBH-JUYCR", "Drinkware"),
                            new Product(R.drawable.himylayiantumbler20, "Everest 20 oz Tumbler", "Stainless Steel Outer And Inner. Double Wall Construction For Insulation Of Hot Or Cold Liquids. Spill-Resistant Slide Action Lid With Rubber Gasket. Due To Vacuum Insulation Technology, Capacity Is 18 Oz. With Lid On. Meets FDA Requirements. BPA Free. Hand Wash Recommended. Keeps Drinks Hot Or Cold Up To 6 Hours. Silver or White, both with Clear Lid. Matte: Black with Charcoal Lid. Metallic: Blue or Red with Charcoal Lid. Metallic: Fuchsia, Green, Orange, Purple or Teal, all with Clear Lid.", "GKHCE-KWVNS", "Drinkware"),
                            new Product(R.drawable.continental30tumbler, "Continental 30 oz Tumbler", "30 ounces. Double wall stainless steel. Copper vacuum insulation. Push on, sip through lid. Perfect for hot and cold. Silver, Matte Black.", "NCGEB-KXHER", "Drinkware"),
                            new Product(R.drawable.airtextstyluspen, "Airtext Stylus Pen", "Designed for advanced connectivity with all touchscreen devices, this promotional stylus pen hits all the right buttons! Shimmering jewel tones are set off by striking silver tone accents while the elegant diamond etched grip provides writing comfort. Each ballpoint pen contains a smooth-writing  ink cartridge. Silver engraved with your logo or personalized message, this pen is a perfect gift for valued clients, customers, and executives. For your convenience, each pen is individually cellophane wrapped.", "AJGHE-JKTRE", "Pen"),
                            new Product(R.drawable.palmbeachumbrella, "Palm Beach 60\" Steel Golf Umbrella", "Solid colors: 190T polyester, two tone colors: half polyester/half nylon. Full size vented golf umbrella. 60\" arc. Manual opening. Large polyester canopy. Sturdy metal shaft with wooden handle. 39\" L. Black, Khaki Beige, Navy Blue, Navy Blue/Khaki Beige, Red, Royal Blue, White/Black, White/Royal Blue.", "CDFBJ-JJKCT","Umbrella")
                    };

            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /////////////////////////////////////


            ////// Writes External File //////////////////////////////////////////////////////////////      //
            FileOutputStream outputStream = null;                                                   //      //
                                                                                                    //      //
            String writeOut = "";                                                                   //      //
                                                                                                    //      //
            int lineCount = 0;                                                                      //      //
                                                                                                    //      //
            try {                                                                                   //      //
                                                                                                    //      //
                outputStream = new FileOutputStream(dataFile);                                      //      //
                                                                                                    //      //
                for (int i = 0; i < product_data.length; i++) {                                     //      //
                                                                                                    //      //
                    for (int p = 0; p < 5; p++) {                                                   //      //
                                                                                                    //      //
                        switch (p){                                                                 //      //
                            case 0:                                                                 //      //
                                writeOut = Integer.toString(product_data[i].image) + "\n";          //      //
                                Log.i("WriteOut", writeOut);                                        //      //
                                break;                                                              //      //
                            case 1:                                                                 //      //
                                writeOut = product_data[i].title + "\n";                            //      //
                                Log.i("WriteOut", writeOut);                                        //      //
                                break;                                                              //      //
                            case 2:                                                                 //      //
                                writeOut = product_data[i].shortDesc + "\n";                        //      //
                                Log.i("WriteOut", writeOut);                                        //      //
                                break;                                                              //      //
                            case 3:                                                                 //      //
                                writeOut = product_data[i].productSystNum + "\n";                   //      //
                                Log.i("WriteOut", writeOut);                                        //      //
                                break;                                                              //      //
                            case 4:                                                                 //      //
                                writeOut = product_data[i].category + "\n";                         //      //
                                Log.i("WriteOut", writeOut);                                        //      //
                                break;                                                              //      //
                        }                                                                           //      //
                                                                                                    //      //
                        lineCount = lineCount + 1;                                                  //      //
                                                                                                    //      //
                        outputStream.write(writeOut.getBytes());                                    //      //
                                                                                                    //      //
                                                                                                    //      //
                    }                                                                               //      //
                }                                                                                   //      //
                lineCount = lineCount / 5;                                                          //      //
                outputStream.close();                                                               //      //
                Log.i("LineCount", Integer.toString(lineCount));                                    //      //
            } catch (Exception e) {                                                                 //      //
                e.printStackTrace();                                                                //      //
            }                                                                                       //      //
            //////////////////////////////////////////////////////////////////////////////////////////      //
                                                                                                            //
        }else{                                                                                              //
                                                                                                            //
            /////// Reads in file created above on app start if file exists //////////////////////////////  //
            Log.i("File Exists","true");                                                                //  //
                                                                                                        //  //
            FileInputStream inputStream = null;                                                         //  //
            BufferedReader reader = null;                                                               //  //
            try{                                                                                        //  //
                int lines = 0;                                                                          //  //
                                                                                                        //  //
                for (int c = 1; c < 3; c++) {                                                           //  //
                    if (c == 1) {                                                                       //  //
                        inputStream = new FileInputStream(dataFile);                                    //  //
                        reader = new BufferedReader(new InputStreamReader(inputStream));                //  //
                        String line = reader.readLine();                                                //  //
                        while (line != null) {                                                          //  //
                            lines = lines + 1;                                                          //  //
                            line = reader.readLine();                                                   //  //
                        }                                                                               //  //
                        lines = lines / 5;                                                              //  //
                                                                                                        //  //
                        Log.i("Lines", Integer.toString(lines));                                        //  //
                    } else {                                                                            //  //
                        Log.i("Running", "Yes");                                                        //  //
                        product_data = new Product[lines];                                              //  //
                        Log.i("Num of Items",Integer.toString(product_data.length));                    //  //
                        inputStream = new FileInputStream(dataFile);                                    //  //
                        reader = new BufferedReader(new InputStreamReader(inputStream));                //  //
                        String line = reader.readLine();                                                //  //
                        int pImg = 0;                                                                   //  //
                        String pTitle = "";                                                             //  //
                        String pSrtDesc = "";                                                           //  //
                        String pProductNum = "";                                                        //  //
                        String pCategory = "";                                                          //  //
                        int pCount = 0;                                                                 //  //
                        int iCount = 0;                                                                 //  //
                        while (line != null) {                                                          //  //
                            Log.i("Line not Null", "True");                                             //  //
                            switch (pCount) {                                                           //  //
                                case 0:                                                                 //  //
                                    pImg = Integer.parseInt(line);                                      //  //
                                    Log.i("pImg",Integer.toString(pImg));                               //  //
                                    break;                                                              //  //
                                case 1:                                                                 //  //
                                    pTitle = line;                                                      //  //
                                    Log.i("pTitle",pTitle);                                             //  //
                                    break;                                                              //  //
                                case 2:                                                                 //  //
                                    pSrtDesc = line;                                                    //  //
                                    Log.i("pSrtDesc",pSrtDesc);                                         //  //
                                    break;                                                              //  //
                                case 3:                                                                 //  //
                                    pProductNum = line;                                                 //  //
                                    Log.i("pProductNum", pProductNum);                                  //  //
                                    break;                                                              //  //
                                case 4:                                                                 //  //
                                    pCategory = line;                                                   //  //
                                    Log.i("pCategory", pCategory);                                      //  //
                            }                                                                           //  //
                            Log.i("pCount",Integer.toString(pCount));                                   //  //
                            Log.i("iCount",Integer.toString(iCount));                                   //  //
                            if (pCount == 4) {                                                          //  //
                                product_data[iCount] = new Product(pImg, pTitle, pSrtDesc,              //  //
                                        pProductNum, pCategory);                                        //  //
                                Log.i("ProNum", Integer.toString(iCount));                              //  //
                                Log.i("Image", Integer.toString(product_data[iCount].image));           //  //
                                Log.i("Title", product_data[iCount].title);                             //  //
                                Log.i("ShortDesc", product_data[iCount].shortDesc);                     //  //
                                Log.i("Category", product_data[iCount].category);                       //  //
                                iCount = iCount + 1;                                                    //  //
                                pCount = 0;                                                             //  //
                                                                                                        //  //
                            } else {                                                                    //  //
                                pCount = pCount + 1;                                                    //  //
                            }                                                                           //  //
                                                                                                        //  //
                            line = reader.readLine();                                                   //  //
                                                                                                        //  //
                        }                                                                               //  //
                    }                                                                                   //  //
                }                                                                                       //  //
                                                                                                        //  //
                                                                                                        //  //
                inputStream.close();                                                                    //  //
            }catch (Exception e) {                                                                      //  //
                e.printStackTrace();                                                                    //  //
            }                                                                                           //  //
            //////////////////////////////////////////////////////////////////////////////////////////////  //
                                                                                                            //
        }                                                                                                   //
                                                                                                            //
        //////////////// End of Initial Data Creation ////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////// Product List View Creation ///////////////////////////

        /////// Creates List and Adapter //
        adapter = new ProductAdapter(this, R.layout.row, product_data);
        productListV = (ListView)findViewById(R.id.mainProductLV);
        productListV.setAdapter(adapter);
        ///////////////////////////////////

        productListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(menuBtn.getTag() == "1") {
                    Intent intent = new Intent(getApplicationContext(), ProductFullView.class);

                    if (checkProd != null) {
                        int c = 0;
                        for (int i = 0; i < checkProd.length; i++) {
                            if (c == position && checkProd[i] == 1) {
                                intent.putExtra("item", Integer.toString(i));
                                break;
                            }
                            if (checkProd[i] == 1) {
                                c = c + 1;
                            }
                        }
                        if (c == 0) {
                            intent.putExtra("item", Integer.toString(position));
                        }
                    } else {
                        intent.putExtra("item", Integer.toString(position));
                    }

                    startActivity(intent);
                    Log.i("ID OnClick", Long.toString(id));
                }

            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(menuBtn.getTag()==("1")) {
                    menuBtn.setBackgroundResource(R.drawable.exitbtnimg);
                    menuBtn.setTag("2");
                    menuLay.setVisibility(View.VISIBLE);
                    if(drinkwareCK.isChecked()){
                        categorySwitch.setTag("2");
                        categorySwitch.setBackgroundResource(R.drawable.categoryopen);
                    }
                    //newTimer();
                   // menuBarView.animate().translationX(500);
                }else{
                    menuBtn.setBackgroundResource(R.drawable.menubtnimg);
                    menuBtn.setTag("1");
                    menuLay.setVisibility(View.GONE);
                }
            }
        });

        clearSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
                adapter.data = product_data;
                productListV.setAdapter(adapter);
                menuBtn.setBackgroundResource(R.drawable.menubtnimg);
                menuBtn.setTag("1");
                menuLay.setVisibility(View.GONE);
                checkProd = null;
                noSearchResults.setVisibility(View.GONE);
                productListV.setVisibility(View.VISIBLE);
                drinkwareCK.setChecked(false);
                categorySwitch.setTag("1");
                categorySwitch.setBackgroundResource(R.drawable.categoryclose);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchTxt = String.valueOf(searchText.getText());
                Log.i("Search Text", searchTxt);
                noSearchResults.setVisibility(View.GONE);
                productListV.setVisibility(View.VISIBLE);

                if(!searchTxt.equals("")) {
                    prodSearchCount = 0;
                    checkProd = new int[product_data.length];

                    for (int i = 0; i < product_data.length; i++) {
                        Log.i("Product Num", Integer.toString(i));
                        compString(product_data[i].title.toUpperCase(), searchTxt.toUpperCase(), i);

                    }

                    if(drinkwareCK.isChecked()) {
                        for (int i = 0; i < checkProd.length; i++){
                            if(checkProd[i] == 1){
                                if(!(product_data[i].category.equals("Drinkware"))) {
                                    checkProd[i] = 0;
                                    prodSearchCount = prodSearchCount - 1;
                                }
                            }
                        }
                    }

                    Product tempProductList[] = new Product[prodSearchCount];

                    prodSearchCount = 0;

                    for (int r = 0; r < product_data.length; r++) {
                        if (checkProd[r] == 1) {
                            tempProductList[prodSearchCount] = product_data[r];
                            prodSearchCount = prodSearchCount + 1;
                        }
                    }

                    if(prodSearchCount != 0) {
                        adapter.data = tempProductList;

                        ProductAdapter tempAdapt = new ProductAdapter(adapter);

                        productListV.setAdapter(tempAdapt);
                    }else{
                        noSearchResults.setText("No Results found for: " + searchTxt);
                        noSearchResults.setVisibility(View.VISIBLE);
                        productListV.setVisibility(View.GONE );
                    }

                    menuBtn.setBackgroundResource(R.drawable.menubtnimg);
                    menuBtn.setTag("1");
                    menuLay.setVisibility(View.GONE);
                }else{
                    adapter.data = product_data;
                    productListV.setAdapter(adapter);
                    menuBtn.setBackgroundResource(R.drawable.menubtnimg);
                    menuBtn.setTag("1");
                    menuLay.setVisibility(View.GONE);
                    checkProd = null;
                    noSearchResults.setVisibility(View.GONE);
                    productListV.setVisibility(View.VISIBLE);
                }
            }
        });

        categorySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categorySwitch.getTag() == "1"){
                    categorySwitch.setBackgroundResource(R.drawable.categoryopen);
                    categorySwitch.setTag("2");
                    drinkwareCK.setVisibility(View.VISIBLE);
                }else{
                    categorySwitch.setBackgroundResource(R.drawable.categoryclose);
                    categorySwitch.setTag("1");
                    drinkwareCK.setVisibility(View.GONE);
                }
            }
        });

    }

    int counter;

    public void newTimer(){
        final ViewGroup.LayoutParams params = menuBarView.getLayoutParams();
         counter = 0;
        final int pixel = (int)convertDpToPixel(6, getApplicationContext());
        new CountDownTimer(600, 20){

            public void onTick(long millisUntilFinished) {
                counter = counter + pixel;
                params.width = counter;
                menuBarView.setLayoutParams(params);
            }

            public void onFinish() {
                menuLay.setBackgroundResource(R.drawable.graybackground);
            }
        }.start();

    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public void compString(String one, String two, int w){
        boolean check = false;

        for (int i = 0; i < one.length(); i++){
            if (one.charAt(i) == two.charAt(0)){
                Log.i("Letter Compare", "true");
                if(two.length() == 1){
                    check = true;
                    Log.i("Check", "true");
                    i = one.length();
                }else {
                    for (int p = 1; p < two.length(); p++) {
                        Log.i("Next Letter Compare", "true");
                        if (i + p < one.length()) {
                            if (one.charAt(i + p) != two.charAt(p)) {
                                check = false;
                                Log.i("Check", "false");
                                p = two.length();
                            } else if (p == two.length() - 1) {
                                check = true;
                                Log.i("Check", "true");
                                i = one.length();
                            }
                        } else {
                            check = true;
                            Log.i("Check", "true");
                            i = one.length();
                        }
                    }
                }
            }
        }

        if(check){
            checkProd[w] = 1;
            prodSearchCount = prodSearchCount + 1;
            Log.i("CheckProd", "1");
        }else{
            checkProd[w] = 0;
            Log.i("CheckProd", "0");
        }

    }

   /* public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }*/

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainShopActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}

//////////////////////////////////////////
//
//   BUGS TO FIX
//
//  1.
//  2.
//  3.
//  4.
//  5.
//
//////////////////////////////////////////