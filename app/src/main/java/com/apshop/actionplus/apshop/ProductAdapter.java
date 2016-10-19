package com.apshop.actionplus.apshop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Cameron on 10/11/2016.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;
    int layoutResourceId;
    Product data[] = null;

    public ProductAdapter(Context context, int layoutResourceId, Product[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProductHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ProductHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imageLI);
            holder.txtTitle = (TextView)row.findViewById(R.id.titleTVLI);
            holder.txtProdNum = (TextView)row.findViewById(R.id.prodNumTVLI);

            row.setTag(holder);
        }
        else
        {
            holder = (ProductHolder)row.getTag();
        }

        Product product = data[position];
        holder.txtTitle.setText(product.title);
        holder.imgIcon.setImageResource(product.image);
        holder.txtProdNum.setText(product.productSystNum);

        return row;
    }

    static class ProductHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtProdNum;
    }

}
