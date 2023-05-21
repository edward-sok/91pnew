package com.example.a71p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveItemActivity extends AppCompatActivity {

    private AdvertDatabaseHelper databaseHelper;
    private long advertId;

    private TextView typeTextView;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;
    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removeitem);

        databaseHelper = new AdvertDatabaseHelper(this);

        Intent intent = getIntent();
        advertId = intent.getLongExtra("advert_id", -1);

        typeTextView = findViewById(R.id.typeTextView);
        nameTextView = findViewById(R.id.nameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        dateTextView = findViewById(R.id.dateTextView);
        locationTextView = findViewById(R.id.locationTextView);

        if (advertId != -1) {
            Advert advert = databaseHelper.getAdvert(advertId);

            typeTextView.setText("Type: " + advert.getType());
            nameTextView.setText("Name: " + advert.getName());
            phoneTextView.setText("Phone: " + advert.getPhone());
            descriptionTextView.setText("Description: " + advert.getDescription());
            dateTextView.setText("Date: " + advert.getDate());
            locationTextView.setText("Location: " + advert.getLocation());
        }

        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem();
            }
        });
    }

    private void removeItem() {
        if (advertId != -1) {
            databaseHelper.removeItem(advertId);
            finish();
        }
    }
}
