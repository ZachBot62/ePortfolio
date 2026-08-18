package com.example.inventorytracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsPermissionActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 100;

    EditText editTextPhone;
    Button buttonEnableSms;
    Button buttonSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_permission);

        editTextPhone = findViewById(R.id.editTextPhone);
        buttonEnableSms = findViewById(R.id.buttonEnableSms);
        buttonSkip = findViewById(R.id.buttonSkip);

        buttonEnableSms.setOnClickListener(v -> requestSmsPermission());

        buttonSkip.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    "SMS alerts disabled",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
        });
    }

    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        } else {
            Toast.makeText(
                    this,
                    "SMS permission already granted",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);

        if (requestCode == SMS_PERMISSION_CODE) {

            if (grantResults.length > 0 &&
                    grantResults[0]
                            == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                        this,
                        "SMS permission granted",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Toast.makeText(
                        this,
                        "SMS permission denied. App will still function normally.",
                        Toast.LENGTH_LONG
                ).show();
            }
        }
    }
}