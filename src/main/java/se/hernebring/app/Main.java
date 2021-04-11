package se.hernebring.app;

import java.io.File;

import se.hernebring.deprecated.OldConfigPrinter;
import se.hernebring.deprecated.OldSimplePrinter;
import se.hernebring.deprecated.OldBookController;
import se.hernebring.print.Printer;
import se.hernebring.print.HarnestPrinter;
import se.hernebring.store.AuthorController;
import se.hernebring.store.BookController;

@SuppressWarnings("deprecation")
public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        //BasicConfigurator.configure(); for logging
        Printer printer;
        if (args != null && args.length > 0 && !args[0].equals("")) {
            if(Character.isDigit(args[0].trim().charAt(0))) {
                printer = new OldConfigPrinter(new File("config_test.tmp"), new OldBookController(args));
            } else {
                printer = new OldSimplePrinter(new File("print_test.tmp"), new OldBookController(args));
            }
        } else {
            printer = new HarnestPrinter(new File("relational_test.tmp"), new AuthorController(new BookController()));
        }
        printer.print();
    }

}