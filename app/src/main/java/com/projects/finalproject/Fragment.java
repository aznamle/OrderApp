package com.projects.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.projects.finalproject.Database.OrderC;

public class Fragment extends CursorAdapter {

    public Fragment(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_blank, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //get views

        TextView Rname, Rquantity, Rprice;

        Rname = view.findViewById(R.id.itemName);
        Rquantity = view.findViewById(R.id.itemQuantity);
        Rprice = view.findViewById(R.id.itemPrice);


        //get positions of columns
        int name = cursor.getColumnIndex(OrderC.OrderEntry.COLUMN_NAME);
        int quantity = cursor.getColumnIndex(OrderC.OrderEntry.COLUMN_QUANTITY);
        int price = cursor.getColumnIndex(OrderC.OrderEntry.COLUMN_PRICE);

        String itemname = cursor.getString(name);
        String itemprice = cursor.getString(price);
        String itemquantity = cursor.getString(quantity);

        //setting text in views

        Rname.setText(itemname);
        Rprice.setText(itemprice);
        Rquantity.setText("(" + itemquantity + ")");

    }
}
