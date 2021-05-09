package com.projects.finalproject.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderP extends ContentProvider {

    //path of modification in table
    public static final int ORDER = 100;

    public static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(OrderC.CONTENT_AUTHORITY, OrderC.PATH, ORDER);
    }

    public OrderHelper mHelper;


    @Override
    public boolean onCreate() {
        mHelper = new OrderHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch(match) {
            case ORDER:
                cursor = database.query(OrderC.OrderEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match((uri));
        switch(match) {
            case ORDER:
                return insertCart(uri, values);

            default:
                throw new IllegalArgumentException("Cannot insert data");
        }
    }

    private Uri insertCart(Uri uri, ContentValues values) {
        String name = values.getAsString(OrderC.OrderEntry.COLUMN_NAME);
        if(name == null) {
            throw new IllegalArgumentException("Name is Required");
        }


        String quantity = values.getAsString(OrderC.OrderEntry.COLUMN_QUANTITY);
        if(quantity == null) {
            throw new IllegalArgumentException("Quantity is Required");
        }

        String price = values.getAsString(OrderC.OrderEntry.COLUMN_PRICE);
        if(price == null) {
            throw new IllegalArgumentException("Price is Required");
        }

        SQLiteDatabase database = mHelper.getWritableDatabase();
        long id = database.insert(OrderC.OrderEntry.TABLE_NAME, null, values);

        if (id == -1) {
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted;

        SQLiteDatabase database = mHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        switch(match) {
            case ORDER:
                rowsDeleted = database.delete(OrderC.OrderEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Cannot delete");
        }

        if(rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);

        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
