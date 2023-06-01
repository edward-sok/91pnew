package com.example.a71p;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a71p.Advert;
import com.example.a71p.AdvertListAdapter;
import com.example.a71p.AdvertDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    private ListView listView;
    private AdvertListAdapter adapter;
    private List<Advert> advertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);

        listView = findViewById(R.id.itemslistView);
        advertList = getAdverts();

        if (advertList != null) {
            adapter = new AdvertListAdapter(this, advertList);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No adverts available", Toast.LENGTH_SHORT).show();
        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Advert selectedAdvert = advertList.get(position);
            openMapActivity(selectedAdvert);
        });
    }

    private List<Advert> getAdverts() {
        AdvertDatabaseHelper databaseHelper = new AdvertDatabaseHelper(this);
        return databaseHelper.getAllAdverts();
    }

    private void openMapActivity(Advert selectedAdvert) {
        Intent intent = new Intent(ShowItemsActivity.this, MapActivity.class);
        intent.putParcelableArrayListExtra("advertList", new ArrayList<>(advertList));
        startActivity(intent);
    }
}
