## Bit Manipulation
Geeks For Geeks : https://www.geeksforgeeks.org/bitwise-algorithms/?ref=lbp

Bit stands for binary digit. A bit is the basic unit of information and can only have one of two possible values that is 0 or 1.

A binary number is a number expressed in the base-2 numeral system or binary numeral system. It is a method of mathematical expression which uses only two symbols: typically "0" (zero) and "1" (one).

We say that a certain bit is set if it is one, and cleared if it is zero.

The binary number $(a_k a_{k-1} \dots a_1 a_0)_2$ represents the number:

$$(a_k a_{k-1} \dots a_1 a_0)_2 = a_k \cdot 2^k + a_{k-1} \cdot 2^{k-1} + \dots + a_1 \cdot 2^1 + a_0 \cdot 2^0.$$

For instance, the binary number $1101_2$ represents the number $13$:

$$
\begin{align*}
1101_2 &= 1 \cdot 2^3 + 1 \cdot 2^2 + 0 \cdot 2^1 + 1 \cdot 2^0 \\
&= 1\cdot 8 + 1 \cdot 4 + 0 \cdot 2 + 1 \cdot 1 \\
&= 13
\end{align*}
$$
### Bitwise Algorithms

**Bitwise algorithms** in Data Structures and Algorithms (DSA) involve manipulating individual bits of binary representations of numbers to perform operations efficiently.

