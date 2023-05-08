package com.example.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookmanagement.database.BookQuery;
import com.example.bookmanagement.entity.Books;

public class BookDetail extends AppCompatActivity {

    EditText editTextTitle;

    EditText editTextAuthor;

    EditText editTextYearPublic;

    EditText editTextPrice;

    Button btnAddNewBookDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        editTextTitle = findViewById(R.id.title);
        editTextAuthor = findViewById(R.id.author);
        editTextYearPublic = findViewById(R.id.yearPublic);
        editTextPrice = findViewById(R.id.price);

        btnAddNewBookDB = findViewById(R.id.add);

        btnAddNewBookDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookQuery bookQuery = new BookQuery(getBaseContext());
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();
                int yearPublic = Integer.parseInt(editTextYearPublic.getText().toString());
                int price = Integer.parseInt(editTextPrice.getText().toString());
                Books books = new Books(title, author, yearPublic, price);

                bookQuery.add(books);
                Toast.makeText(getBaseContext(), "Add Success", Toast.LENGTH_SHORT).show();
                reset();
                setResult(RESULT_OK, null);
                finish();
            }
        });


    }

    protected void reset() {
        editTextAuthor.setText("");
        editTextTitle.setText("");
        editTextYearPublic.setText("");
        editTextPrice.setText("");
    }
}