package com.example.demoapp2;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxParacetamol, checkBoxIbuprofen, checkBoxAntibiotic;
    private RadioGroup radioGroupDelivery;
    private RatingBar ratingBar;
    private SeekBar seekBarQuantity;
    private TextView textViewQuantityValue;
    private Switch switchConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        checkBoxParacetamol = findViewById(R.id.checkBox_paracetamol);
        checkBoxIbuprofen = findViewById(R.id.checkBox_ibuprofen);
        checkBoxAntibiotic = findViewById(R.id.checkBox_antibiotic);

        radioGroupDelivery = findViewById(R.id.radioGroup_delivery);
        ratingBar = findViewById(R.id.ratingBar);
        seekBarQuantity = findViewById(R.id.seekBar_quantity);
        textViewQuantityValue = findViewById(R.id.textView_quantityValue);
        switchConfirmation = findViewById(R.id.switch_confirmation);

        // Handle SeekBar changes
        seekBarQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewQuantityValue.setText("Quantity: " + (progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Handle Switch confirmation
        switchConfirmation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String order = "Order details: ";

                // Get selected medicines
                if (checkBoxParacetamol.isChecked()) order += "Paracetamol ";
                if (checkBoxIbuprofen.isChecked()) order += "Ibuprofen ";
                if (checkBoxAntibiotic.isChecked()) order += "Antibiotic ";

                // Get selected delivery option
                int selectedDeliveryId = radioGroupDelivery.getCheckedRadioButtonId();
                if (selectedDeliveryId != -1) {
                    RadioButton selectedDelivery = findViewById(selectedDeliveryId);
                    order += "\nDelivery Option: " + selectedDelivery.getText();
                }

                // Get service rating
                float rating = ratingBar.getRating();
                order += "\nRating: " + rating + " stars";

                // Get medicine quantity
                int quantity = seekBarQuantity.getProgress() + 1;
                order += "\nQuantity: " + quantity;

                Toast.makeText(MainActivity.this, order, Toast.LENGTH_LONG).show();
            }
        });
    }
}
