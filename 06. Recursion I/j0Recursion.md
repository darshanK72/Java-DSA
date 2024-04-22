# Recursion

Recursion is defined as a process which calls itself directly or indirectly and the corresponding function is called a recursive function.

## Properties of Recursion:

Recursion has some important properties. Some of which are mentioned below:

- The primary property of recursion is the ability to solve a problem by breaking it down into smaller sub-problems, each of which can be solved in the same way.
- A recursive function must have a base case or stopping criteria to avoid infinite recursion.
- Recursion involves calling the same function within itself, which leads to a call stack.
- Recursive functions may be less efficient than iterative solutions in terms of memory and performance.

## Types of Recursion:

**A) Direct recursion**: When a function is called within itself directly it is called direct recursion. This can be further categorized into four types:
  
1. **Tail recursion :** If a recursive function calling itself and that recursive call is the last statement in the function then it’s known as Tail Recursion. After that call the recursive function performs nothing. The function has to process or perform any operation at the time of calling and it does nothing at returning time.

```java
static void fun(int n) 
  { 
    if (n > 0)  
    { 
      System.out.print(n + " "); 
  
      // Last statement in the function 
      fun(n - 1); 
    } 
  } 
```
![Tail Recursion](https://media.geeksforgeeks.org/wp-content/uploads/20190621015455/tail1.jpg)

2. **Head recursion :** If a recursive function calling itself and that recursive call is the first statement in the function then it’s known as Head Recursion. There’s no statement, no operation before the call. The function doesn’t have to process or perform any operation at the time of calling and all operations are done at returning time.

```java
static void fun(int n) 
{ 
    if (n > 0) { 
  
        // First statement in the function 
        fun(n - 1); 
  
        System.out.print(" "+ n); 
    } 
} 
```

![Head Recursion](https://media.geeksforgeeks.org/wp-content/uploads/20190621015721/head3.jpg)

3. **Tree recursion :**  To understand Tree Recursion let’s first understand Linear Recursion. If a recursive function calling itself for one time then it’s known as Linear Recursion. Otherwise if a recursive function calling itself for more than one time then it’s known as Tree Recursion.

```java
static void fun(int n) 
{ 
    if (n > 0) { 
        System.out.print(" "+ n); 
  
        // Calling once 
        fun(n - 1); 
  
        // Calling twice 
        fun(n - 1); 
    } 
} 
```

![Tree Recursion](https://media.geeksforgeeks.org/wp-content/uploads/20190621015814/tree4.jpg)

4. **Nested recursion :** In this recursion, a recursive function will pass the parameter as a recursive call. That means **“recursion inside recursion”**. 

```java
static int fun(int n) 
{ 
    if (n > 100) 
        return n - 10; 
  
    // A recursive function passing parameter 
    // as a recursive call or recursion 
    // inside the recursion 
    return fun(fun(n + 11)); 
} 
```

![Nested Recursion](https://media.geeksforgeeks.org/wp-content/uploads/20190621015942/nested2.jpg)

5. **Indirect recursion**: Indirect recursion occurs when a function calls another function that eventually calls the original function and it forms a cycle. In this recursion, there may be more than one functions and they are calling one another in a circular manner.

![Indirect Recursion](https://media.geeksforgeeks.org/wp-content/uploads/20190608232223/Capture34.jpg)


### Complexity of Recursion

Analyzing the time and space complexity of recursive algorithms involves understanding how the algorithm behaves as the input size increases. There are several techniques for determining these complexities:

#### Time Complexity:

1. **Recurrence Relations**: Recurrence relations describe the time complexity of a recursive algorithm in terms of the size of the input and the time complexity of its sub-problems. You can derive these relations by analyzing how many recursive calls are made and how much work is done in each call.

2. **Master Theorem**: The Master Theorem provides a way to solve recurrence relations and determine the time complexity of divide-and-conquer algorithms, which often involve recursion.

3. **Iteration Method**: This method involves unwinding the recursion manually, converting the recursive algorithm into an equivalent iterative one, and then analyzing its time complexity. This approach can provide insight into the number of iterations required to solve the problem.

#### Space Complexity:

1. **Call Stack Analysis**: Recursion uses the call stack to store information about each recursive call. Analyzing the maximum depth of the call stack and the space required for each call can help determine the space complexity of the algorithm.

2. **Memory Usage Analysis**: Apart from the call stack, recursive algorithms may use additional memory for data structures such as arrays, lists, or trees. Analyzing the space required by these data structures and how it grows with the input size can provide insight into the overall space complexity.

3. **Tail Recursion Optimization**: Some languages and compilers optimize tail-recursive functions to reuse the same stack frame for each recursive call, effectively reducing the space complexity to O(1) if tail recursion elimination is applied.

4. **Auxiliary Space**: In addition to the call stack, recursive algorithms may use auxiliary space for variables, data structures, or temporary storage. Analyzing the space required by these auxiliary components is essential for determining the overall space complexity.
