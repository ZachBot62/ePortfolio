package com.example.inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText editTextItemName;
    EditText editTextQuantity;

    Button buttonSaveItem;
    Button buttonCancel;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextQuantity = findViewById(R.id.editTextQuantity);

        buttonSaveItem = findViewById(R.id.buttonSaveItem);
        buttonCancel = findViewById(R.id.buttonCancel);

        databaseHelper = new DatabaseHelper(this);

        buttonSaveItem.setOnClickListener(v -> {

            String name = editTextItemName.getText().toString().trim();
            String quantityText =
                    editTextQuantity.getText().toString().trim();

            if (name.isEmpty() || quantityText.isEmpty()) {
                Toast.makeText(
                        AddItemActivity.this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int quantity = Integer.parseInt(quantityText);

            boolean inserted =
                    databaseHelper.addItem(name, quantity);

            if (inserted) {
                Toast.makeText(
                        AddItemActivity.this,
                        "Item Added",
                        Toast.LENGTH_SHORT
                ).show();

                startActivity(
                        new Intent(
                                AddItemActivity.this,
                                InventoryActivity.class
                        )
                );
                finish();
            } else {
                Toast.makeText(
                        AddItemActivity.this,
                        "Error adding item",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        buttonCancel.setOnClickListener(v -> {
            finish();
        });
    }
}