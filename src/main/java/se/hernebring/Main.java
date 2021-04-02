package se.hernebring;

import org.hibernate.Session;

import se.hernebring.domain.Book;

public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        Book book;
        if (args != null && !args[0].equals("")) {
            int argNo = args.length;
            if (argNo > 0) {
                if (argNo < 3) {
                    book = new Book(args[0]);
                    System.out.println(book);
                } else {
                    book = new Book(args[0], args[1], args[2]);
                    System.out.println(book);
                }
            }
        } else {
            System.err.println("Usage: se.hernebring.Main title <isbn> <author>");
            System.exit(1);
        }
        // Tutor tutor = new Tutor("ABC123" ,"Edward", 30000);
        // Student student = new Student("Sara Hedborn");

        /*
         * var factory = getSessionFactory(); Session session = factory.openSession();
         * var transaction = session.beginTransaction(); /*Tutor myTutor =(Tutor)
         * session.get(Tutor.class, 1); student.allocateTutor(myTutor);
         * session.save(student); System.out.println(student.getTutorName()); //Student
         * student = (Student) session.get(Student.class, 2); //session.delete(student);
         * Student student2 = new Student("Martin Sten"); Tutor myTutor =(Tutor)
         * session.get(Tutor.class, 1); student2.allocateTutor(myTutor);
         * session.save(student2);
         * 
         * transaction.commit(); session.close();
         */
    }
}