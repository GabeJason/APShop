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



    public VPCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inMutable = true;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.viking20tumbler, opts );
        int[] pixels1 = new int[bm.getWidth() * bm.getHeight()];
        bm.getPixels(pixels1, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());
        /*for (int i = 0; i < pixels1.length; i++){
            if (pixels1[i] == Color.WHITE) {
                pixels1[i] = Color.TRANSPARENT;
            }else {
                for (int p = 251; p < 256; p++) {
                    for (int q = 251; q < 256; q++) {
                        for (int r = 251; r < 256; r++) {
                            if (pixels1[i] == Color.rgb(p, q, r)) {
                                pixels1[i] = Color.TRANSPARENT;
                            }
                        }
                    }
                }
            }

        }*/
        Log.i("Mutable", Boolean.toString(bm.isMutable()));
        //bm.setPixels(pixels1, 0, bm.getWidth(), 0, 0, bm.getWidth(), bm.getHeight());
        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.actionpluslogo25, opts);
        int[] pixels2 = new int[b2.getWidth() * b2.getHeight()];
        b2.getPixels(pixels2, 0, b2.getWidth(), 0, 0, b2.getWidth(), b2.getHeight());
        boolean changed = false;
        for (int i = 0; i < pixels2.length; i++){
            if (pixels2[i] == Color.WHITE) {
                pixels2[i] = Color.TRANSPARENT;
                changed = true;
            }else {
                for (int p = 251; p < 256; p++) {
                    for (int q = 251; q < 256; q++) {
                        for (int r = 251; r < 256; r++) {
                            if (pixels2[i] == Color.rgb(p, q, r)) {
                                pixels2[i] = Color.TRANSPARENT;
                                changed = true;
                            }
                        }
                    }
                }

            }
            if(!changed) {
                pixels2[i] = Color.BLUE;
            }
            changed = false;

        }
        b2.setPixels(pixels2, 0, b2.getWidth(), 0, 0, b2.getWidth(), b2.getHeight());

        int width;
        int height;
        if(bm.getWidth() >= b2.getWidth()){
            width = bm.getWidth();
        }else{
            width = b2.getWidth();
        }
        if(bm.getHeight() >= b2.getHeight()){
            height = bm.getHeight();
        }else{
            height = b2.getHeight();
        }

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
        /*int p = 0;
        int prevw = 0;
        boolean heightR = false;
        for (int i = 0; i < height; i++){
            for (int w = 0; w < width; w++){

                if (w < bm.getWidth() && !heightR){
                    pixels[w + prevw] = pixels1[p];
                    p++;
                }else{
                    pixels[w + prevw] = Color.YELLOW;
                }

            }
            prevw = prevw + width;
            if (i == bm.getHeight()){
                heightR = true;
            }
        }*/
        Log.i ("Spacing", Integer.toString(spacing));
        for (int i = 0; i < pixels.length; i++){
            if(n < pixels1.length) {
                pixels[i] = pixels1[n];
                n++;
                if ((i / bm.getWidth()) == 1 && spacing > 0) {
                    q = i +spacing;
                    /*for (i = i + 1; i < q; i++) {
                        pixels[i] = Color.WHITE;
                    }*/
                    i = i + 1;
                    while (i < q){
                        pixels[i] = Color.BLUE;
                        i = i + 1;
                    }
                }
            }else{
                pixels[i] = Color.WHITE;
            }

        }

        spacing = 0;
        if ((bm.getWidth() - b2.getWidth()) > 0){
            spacing = (bm.getWidth() - b2.getWidth());
        }
        n = 0;
        for (int i = 0; i < pixels2.length; i++){
            if (n < pixels2.length) {
                if (pixels2[i] == Color.BLUE) {
                    pixels[n] = Color.BLUE;
                }
                if ((i / b2.getWidth()) == 1) {
                    n = n + spacing;
                }
                n++;
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap combBit = Bitmap.createBitmap(pixels, width, height, config);
        BitmapDrawable bd = new BitmapDrawable(getContext().getResources(), combBit);
        getRootView().setBackground(bd);
    }
}
