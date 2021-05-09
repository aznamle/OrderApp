package com.projects.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.projects.finalproject.Database.OrderC;

public class SummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public Fragment mAdapter;
    public static final int LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button clearthedata = findViewById(R.id.clearthedatabase);

        clearthedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletethedata = getContentResolver().delete(OrderC.OrderEntry.CONTENT_URI, null, null);
            }
        });


        getLoaderManager().initLoader(LOADER, null, this);
        ListView listView = findViewById(R.id.list);
        mAdapter = new Fragment(this, null);
        listView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                OrderC.OrderEntry._ID,
                OrderC.OrderEntry.COLUMN_NAME,
                OrderC.OrderEntry.COLUMN_QUANTITY,
                OrderC.OrderEntry.COLUMN_PRICE
        };

        return new CursorLoader(this, OrderC.OrderEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}