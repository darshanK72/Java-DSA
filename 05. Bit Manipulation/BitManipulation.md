# Bit Manipulation Operations and Observations

## 1. Bitwise AND (&)
- **Example**: `1101 & 1011 = 1001`
- **Observation**: The result will only have a `1` in positions where both operands have a `1`.
- **Formula**: `n & n = n`, `n & 0 = 0`

## 2. Bitwise OR (|)
- **Example**: `1101 | 1011 = 1111`
- **Observation**: The result will have a `1` in positions where at least one operand has a `1`.
- **Formula**: `n | n = n`, `n | 0 = n`

## 3. Bitwise XOR (^)
- **Example**: `1101 ^ 1011 = 0110`
- **Observation**: The result will have a `1` in positions where the bits in the operands are different.
- **Formula**: `n ^ n = 0`, `n ^ 0 = n`

## 4. Bitwise NOT (~)
- **Example**: `~1101 = 0010` (assuming a 4-bit representation)
- **Observation**: Each bit is flipped/inverted.
- **Formula**: `~n = -(n+1)`

## 5. Bit Shift Left (<<)
- **Example**: `1101 << 2 = 0100`
- **Observation**: Each bit is shifted left by the specified number of positions, and `0`s fill the vacated positions.
- **Formula**: `n << x = n * (2^x)`

## 6. Bit Shift Right (>>)
- **Example**: `1101 >> 2 = 0011`
- **Observation**: Each bit is shifted right by the specified number of positions. For unsigned numbers, `0`s fill the vacated positions.
- **Formula**: `n >> x = n / (2^x)`

## 7. Bit Rotation
- **Example** (left rotation): `1101 <<< 2 = 0111`
- **Example** (right rotation): `1101 >>> 2 = 1110`
- **Observation**: Bits shifted out on one end are brought back in on the other end.
- **Note**: No standard formula, as this operation is not directly supported in all programming languages.

## 8. Bit Masking
- **Example**: `1101 & 0100 = 0100` (masking with `0100` isolates the third bit)
- **Observation**: A mask is used to select or modify particular bits in a number.
- **Formula**: `n & mask` (to extract bits), `n | mask` (to set bits), `n & ~mask` (to clear bits)

# Important Observations
- Bitwise operations are performed on the binary representation of numbers.
- Shifting left by `n` positions is equivalent to multiplying by `2^n`.
- Shifting right by `n` positions is equivalent to integer division by `2^n` (for unsigned numbers).
- Bitwise NOT inverts the bits and can be used to calculate the two's complement (negative) of a number.
- Bitwise AND can be used to clear specific bits by ANDing with a mask that has `0`s in the bit positions to be cleared.
- Bitwise OR can be used to set specific bits by ORing with a mask that has `1`s in the bit positions to be set.
- Bitwise XOR can be used to toggle specific bits by XORing with a mask that has `1`s in the bit positions to be toggled.
