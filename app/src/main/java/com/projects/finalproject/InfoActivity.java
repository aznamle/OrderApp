package com.projects.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.finalproject.Database.OrderC;

public class InfoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageView imageView;
    TextView quantitynumber, itemNameInfo, ItemPrice;
    Button addtoCart, plusquantity, minusquantity;
    int quantity;
    public Uri mCurrentCartUri;
    boolean RequiredValues = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        itemNameInfo = findViewById(R.id.itemNameInfo);
        quantitynumber = findViewById(R.id.quantity);
        ItemPrice = findViewById(R.id.ItemPrice);
        addtoCart = findViewById(R.id.addtocart);

        itemNameInfo.setText("NVIDIA GeForce RTX 3060 Ti");
        imageView.setImageResource(R.drawable.card1);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, SummaryActivity.class);
                startActivity(intent);
                SaveCart();
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = 399;
                quantity++;
                displayQuantity();
                int price = basePrice * quantity;
                String setnewPrice = String.valueOf("$" + price);
                ItemPrice.setText(setnewPrice);
            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 5;
                if(quantity == 0) {
                    Toast.makeText(InfoActivity.this, "Must add more than 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                }
            }
        });
    }

    private boolean SaveCart() {

        //get values from views
        String name = itemNameInfo.getText().toString();
        String quantity = quantitynumber.getText().toString();
        String price = ItemPrice.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderC.OrderEntry.COLUMN_NAME, name);
        values.put(OrderC.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderC.OrderEntry.COLUMN_QUANTITY, quantity);

        if(mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderC.OrderEntry.CONTENT_URI, values);
            if(newUri==null) {
                Toast.makeText(this, ":Failed to add to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success, adding to cart", Toast.LENGTH_SHORT).show();
            }
        }

        RequiredValues = true;
        return RequiredValues;
    }

    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                OrderC.OrderEntry._ID,
                OrderC.OrderEntry.COLUMN_NAME,
                OrderC.OrderEntry.COLUMN_QUANTITY,
                OrderC.OrderEntry.COLUMN_PRICE
        };

        return new CursorLoader(this, mCurrentCartUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor == null || cursor.getCount() < 1) {
            return;
        }

        if(cursor. moveToFirst()) {
            int name = cursor.getColumnIndex(OrderC.OrderEntry.COLUMN_NAME);
            int quantity = cursor.getColumnIndex(OrderC.OrderEntry.COLUMN_QUANTITY);
            int price = cursor.getColumnIndex(OrderC.OrderEntry.COLUMN_PRICE);

            String itemname = cursor.getString(name);
            String itemprice = cursor.getString(price);
            String itemquantity = cursor.getString(quantity);

            itemNameInfo.setText(itemname);
            ItemPrice.setText(itemprice);
            quantitynumber.setText(itemquantity);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        itemNameInfo.setText("");
        ItemPrice.setText("");
        quantitynumber.setText("");

    }
}