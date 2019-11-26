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

public class mainCourseDetail extends AppCompatActivity {

    private ElegantNumberButton quantity;
    private TextView itemName, itemPrice;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course_detail);

        db = new DBHelper(this);

        quantity = (ElegantNumberButton)findViewById(R.id.quantityBtn);
        itemName = (TextView)findViewById(R.id.mainCourseName);
        itemPrice = (TextView)findViewById(R.id.mainCoursePrice);

        Bundle b = getIntent().getExtras();
        if(b.containsKey("mainCourseName")){
            int mainCourseID = b.getInt("mainCourseName");
            int defaultValue = 0;
            String [] mainCourseList = getResources().getStringArray(R.array.availableMainCourse);
            String [] mainCoursePriceList = getResources().getStringArray(R.array.mainCoursePrices);
            String [] mainCourseDescList = getResources().getStringArray(R.array.mainCourseDesc);

            String dessertName = mainCourseList[mainCourseID];
            String dessertPrice = mainCoursePriceList[mainCourseID];
            String dessertDesc = mainCourseDescList[mainCourseID];

            TypedArray dessertImgs = getResources().obtainTypedArray(R.array.mainCourseImages);

            //Set the Text view with the name of the dessert
            TextView txtName = (TextView) findViewById(R.id.mainCourseName);
            txtName.setText(dessertName);

            //Set the TextView with the price of the dessert
            TextView txtPrice = (TextView) findViewById(R.id.mainCoursePrice);
            txtPrice.setText(dessertPrice);

            //Set the TextView with the description of the dessert
            TextView txtDesc = (TextView) findViewById(R.id.mainCourseDesc);
            txtDesc.setText(dessertDesc);

            //Set the Image View to the image thta corresponds to the selected dessert
            ImageView img = (ImageView)findViewById(R.id.img_icon);
            img.setImageResource(dessertImgs.getResourceId(mainCourseID, defaultValue));

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
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //startActivity(new Intent(this,mainCourseList.class));
    }
}
