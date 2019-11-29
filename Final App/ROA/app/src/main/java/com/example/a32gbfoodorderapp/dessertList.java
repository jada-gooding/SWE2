package com.example.a32gbfoodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class dessertList extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_list);

        lv = (ListView) findViewById(R.id.dessertList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(dessertList.this, dessertDetail.class);
                Bundle b = new Bundle();
                b.putInt("dessertName", position);
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
        Intent i = new Intent(dessertList.this, BillActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

//    public void back(View view)
//    {
//        startActivity(new Intent(this,MainActivity.class));
//    }
//
//    public void viewBill(View view) {
//        Intent i = new Intent(dessertList.this, BillActivity.class);
//        startActivity(i);
//    }
}
