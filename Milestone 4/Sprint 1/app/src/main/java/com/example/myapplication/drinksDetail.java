package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class drinksDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_detail);

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
            txtPrice.setText(drinkPrice);

            //Set the TextView with the description of the drink
            TextView txtDesc = (TextView) findViewById(R.id.drinkDesc);
            txtDesc.setText(drinkDesc);

            //Set the Image View to the image that corresponds to the selected drink
            ImageView img = (ImageView)findViewById(R.id.img_icon);
            img.setImageResource(drinkImgs.getResourceId(drinkID, defaultValue));


        }
    }
}
