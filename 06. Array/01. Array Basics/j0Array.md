# What is an Array?

An array is a collection of items of the same variable type that are stored at contiguous memory locations. It’s one of the most popular and simple data structures and is often used to implement other data structures. Each item in an array is indexed starting with 0. Each element in an array is accessed through its index.

![Array](https://media.geeksforgeeks.org/wp-content/uploads/20240405101013/Memory-Representation-of-Array-(1).webp)

## Need of Array Data Structures

Arrays are a fundamental data structure in computer science. They are used in a wide variety of applications, including:

- Storing data for processing
- Implementing data structures such as stacks and queues
- Representing data in tables and matrices
- Creating dynamic data structures such as linked lists and trees

## Types of Array

There are two main types of arrays:

1. One-dimensional arrays: These arrays store a single row of elements.
![1D Array](https://media.geeksforgeeks.org/wp-content/uploads/20240405123929/One-Dimensional-Array(1-D-Array).webp)
2. Multidimensional arrays: These arrays store multiple rows of elements.

![2D Array](https://media.geeksforgeeks.org/wp-content/uploads/20240408165401/Two-Dimensional-Array(2-D-Array-or-Matrix).webp)
3. Three-Dimensional Array(3-D Array): A 3-D Multidimensional array contains three dimensions, so it can be considered an array of two-dimensional arrays.
![3D Array](https://media.geeksforgeeks.org/wp-content/uploads/20240408165421/Three-Dimensional-Array(3-D-Array).webp)

## Array Operations

Common operations performed on arrays include:

- Traversal: Visiting each element of an array in a specific order (e.g., sequential, reverse).
- Insertion: Adding a new element to an array at a specific index.
- Deletion: Removing an element from an array at a specific index.
- Searching: Finding the index of an element in an array.

## Applications of Array

Arrays are used in a wide variety of applications, including:

- Storing data for processing
- Implementing data structures such as stacks and queues
- Representing data in tables and matrices
- Creating dynamic data structures such as linked lists and trees


#### 1. Static Arrays

In static arrays, memory is allocated at compile time with a fixed size. The size of the array cannot be altered or updated after compilation. This type of memory allocation is also known as static or compile-time memory allocation. Only a fixed amount of memory, as specified in square brackets [], is allocated for storage. 

If the size of the array is not known in advance, declaring a larger size than necessary may result in memory wastage, while declaring a smaller size may not provide enough memory to store all the elements. Therefore, static memory allocation may not be preferred in such cases.

```java
// Static Array
int[] arr = { 1, 2, 3, 4, 5 };
```


#### 2. Dynamic Arrays
Dynamic arrays allocate memory at runtime without having a fixed size. If a user needs to declare an array with a random size, dynamic arrays are used instead of static arrays. This type of memory allocation is also known as dynamic or run-time memory allocation. It allows specifying the size of the array during the execution of the program.

```java
// Dynamic Integer Array
ArrayList<Integer> arr = new ArrayList<>();
```

## Subarray

A subarray is a contiguous part of an array, meaning it consists of elements that are adjacent to each other in the original array. In simpler terms, a subarray is an array within another array. 

For example, if we have an array `[1, 2, 3, 4]`, sub array of that are `(1), (2), (3), (4), (1,2), (2,3), (3,4), (1,2,3), (2,3,4), and (1,2,3,4)`

**Number of Subarrays :** For an array of size \( n \), the number of non-empty subarrays is given by the formula \( \frac{n \times (n + 1)}{2} \). This formula counts all possible contiguous subarrays, including single elements and the entire array itself.

![Subarrays](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20220620163127/subarray.png)

## Subsequence
A subsequence is a sequence that can be derived from another sequence by removing zero or more elements, without changing the order of the remaining elements. Unlike subarrays, the elements in a subsequence are not required to be contiguous. 

For example, if we have a sequence `[1, 2, 3, 4]`, its subsequences are `(1), (2), (3), (4), (1,2), (1,3),(1,4), (2,3), (2,4), (3,4), (1,2,3), (1,2,4), (1,3,4), (2,3,4), (1,2,3,4).`

**Number of Subsequences :** For a sequence of size \( n \), the number of non-empty subsequences is \( 2^n - 1 \). This formula accounts for all possible combinations of including or excluding each element from the original sequence, excluding the empty subsequence.

![Subsequences](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20220620164513/Subsequences-768x432.png)

## Subset

A subset is a set that contains all its elements from another set. In other words, if every element of one set is also present in another set, then the former set is considered a subset of the latter. 

For example, if we have Set \( A = \{1, 2\} \) and Set \( B = \{1, 2, 3\} \), then Set \( A \) is a subset of Set \( B \), denoted as \( A \subseteq B \).

**Representation :** Subsets are denoted using the symbol \( \subseteq \). For instance, if Set \( A \) is a subset of Set \( B \), it is represented as \( A \subseteq B \).

![Subset](https://media.geeksforgeeks.org/wp-content/cdn-uploads/20220620184210/subset1-768x433.png)

#### Difference Between Subset, Subarray, and Subsequence

| Feature       | Subset                                      | Subarray                                     | Subsequence                                 |
|---------------|---------------------------------------------|----------------------------------------------|---------------------------------------------|
| Definition    | A set that contains all its elements from another set. | A contiguous part of an array.              | A sequence derived by removing zero or more elements from another sequence without changing the order. |
| Contiguity    | Not necessarily contiguous.                 | Contiguous.                                 | Not necessarily contiguous.                 |
| Order         | Irrelevant.                                 | Relevant.                                   | Relevant.                                   |
| Size          | Can be smaller, equal, or larger than the original set. | Can be smaller, equal, or larger than the original array. | Can be smaller, equal, or larger than the original sequence. |
| Number        | \(2^n\), where \(n\) is the number of elements in the original set. | \(\frac{n \times (n + 1)}{2}\), where \(n\) is the number of elements in the original array. | \(2^n - 1\), where \(n\) is the number of elements in the original sequence. |
