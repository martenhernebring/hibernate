package se.hernebring.store;

import se.hernebring.domain.Author;

public class AuthorStorage implements Storage {

    private Author author;
    private int id;
    
    public AuthorStorage() {
        createLocal();
    }
    
    public void createLocal() {
        author = new Author("Joshua Bloch");
    }
    
    public Author getAuthor() {
        return author;
    }

    public void save() {
        Persistence.save(author);
    }

    public boolean isNull() {
        return author == null ? true : false;
    }

    public void getId(int id) {
        this.id = id;
        author = Persistence.getAuthor(id);
    }
    
    public void delete() {
        Persistence.delete(id);
        this.id = -1;
        author = null;
    }
    
    @Override
    public String toString() {
        return author.toString();
    }

}
