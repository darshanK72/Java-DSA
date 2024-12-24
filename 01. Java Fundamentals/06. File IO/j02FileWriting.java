/*
 * Comprehensive File Writing Demo
 * Demonstrates all major ways to write files in Java:
 * 1. Character-based Writing
 *    - FileWriter
 *    - BufferedWriter
 *    - PrintWriter
 * 2. Byte-based Writing
 *    - FileOutputStream
 *    - BufferedOutputStream
 *    - DataOutputStream
 * 3. Advanced Writing
 *    - Random Access Files
 *    - Files utility class (NIO)
 */

import java.io.*;
import java.nio.file.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Properties;

public class j02FileWriting {
    public static void main(String[] args) {
        // 1. CHARACTER STREAMS
        demonstrateCharacterWriting();

        // 2. BYTE STREAMS
        demonstrateByteWriting();

        // 3. ADVANCED WRITING
        demonstrateAdvancedWriting();

        // 4. PERFORMANCE TESTING
        compareWritingPerformance();
    }

    private static void demonstrateCharacterWriting() {
        System.out.println("\n=== Character Stream Writing Demo ===");

        // 1.1 Basic FileWriter
        try (FileWriter writer = new FileWriter("file1.txt")) {
            System.out.println("Writing with FileWriter:");
            // Simple string writing
            writer.write("Hello using FileWriter!\n");
            writer.write("This is a direct write.\n");
            
            // Write char array
            char[] charArray = {'H', 'e', 'l', 'l', 'o'};
            writer.write(charArray);
            
            // Write portion of char array
            writer.write(charArray, 0, 3);  // Writes "Hel"
            
            // Write single character
            writer.write(65);  // Writes 'A'
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.2 BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("file2.txt", true))) {
            
            System.out.println("Writing with BufferedWriter:");
            // Write strings with platform-independent line breaks
            writer.write("Hello using BufferedWriter!");
            writer.newLine();
            writer.write("This is buffered writing.");
            writer.newLine();
            
            // Write multiple lines
            for(int i = 1; i <= 3; i++) {
                writer.write("Line " + i);
                writer.newLine();
            }
            
            writer.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.3 PrintWriter
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter("file3.txt")))) {
            
            System.out.println("Writing with PrintWriter:");
            // Formatted output
            writer.printf("Formatted number: %d\n", 42);
            writer.println("This is a new line");
            writer.print("This stays on same line");
            writer.println("This is after");
            
            // Print different data types
            writer.println(42);          // integer
            writer.println(3.14);        // double
            writer.println(true);        // boolean
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateByteWriting() {
        System.out.println("\n=== Byte Stream Writing Demo ===");

        // 2.1 FileOutputStream
        try (FileOutputStream fos = new FileOutputStream("file4.txt")) {
            System.out.println("Writing with FileOutputStream:");
            // Write string as bytes
            String text = "Hello using FileOutputStream!";
            fos.write(text.getBytes());
            
            // Write byte array
            byte[] bytes = {65, 66, 67, 68};  // ABCD
            fos.write(bytes);
            
            // Write single byte
            fos.write(69);  // E
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.2 BufferedOutputStream
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("file5.txt"))) {
            
            System.out.println("Writing with BufferedOutputStream:");
            // Write byte array with buffer
            String text = "Using BufferedOutputStream";
            byte[] bytes = text.getBytes();
            bos.write(bytes);
            
            bos.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.3 DataOutputStream
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("file6.dat"))) {
            
            System.out.println("Writing with DataOutputStream:");
            // Write primitive data types
            dos.writeInt(42);
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
            dos.writeUTF("Hello DataOutputStream");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateAdvancedWriting() {
        System.out.println("\n=== Advanced Writing Demo ===");

        // 3.1 RandomAccessFile
        try (RandomAccessFile raf = new RandomAccessFile("file7.txt", "rw")) {
            System.out.println("Writing with RandomAccessFile:");
            // Write at specific position
            raf.writeBytes("Hello");
            raf.seek(2);  // Move to position 2
            raf.writeBytes("XX");  // Overwrite characters
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.2 NIO Files class
        try {
            System.out.println("Writing with NIO Files:");
            // Write string in one go
            Files.writeString(Path.of("file8.txt"), "Hello NIO Files!");
            
            // Write bytes with options
            Files.write(Path.of("file9.txt"), 
                       "Hello Bytes!".getBytes(),
                       StandardOpenOption.CREATE,
                       StandardOpenOption.APPEND);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.3 Properties file
        Properties props = new Properties();
        props.setProperty("name", "value");
        props.setProperty("key", "data");
        
        try (FileOutputStream out = new FileOutputStream("config.properties")) {
            System.out.println("Writing Properties file:");
            props.store(out, "Properties file example");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compareWritingPerformance() {
        System.out.println("\n=== Performance Comparison ===");

        // Test different buffer sizes
        int[] bufferSizes = {1024, 4096, 8192, 16384};
        
        for (int bufferSize : bufferSizes) {
            Instant start = Instant.now();
            
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("perf_test_" + bufferSize + ".txt"), bufferSize)) {
                
                // Write 100,000 lines
                for (int i = 0; i < 100_000; i++) {
                    writer.write("Performance test line " + i + "\n");
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Duration duration = Duration.between(start, Instant.now());
            System.out.printf("Buffer size %d: %d ms%n", 
                            bufferSize, duration.toMillis());
        }
    }
}