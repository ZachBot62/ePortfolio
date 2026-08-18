package com.example.inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class InventoryActivity extends AppCompatActivity {

    Button buttonAddItem, buttonEditItem, buttonSms, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        buttonAddItem = findViewById(R.id.buttonAddItem);
        buttonEditItem = findViewById(R.id.buttonEditItem);
        buttonSms = findViewById(R.id.buttonSms);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonAddItem.setOnClickListener(v ->
                startActivity(new Intent(InventoryActivity.this, AddItemActivity.class)));

        buttonEditItem.setOnClickListener(v ->
                startActivity(new Intent(InventoryActivity.this, EditItemActivity.class)));

        buttonSms.setOnClickListener(v ->
                startActivity(new Intent(InventoryActivity.this, SmsPermissionActivity.class)));

        buttonLogout.setOnClickListener(v ->
                startActivity(new Intent(InventoryActivity.this, MainActivity.class)));
    }
}