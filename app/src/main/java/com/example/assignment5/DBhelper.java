package com.example.assignment5;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class DBhelper extends SQLiteOpenHelper {
    public static final String TAG =DBhelper.class.getSimpleName();

    private Resources mResources;
    public static final String DATABASE_NAME = "menu.db";
    public static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;

    public DBhelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();

        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_BUGS_TABLE = "CREATE TABLE" + DB.MenuEntry.COLUMN_NAME
                + " (" + DB.MenuEntry.COLUMN_DATE +")";

        db.execSQL(SQL_CREATE_BUGS_TABLE);
        Log.d(TAG, "Database Created Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void readDataToDb (SQLiteDatabase db) throws IOException, JSONException {
        final String MNU_NAME ="nimi";
        final String MNU_DATE ="pvm";

        String jsonDataString = readJsonDataFromWebsite();

        // I have no idea, I give up.
    }

    private String readJsonDataFromWebsite() {
        final JSONObject[] inputStream = {null};
        StringBuilder builder =new StringBuilder();

        JsonArrayRequest request = new JsonArrayRequest(
                "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                inputStream[0] = response.getJSONObject(i);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
    }
});
    }
}
