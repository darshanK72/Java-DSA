## Bit Manipulation
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

**5. Botwise Left Shift (<<)**

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
