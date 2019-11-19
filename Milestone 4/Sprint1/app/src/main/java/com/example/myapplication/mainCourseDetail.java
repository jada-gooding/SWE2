package com.example.myapplication;

import android.os.Bundle;
import android.content.res.TypedArray;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class mainCourseDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course_detail);

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

}
