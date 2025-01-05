/*
 * Complete File Class Demo
 * This program demonstrates all major operations possible with Java's File class:
 * - File/Directory Creation and Deletion
 * - File Properties and Metadata
 * - Directory Operations
 * - File Path Operations
 * - File Permissions
 * - File Comparison
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class j01FileOperations {
    public static void main(String[] args) {
        try {
            // 1. File Creation and Basic Operations
            File file = new File("example.txt");
            System.out.println("\n=== File Creation and Basic Operations ===");
            System.out.println("File created: " + file.createNewFile());
            System.out.println("File exists: " + file.exists());
            System.out.println("Delete file: " + file.delete());

            // 2. Directory Operations
            System.out.println("\n=== Directory Operations ===");
            File dir = new File("testDirectory");
            System.out.println("Directory created: " + dir.mkdir());
            File nestedDir = new File("testDirectory/nested/deep");
            System.out.println("Nested directories created: " + nestedDir.mkdirs());

            // Create some files in the directory
            File fileInDir = new File(dir, "test.txt");
            fileInDir.createNewFile();

            // Create temporary files in the same directory
            System.out.println("\n=== Temporary Files in Directory ===");
            File tempFile1 = File.createTempFile("temp", ".txt", dir);
            File tempFile2 = File.createTempFile("prefix", ".tmp", dir);
            System.out.println("Temp file 1 created: " + tempFile1.getName());
            System.out.println("Temp file 2 created: " + tempFile2.getName());

            // List directory contents
            System.out.println("\nDirectory contents:");
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    System.out.println(f.getName());
                }
            }

            // 3. File Properties and Metadata
            System.out.println("\n=== File Properties ===");
            file.createNewFile(); // Recreate the file
            System.out.println("Name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Canonical path: " + file.getCanonicalPath());
            System.out.println("Parent: " + file.getParent());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Is File: " + file.isFile());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Is Hidden: " + file.isHidden());
            
            // Format last modified date
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            System.out.println("Last modified: " + sdf.format(new Date(file.lastModified())));

            // 4. File Permissions
            System.out.println("\n=== File Permissions ===");
            System.out.println("Can Read: " + file.canRead());
            System.out.println("Can Write: " + file.canWrite());
            System.out.println("Can Execute: " + file.canExecute());
            
            // Set permissions
            file.setReadable(true);
            file.setWritable(true);
            file.setExecutable(false);

            // 5. Path Operations
            System.out.println("\n=== Path Operations ===");
            System.out.println("Is Absolute: " + file.isAbsolute());
            System.out.println("Path separator: " + File.separator);
            System.out.println("Path separator char: " + File.separatorChar);

            // 6. File Comparison
            File file2 = new File("example2.txt");
            file2.createNewFile();
            System.out.println("\n=== File Comparison ===");
            System.out.println("Files are same: " + file.compareTo(file2));
            System.out.println("Files are equal: " + file.equals(file2));

            // 7. Space Information
            System.out.println("\n=== Disk Space Information ===");
            System.out.println("Total space: " + file.getTotalSpace() / 1024 / 1024 + " MB");
            System.out.println("Free space: " + file.getFreeSpace() / 1024 / 1024 + " MB");
            System.out.println("Usable space: " + file.getUsableSpace() / 1024 / 1024 + " MB");

            // Cleanup
            file.delete();
            file2.delete();
            deleteDirectory(dir);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Utility method to delete directory and its contents
    private static void deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }
} 