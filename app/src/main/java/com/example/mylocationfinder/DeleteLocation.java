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

public class DeleteLocation extends AppCompatActivity {

    private EditText etId;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);

        etId = findViewById(R.id.etId);
        tvResult = findViewById(R.id.tvResult);


        SQLiteHelper dbHelper = new SQLiteHelper(this);


        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnReturn = findViewById(R.id.btnReturn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = etId.getText().toString(); // Get the text from EditText
                    if (idText.isEmpty()) { // Check if the input is empty
                        showToast("Enter an ID to delete.");
                        tvResult.setText("Enter ID");
                        return;
                    }
                    int id = Integer.parseInt(idText); // Parse the input to an integer
                    dbHelper.deleteLocation(id);
                    etId.setText("");
                    showToast("Success");
                    tvResult.setText("Successfully Deleted.");
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
        Toast toast = Toast.makeText(DeleteLocation.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 1000);
        toast.show();
    }
}
