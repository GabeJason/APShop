package com.apshop.actionplus.apshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Gabriel Jason on 12/15/2016.
 */

public class VPCanvas extends View {

    int width;
    int height;
    float viewWidth;
    float viewHeight;
    Bitmap.Config config = Bitmap.Config.ARGB_8888;
    int[][] permitSq = new int[180][200]; //left corner is x 200   y 140
    BitmapFactory.Options opts = new BitmapFactory.Options();
    int[] logoBit;
    int[] backBit;
    boolean logoLoaded = false;
    BitmapDrawable bd;
    boolean loaded;
    int logoWidth;
    int logoHeight;

    public VPCanvas(Context context){
        super(context);
    }

    public VPCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        opts.inMutable = true;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.viking20tumbler, opts );
        int[] pixels1 = new int[bm.getWidth() * bm.getHeight()];
        bm.getPixels(pixels1, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());

        Log.i ("BM Width", Integer.toString(bm.getWidth()));
        Log.i("Mutable", Boolean.toString(bm.isMutable()));
        //bm.setPixels(pixels1, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());
        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.actionpluslogo25, opts);
        int[] pixels2 = new int[b2.getWidth() * b2.getHeight()];
        b2.getPixels(pixels2, 0, b2.getWidth(), 0, 0, b2.getWidth(), b2.getHeight());
        Log.i ("B2 Width", Integer.toString(b2.getWidth()));
        int b2width = (int) Math.round(b2.getWidth() * .1666);
        Log.i ("B2 Width", Integer.toString(b2width));
        Log.i ("B2 Height", Integer.toString(b2.getHeight()));
        int b2height = (int) Math.round(b2.getHeight() * .1666);
        Log.i ("B2 Height", Integer.toString(b2height));
        //float pixlen = pixels2.length;
        //int scale2 = (int)Math.round(pixlen * .25);
        int[][] pixels2M = new int[650][1200];


        int mcount = 0;
        for (int i = 0; i < b2.getHeight(); i++){
            for (int p = 0; p < b2.getWidth(); p++){
                pixels2M[i][p] = pixels2[mcount];
                mcount++;
            }
        }


        int[] pixels2Scale = new int[b2width * b2height];

        mcount = 0;
        for (int i = 0; i < b2.getHeight(); i = i + 6){
            for (int p = 0; p < b2.getWidth(); p = p + 6){
                if (mcount < pixels2Scale.length) {
                    pixels2Scale[mcount] = pixels2M[i][p];
                    mcount++;
                }
            }
        }

        Log.i ("Testing","Testing");
        pixels2 = pixels2Scale;
        boolean changed = false;
        for (int i = 0; i < pixels2.length; i++){
            if (pixels2Scale[i] == Color.WHITE) {
                pixels2Scale[i] = Color.TRANSPARENT;
                changed = true;
            }else {
                for (int p = 251; p < 256; p++) {
                    for (int q = 251; q < 256; q++) {
                        for (int r = 251; r < 256; r++) {
                            if (pixels2Scale[i] == Color.rgb(p, q, r)) {
                                pixels2Scale[i] = Color.TRANSPARENT;
                                changed = true;
                            }
                        }
                    }
                }

            }
            if(!changed) {
                pixels2Scale[i] = Color.BLACK;
            }
            changed = false;

        }

        Bitmap b2Temp = Bitmap.createBitmap(pixels2Scale, b2width, b2height, config);
        b2 = b2Temp;
        Log.i ("B2 Width", Integer.toString(b2.getWidth()));
        //b2.setPixels(pixels2, 0, b2.getWidth(), 0, 0, b2.getWidth(), b2.getHeight());


        if(bm.getWidth() >= b2.getWidth()){
            width = bm.getWidth();
        }else{
            width = b2.getWidth();
        }

        Log.i("Comb Width", Integer.toString(width));
        if(bm.getHeight() >= b2.getHeight()){
            height = bm.getHeight();
        }else{
            height = b2.getHeight();
        }
        Log.i("Comb Height", Integer.toString(height));
        int[] pixels = new int[width * height];

    /*    for (int i = 0; i < pixels.length; i++){
            pixels[i] = Color.BLUE;
        }*/

        int spacing = 0;
        if ((b2.getWidth() - bm.getWidth()) > 0){
            spacing = (b2.getWidth() - bm.getWidth());
        }
        int n = 0;
        int q =0;

        int count = 0;
        Log.i ("Spacing", Integer.toString(spacing));
        for (int i = 0; i < pixels.length; i++){
            if(n < pixels1.length) {
                pixels[i] = pixels1[n];
                count = count + 1;
                n++;
                if ( i > 1) {
                    if (i == bm.getWidth()){
                        q = i + spacing;
                        count = 0;
                        while (i < q) {
                            pixels[i] = Color.YELLOW;
                            i = i + 1;
                        }
                    }else if (i > bm.getWidth() && count > (bm.getWidth() - 1) && spacing > 0) {
                        count = 0;
                        //Log.i("I", Integer.toString(i));
                        q = i + spacing;
                       // Log.i("Spacing", Integer.toString(q));
                    /*for (i = i + 1; i < q; i++) {
                        pixels[i] = Color.WHITE;
                    }*/
                        i = i + 1;
                        while (i < q) {
                            pixels[i] = Color.YELLOW;
                            i = i + 1;
                        }
                    }
                }
            }else{
                pixels[i] = Color.YELLOW;
            }

        }

        spacing = 0;
        if ((bm.getWidth() - b2.getWidth()) > 0){
            spacing = (bm.getWidth() - b2.getWidth());
        }
        n = 83600;
        count = 0;
        int c =0;
        Log.i("2 Length", Integer.toString(pixels2Scale.length));
        for (int i = 0; i < pixels2Scale.length; i++){
            if (i < pixels2Scale.length) {
                if (pixels2Scale[i] == Color.BLACK) {
                    pixels[n] = Color.BLACK;
                }
                if (count > (b2.getWidth() - 1 )) {
                    n = n + spacing;
                    count = 0;
                    c = c +1;
                    //Log.i ("Line", Integer.toString(c));
                }
                n++;
                count++;
            }
        }



        Bitmap BScale = Bitmap.createBitmap(pixels, width, height, config);



        int returncalc = calcRatio();
        int[] pixelsScaled = new int[width * height];

        if (returncalc == 1) {
            for (int i = 0; i < pixelsScaled.length; i++) {
                if (i < pixels.length) {
                    pixelsScaled[i] = pixels[i];
                } else {
                    pixelsScaled[i] = Color.GREEN;
                }
            }
        }else if(returncalc > 1){
            n = 0;
            q =0;
            count = 0;
            spacing = width - returncalc;
            //Log.i ("Spacing", Integer.toString(spacing));
            for (int i = 0; i < pixelsScaled.length; i++){
                if(n < pixels.length) {
                    pixelsScaled[i] = pixels[n];
                    count = count + 1;
                    n++;
                    if ( i > 1) {
                        if (i == returncalc){
                            q = i + spacing;
                            count = 0;
                            while (i < q) {
                                pixelsScaled[i] = Color.YELLOW;
                                i = i + 1;
                            }
                        }else if (i > returncalc && count > (returncalc - 1)) {
                            count = 0;
                           // Log.i("I", Integer.toString(i));
                            q = i + spacing;
                           // Log.i("Spacing", Integer.toString(q));
                            i = i + 1;
                            while (i < q && i < pixelsScaled.length) {
                                pixelsScaled[i] = Color.YELLOW;
                                i = i + 1;
                            }
                        }
                    }
                }else if (i < pixelsScaled.length){
                    pixelsScaled[i] = Color.YELLOW;
                }

            }
        }
        Log.i ("Width before", Integer.toString(width));
        Bitmap combBit = Bitmap.createBitmap(pixelsScaled, width, height, config);
        BitmapDrawable bd = new BitmapDrawable(getContext().getResources(), combBit);

        //getRootView().setBackground(bd);
    }

    public int calcRatio(){
        viewWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, getResources().getDisplayMetrics());
        viewHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());

        Log.i ("View Width", Float.toString(viewWidth));
        Log.i ("View Height", Float.toString(viewHeight));
        boolean testheight = false;
        boolean testwidth = false;
        boolean equal = false;


        float fwidth = width;
        float fheight = height;

        int oldWidth = 0;


        float ratioViewWH = viewHeight / viewWidth; // 900/500 = 1.8 to multiply width to get proper height
        float ratioViewHW = viewWidth / viewHeight; // 500/900 = .55555 to multiply height to get proper width

        float ratioWH = fheight / fwidth; // 900/500 = 1.8 to multiply width to get proper height
        float ratioHW = fwidth / fheight; // 500/900 = .55555 to multiply height to get proper width

        Log.i ("RatioView W H", Float.toString(ratioViewWH));
        Log.i ("RatioView H W", Float.toString(ratioViewHW));

        Log.i ("Ratio W H", Float.toString(ratioWH));
        Log.i ("Ratio H W", Float.toString(ratioHW));

        if (ratioViewWH > ratioWH) {
            testwidth = true;
        }

        if (ratioViewHW > ratioHW){
            testheight = true;
        }

        Log.i ("TestWidth", Boolean.toString(testwidth));
        Log.i ("TestHeight", Boolean.toString(testheight));

        Log.i ("Height", Integer.toString(height));
        if (testwidth){
            height = Math.round(fwidth * ratioViewWH);
            Log.i ("Height", Integer.toString(height));
            return 1;
        }

        if (testheight){
            oldWidth = width;
            width = Math.round(fheight * ratioViewHW);
            return oldWidth;
        }
        return 0;

    }

    public boolean setLogo(Bitmap map){
        logoBit = new int[map.getWidth() * map.getHeight()];
        map.getPixels(logoBit, 0, map.getWidth(), 0, 0, map.getWidth(), map.getHeight());

        logoLoaded =true;
        loaded = setView();
        while (!loaded){}
        loaded = false;
        return true;
    }

    public boolean setBack(Bitmap map){
        backBit = new int[map.getWidth() * map.getHeight()];
        map.getPixels(backBit, 0, map.getWidth(), 0, 0, map.getWidth(), map.getHeight());
        loaded = setView();
        while (!loaded){}
        loaded = false;
        return true;
    }

    public boolean setView(){
        if(logoLoaded){
            Bitmap backImg = Bitmap.createBitmap(backBit, 1000, 1000, config);
            bd = new BitmapDrawable(getContext().getResources(), backImg);
            return true;
        }else{
            Bitmap backImg = Bitmap.createBitmap(backBit, 1000, 1000, config);
            bd = new BitmapDrawable(getContext().getResources(), backImg);
            return true;
        }
    }

    public BitmapDrawable getBack(){
        return bd;
    }

}
