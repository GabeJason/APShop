package com.apshop.actionplus.apshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Gabriel Jason on 12/15/2016.
 */

public class VPCanvas extends View {

    Bitmap.Config config = Bitmap.Config.ARGB_8888;
    BitmapFactory.Options opts = new BitmapFactory.Options();
    int[][] logoBit;
    int[] logoBitTemp;
    int[][] backBit;
    int[] backBitTemp;
    boolean logoLoaded = false;
    BitmapDrawable bd;
    boolean loaded;
    boolean colorChanged;
    int logoWidth;
    int logoHeight;
    int ProductNum;
    int[] placement;
    int imgNum;
    boolean whiteSpace;
    Bitmap logoBitmapOrg;
    boolean first;

    public VPCanvas(Context context, int product){
        super(context);
        ProductNum = product;
        placement = logoPlacement(product);
        whiteSpace = true;
        first = false;
    }

    public VPCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        opts.inMutable = true;
    }

    public boolean setLogo(Bitmap map){

        logoBitmapOrg = map;

        logoWidth = map.getWidth();
        logoHeight = map.getHeight();

        logoLoaded =true;

        Bitmap scaled = scaleBit(map);

        logoBitTemp = new int[scaled.getWidth() * scaled.getHeight()];
        scaled.getPixels(logoBitTemp, 0, scaled.getWidth(), 0, 0, scaled.getWidth(), scaled.getHeight());

        logoBit = new int[scaled.getHeight()][scaled.getWidth()];

        int count = 0;
        for (int i = 0; i < scaled.getHeight(); i++){
            for (int p = 0; p < scaled.getWidth(); p++){
                logoBit[i][p] = logoBitTemp[count];
                count++;
            }
        }

        return true;
    }

    public boolean setBack(Bitmap map, int img){
        imgNum = img;
        backBitTemp = new int[map.getWidth() * map.getHeight()];
        map.getPixels(backBitTemp, 0, map.getWidth(), 0, 0, map.getWidth(), map.getHeight());

        backBit = new int[map.getHeight()][map.getWidth()];

        int count = 0;
        for (int i = 0; i < map.getHeight(); i++){
            for (int p = 0; p < map.getWidth(); p++){
                backBit[i][p] = backBitTemp[count];
                count++;
            }
        }

        if (logoLoaded && !first){
            loaded = setLogo(logoBitmapOrg);
            while (!loaded){}
            loaded = false;
        }

        loaded = setView();
        while (!loaded){}
        loaded = false;
        return true;
    }

    public boolean setView(){
        if(logoLoaded){

            colorChanged = false;
            thread.run();

            while(!colorChanged){}
            colorChanged = false;

            int ioffset = placement[2] / 1000;
            int poffset = placement[2] % 1000;

            poffset = poffset + ((500 - (logoBit[0].length / 2)) - poffset);

            for (int i = 0; i < logoBit.length; i++){
                for (int p = 0; p < logoBit[0].length; p++){
                    if (whiteSpace && logoBit[i][p] != Color.TRANSPARENT){
                        backBit[i + ioffset][p + poffset] = logoBit[i][p];
                    }else if(!whiteSpace) {
                        backBit[i + ioffset][p + poffset] = logoBit[i][p];
                    }
                }
            }

            int count =0;
            for (int i = 0; i < backBit.length; i++){
                for (int p = 0; p < backBit[0].length; p++){
                    backBitTemp[count] = backBit[i][p];
                    count++;
                }
            }

            Bitmap backImg = Bitmap.createBitmap(backBitTemp, 1000, 1000, config);
            bd = new BitmapDrawable(getContext().getResources(), backImg);
            first =true;
            return true;
        }else{
            Bitmap backImg = Bitmap.createBitmap(backBitTemp, 1000, 1000, config);
            bd = new BitmapDrawable(getContext().getResources(), backImg);
            return true;
        }
    }

    public Bitmap scaleBit(Bitmap map){
        int maxSize = 0;

        if (placement[0] < placement[1]){
            maxSize = placement[0];
        }else if(placement[0] >= placement[1]){
            maxSize = placement[1];
        }

        Log.i("MaxSize", Integer.toString(maxSize));

        int outWidth = 0;
        int outHeight = 0;
        int inWidth = map.getWidth();
        Log.i("inWidth", Integer.toString(inWidth));
        int inHeight = map.getHeight();
        Log.i("inHeight", Integer.toString(inHeight));
        if (placement[0] <= placement[1]) {
            if (inWidth > inHeight) {
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
                Log.i("outWidth", Integer.toString(outWidth));
                Log.i("outHeight", Integer.toString(outHeight));
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;

                Log.i("outWidth", Integer.toString(outWidth));
                Log.i("outHeight", Integer.toString(outHeight));
            }
        }else if(placement[0] > placement[1]){
            if (inWidth > inHeight) {
                maxSize = placement[0];
                outWidth = maxSize;
                float flHeight = ((float)maxSize / (float)inWidth) * inHeight;
                outHeight = (int)flHeight;
                if (outHeight > placement[1]){
                    flHeight = (float)placement[1] / (float)outHeight;
                    outWidth = (int)(outWidth * flHeight);
                    outHeight = (int)(outHeight * flHeight);
                }
                Log.i("outWidth", Integer.toString(outWidth));
                Log.i("outHeight", Integer.toString(outHeight));
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;

                Log.i("outWidth", Integer.toString(outWidth));
                Log.i("outHeight", Integer.toString(outHeight));
            }
        }

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(map, outWidth, outHeight, false);

        return resizedBitmap;
    }

    public int[][] changeColor(int[][] logo, boolean whiteBack, boolean allcolor){
        boolean changed = false;
        for (int i = 0; i < logo.length; i++){
            for(int p = 0; p < logo[0].length; p++){
                if(whiteBack && !first){
                    if(logo[i][p] == Color.WHITE){
                        logo[i][p] = Color.TRANSPARENT;
                        changed = true;
                    }else {
                        for (int t = 240; t < 255; t++) {
                            for (int q = 240; q < 255; q++) {
                                for (int r = 240; r < 255; r++) {
                                    if (logo[i][p] == Color.rgb(t, q, r)) {
                                        logo[i][p] = Color.TRANSPARENT;
                                        changed = true;
                                    }
                                }
                            }
                        }

                    }
                    if(allcolor && !changed) {
                        if (ProductNum == 3 && imgNum == 0) {
                            logo[i][p] = Color.parseColor("#acada5");
                        } else {
                            logo[i][p] = placement[3];
                        }
                    }

                    changed = false;
                }else {
                    if(allcolor && logoBit[i][p] != Color.TRANSPARENT) {
                        if (ProductNum == 3 && imgNum == 0) {
                            logo[i][p] = Color.parseColor("#acada5");
                        } else {
                            logo[i][p] = placement[3];
                        }
                    }
                }

            }
        }
        colorChanged = true;
        return logo;
    }

    public BitmapDrawable getBack(){
        return bd;
    }

    public int[] logoPlacement(int product){

        int[] place = new int[3];

        if(product == 2){
            place[0] = 240;
            place[1] = 380;
            place[2] = 484380;
            return place;
        }else if(product == 3){
            place = new int[4];
            place[0] = 400;
            place[1] = 325;
            place[2] = 214300;
            place[3] = Color.BLACK;
            return place;
        }else if(product == 4){
            place[0] = 400;
            place[1] = 290;
            place[2] = 162300;
            return place;
        }else if(product == 5){
            place = new int[4];
            place[0] = 380;
            place[1] = 55;
            place[2] = 486400;
            place[3] = Color.WHITE;
            return place;
        }else{
            place[0] = 0;
            place[1] = 0;
            place[2] = 0;
            return place;
        }
    }

    Thread thread = new Thread(new Runnable(){
        @Override
        public void run(){
            if (placement.length == 4){
                logoBit = changeColor(logoBit, whiteSpace, true);
            }else{
                logoBit = changeColor(logoBit, whiteSpace, false);
            }
        }
    });

}
