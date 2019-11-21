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

public class AppetizerDetail extends AppCompatActivity {
    private ElegantNumberButton quantity;
    private TextView itemName, itemPrice;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer_detail);

        db = new DBHelper(this);

        quantity = (ElegantNumberButton)findViewById(R.id.quantityBtn);
        itemName = (TextView)findViewById(R.id.AppetizerName);
        itemPrice = (TextView)findViewById(R.id.AppetizerPrice);

        Bundle b = getIntent().getExtras();
        if (b.containsKey("AppetizerName")) {
            int AppetizerID = b.getInt("AppetizerName");
            int defaultValue = 0;
            String[] AppetizerList = getResources().getStringArray(R.array.availableAppetizers);
            String[] AppetizerPriceList = getResources().getStringArray(R.array.AppetizerPrices);
            String[] AppetizerDescList = getResources().getStringArray(R.array.AppetizerDesc);

            String AppetizerName = AppetizerList[AppetizerID ];
            String AppetizerPrice = AppetizerPriceList[AppetizerID ];
            String AppetizerDesc = AppetizerDescList[AppetizerID ];

            TypedArray AppetizerImgs = getResources().obtainTypedArray(R.array.AppetizerImages);

            //Set the Text view with the name of the dessert
            TextView txtName = findViewById(R.id.AppetizerName);
            txtName.setText(AppetizerName);

            //Set the TextView with the price of the dessert
            TextView txtPrice = (TextView) findViewById(R.id.AppetizerPrice);
            txtPrice.setText(AppetizerPrice);

            //Set the TextView with the description of the dessert
            TextView txtDesc = (TextView) findViewById(R.id.AppetizerDesc);
            txtDesc.setText(AppetizerDesc);

            //Set the Image View to the image thta corresponds to the selected dessert
            ImageView img = (ImageView) findViewById(R.id.img_icon);
            img.setImageResource(AppetizerImgs.getResourceId(AppetizerID , defaultValue));

        }
    }

    public void addToOrder(View v){

        String itemQty = quantity.getNumber();
        String name = itemName.getText().toString();
        String price = itemPrice.getText().toString();

        price = price.replace("$", "");

       // System.out.println("Price in string " + price);

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

    public void back(View view)
    {
        startActivity(new Intent(this,AppetizerList.class));
    }
}
