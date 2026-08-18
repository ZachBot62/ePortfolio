package com.example.inventorytracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditItemActivity extends AppCompatActivity {

    EditText editTextItemId;
    EditText editTextEditName;
    EditText editTextEditQuantity;

    Button buttonIncrease;
    Button buttonDecrease;
    Button buttonSaveChanges;
    Button buttonDeleteItem;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editTextItemId = findViewById(R.id.editTextItemId);
        editTextEditName = findViewById(R.id.editTextEditName);
        editTextEditQuantity = findViewById(R.id.editTextEditQuantity);

        buttonIncrease = findViewById(R.id.buttonIncrease);
        buttonDecrease = findViewById(R.id.buttonDecrease);
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        buttonDeleteItem = findViewById(R.id.buttonDeleteItem);

        databaseHelper = new DatabaseHelper(this);

        buttonIncrease.setOnClickListener(v -> {
            if (!editTextEditQuantity.getText().toString().isEmpty()) {
                int qty = Integer.parseInt(
                        editTextEditQuantity.getText().toString());
                editTextEditQuantity.setText(String.valueOf(qty + 1));
            }
        });

        buttonDecrease.setOnClickListener(v -> {
            if (!editTextEditQuantity.getText().toString().isEmpty()) {
                int qty = Integer.parseInt(
                        editTextEditQuantity.getText().toString());

                if (qty > 0) {
                    editTextEditQuantity.setText(
                            String.valueOf(qty - 1));
                }
            }
        });

        buttonSaveChanges.setOnClickListener(v -> {

            String idText =
                    editTextItemId.getText().toString();
            String name =
                    editTextEditName.getText().toString();
            String qtyText =
                    editTextEditQuantity.getText().toString();

            if (idText.isEmpty() ||
                    name.isEmpty() ||
                    qtyText.isEmpty()) {

                Toast.makeText(
                        this,
                        "Fill in all fields",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int id = Integer.parseInt(idText);
            int quantity = Integer.parseInt(qtyText);

            boolean updated =
                    databaseHelper.updateItem(
                            id,
                            name,
                            quantity
                    );

            if (updated) {
                Toast.makeText(
                        this,
                        "Item Updated",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        this,
                        "Item not found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        buttonDeleteItem.setOnClickListener(v -> {

            String idText =
                    editTextItemId.getText().toString();

            if (idText.isEmpty()) {
                Toast.makeText(
                        this,
                        "Enter Item ID",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int id = Integer.parseInt(idText);

            boolean deleted =
                    databaseHelper.deleteItem(id);

            if (deleted) {
                Toast.makeText(
                        this,
                        "Item Deleted",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        this,
                        "Item not found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}