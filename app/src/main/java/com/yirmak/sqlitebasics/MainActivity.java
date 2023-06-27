package com.yirmak.sqlitebasics;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase database = this.openOrCreateDatabase("Games", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY, name VARCHAR, year INT)");

            database.execSQL("INSERT INTO games (name, year) VALUES ('Stray', 2022)");
            database.execSQL("INSERT INTO games (name, year) VALUES ('Devour', 2021)");

            //database.execSQL("UPDATE games SET year = 2023 WHERE name = 'Devour'");

            Cursor cursor = database.rawQuery("SELECT * FROM games", null);

            int nameIx = cursor.getColumnIndex("name");
            int yearIx = cursor.getColumnIndex("year");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Year: " + cursor.getInt(yearIx));
                System.out.println("Id: " + cursor.getInt(idIx));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}