package com.example.barcodetester;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the EditText view
        EditText editText = findViewById(R.id.barcodeEditText);

        // Handler for managing delayed tasks
        Handler handler = new Handler();

        // Use an array to hold the Runnable reference (mutable container)
        final Runnable[] runnableHolder = new Runnable[1];

        // Add a TextWatcher to handle text changes
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Remove any pending runnable if one exists
                if (runnableHolder[0] != null) {
                    handler.removeCallbacks(runnableHolder[0]);
                }

                // Schedule a new runnable to execute after 5 seconds
                runnableHolder[0] = new Runnable() {
                    @Override
                    public void run() {
                        String fullText = editText.getText().toString();
                        // Display the text in a Toast message
                        Toast.makeText(MainActivity.this, fullText, Toast.LENGTH_SHORT).show();
                    }
                };
                handler.postDelayed(runnableHolder[0], 5000); // 5-second delay
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed after text changes
            }
        });
    }
}
