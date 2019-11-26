package com.example.a32gbfoodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class drinksList extends AppCompatActivity {

    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_list);

        lv = (ListView) findViewById(R.id.drinkList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(drinksList.this, drinksDetail.class);
                Bundle b = new Bundle();
                b.putInt("drinkName", position);
                i.putExtras(b);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void back(View view)
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //startActivity(new Intent(this,MainActivity.class));
    }

    public void viewBill(View view) {
        Intent i = new Intent(drinksList.this, BillActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

//    public void back(View view)
//    {
//        startActivity(new Intent(this,MainActivity.class));
//    }
//
//    public void viewBill(View view) {
//        Intent i = new Intent(drinksList.this, BillActivity.class);
//        startActivity(i);
//    }
}
