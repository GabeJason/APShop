package com.apshop.actionplus.apshop;

/**
 * Created by Gabriel Jason on 10/10/2016.
 */

public class Product {

    int image;
    String title;
    String shortDesc;
    String productSystNum;
    String category;
    boolean vrmode = false;
    boolean vrproof = false;

    public Product(){
        super();
    }

    public Product(int inImage, String inTitle, String inShortDesc, String inProductSystNum, String inCategory, boolean inVrMode, boolean inVrProof){
        super();
        image = inImage;
        title = inTitle;
        shortDesc = inShortDesc;
        productSystNum = inProductSystNum;
        category = inCategory;
        vrmode = inVrMode;
        vrproof = inVrProof;
    }

    public String[] getColors(int ID) {
        if (ID == 2){
            String[] s = new String[8];
            s[0] = "#000000";
            s[1] = "#00a2c3";
            s[2] = "#7d7670";
            s[3] = "#01d801";
            s[4] = "#f17c1f";
            s[5] = "#ea467b";
            s[6] = "#FFFFFF";
            s[7] = "#c8d929";
            return s;
        }else if (ID == 3){
            String[] s = new String[10];
            s[0] = "#000000";
            s[1] = "#1a3d77";
            s[2] = "#3e6f1e";
            s[3] = "#0b6c7c";
            s[4] = "#b2591d";
            s[5] = "#9a1a3b";
            s[6] = "#6c2e81";
            s[7] = "#6c1d22";
            s[8] = "#acada5";
            s[9] = "#FFFFFF";
            return s;
        }else if (ID == 4){
            String[] s = new String[3];
            s[0] = "#000000";
            s[1] = "#acada5";
            s[2] = "#FFFFFF";
            return s;
        }else if (ID == 5){
            String[] s = new String[5];
            s[0] = "#000000";
            s[1] = "#006ab5";
            s[2] = "#857c7a";
            s[3] = "#3cac49";
            s[4] = "#3cac49";
            return s;
        }else{
            String[] s = new String[1];
            s[0] = "NO";
            return s;
        }

    }

    public int[] getImages(int ID){
        if (ID == 2){
            int[] s = new int[8];
            s[0] = R.drawable.impact26black;
            s[1] = R.drawable.impact26blue;
            s[2] = R.drawable.impact26gray;
            s[3] = R.drawable.impact26green;
            s[4] = R.drawable.impact26orange;
            s[5] = R.drawable.impact26pink;
            s[6] = R.drawable.impact26white;
            s[7] = R.drawable.impact26yellow;
            return s;
        }else if (ID == 3){
            int[] s = new int[10];
            s[0] = R.drawable.him20black;
            s[1] = R.drawable.him20blue;
            s[2] = R.drawable.him20green;
            s[3] = R.drawable.him20lightblue;
            s[4] = R.drawable.him20orange;
            s[5] = R.drawable.him20pink;
            s[6] = R.drawable.him20purple;
            s[7] = R.drawable.him20red;
            s[8] = R.drawable.him20silver;
            s[9] = R.drawable.him20white;
            return s;
        }else if (ID == 4){
            int[] s = new int[3];
            s[0] = R.drawable.continental30black;
            s[1] = R.drawable.continental30silver;
            s[2] = R.drawable.continental30white;
            return s;
        }else if (ID == 5){
            int[] s = new int[5];
            s[0] = R.drawable.airtextblack;
            s[1] = R.drawable.airtextblue;
            s[2] = R.drawable.airtextgray;
            s[3] = R.drawable.airtextgreen;
            s[4] = R.drawable.airtextred;
            return s;
        }else{
            int[] s = new int[1];
            s[0] = 0;
            return s;
        }
    }
}
