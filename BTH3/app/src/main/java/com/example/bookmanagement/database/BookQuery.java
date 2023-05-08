package com.example.bookmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagement.entity.Books;

import java.util.ArrayList;
import java.util.List;

public class BookQuery {

    private Context context;

    private DatabaseHelper databaseHelper;

    private static final String TABLE_NAME = "books";

    private static final String ID = "id";

    private static final String TITLE = "title";

    private static final String AUTHOR = "author";

    private static final String PUBLIC_YEAR = "public_year";

    private static final String PRICE = "price";

    public BookQuery(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void add(Books books) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, books.getTitle());
        values.put(AUTHOR, books.getAuthor());
        values.put(PUBLIC_YEAR, books.getYearPublic());
        values.put(PRICE, books.getPrice());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Books> getAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        List<Books> booksList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Books books = new Books();
                books.setId(cursor.getInt(0));
                books.setTitle(cursor.getString(1));
                books.setAuthor(cursor.getString(2));
                books.setYearPublic(cursor.getInt(3));
                books.setPrice(cursor.getInt(4));
                booksList.add(books);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return booksList;
    }

    public List<Books> findALLByPrice(int lowPrice, int highPrice) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                PRICE + " BETWEEN " + lowPrice + " AND " + highPrice;
        List<Books> booksList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Books books = new Books();
                books.setId(cursor.getInt(0));
                books.setTitle(cursor.getString(1));
                books.setAuthor(cursor.getString(2));
                books.setYearPublic(cursor.getInt(3));
                books.setPrice(cursor.getInt(4));
                booksList.add(books);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return booksList;
    }

    public Books findByName(String name) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {ID, TITLE, AUTHOR, PUBLIC_YEAR, PRICE},
                TITLE + "=?", new String[]{name}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Books books = new Books((cursor.getInt(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getInt(3), cursor.getInt(4));

        cursor.close();
        db.close();
        return books;
    }

    public List<Books> sortByASC() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + PUBLIC_YEAR + " DESC";
        List<Books> booksList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Books books = new Books();
                books.setId(cursor.getInt(0));
                books.setTitle(cursor.getString(1));
                books.setAuthor(cursor.getString(2));
                books.setYearPublic(cursor.getInt(3));
                books.setPrice(cursor.getInt(4));
                booksList.add(books);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return booksList;
    }

}
