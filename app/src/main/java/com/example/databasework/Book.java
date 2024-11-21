package com.example.databasework;

public class Book {
    private int ID_Book;  // 3 usages
    private String Book_Name;  // 3 usages
    private String Book_Author;  // 3 usages

    // Конструктор класса
    public Book(int ID_Book, String book_Name, String book_Author) {
        this.ID_Book = ID_Book;
        this.Book_Name = book_Name;
        this.Book_Author = book_Author;
    }

    // Геттер для ID_Book
    public int getID_Book() {
        return ID_Book;
    }

    // Сеттер для ID_Book
    public void setID_Book(int ID_Book) {
        this.ID_Book = ID_Book;
    }

    // Геттер для Book_Name
    public String getBook_Name() {
        return Book_Name;
    }

    // Сеттер для Book_Name
    public void setBook_Name(String book_Name) {
        this.Book_Name = book_Name;
    }

    // Геттер для Book_Author
    public String getBook_Author() {
        return Book_Author;
    }

    // Сеттер для Book_Author
    public void setBook_Author(String book_Author) {
        this.Book_Author = book_Author;
    }
}

