package org.example;

public class Library {
    private MyLinkedList<Book> books;

    public Library() {
        books = new MyLinkedList<>();
    }

    /* اضافه کردن کتاب*/
    public void addBook(Book book) {
        books.add(book);
    }

    /* حذف کتاب در صورت وجود */
    public void deleteBook(Book deletingBook){
        boolean found = false;
        for(Book book : books){
            if(book.equals(deletingBook)){
                books.remove(book);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Book not found");
        }
    }

    /* به روز رسانی کتاب با گرفتن اطلاعات */
    public void updateBook(Book updatingBook,Book newBook){
        boolean found = false;

        for(Book book : books){
            if(book.equals(updatingBook)){
                book.setTitle(newBook.getTitle());
                book.setAuthor(newBook.getAuthor());
                book.setPublicationYear(newBook.getPublicationYear());
                book.setStatus(newBook.getStatus());
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Book not found");
        }
    }

    /* جست و جو بر اساس عنوان */
    public MyLinkedList<Book> searchByTitle(String title){
        MyLinkedList<Book> searchedBooks = new MyLinkedList<>();
        boolean found = false;
        for(Book book : books){
            if(book.getTitle().equals(title)){
                found = true;
                searchedBooks.add(book);
            }
        }
        if(!found){
            System.out.println("Book not found");
            return null;
        }
        return searchedBooks;
    }

    /* جست و جو بر اساس نویسنده */
    public MyLinkedList<Book> searchByAuthor(String author){
        MyLinkedList<Book> searchedBooks = new MyLinkedList<>();
        boolean found = false;
        for(Book book : books){
            if(book.getAuthor().equals(author)){
                found = true;
                searchedBooks.add(book);
            }
        }
        if(!found){
            System.out.println("Author not found");
            return null;
        }
        return searchedBooks;
    }

    /* نزولی مرتب کردن */
    public void sortedByPublicationYear(){
        books.bubbleSort();

    }

    public MyLinkedList<Book> getBooks() {
        return books;
    }

    /* چاپ کتاب ها */
    public void printBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }


}
