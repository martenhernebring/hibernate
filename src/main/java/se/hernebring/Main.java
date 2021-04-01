package se.hernebring;

import se.hernebring.domain.Book;

public class Main {

    protected Main() { }

    public static void main(String[] args) {
        if(args != null) {
            System.out.println(new Book(args[0]));
        }
    }
}