**1. Bitwise AND Operator (&)**
The bitwise AND operator is denoted using a single ampersand symbol, i.e. &. The & operator takes two equal-length bit patterns as parameters. The two-bit integers are compared. If the bits in the compared positions of the bit patterns are 1, then the resulting bit is 1. If not, it is 0.
![AND Truth Table](https://media.geeksforgeeks.org/wp-content/uploads/20220922161337/BItwiseANDoperatortruthtable-300x216.png)

**For Example**
 X = 7= (111)2 and Y = 4 = (100)2 .
![AND EXAMPLE](https://media.geeksforgeeks.org/wp-content/uploads/20220922161722/BitwiseANDExample-300x172.png)

**Observations for Bitwise AND (&)**
1. **Bitwise AND of \( n \) with \( n \):**
    \( n \& n = n \)
   
2. **Bitwise AND of \( n \) with 0:**
   \( n \& 0 = 0 \)
   
3. **Bitwise AND of \( n \) with 1:**
   \( n \& 1 = 0 \) if n is even
   \( n \& 1 = 1 \) if n is odd


**2. ​Bitwise OR Operator (|)**

The | Operator takes two equivalent length bit designs as boundaries; if the two bits in the looked-at position are 0, the next bit is zero. If not, it is 1.
![or TRUTH TABLe](https://media.geeksforgeeks.org/wp-content/uploads/20230731115139/BItwiseORoperatortruthtable-300x216-(2).png)

**For Example**
X = 7= (111)2 and Y = 4 = (100)2 
![or ex](https://media.geeksforgeeks.org/wp-content/uploads/20220922172435/BitwiseORExample-300x172.png)

**Observations for Bitwise OR (|)**

1. **Bitwise OR of \( n \) with \( n \):**
   \( n | n = n \)
   
2. **Bitwise OR of \( n \) with 0:**
  \( n | 0 = n \)


**3. ​Bitwise XOR Operator (^)**

The ^ operator (also known as the XOR operator) stands for Exclusive Or. Here, if bits in the compared position do not match their resulting bit is 1. i.e, The result of the bitwise XOR operator is 1 if the corresponding bits of two operands are opposite, otherwise 0.
![xor truth table](https://media.geeksforgeeks.org/wp-content/uploads/20230731115214/BItwiseXORoperatortruthtable-300x216-(1).png)

**For Example**
X = 7= (111)2 and Y = 4 = (100)2 
![xor ex](https://media.geeksforgeeks.org/wp-content/uploads/20220922173122/BitwiseXORExample-300x176.png)

**Observations for Bitwise XOR (^)**

1. **Bitwise XOR of \( n \) with \( n \):**
    \( n \oplus n = 0 \)
2. **Bitwise XOR of \( n \) with 0:**
    \( n \oplus 0 = n \)
   
3. **Bitwise XOR of \( n \) with 1:**
    \( n \oplus 1 \)
     - If \( n \) is even: \( n \oplus 1 = n + 1 \)
     - If \( n \) is odd: \( n \oplus 1 = n - 1 \)

**4. ​Bitwise NOT Operator (! or ~)**

The Bitwise Not (~) Operator takes a single value and returns its one’s complement. The one’s complement of a binary number is obtained by toggling all bits in it, i.e, transforming the 0 bit to 1 and the 1 bit to 0.

![not truth](https://media.geeksforgeeks.org/wp-content/uploads/20220922152419/BItwiseNOToperatortruthtable-300x166.png)

**For Example**
 X = 5= (101)2 
 ![not ex](https://media.geeksforgeeks.org/wp-content/uploads/20220922175838/BitwiseNOTExample-300x163.png)

**5. Bitwise Left Shift (<<)**

The left shift operator is denoted by the double left arrow key (<<). The general syntax for left shift is **n << k**. The left-shift operator causes the bits in shift expression to be shifted to the left by the number of positions specified by k. The bit positions that the shift operation has vacated are zero-filled. 

*Note: Every time we shift a number towards the left by 1 bit it multiply that number by 2.* 

$n \ll x = n \times 2^x$

![left shift](https://media.geeksforgeeks.org/wp-content/uploads/20221219184215/Logical-Left-Shift.png)

**For Example**
5 << 1 = 10
![left shift](https://media.geeksforgeeks.org/wp-content/uploads/20220922195905/11-300x44.png)

**6. Bitwise Right Shift (>>)**

The right shift operator is denoted by the double right arrow key (>>). The general syntax for the right shift is **n >> k**. The right-shift operator causes the bits in shift expression to be shifted to the right by the number of positions specified by k. 

*Note: Every time we shift a number towards the right by 1 bit it divides that number by 2.*

$n \gg x = \left\lfloor \frac{n}{2^x} \right\rfloor$

![right shift](https://media.geeksforgeeks.org/wp-content/uploads/20221219184254/Logical-Right-Shift.png)

**For Example**
5 = 00101 >> 1
![right shift](https://media.geeksforgeeks.org/wp-content/uploads/20220922200449/111-300x44.png)


## Bit Manipulation Cheat Sheet

**Note: Position Starts from 1 Right to Left**

### 1. Check if nth bit is set or unset

```java
int number = 42; // 101010 in binary
int n = 3;
if ((number & (1 << (n-1))) != 0) {
    System.out.println("Bit is set");
} else {
    System.out.println("Bit is not set"); // Not set
}
```

### 2. Set nth bit

```java
// Set the 4th bit in number
int number = 42; // 101010 in binary
int n = 4; 
number = number | (1 << (n-1));
System.out.println(Integer.toBinaryString(number)); // 101010 already set
```

### 3. Clear nth bit

```java
int number = 42; // 101010 in binary
int n = 4; 
number = number & ~(1 << (n-1))
System.out.println(Integer.toBinaryString(number)); // 100010 
```

### 4. Toggle nth bit
```java
int number = 42; // 101010 in binary
int n = 4; 
number = number ^ (1 << (n-1))
System.out.println(Integer.toBinaryString(number)); // 100010 
```

### 5. Counting Set bits

```java
int number = 42; // 101010 in binary
int count = 0;
while(number > 0){
    if((number & 1) == 1) count++;
    number >>= 1;
}
```

### 6. Is Power of 2
```java
int number = 16;
if ((number & (number - 1)) == 0 && number != 0) {
    System.out.println("Power of two"); // Power of two
} else {
    System.out.println("Not a power of two");
}
```

### 7. Swapping two numbers
```java
// Swap two values without using a temporary variable
int a = 10;
int b = 20;
a ^= b;
b ^= a;
a ^= b;
System.out.println("a: " + a); // a: 20
System.out.println("b: " + b); // b: 10
```

### 8. XOR of Range 0 to n
```java
xorRange(n){
    switch(n % 3){ // (n & 3)
        case 0: return n;     // if n is multiple of 4 
        case 1: return 1;     // If n % 4 gives remainder 1   
        case 2: return n + 1; // If n % 4 gives remainder 2     
        case 3: return 0;     // If n % 4 gives remainder 3   
    } 
}
```

### 9. XOR of Range n1 to n2
```java
xorRange(n1-1) ^ xorRange(n2);
```

### 10. Check if two integers have opposite signs
```java
if(x^y >0)
    return false;
else
    return true;
```

### 11. Add 1 to a given number
```java
(-(~x));
```

### 12. Turn off the rightmost set bit
```java
x & (x-1);
```

### Check whether a given number is a power of 4 or not
```java
if(!(x & (x-1))){
    if(__builtin_ctz(x)%2==0)
        return true;
}
```

### Compute modulus division by a power-of-2-number
```java
x & (powerOf2 - 1);
```

### Rotate bits of a number
```java
int leftCircularShift(int x,int shiftBy)
    return (x<<shiftBy) | (x>>shiftBy);

int rightCircularShift(int x,int shiftBy)
    return (x>>shiftBy) | (x<<shiftBy);
```

### Count number of bits to be flipped to convert A to B
```java
__builtin_popcount(a ^ b);
```

### Find whether a no is power of two
```java
return (n>0 and !(n&(n-1)));
```

### Position of rightmost set bit
```java
ffs(x);
//another way
log2(x & -x)+1;
//another way
if(x!=0)
    __builtin_ctz(x)+1;
```

### Calculate XOR from 1 to n
```java
switch(n & 3) // n % 4  
{ 
    case 0: return n;     // if n is multiple of 4 
    case 1: return 1;     // If n % 4 gives remainder 1   
    case 2: return n + 1; // If n % 4 gives remainder 2     
    case 3: return 0;     // If n % 4 gives remainder 3   
} 
```

### Binary representation of a given number
```java
void bin(unsigned n) 
{ 
    if (n > 1) 
        bin(n>>1); 
      
    cout<<(d & 1)<<endl;
}
```

### Find position of only set bit
```java
if(!(n&(n-1)))
	ffs(n);
```

### Turn off a particular bit in a number
```java
x & (~1<<(pos-1));
```

### Check if 2 numbers are equal
```java
if((x ^ y))
    return false;
else
    return true;
```

### Find XOR of numbers without using xor operator
```java
(x | y) & (~x | ~y);
```

### Swap three variables
```java
x = x ^ y ^ z;
y = x ^ y ^ z;
z = x ^ y ^ z;
x = x ^ y ^ z;
```

### Toggle Kth bit of a number
```java
x = x ^ 1<<(k-1);
```

### Toggle all bits except Kth bit of a number
```java
x = ~(x ^ 1<<(k-1);)
```

### Set the rightmost unset bit
```java
x = x | (x+1)
```

### Toggle the last m bits
```java
x ^ ~(-1<<m);
```

### Maximum XOR-value of at-most k-elements from 1 to n
```java
int x = log2(n) + 1;
return x<<1 - 1;

//Alt way

int res = 1; 
while (res <= n) 
    res <<= 1; 
// Finding number greater than 
// or equal to n with most significant  
// bit same as n. For example, if n is 
// 4, result is 7. If n is 5 or 6, result  
// is 7 
// Return res - 1 which denotes 
// a number with all bits set to 1 
return res - 1; 
``` 

### Check if a number is divisible by 8 using bitwise operators
```java
return (((n >> 3) << 3) == n);
```

### Toggle bits of a number except first and last bits
```java
int size = sizeof(int)*8 - __builtin_clz(n);
int one = (1<<(size-1))-1;
n = n ^ one;
n = n ^ 1;
```

### Toggle all even bits of a number
```java
int temp = n,i=1;
while(temp){
    n = n ^ 1>>(i*2);
    java;
    temp = temp>>1;
}
```

### Brian Kernighan’s Algorithm for counting set Bits
```java
while(n){
    n &=(n-1);
    counjava;
}

```
