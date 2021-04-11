package se.hernebring.store;

import java.util.List;

import se.hernebring.domain.Author;
import se.hernebring.domain.Book;

public class AuthorController implements Controller {

    private Author author;
    private int id;
    private final BookController bookController;

    public AuthorController(BookController bookController) {
        this.bookController = bookController;
        create("Joshua Bloch");
    }

    public void create(String name) {
        author = new Author(name);
    }
    
    @Override
    public boolean isNull() {
        boolean authorIsNull = author == null ? true : false;
        boolean bookIsNull = bookController.isNull();
        return authorIsNull && bookIsNull; // both must be null
    }

    public boolean isNotNull() {
        boolean authorIsNotNull = author != null ? true : false;
        boolean bookIsNotNull = !bookController.isNull();
        return authorIsNotNull && bookIsNotNull; // both must be not null
    }

    public void getFirstIdsFromStorage() {
        author = (Author) Persistence.getAuthor(1);
        id = 1;
        bookController.getId(1);
    }

    public void saveAnAuthorWith3Books() {
        create("Joshua Bloch");
        saveBook("Effective Java 3rd Edition");
        saveBook("Java Puzzlers With Access Codes");
        saveBook("Java™ Puzzlers: Traps, Pitfalls, and Corner Cases");
        save();
    }

    @Override
    public void save() {
        Persistence.save(author);
    }

    private void saveBook(String title) {
        bookController.create(title);
        bookController.save();
        author.add(bookController.getId(id));
        id++;
    }

    @Override
    public void delete() {
        Persistence.delete(id);
        this.id = -1;
        author = null;
    }

    @Override
    public String toString() {
        return author.toString();
    }

    public int getNumberOfBooks() {
        List<Book> books = author.getBooks();
        return books.size();
    }

}
