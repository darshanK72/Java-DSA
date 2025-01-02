/*
 * Comprehensive File Reading Demo
 * Demonstrates different buffered reading approaches in Java:
 * 1. BufferedReader
 *    - Line by line reading
 *    - Character by character reading
 *    - Reading into char array
 * 2. BufferedInputStream
 *    - Byte by byte reading
 *    - Reading into byte array
 * 3. Advanced Reading
 *    - Reading with different buffer sizes
 *    - Performance comparison
 */

import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class j03FileReading {
    public static void main(String[] args) {
        // 1. BUFFERED CHARACTER READING
        demonstrateBufferedReader();

        // 2. BUFFERED BYTE READING
        demonstrateBufferedInputStream();

        // 3. PERFORMANCE TESTING
        compareReadingPerformance();
    }

    private static void demonstrateBufferedReader() {
        System.out.println("\n=== BufferedReader Demo ===");

        // 1.1 Line by line reading
        try (BufferedReader reader = new BufferedReader(
                new FileReader("file1.txt"))) {
            
            System.out.println("Reading line by line:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.2 Character by character reading
        try (BufferedReader reader = new BufferedReader(
                new FileReader("file1.txt"))) {
            
            System.out.println("\nReading character by character:");
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1.3 Reading into char array
        try (BufferedReader reader = new BufferedReader(
                new FileReader("file1.txt"))) {
            
            System.out.println("\nReading into char array:");
            char[] charBuffer = new char[1024];
            int charsRead;
            while ((charsRead = reader.read(charBuffer)) != -1) {
                System.out.print(new String(charBuffer, 0, charsRead));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateBufferedInputStream() {
        System.out.println("\n=== BufferedInputStream Demo ===");

        // 2.1 Byte by byte reading
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("file2.txt"))) {
            
            System.out.println("Reading byte by byte:");
            int data;
            while ((data = bis.read()) != -1) {
                System.out.print((char) data);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.2 Reading into byte array
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("file2.txt"))) {
            
            System.out.println("\nReading into byte array:");
            byte[] byteBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(byteBuffer)) != -1) {
                System.out.write(byteBuffer, 0, bytesRead);
            }
            System.out.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.3 Reading with mark/reset support
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("file2.txt"))) {
            
            System.out.println("\nDemonstrating mark/reset:");
            if (bis.markSupported()) {
                // Read first 3 bytes
                for (int i = 0; i < 3; i++) {
                    System.out.print((char) bis.read());
                }
                
                // Mark this position
                bis.mark(100);
                
                // Read next 3 bytes
                System.out.print(" | ");
                for (int i = 0; i < 3; i++) {
                    System.out.print((char) bis.read());
                }
                
                // Reset to marked position and read again
                bis.reset();
                System.out.print(" | ");
                for (int i = 0; i < 3; i++) {
                    System.out.print((char) bis.read());
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compareReadingPerformance() {
        System.out.println("\n=== Performance Comparison ===");

        // Test different buffer sizes
        int[] bufferSizes = {1024, 4096, 8192, 16384};
        
        for (int bufferSize : bufferSizes) {
            Instant start = Instant.now();
            
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("largefile.txt"), bufferSize)) {
                
                char[] buffer = new char[bufferSize];
                while (reader.read(buffer) != -1) {
                    // Just read, don't process
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