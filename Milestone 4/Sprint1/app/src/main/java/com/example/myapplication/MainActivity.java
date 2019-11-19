package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewMainCourse(View v){
        Intent i = new Intent(MainActivity.this, mainCourseList.class);
        startActivity(i);
    }
    public void viewDessert(View v){
        Intent i = new Intent(MainActivity.this, dessertList.class);
        startActivity(i);
    }

    public void viewDrink(View v){
        Intent i = new Intent(MainActivity.this, drinksList.class);
        startActivity(i);
    }
}
