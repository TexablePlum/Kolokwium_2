package org.example.kolokwium_2;

public class Book {
    private String author;
    private String title;
    private long isbn;
    private int number;
    private String reader;

    public Book(String author, String title, long isbn, int number)
    {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.number = number;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String getReader() {
        return reader;
    }
    public void setReader(String reader) {
        this.reader = reader;
    }
}
