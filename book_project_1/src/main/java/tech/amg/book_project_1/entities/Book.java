package tech.amg.book_project_1.entities;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Book {
    public static long lastIdValue=1;
    long id;
    String title, author, category;
    int rating;

    public Book(String title, String author, String category, int rating) {
        this.id = lastIdValue++;
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }
}
