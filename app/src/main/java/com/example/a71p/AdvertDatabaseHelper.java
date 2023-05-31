package com.example.a71p;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AdvertDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "advert.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "adverts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_LOCATION + " TEXT, " +
                    COLUMN_LATITUDE + " REAL, " +
                    COLUMN_LONGITUDE + " REAL)";

    public AdvertDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertAdvert(String type, String name, String phone, String description, String date, String location, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        return db.insert(TABLE_NAME, null, values);
    }

    public List<Advert> getAllAdverts() {
        List<Advert> adverts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int typeColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TYPE);
            int nameColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            int phoneColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_PHONE);
            int descriptionColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION);
            int dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE);
            int locationColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LOCATION);
            int latitudeColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LATITUDE);
            int longitudeColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LONGITUDE);

            do {
                long id = cursor.getLong(idColumnIndex);
                String type = cursor.getString(typeColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String phone = cursor.getString(phoneColumnIndex);
                String description = cursor.getString(descriptionColumnIndex);
                String date = cursor.getString(dateColumnIndex);
                String location = cursor.getString(locationColumnIndex);
                double latitude = cursor.getDouble(latitudeColumnIndex);
                double longitude = cursor.getDouble(longitudeColumnIndex);

                Advert advert = new Advert(id, type, name, phone, description, date, location);
                advert.setLatitude(latitude);
                advert.setLongitude(longitude);
                adverts.add(advert);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return adverts;
    }

    public void removeItem(long advertId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(advertId)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public Advert getAdvert(long advertId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(advertId)};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Advert advert = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int typeColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TYPE);
            int nameColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            int phoneColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_PHONE);
            int descriptionColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION);
            int dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE);
            int locationColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LOCATION);
            int latitudeColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LATITUDE);
            int longitudeColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_LONGITUDE);

            if (idColumnIndex >= 0) {
                long id = cursor.getLong(idColumnIndex);
                String type = cursor.getString(typeColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String phone = cursor.getString(phoneColumnIndex);
                String description = cursor.getString(descriptionColumnIndex);
                String date = cursor.getString(dateColumnIndex);
                String location = cursor.getString(locationColumnIndex);
                double latitude = cursor.getDouble(latitudeColumnIndex);
                double longitude = cursor.getDouble(longitudeColumnIndex);

                advert = new Advert(id, type, name, phone, description, date, location);
                advert.setLatitude(latitude);
                advert.setLongitude(longitude);
            }

            cursor.close();
        }
        return advert;
    }
}
