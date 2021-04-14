package se.hernebring.app;

import java.io.File;

import se.hernebring.deprecated.OldConfigPrinter;
import se.hernebring.deprecated.OldSimplePrinter;
import se.hernebring.deprecated.OldBookController;
import se.hernebring.print.Printer;
import se.hernebring.print.ManyToManyPrinter;
import se.hernebring.print.HarnestPrinter;
import se.hernebring.store.AuthorController;
import se.hernebring.store.BookController;
import se.hernebring.store.PublisherController;

@SuppressWarnings("deprecation")
public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        //BasicConfigurator.configure(); for logging
        Printer printer;
        if (args != null && args.length > 0 && !args[0].equals("")) {
            Character c1 = args[0].trim().charAt(0);
            if(Character.isDigit(c1)) {
                if(c1 == '3') {
                    printer = new OldConfigPrinter(new File("config_test.tmp"), new OldBookController(args));
                } else {
                    printer = new HarnestPrinter(new File("relational_test.tmp"), new AuthorController(new BookController()));
                }
            } else {
                printer = new OldSimplePrinter(new File("print_test.tmp"), new OldBookController(args));
            }
        } else {
            printer = new ManyToManyPrinter(new File("many_to_many_test.tmp"), new AuthorController(new BookController()), new PublisherController());
        }
        printer.print();
    }

}