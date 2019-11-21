package com.example.a32gbfoodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

public class drinksDetail extends AppCompatActivity {
    private ElegantNumberButton quantity;
    private TextView itemName, itemPrice;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_detail);
        db = new DBHelper(this);

        quantity = (ElegantNumberButton)findViewById(R.id.quantityBtn);
        itemName = (TextView)findViewById(R.id.drinkName);
        itemPrice = (TextView)findViewById(R.id.drinkPrice);

        Bundle b = getIntent().getExtras();
        if(b.containsKey("drinkName")){
            int drinkID = b.getInt("drinkName");
            int defaultValue = 0;
            String [] drinksList = getResources().getStringArray(R.array.availableDrinks);
            String [] drinkPriceList = getResources().getStringArray(R.array.drinkPrices);
            String [] drinkDescList = getResources().getStringArray(R.array.drinkDesc);

            String drinkName = drinksList[drinkID];
            String drinkPrice = drinkPriceList[drinkID];
            String drinkDesc = drinkDescList[drinkID];

            TypedArray drinkImgs = getResources().obtainTypedArray(R.array.drinkImages);

            //Set the Text view with the name of the drink
            TextView txtName = (TextView) findViewById(R.id.drinkName);
            txtName.setText(drinkName);

            //Set the TextView with the price of the drink
            TextView txtPrice = (TextView) findViewById(R.id.drinkPrice);
            txtPrice.setText("$" + drinkPrice);

            //Set the TextView with the description of the drink
            TextView txtDesc = (TextView) findViewById(R.id.drinkDesc);
            txtDesc.setText(drinkDesc);

            //Set the Image View to the image that corresponds to the selected drink
            ImageView img = (ImageView)findViewById(R.id.img_icon);
            img.setImageResource(drinkImgs.getResourceId(drinkID, defaultValue));


        }


    }


    public void addToOrder(View v){

        String itemQty = quantity.getNumber();
        String name = itemName.getText().toString();
        String price = itemPrice.getText().toString();

        price = price.replace("$", "");

        //System.out.println("Price in string " + price);

        double p = Double.parseDouble(price);
        int qty = Integer.parseInt(itemQty);

        p = p * qty;


        String newPrice = String.valueOf(p);

        //System.out.println("new Price " + newPrice);


        if(db.insertData(name, itemQty, newPrice))
            Toast.makeText(getApplicationContext(), "Order added", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(getApplicationContext(), "Order not added", Toast.LENGTH_SHORT).show();

    }
}
