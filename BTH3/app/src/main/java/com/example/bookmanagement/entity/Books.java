package com.example.bookmanagement.entity;

public class Books {

    private int id;

    private String title;

    private String author;

    private int yearPublic;

    private int price;

    public Books() {

    }

    public Books(int id, String title, String author, int yearPublic, int price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublic = yearPublic;
        this.price = price;
    }

    public Books(String title, String author, int yearPublic, int price) {
        this.title = title;
        this.author = author;
        this.yearPublic = yearPublic;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublic() {
        return yearPublic;
    }

    public void setYearPublic(int yearPublic) {
        this.yearPublic = yearPublic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + " TITLE: " + title + " AUTHOR: " + author
                + " YEAR: " + yearPublic + " PRICE: " + price;
    }
}
