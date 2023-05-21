package com.example.a71p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a71p.Advert;
import com.example.a71p.R;

import java.util.List;

public class AdvertListAdapter extends BaseAdapter {

    private Context context;
    private List<Advert> adverts;

    public AdvertListAdapter(Context context, List<Advert> adverts) {
        this.context = context;
        this.adverts = adverts;
    }

    @Override
    public int getCount() {
        return adverts.size();
    }

    @Override
    public Object getItem(int position) {
        return adverts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_advert, parent, false);
        }

        Advert advert = adverts.get(position);

        TextView typeTextView = convertView.findViewById(R.id.typeTextView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView phoneTextView = convertView.findViewById(R.id.phoneTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView locationTextView = convertView.findViewById(R.id.locationTextView);

        typeTextView.setText(advert.getType());
        nameTextView.setText(advert.getName());
        phoneTextView.setText(advert.getPhone());
        descriptionTextView.setText(advert.getDescription());
        dateTextView.setText(advert.getDate());
        locationTextView.setText(advert.getLocation());

        return convertView;
    }

    public void updateList(List<Advert> newAdverts) {
        adverts.clear();
        adverts.addAll(newAdverts);
        notifyDataSetChanged();
    }
}
