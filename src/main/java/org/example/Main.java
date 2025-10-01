package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "library_log.csv";

    public static void main(String[] args) {
        Library library = new Library();
        MyLinkedList <Book> logBooks;
        loadData(library);
        String commands = "Available commands:\n" +
                "1. add - Add a new book\n" +
                "2. delete - Delete an existing book\n" +
                "3. update - Update book information\n" +
                "4. search by title - Find books by title\n" +
                "5. search by author - Find books by author\n" +
                "6. sort- Find books by year\n"+
                "7. print - Print books\n";

        Book book;
        String title, author;
        System.out.println(commands);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine();
            switch (command){
                case "add":
                    book = readBookFromUser(scanner);
                    library.addBook(book);
                    logBooks = new MyLinkedList<>();
                    logBooks.add(book);
                    writeLibrary("Add" ,logBooks);
                    break;
                case  "delete":
                    book = readBookFromUser(scanner);
                    library.deleteBook(book);
                    logBooks = new MyLinkedList<>();
                    logBooks.add(book);
                    writeLibrary("Delete" ,logBooks);
                    break;
                case "update":
                    book = readBookFromUser(scanner);
                    System.out.println("Please enter new book information:");
                    Book newBook =  readBookFromUser(scanner);
                    library.updateBook(book, newBook);
                    logBooks = new MyLinkedList<>();
                    logBooks.add(book);
                    writeLibrary("Update" ,logBooks);
                    break;
                case "search by title":
                    System.out.print("Please enter the book title: ");
                    title = scanner.nextLine();
                    MyLinkedList<Book> searchedBooks = library.searchByTitle(title);
                    if(searchedBooks != null) {
                        writeLibrary("TitleSearch", searchedBooks);
                    }
                    break;
                case "search by author":
                    System.out.print("Please enter the book author: ");
                    author = scanner.nextLine();
                    MyLinkedList<Book> searchedByAuthor = library.searchByAuthor(author);
                    if(searchedByAuthor != null) {
                        writeLibrary("AuthorSearch", searchedByAuthor);
                    }
                    break;
                case "sort":
                    library.sortedByPublicationYear();
                    writeLibrary("Sort" ,library.getBooks());
                    break;
                case "print":
                    library.printBooks();
                    writeLibrary("Print" ,library.getBooks());
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    public static Book readBookFromUser(Scanner scanner) {
        System.out.print("Please enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Please enter the book author: ");
        String author = scanner.nextLine();
        System.out.print("Please enter the publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter the book status (EXIST, BORROWED, BANNED): ");
        BookStatus status = BookStatus.valueOf(scanner.nextLine().trim().toUpperCase());

        return new Book(title, author, year, status);
    }

    public static void writeLibrary(String operation, MyLinkedList<Book> books) {
        try (FileWriter writer = new FileWriter(FILE_NAME , true)) {
            writer.append("\nOperation: ").append(operation).append("\n");

            writer.append("Title,Author,PublicationYear,Status\n");
            for (Book book : books) {
                writer.append(book.getTitle()).append(",")
                        .append(book.getAuthor()).append(",")
                        .append(String.valueOf(book.getPublicationYear())).append(",")
                        .append(book.getStatus().name())
                        .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadData(Library library) {
        String fileName = "data.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                String title = values[0];
                String author = values[1];
                int year = Integer.parseInt(values[2]);
                BookStatus status = BookStatus.valueOf(values[3]);

                library.addBook(new Book(title, author, year, status));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}