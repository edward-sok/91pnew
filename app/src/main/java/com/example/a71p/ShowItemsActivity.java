package com.example.a71p;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.a71p.AdvertListAdapter;



import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    private ListView itemsListView;
    private AdvertDatabaseHelper databaseHelper;
    private List<Advert> adverts;
    private AdvertListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);

        databaseHelper = new AdvertDatabaseHelper(this);
        adverts = databaseHelper.getAllAdverts();

        itemsListView = findViewById(R.id.itemsListView);
        adapter = new AdvertListAdapter(this, adverts);
        itemsListView.setAdapter(adapter);

        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Advert selectedAdvert = adverts.get(position);
                Intent intent = new Intent(ShowItemsActivity.this, RemoveItemActivity.class);
                intent.putExtra("advert_id", selectedAdvert.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adverts = databaseHelper.getAllAdverts();
        adapter.updateList(adverts);
    }
}
