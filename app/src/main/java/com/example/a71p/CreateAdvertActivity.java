package com.example.a71p;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {

    private RadioButton lostRadioButton;
    private RadioButton foundRadioButton;
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;
    private EditText locationEditText;
    private Button saveButton;
    private AdvertDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new AdvertDatabaseHelper(this);

        lostRadioButton = findViewById(R.id.radioButton);
        foundRadioButton = findViewById(R.id.radioButton2);
        nameEditText = findViewById(R.id.editTextTextPersonName2);
        phoneEditText = findViewById(R.id.editTextTextPersonName3);
        descriptionEditText = findViewById(R.id.editTextTextPersonName4);
        dateEditText = findViewById(R.id.editTextTextPersonName5);
        locationEditText = findViewById(R.id.editTextTextPersonName6);
        saveButton = findViewById(R.id.button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = lostRadioButton.isChecked() ? "Lost" : "Found";
                String name = nameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                String location = locationEditText.getText().toString().trim();

                // Save the advert details to the database
                long rowId = databaseHelper.insertAdvert(type, name, phone, description, date, location);

                if (rowId != -1) {
                    Toast.makeText(CreateAdvertActivity.this, "Advert details saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreateAdvertActivity.this, "Failed to save advert details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
