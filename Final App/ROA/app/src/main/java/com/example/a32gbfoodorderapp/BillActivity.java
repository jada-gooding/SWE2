package com.example.a32gbfoodorderapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    private DBHelper db;
    private ArrayList<Order> orderArrayList = new ArrayList<Order>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        db = new DBHelper(this);

        orderBill();

    }

    public void orderBill(){

        Cursor res = db.getAllOrders();

        if(res.getCount() == 0) {
            showMessage("Error", "No item ordered");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("Order Item: " + res.getString(1) + "\n");
            buffer.append("Quantity: " + res.getString(2) + "\n");
            buffer.append("Total: " + res.getString(3) + "\n\n");

            orderArrayList.add(new Order(res.getString(0), res.getString(1), res.getString(2), res.getString(3)));

        }

        showMessage("Order", buffer.toString());

        String text = "";
        for(Order o: orderArrayList) {
            System.out.println(o.toString());
            text += o.toString();
        }
        TextView ordTxt = (TextView)findViewById(R.id.orderTxt);
        ordTxt.setText(text);
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void deleteItem(View v){
        EditText num = (EditText)findViewById(R.id.foodID);

        Integer deletedRow = db.deleteData(num.getText().toString());

        if(deletedRow > 0) {
            Cursor res = db.getAllOrders();

            if(res.getCount() == 0) {
                showMessage("Error", "No item ordered");
                return;
            }

            StringBuffer buffer = new StringBuffer();

            while(res.moveToNext()){
                orderArrayList.add(new Order(res.getString(0), res.getString(1), res.getString(2), res.getString(3)));
            }

            String text = "";
            for(Order o: orderArrayList) {
                System.out.println(o.toString());
                text += o.toString();
            }
            TextView ordTxt = (TextView)findViewById(R.id.orderTxt);
            ordTxt.setText(text);

            Toast.makeText(getApplicationContext(), "Item order deleted", Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(getApplicationContext(), "Item order not deleted", Toast.LENGTH_SHORT).show();
    }
    public void back(View view)
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //startActivity(new Intent(this,MainActivity.class));
    }
}
