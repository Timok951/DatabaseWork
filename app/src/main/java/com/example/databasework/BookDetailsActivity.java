package com.example.databasework;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.databasework.DataBaseHelper;
import com.example.databasework.MainActivity;
import com.example.databasework.R;

public class BookDetailsActivity extends AppCompatActivity {

    private TextView bookNameTextView, bookAuthorTextView;
    private Button deleteButton;
    private DataBaseHelper dbHelper;
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookNameTextView = findViewById(R.id.bookName);
        bookAuthorTextView = findViewById(R.id.bookAuthor);
        deleteButton = findViewById(R.id.deleteButton);

        dbHelper = new DataBaseHelper(this);

        // Get the book ID passed from the MainActivity
        bookId = getIntent().getIntExtra("BOOK_ID", -1);

        // Fetch the book details and display them
        loadBookDetails();

        // Set up delete button click listener
        deleteButton.setOnClickListener(v -> deleteBook());
    }

    private void loadBookDetails() {
        Cursor cursor = dbHelper.getBookById(bookId); // Method to fetch the book by ID
        if (cursor != null && cursor.moveToFirst()) {
            String bookName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_NAME));
            String bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COLUMN_AUTHOR));
            bookNameTextView.setText(bookName);
            bookAuthorTextView.setText(bookAuthor);
            cursor.close();
        }
    }

    private void deleteBook() {
        int result = dbHelper.deleteBookById(bookId);
        if (result > 0) {
            // Book was successfully deleted
            Intent intent = new Intent(BookDetailsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Show a message or handle the failure to delete
        }
    }
}
