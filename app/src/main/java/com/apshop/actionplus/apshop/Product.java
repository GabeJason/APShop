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

    public Product(){
        super();
    }

    public Product(int inImage, String inTitle, String inShortDesc, String inProductSystNum, String inCategory){
        super();
        image = inImage;
        title = inTitle;
        shortDesc = inShortDesc;
        productSystNum = inProductSystNum;
        category = inCategory;
    }
}
