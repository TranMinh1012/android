package com.example.bookmanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bookmanagement.database.BookQuery;
import com.example.bookmanagement.entity.Books;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddNewUser;

    Button btnSearchPrice;

    Button btnSearchTitle;

    Button btnSort;

    EditText editTextTitle;

    EditText editTextPriceLow;

    EditText editTextPriceHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNewUser = findViewById(R.id.addNewBook);
        btnSearchPrice = findViewById(R.id.searchPrice);
        btnSearchTitle = findViewById(R.id.searchTitle);
        btnSort = findViewById(R.id.sort);
        editTextTitle = findViewById(R.id.searchInput);
        editTextPriceLow = findViewById(R.id.priceLow);
        editTextPriceHigh = findViewById(R.id.priceHigh);

        btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookDetail.class);
                startActivityForResult(intent, 1);
            }
        });

        loadListBook();

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadListBookSortYearASNC();
            }
        });

        btnSearchTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadListBookSearchByName(editTextTitle.getText().toString());
            }
        });

        btnSearchPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lowPrice = Integer.parseInt(editTextPriceLow.getText().toString());
                int highPrice = Integer.parseInt(editTextPriceHigh.getText().toString());
                loadListBookByPrice(lowPrice, highPrice);
            }
        });
    }

    private void loadListBookByPrice(int lowPrice, int highPrice) {
        BookQuery bookQuery = new BookQuery(getBaseContext());
        List<Books> booksList = bookQuery.findALLByPrice(lowPrice, highPrice);
        ArrayAdapter<Books> listBookAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, booksList);
        ListView listBookView = findViewById(R.id.listBook);

        listBookView.setAdapter(listBookAdapter);
    }

    private void loadListBookSearchByName(String name) {
        BookQuery bookQuery = new BookQuery(getBaseContext());
        Books books = bookQuery.findByName(name);
        List<Books> booksList = new ArrayList<>();
        booksList.add(books);
        ArrayAdapter<Books> listBookAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, booksList);
        ListView listBookView = findViewById(R.id.listBook);

        listBookView.setAdapter(listBookAdapter);
    }

    private void loadListBook() {
        BookQuery bookQuery = new BookQuery(getBaseContext());
        List<Books> booksList = bookQuery.getAll();
        ArrayAdapter<Books> listBookAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, booksList);
        ListView listBookView = findViewById(R.id.listBook);

        listBookView.setAdapter(listBookAdapter);

    }

    private void loadListBookSortYearASNC() {
        BookQuery bookQuery = new BookQuery(getBaseContext());
        List<Books> booksList = bookQuery.sortByASC();
        ArrayAdapter<Books> listBookAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, booksList);
        ListView listBookView = findViewById(R.id.listBook);

        listBookView.setAdapter(listBookAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            this.finish();
        }
        if ((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
}