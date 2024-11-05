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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QueryLocation extends AppCompatActivity {

    private EditText etAddress;
    private TextView tvResult;
    private TextView idResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_activity);

        etAddress = findViewById(R.id.etAddress);
        tvResult = findViewById(R.id.tvResult);
        idResult = findViewById(R.id.idResult);


        SQLiteHelper dbHelper = new SQLiteHelper(this);


        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnReturn = findViewById(R.id.btnReturn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etAddress.getText().toString().trim();
                if(address.isEmpty()){
                    tvResult.setText("Enter an address");
                }
                Cursor cursor = dbHelper.getLocation(address);

                if (cursor != null && cursor.moveToFirst()) {
                    StringBuilder resultBuilder = new StringBuilder();
                    StringBuilder idBuilder = new StringBuilder();

                    do {
                        // Retrieve latitude, longitude, and id for each row
                        String addressRow = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                        double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"));
                        double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"));
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));

                        // Append each result to the result strings
                        resultBuilder.append(addressRow+"\n").append("Latitude: ").append(latitude)
                                .append(", Longitude: ").append(longitude).append("\n").append("ID: "+id).append("\n\n");



                    } while (cursor.moveToNext());

                    // Set the complete result in the TextViews
                    tvResult.setText(resultBuilder.toString());
                    idResult.setText(idBuilder.toString());

                    cursor.close();
                } else {
                    // Log the error for debugging
                    tvResult.setText("Location not found. Please check address format.");
                    Log.d("MainActivity", "Queried Address: " + address);
                    // Optional: Display all entries for debugging
                    Cursor allEntries = dbHelper.getAllLocations(); // Assuming you add this method in SQLiteHelper
                    while (allEntries.moveToNext()) {
                        String dbAddress = allEntries.getString(allEntries.getColumnIndexOrThrow("address"));
                        Log.d("MainActivity", "DB Entry - Address: " + dbAddress);
                    }
                    allEntries.close();
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
        Toast toast = Toast.makeText(QueryLocation.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1000);
        toast.show();
    }
}

