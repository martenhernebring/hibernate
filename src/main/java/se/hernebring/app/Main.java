package se.hernebring.app;

import se.hernebring.store.BookDecorator;

public class Main {

    protected Main() {
    }

    public static void main(String[] args) {
        try{
            BookDecorator book = new BookDecorator(args);
            System.out.println(book);
        } catch (IllegalArgumentException ex) {
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