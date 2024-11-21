package com.example.databasework;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Book> bookArrayList;

    // Конструктор
    public RecyclerViewAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookArrayList.get(position);
        holder.bookName.setText(book.getBook_Name());
        holder.bookAuthor.setText(book.getBook_Author());
        holder.itemView.setOnClickListener(v -> {
            // Create an intent to navigate to BookDetailsActivity
            Intent intent = new Intent(context, BookDetailsActivity.class);
            intent.putExtra("BOOK_ID", book.getID_Book());  // Pass the book ID to BookDetailsActivity
            context.startActivity(intent);
        });

        // Add a click listener to open the details page
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookDetailsActivity.class);
            intent.putExtra("BOOK_ID", book.getID_Book()); // Pass the book ID
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    // Внутренний класс ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookName); // Используйте правильные идентификаторы
            bookAuthor = itemView.findViewById(R.id.bookAuthor); // Используйте правильные идентификаторы
        }
    }
}
