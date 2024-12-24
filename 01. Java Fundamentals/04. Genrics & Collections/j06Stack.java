/*
 * Topic: Stack Implementation in Java
 * 
 * Key Characteristics:
 * - LIFO (Last-In-First-Out) data structure
 * - Extends Vector class (legacy implementation)
 * - Thread-safe (inherited from Vector)
 * - Recommended to use ArrayDeque instead for stack operations
 * - Primarily used for:
 *   - Undo mechanisms
 *   - Expression evaluation
 *   - Backtracking algorithms
 *   - Function call management
 */

import java.util.*;

public class j06Stack {
    public static void main(String[] args) {
        // Initialize Stack
        System.out.println("=== Stack Initialization ===");
        Stack<String> stack = new Stack<>();
        
        // 1. Basic Stack Operations
        System.out.println("\n=== Basic Stack Operations ===");
        
        // push - Adds element to top
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        System.out.println("After pushing 3 elements: " + stack);
        
        // peek - Views top element without removing
        System.out.println("Top element (peek): " + stack.peek());
        
        // pop - Removes and returns top element
        System.out.println("Popped element: " + stack.pop());
        System.out.println("After pop: " + stack);
        
        // empty - Checks if stack is empty
        System.out.println("Is stack empty? " + stack.empty());
        
        // search - Returns 1-based position from top
        System.out.println("Position of 'First' from top: " + stack.search("First"));

        // 2. Vector Operations (inherited)
        System.out.println("\n=== Vector Operations ===");
        
        // Adding elements using Vector methods
        stack.add("Fourth");                        // Adds at end
        stack.add(0, "Start");                     // Adds at index
        System.out.println("After Vector adds: " + stack);
        
        // Accessing elements
        System.out.println("Element at index 1: " + stack.get(1));
        System.out.println("First element: " + stack.firstElement());
        System.out.println("Last element: " + stack.lastElement());
        
        // 3. Stack Usage Examples
        System.out.println("\n=== Stack Usage Examples ===");
        
        // Example 1: Reverse a string
        System.out.println("String Reversal Example:");
        String original = "Hello";
        Stack<Character> reverseStack = new Stack<>();
        
        // Push all characters
        for (char c : original.toCharArray()) {
            reverseStack.push(c);
        }
        
        // Pop to reverse
        StringBuilder reversed = new StringBuilder();
        while (!reverseStack.empty()) {
            reversed.append(reverseStack.pop());
        }
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
        
        // Example 2: Balanced parentheses checker
        System.out.println("\nBalanced Parentheses Example:");
        System.out.println("'((()))' is balanced: " + 
            checkBalancedParentheses("((()))"));
        System.out.println("'(()' is balanced: " + 
            checkBalancedParentheses("(()"));

        // 4. Iteration Methods
        System.out.println("\n=== Iteration Methods ===");
        Stack<String> iterStack = new Stack<>();
        iterStack.addAll(Arrays.asList("A", "B", "C", "D"));
        
        // Using Iterator
        System.out.println("Standard iteration:");
        Iterator<String> iterator = iterStack.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // Using forEach
        System.out.println("forEach iteration:");
        iterStack.forEach(item -> System.out.print(item + " "));
        System.out.println();

        // 5. Bulk Operations
        System.out.println("\n=== Bulk Operations ===");
        Stack<String> stack2 = new Stack<>();
        stack2.addAll(Arrays.asList("X", "Y", "Z"));
        System.out.println("New stack: " + stack2);
        
        // Clear all elements
        stack2.clear();
        System.out.println("After clear: " + stack2);
        
        // 6. Thread Safety Demo
        System.out.println("\n=== Thread Safety Demo ===");
        Stack<Integer> threadStack = new Stack<>();
        
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                threadStack.push(i);
                System.out.println("Pushed: " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                if (!threadStack.empty()) {
                    System.out.println("Popped: " + threadStack.pop());
                }
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });
        
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Helper method for balanced parentheses example
    private static boolean checkBalancedParentheses(String str) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty()) {
                    return false;
                }
                stack.pop();
            }
        }
        
        return stack.empty();
    }
} 