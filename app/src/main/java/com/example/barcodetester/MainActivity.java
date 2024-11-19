package com.example.barcodetester;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BarcodeScannerApp";
    private StringBuilder barcodeBuffer = new StringBuilder();
    private EditText barcodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // Initialize the EditText to display scanned barcode
        barcodeEditText = findViewById(R.id.barcodeEditText);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            char inputChar = (char) event.getUnicodeChar();

            // Filter out non-printable characters like null (0)
            if (inputChar != '\u0000' && event.getKeyCode() != KeyEvent.KEYCODE_ENTER) {
                // Append valid characters
                barcodeBuffer.append(inputChar);
            }

            // If Enter key is pressed, treat it as end of barcode input
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                // Get the barcode string
                String barcode = barcodeBuffer.toString().trim();
                barcodeBuffer.setLength(0); // Clear buffer for next input
                handleBarcodeScanned(barcode);
                return true; // Consume Enter key to prevent additional action
            }
        }

        return super.dispatchKeyEvent(event); // Allow other key events to propagate
    }

    // Handle the scanned barcode (e.g., display it or send it for processing)
    private void handleBarcodeScanned(String barcode) {
        Log.d(TAG, "Scanned Barcode: " + barcode);
        barcodeEditText.setText(barcode);  // Display the barcode in EditText
    }


}