package com.example.databasework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextName, editTextAuthor;
    private Button addButton;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextName = findViewById(R.id.editTextName);
        editTextAuthor = findViewById(R.id.author);
        addButton = findViewById(R.id.add);

        dbHelper = new DataBaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookToDatabase();
            }
        });
    }

    private void addBookToDatabase() {
        String bookName = editTextName.getText().toString().trim();
        String bookAuthor = editTextAuthor.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            // Можно добавить сообщение об ошибке или валидацию
            return;
        }

        long result = dbHelper.addBook(bookName, bookAuthor);
        if (result != -1) {

            Toast.makeText(this, "Книга добавлена", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, "Ошибка добавления книги", Toast.LENGTH_SHORT).show();
        }
    }

}
