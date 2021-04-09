package se.hernebring.app;

import java.io.File;

import se.hernebring.print.ConfigPrinter;
import se.hernebring.print.Printer;
import se.hernebring.print.SimplePrinter;
import se.hernebring.store.Client;

public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        Printer printer;
        if (args != null && args.length > 0 && !args[0].equals("")) {
            printer = new SimplePrinter(new File("print_test.tmp"), new Client(args));
        } else {
            printer = new ConfigPrinter(new File("config_test.tmp"), new Client());
        }
        printer.print();
    }

}