package com.example.mylocationfinder;
import android.os.Bundle;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;

public class AddLocationActivity extends AppCompatActivity {

    private EditText etAddress, etLatitude, etLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_location);

        etAddress = findViewById(R.id.etAddress);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);

        SQLiteHelper dbHelper = new SQLiteHelper(this);


        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnReturn = findViewById(R.id.btnReturn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etAddress.getText().toString();
                if (address.isEmpty()) {
                    showToast("Please Enter an Address.");
                    return;
                }
                try{
                    double latitude = Double.parseDouble(etLatitude.getText().toString());
                    double longitude = Double.parseDouble(etLongitude.getText().toString());
                    dbHelper.addLocation(address, latitude, longitude);
                    etAddress.setText("");
                    etLatitude.setText("");
                    etLongitude.setText("");
                    showToast("Success!");
                }
                catch (NumberFormatException e){
                    showToast("Latitude and Longitude must be valid numbers.");
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and return to the previous one
                finish(); // This will return to the previous activity in the stack
            }
        });
    }
    private void showToast(String message) {
        Toast toast = Toast.makeText(AddLocationActivity.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1000);
        toast.show();
    }
}

