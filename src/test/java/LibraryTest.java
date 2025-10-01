import org.example.Book;
import org.example.BookStatus;
import org.example.Library;
import org.example.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book duplicateBook2;
    private int year1;
    private int year2;
    private int year3;
    private String firstBookTitle;
    private String secondBookTitle;
    private String nonExistence;
    private String secondBookAuthor;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, BookStatus.BANNED);
        book2 = new Book("1984", "George Orwell", 1949, BookStatus.BORROWED);
        book3 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, BookStatus.EXIST);
        book4 = new Book("Animal Farm", "George Orwell", 1945, BookStatus.EXIST);
        duplicateBook2 = new Book("1984", "Another Author", 1950, BookStatus.EXIST);
        year1 = 1960;
        year2 = 1949;
        year3 = 1925;
        firstBookTitle = "The Great Gatsby";
        secondBookTitle = "1984";
        nonExistence = "Nonexistent";
        secondBookAuthor = "George Orwell";
    }

    @Test
    void testAddBook() {
        library.addBook(book1);
        library.addBook(book2);
        assertEquals(2, library.getBooks().size());
        assertTrue(library.getBooks().contains(book1));
        assertTrue(library.getBooks().contains(book2));
    }

    @Test
    void testDeleteBooks() {
        library.addBook(book1);
        library.addBook(book2);
        library.deleteBook(book1);
        library.deleteBook(book3);
        assertEquals(1, library.getBooks().size());
        assertFalse(library.getBooks().contains(book1));
        assertTrue(library.getBooks().contains(book2));
    }

    @Test
    void testUpdateBooks() {
        library.addBook(book1);
        library.updateBook(book1 , book2);
        assertEquals(1, library.getBooks().size());
        assertEquals(secondBookTitle, book1.getTitle());
    }


    @Test
    void testSearchBTitle() {
        library.addBook(book1);
        MyLinkedList<Book> books = library.searchByTitle(firstBookTitle);
        assertEquals(1, books.size());
        assertTrue(books.contains(book1));
    }

    @Test
    void testSearchByTitleMultipleBooksSameTitle() {
        library.addBook(book2);
        library.addBook(duplicateBook2);

        MyLinkedList<Book> result = library.searchByTitle(secondBookTitle);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSearchByTitleBookNotExists() {
        library.addBook(book1);
        library.addBook(book2);

        MyLinkedList<Book> result = library.searchByTitle(nonExistence);

        assertNull(result);
    }

    @Test
    void testSearchByAuthor() {
        library.addBook(book2);
        library.addBook(book4);

        MyLinkedList<Book> result = library.searchByAuthor(secondBookAuthor);

        MyLinkedList<Book> result2 = library.searchByAuthor(nonExistence);

        assertNull(result2);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSortedByPublicationYear() {
        library.addBook(book3); // 1960
        library.addBook(book1); // 1925
        library.addBook(book2); // 1949

        library.sortedByPublicationYear();
        MyLinkedList<Book> sortedBooks = library.getBooks();
        assertEquals(3, sortedBooks.size());
        assertEquals(year1, sortedBooks.get(0).getPublicationYear());
        assertEquals(year2, sortedBooks.get(1).getPublicationYear());
        assertEquals(year3, sortedBooks.get(2).getPublicationYear());
    }

    @Test
    void testGetBooks() {
        assertNotNull(library.getBooks());
        assertEquals(0, library.getBooks().size());

        library.addBook(book1);
        assertEquals(1, library.getBooks().size());
    }
}
