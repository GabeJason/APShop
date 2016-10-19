package com.apshop.actionplus.apshop;

/**
 * Created by Cameron on 10/10/2016.
 */

public class Product {

    int image;
    String title;
    String shortDesc;
    String productSystNum;

    public Product(){
        super();
    }

    public Product(int inImage, String inTitle, String inShortDesc, String inProductSystNum){
        super();
        image = inImage;
        title = inTitle;
        shortDesc = inShortDesc;
        productSystNum = inProductSystNum;
    }
}
