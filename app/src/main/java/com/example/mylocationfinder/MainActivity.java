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

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private SQLiteHelper dbHelper;
    //private EditText etAddress, etLatitude, etLongitude, etId;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnQuery = findViewById(R.id.btnQuery);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddLocationActivity.class);
                startActivity(intent);
//                String address = etAddress.getText().toString();
//                double latitude = Double.parseDouble(etLatitude.getText().toString());
//                double longitude = Double.parseDouble(etLongitude.getText().toString());
//                dbHelper.addLocation(address, latitude, longitude);
//                etAddress.setText("");
//                etLatitude.setText("");
//                etLongitude.setText("");
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QueryLocation.class);
                startActivity(intent);

//                String address = etAddress.getText().toString().trim();  // Trim whitespace
//                Cursor cursor = dbHelper.getLocation(address);
//
//                if (cursor != null && cursor.moveToFirst()) {
//                    // Address matched; retrieve latitude and longitude
//                    double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"));
//                    double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"));
//                    tvResult.setText("Latitude: " + latitude + ", Longitude: " + longitude);
//                    cursor.close();
//                } else {
//                    // Log the error for debugging
//                    tvResult.setText("Location not found. Please check address format.");
//                    Log.d("MainActivity", "Queried Address: " + address);
//                    // Optional: Display all entries for debugging
//                    Cursor allEntries = dbHelper.getAllLocations(); // Assuming you add this method in SQLiteHelper
//                    while (allEntries.moveToNext()) {
//                        String dbAddress = allEntries.getString(allEntries.getColumnIndexOrThrow("address"));
//                        Log.d("MainActivity", "DB Entry - Address: " + dbAddress);
//                    }
//                    allEntries.close();
//                }
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateLocation.class);
                startActivity(intent);

//                int id = Integer.parseInt(etId.getText().toString());
//                String address = etAddress.getText().toString();
//                double latitude = Double.parseDouble(etLatitude.getText().toString());
//                double longitude = Double.parseDouble(etLongitude.getText().toString());
//                dbHelper.updateLocation(id, address, latitude, longitude);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteLocation.class);
                startActivity(intent);
//                String idText = etId.getText().toString(); // Get the text from EditText
//                if (idText.isEmpty()) { // Check if the input is empty
//                    tvResult.setText("Enter an ID to delete.");
//                    return;
//                }
//                int id = Integer.parseInt(idText); // Parse the input to an integer
//                dbHelper.deleteLocation(id);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Initialize database helper on start if null
        if (dbHelper == null) {
            dbHelper = new SQLiteHelper(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Close the database helper on stop to release resources
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null; // Set to null to avoid reusing a closed instance
        }
    }
}
