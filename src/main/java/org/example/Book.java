package org.example;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private int publicationYear;
    private BookStatus status;

    public Book(String title, String author, int publicationYear, BookStatus status) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nAuthor: " + author +
                "\nPublication Year: " + publicationYear +
                "\nStatus: " + status +
                "\n-----------------------";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book other = (Book) obj;
        return publicationYear == other.publicationYear &&
                title.equals(other.title) &&
                author.equals(other.author) &&
                status == other.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationYear, status);
    }
}
