package com.example.a32gbfoodorderapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Random;

import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button cwaiter,waiterhere;
    String val;
    //TextView counttime;

    final Random waiter = new Random();
    public int counter=180;
    private final int secs=15000;
    public int num=1;
    private static CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Random waiter = new Random();

        cwaiter=(Button)findViewById(R.id.cwaiter);
        waiterhere = (Button) findViewById(R.id.waiterhere);


    }
    public void viewAppetizers(View v){
        Intent i = new Intent(MainActivity.this, AppetizerList.class);
        startActivity(i);
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

    public void viewBill(View v){
        Intent i = new Intent(MainActivity.this, BillActivity.class);
        startActivity(i);
    }

    public void startTimer(int numofSecs)

    {

        countDownTimer= new CountDownTimer(numofSecs, 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {

                Toast.makeText(getApplicationContext(),String.valueOf(counter) + "Seconds Left ", Toast.LENGTH_LONG).show();
                counter--;
            }

            @Override
            public void onFinish()
            {

                val = String.valueOf(waiter.nextInt(100));
                //countDownTimer.start();
                counter=180;
                startTimer(secs);

            }

        }.start();

    }

    public void stopCountdown(){
        if(countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }

    }

    public void CallWaiter(View v)
    {
        val = String.valueOf(waiter.nextInt(12));
        String t= "Waiter" + val +" is coming";
        Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();

        cwaiter.setVisibility(v.INVISIBLE);
        waiterhere.setVisibility(v.VISIBLE);

        startTimer(secs);


            waiterhere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    stopCountdown();

                   // num=0;
                    String wait= "Waiter" + val +" is here";
                    Toast.makeText(getApplicationContext(), wait, Toast.LENGTH_SHORT).show();
                    //counttime.setText(wait);

                    cwaiter.setVisibility(v.VISIBLE);
                    waiterhere.setVisibility(v.INVISIBLE);

                }
            });

    }

}
