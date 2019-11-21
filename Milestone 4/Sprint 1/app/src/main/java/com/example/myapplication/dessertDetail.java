package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.TypedArray;
import android.widget.ImageView;
import android.widget.TextView;

public class dessertDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_detail);

        Bundle b = getIntent().getExtras();
        if(b.containsKey("dessertName")){
            int dessertID = b.getInt("dessertName");
            int defaultValue = 0;
            String [] dessertList = getResources().getStringArray(R.array.availableDesserts);
            String [] dessertPriceList = getResources().getStringArray(R.array.dessertPrices);
            String [] dessertDescList = getResources().getStringArray(R.array.dessertDesc);

            String dessertName = dessertList[dessertID];
            String dessertPrice = dessertPriceList[dessertID];
            String dessertDesc = dessertDescList[dessertID];

            TypedArray dessertImgs = getResources().obtainTypedArray(R.array.dessertImages);

            //Set the Text view with the name of the dessert
            TextView txtName = (TextView) findViewById(R.id.dessertName);
            txtName.setText(dessertName);

            //Set the TextView with the price of the dessert
            TextView txtPrice = (TextView) findViewById(R.id.dessertPrice);
            txtPrice.setText(dessertPrice);

            //Set the TextView with the description of the dessert
            TextView txtDesc = (TextView) findViewById(R.id.dessertDesc);
            txtDesc.setText(dessertDesc);

            //Set the Image View to the image thta corresponds to the selected dessert
            ImageView img = (ImageView)findViewById(R.id.img_icon);
            img.setImageResource(dessertImgs.getResourceId(dessertID, defaultValue));

        }
    }

}
