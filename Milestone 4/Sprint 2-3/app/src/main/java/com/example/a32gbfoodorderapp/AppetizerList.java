package com.example.a32gbfoodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AppetizerList extends AppCompatActivity {

    private ListView lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer_list);

        lists = (ListView) findViewById(R.id.appetizerList);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(AppetizerList.this, AppetizerDetail.class);
                Bundle b = new Bundle();
                b.putInt("AppetizerName", position);
                i.putExtras(b);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsTtemSelected(MenuItem item)
    {

        int id=item.getItemId();
        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
    public void back(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }

}
