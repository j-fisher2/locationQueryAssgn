package com.example.mylocationfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "location.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LOCATION = "locations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_LATITUDE + " REAL,"
                + COLUMN_LONGITUDE + " REAL" + ")";
        db.execSQL(CREATE_LOCATION_TABLE);
        populateDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        onCreate(db);
    }

    // Add a new location
    public void addLocation(String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        db.insert(TABLE_LOCATION, null, values);
        db.close();
    }

    // Get location by address
    public Cursor getLocation(String address) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOCATION,
                null,
                COLUMN_ADDRESS + " = ? COLLATE NOCASE",
                new String[]{address},
                null, null, null);
    }

    // Update a location
    public void updateLocation(int id, String address, double latitude, double longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        db.update(TABLE_LOCATION, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public Cursor getAllLocations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_LOCATION, null, null, null, null, null, null);
    }

    private void populateDatabase(SQLiteDatabase db) {
        String[] locations = {
                "Oshawa, ON, Canada, 43.8958, -78.8675",
                "Ajax, ON, Canada, 43.8590, -79.0205",
                "Pickering, ON, Canada, 43.8484, -79.0922",
                "Scarborough, ON, Canada, 43.7701, -79.2040",
                "Downtown Toronto, ON, Canada, 43.6532, -79.3832",
                "Mississauga, ON, Canada, 43.5890, -79.6441",
                "Brampton, ON, Canada, 43.7315, -79.7626",
                "Markham, ON, Canada, 43.8561, -79.3370",
                "Richmond Hill, ON, Canada, 43.8779, -79.4373",
                "Vaughan, ON, Canada, 43.8390, -79.5080",
                "Toronto Islands, ON, Canada, 43.6204, -79.3832",
                "Thornhill, ON, Canada, 43.8110, -79.4239",
                "East York, ON, Canada, 43.7044, -79.3191",
                "Etobicoke, ON, Canada, 43.6289, -79.5162",
                "York, ON, Canada, 43.7451, -79.4911",
                "North York, ON, Canada, 43.7642, -79.4045",
                "Woodbridge, ON, Canada, 43.7896, -79.5980",
                "Caledon, ON, Canada, 43.8528, -79.7554",
                "Georgetown, ON, Canada, 43.6441, -79.9152",
                "Milton, ON, Canada, 43.5235, -79.8711",
                "Hamilton, ON, Canada, 43.2557, -79.8711",
                "Burlington, ON, Canada, 43.3255, -79.7982",
                "Owen Sound, ON, Canada, 44.5665, -81.3731",
                "Grimsby, ON, Canada, 43.2065, -79.5749",
                "St. Catharines, ON, Canada, 43.1594, -79.2469",
                "Niagara Falls, ON, Canada, 43.0896, -79.0849",
                "Welland, ON, Canada, 42.9986, -79.2437",
                "Fort Erie, ON, Canada, 42.8925, -79.0168",
                "Port Colborne, ON, Canada, 42.8753, -79.2531",
                "Grimsby, ON, Canada, 43.2065, -79.5749",
                "Pelham, ON, Canada, 43.0952, -79.2467",
                "Thorold, ON, Canada, 43.1333, -79.2667",
                "Niagara-on-the-Lake, ON, Canada, 43.2538, -79.0665",
                "Brockville, ON, Canada, 44.5860, -75.6765",
                "Kingston, ON, Canada, 44.2330, -76.4858",
                "Bancroft, ON, Canada, 45.0478, -77.8845",
                "Belleville, ON, Canada, 44.1658, -77.3832",
                "Trenton, ON, Canada, 42.0657, -77.5752",
                "Picton, ON, Canada, 43.9524, -77.1344",
                "Windsor, ON, Canada, 42.3149, -83.0364",
                "Sarnia, ON, Canada, 42.9794, -82.3844",
                "Chatham, ON, Canada, 42.4048, -82.1835",
                "Leamington, ON, Canada, 42.0634, -81.8895",
                "Amherstburg, ON, Canada, 42.0916, -83.1806",
                "Kingsville, ON, Canada, 42.0521, -82.5703",
                "LaSalle, ON, Canada, 42.2375, -83.0300",
                "Tecumseh, ON, Canada, 42.3107, -82.8045",
                "Essex, ON, Canada, 42.1833, -82.6322",
                "Tilbury, ON, Canada, 42.0162, -82.3734",
                "Leamington, ON, Canada, 42.0521, -81.8895",
                "Forest, ON, Canada, 43.0090, -81.7562",
                "Kettle Point, ON, Canada, 43.0585, -81.8497",
                "Grand Bend, ON, Canada, 43.1981, -81.6376",
                "Strathroy, ON, Canada, 43.0300, -81.5942",
                "Parkhill, ON, Canada, 43.1142, -81.4056",
                "Lambton Shores, ON, Canada, 43.2712, -81.7598",
                "Huron Park, ON, Canada, 43.2505, -81.7862",
                "Woodstock, ON, Canada, 43.1288, -80.7462",
                "Ingersoll, ON, Canada, 43.0192, -80.2211",
                "Oxford, ON, Canada, 43.2273, -80.8850",
                "Tillsonburg, ON, Canada, 42.8571, -80.7247",
                "Simcoe, ON, Canada, 42.8390, -80.3023",
                "Delhi, ON, Canada, 42.6302, -80.5530",
                "Waterford, ON, Canada, 42.9621, -80.4258",
                "Port Dover, ON, Canada, 42.6503, -80.1983",
                "Dunnville, ON, Canada, 43.0065, -79.7578",
                "Caledonia, ON, Canada, 43.0836, -80.1250",
                "Stoney Creek, ON, Canada, 43.2255, -79.6684",
                "Binbrook, ON, Canada, 43.1940, -79.7761",
                "Ancaster, ON, Canada, 43.2068, -80.0671",
                "Glanbrook, ON, Canada, 43.1720, -79.7512",
                "Guelph, ON, Canada, 43.5501, -80.2492",
                "Brantford, ON, Canada, 43.1833, -80.2672",
                "Cambridge, ON, Canada, 43.2050, -80.2410",
                "Kitchener, ON, Canada, 43.4519, -80.4925",
                "Waterloo, ON, Canada, 43.4643, -80.5204"
        };

        db.beginTransaction();
        try {
            for (String location : locations) {
                String[] parts = location.split(", ");
                String address = parts[0] + ", " + parts[1] + ", " + parts[2];
                double latitude = Double.parseDouble(parts[3]);
                double longitude = Double.parseDouble(parts[4]);
                ContentValues values = new ContentValues();
                values.put(COLUMN_ADDRESS, address);
                values.put(COLUMN_LATITUDE, latitude);
                values.put(COLUMN_LONGITUDE, longitude);
                db.insert(TABLE_LOCATION, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    // Delete a location
    public void deleteLocation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATION, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}

