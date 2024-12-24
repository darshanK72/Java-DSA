/*
 * Comprehensive Java NIO Demo
 * Demonstrates modern file operations using Java NIO.2:
 * 1. Path Operations
 *    - Path manipulation
 *    - Path information
 *    - Path comparison
 * 2. Files Operations
 *    - File reading/writing
 *    - File attributes
 *    - File copying/moving
 * 3. Directory Operations
 *    - Directory creation
 *    - Directory walking
 *    - Directory streaming
 * 4. Watch Service
 *    - Directory monitoring
 *    - File change notifications
 */

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

public class j04NIODemo {
    public static void main(String[] args) {
        demonstratePathOperations();
        demonstrateFilesOperations();
        demonstrateDirectoryOperations();
    }

    private static void demonstratePathOperations() {
        System.out.println("\n=== Path Operations Demo ===");

        // Creating paths
        Path path1 = Paths.get("test.txt");
        Path path2 = Paths.get("dir", "subdir", "test.txt");
        System.out.println("Path elements: " + path2.getNameCount());
        System.out.println("Subpath (0,2): " + path2.subpath(0, 2)); // prints dir/subdir
        System.out.println("Full path: " + path2.toAbsolutePath());
        
        // Path information
        System.out.println("File name: " + path1.getFileName());
        System.out.println("Parent: " + path1.getParent());
        System.out.println("Root: " + path1.getRoot());
        System.out.println("Absolute: " + path1.isAbsolute());
        
        // Path normalization and resolution
        Path base = Paths.get("/home/user");
        Path relative = Paths.get("docs/../downloads/file.txt");
        System.out.println("Normalized: " + relative.normalize());
        System.out.println("Resolved: " + base.resolve(relative));
        
        // Path comparison
        Path path3 = Paths.get("test.txt");
        System.out.println("Paths equal: " + path1.equals(path3));
        System.out.println("Paths same file: " + path1.toAbsolutePath().equals(path3.toAbsolutePath()));
    }

    private static void demonstrateFilesOperations() {
        System.out.println("\n=== Files Operations Demo ===");

        try {
            Path file = Paths.get("nio_test.txt");
            
            // Writing files
            Files.writeString(file, "Hello NIO!");
            Files.write(file, "More content".getBytes(), StandardOpenOption.APPEND);
            
            // Reading files
            String content = Files.readString(file);
            System.out.println("File content: " + content);
            
            // File attributes
            System.out.println("Size: " + Files.size(file));
            System.out.println("Last modified: " + Files.getLastModifiedTime(file));
            System.out.println("Is hidden: " + Files.isHidden(file));
            
            // Basic file attributes
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("Creation time: " + attrs.creationTime());
            System.out.println("Is directory: " + attrs.isDirectory());
            System.out.println("Is regular file: " + attrs.isRegularFile());
            
            // File permissions - Cross-platform approach
            try {
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    // Windows-specific file attributes
                    DosFileAttributes dosAttrs = Files.readAttributes(file, DosFileAttributes.class);
                    System.out.println("Windows attributes:");
                    System.out.println("- Hidden: " + dosAttrs.isHidden());
                    System.out.println("- System file: " + dosAttrs.isSystem());
                    System.out.println("- Read only: " + dosAttrs.isReadOnly());
                    System.out.println("- Archive: " + dosAttrs.isArchive());
                } else {
                    // POSIX systems (Unix, Linux, macOS)
                    Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(file);
                    System.out.println("POSIX Permissions: " + PosixFilePermissions.toString(permissions));
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("File permission operations not supported on this system");
            }
            
            // Copy and move operations
            Path copy = Paths.get("copy.txt");
            Files.copy(file, copy, StandardCopyOption.REPLACE_EXISTING);
            
            Path moved = Paths.get("moved.txt");
            Files.move(copy, moved, StandardCopyOption.ATOMIC_MOVE);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demonstrateDirectoryOperations() {
        System.out.println("\n=== Directory Operations Demo ===");

        try {
            // Create directory structure
            Path dir = Paths.get("nio_test_dir");
            Files.createDirectories(dir);
            
            // Create some test files
            Files.writeString(dir.resolve("file1.txt"), "Content 1");
            Files.writeString(dir.resolve("file2.txt"), "Content 2");
            
            // List directory contents
            System.out.println("Directory listing using DirectoryStream:");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path entry : stream) {
                    System.out.println(entry.getFileName());
                }
            }
            
            // Stream directory contents with filter
            System.out.println("\nFiltered directory listing using Stream:");
            try (Stream<Path> stream = Files.list(dir)) {
                stream.filter(path -> path.toString().endsWith(".txt"))
                      .forEach(path -> System.out.println(path.getFileName()));
            }
            
            // Walk directory tree
            System.out.println("\nWalking directory tree:");
            try (Stream<Path> stream = Files.walk(dir)) {
                stream.forEach(path -> System.out.println(path));
            }
            
            // Find files
            System.out.println("\nFinding files:");
            try (Stream<Path> stream = Files.find(dir, Integer.MAX_VALUE,
                    (path, attrs) -> attrs.isRegularFile() && 
                                   path.toString().endsWith(".txt"))) {
                stream.forEach(path -> System.out.println(path));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} 