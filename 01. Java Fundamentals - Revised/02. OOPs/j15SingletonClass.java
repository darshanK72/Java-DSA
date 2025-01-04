/**
 * Topic: **Singleton Pattern in Java**
 * Singleton is a creational design pattern that ensures a class has only one instance and provides
 * a global point of access to that instance.
 * 
 * In this example, we demonstrate:
 * 1) **Private Constructor**: Prevents direct instantiation
 * 2) **Static Instance**: Single instance maintained by the class itself
 * 3) **Global Access Point**: Public static method to get the instance
 * 
 * **Key Points**:
 * - Only one instance can exist
 * - Lazy initialization possible
 * - Thread safety considerations
 * - Used for shared resources like configuration, connection pools
 * 
 * Example Structure:
 * - Book class: Demonstrates basic singleton pattern
 * - Enhanced versions show thread-safety and different initialization approaches
 */

public class j15SingletonClass {
    public static void main(String args[]) {
        // Basic singleton usage
        Book b1 = Book.getBookInstance(200, "Game of Thrones");
        System.out.println("First book: " + b1.getName() + ", Pages: " + b1.getPages());

        // Try to create another instance
        Book b2 = Book.getBookInstance(201, "Song of Ice and Fire");
        System.out.println("Second book: " + b2.getName() + ", Pages: " + b2.getPages());
        
        // Demonstrate same instance
        System.out.println("Are b1 and b2 same instance? " + (b1 == b2));

        // Thread-safe singleton
        ThreadSafeBook tsb1 = ThreadSafeBook.getInstance("Harry Potter");
        ThreadSafeBook tsb2 = ThreadSafeBook.getInstance("Lord of the Rings");
        System.out.println("\nThread-safe book: " + tsb1.getName());
        System.out.println("Still same book: " + tsb2.getName());

        // Enum singleton
        EnumBook.INSTANCE.setName("The Hobbit");
        System.out.println("\nEnum singleton book: " + EnumBook.INSTANCE.getName());
    }
}

// Basic Singleton Pattern
class Book {
    private int pages;
    private String name;
    private static Book instance;

    private Book(int pages, String name) {
        this.pages = pages;
        this.name = name;
    }

    public static Book getBookInstance(int pages, String name) {
        if (instance == null) {
            instance = new Book(pages, name);
        }
        return instance;
    }

    // Getters
    public int getPages() { return pages; }
    public String getName() { return name; }
}

// Thread-safe Singleton using double-checked locking
class ThreadSafeBook {
    private String name;
    private static volatile ThreadSafeBook instance;
    
    private ThreadSafeBook(String name) {
        this.name = name;
    }
    
    public static ThreadSafeBook getInstance(String name) {
        if (instance == null) {
            synchronized (ThreadSafeBook.class) {
                if (instance == null) {
                    instance = new ThreadSafeBook(name);
                }
            }
        }
        return instance;
    }
    
    public String getName() { return name; }
}

// Enum Singleton (Thread-safe by default)
enum EnumBook {
    INSTANCE;
    
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// Eager initialization Singleton
class EagerBook {
    private static final EagerBook instance = new EagerBook();
    
    private EagerBook() {}
    
    public static EagerBook getInstance() {
        return instance;
    }
}
