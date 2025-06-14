import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued = false;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}

class Student {
    String name;
    String email;
    Book issuedBook;

    Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

public class LibraryManagementSystem {
    static List<Book> books = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeLibrary();
        int choice;

        do {
            System.out.println("\n==== LIBRARY MANAGEMENT SYSTEM ====");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display Issued Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: issueBook(); break;
                case 2: returnBook(); break;
                case 3: displayIssuedBooks(); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option!");
            }
        } while (choice != 4);
    }

    static void initializeLibrary() {
        books.add(new Book(101, "The Kite Runner", "Khaled Hosseini"));
        books.add(new Book(102, "To Kill A Mockingbird", "Harper Lee"));
        books.add(new Book(103, "The Alchemist", "Paulo Coelho"));
        books.add(new Book(104, "Pride And Prejudice", "Jane Austen"));
        books.add(new Book(105, "A Tale Of Two Cities", "Charles Dickens"));
    }

    static void issueBook() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        System.out.println("\nAvailable Books:");
        for (Book b : books) {
            if (!b.isIssued) {
                System.out.println("ID: " + b.id + " | " + b.title + " by " + b.author);
            }
        }

        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        Book selectedBook = null;
        for (Book b : books) {
            if (b.id == id && !b.isIssued) {
                selectedBook = b;
                break;
            }
        }

        if (selectedBook != null) {
            Student s = new Student(name, email);
            s.issuedBook = selectedBook;
            selectedBook.isIssued = true;
            students.add(s);
            System.out.println("Book issued successfully!");
        } else {
            System.out.println("Book not found or already issued.");
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student returningStudent = null;
        for (Student s : students) {
            if (s.issuedBook != null && s.issuedBook.id == id) {
                returningStudent = s;
                break;
            }
        }

        if (returningStudent != null) {
            returningStudent.issuedBook.isIssued = false;
            System.out.println("Book returned: " + returningStudent.issuedBook.title);
            students.remove(returningStudent);
        } else {
            System.out.println("No such issued book found.");
        }
    }

    static void displayIssuedBooks() {
        if (students.isEmpty()) {
            System.out.println("No books are currently issued.");
            return;
        }

        for (Student s : students) {
            System.out.println("\nStudent: " + s.name + " | Email: " + s.email);
            System.out.println("Issued Book: " + s.issuedBook.title + " by " + s.issuedBook.author + " (ID: " + s.issuedBook.id + ")");
        }
    }
}
