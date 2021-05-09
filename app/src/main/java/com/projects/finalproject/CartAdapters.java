package com.projects.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.projects.finalproject.Database.OrderC;

public class CartAdapters extends Fragment {


    public CartAdapters(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_blank, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}