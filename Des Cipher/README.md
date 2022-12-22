
# DES cipher

Data Encryption Standard (DES) has been discovered to be susceptible to extremely potent attacks, its popularity has been observed to be slightly declining. As a block cipher, DES encrypts data in blocks of 64 bits each, so 64 bits of plain text are used as the input, and DES outputs 64 bits of ciphertext. With a few minor exceptions, both encryption and decryption use the same algorithm and key. The key has a 56-bit length.

![encryption](https://binaryterms.com/wp-content/uploads/2020/04/DES.jpg)

### Steps
- Convert both the key and the plain text to binary 
- performed The permutation on plain text and the key with different `Matrix`.
- Next, the permutation produces two halves of the permuted block saying Left(L) and Right(R).
- generate 16 keys for encryption
- Now each L and R go through 16 rounds of the encryption process.
- In the end, L and R are rejoined and a Final Permutation (FP) is performed on the combined block
- The result of this process produces 64-bit ciphertext.

### Matrix
We have said that the key used by DES is 56 bits. The original key actually has 64 bits in it. However, every eighth bit of the key is destroyed to create a 56-bit key before the DES procedure even begins. So, the bits in the following locations are ignored: 8, 16, 24, 32, 40, 48, 56, and 64.
As a result, the original 64-bit key is reduced to a 56-bit key by throwing away every eighth bit of the key.
DES is built on substitution (also known as confusion) and transposition, two key components of cryptography (also called diffusion). Each of the 16 steps in DES is referred to as a round. The stages of substitution and transposition are carried out in each round. Now let's talk about the fundamental DES steps.
```java
// Initial Permutation table
    public static final byte[] IP = {
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9,  1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7
    };

    // Permuted Choice 1 table
    public static final byte[] PC1 = {
            57, 49, 41, 33, 25, 17, 9,
            1,  58, 50, 42, 34, 26, 18,
            10, 2,  59, 51, 43, 35, 27,
            19, 11, 3,  60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7,  62, 54, 46, 38, 30, 22,
            14, 6,  61, 53, 45, 37, 29,
            21, 13, 5,  28, 20, 12, 4
    };
```
you can find the all matrix have been used in [Resources Class](https://github.com/moha-b/Code-Space/blob/main/des/src/Resources.java) 

### Generate Keys
- Divide the key to two halves C1, D1
- Performed rotations on both halves
- Concatenate the both keys 
- Apply another and the final permutation
- our first key is ready
```java
C1 = Resources.leftShift(Resources.C, rotationTimes);
D1 = Resources.leftShift(Resources.D, rotationTimes);

int[] CnDn = new int[56];

// concatenate the both keys
System.arraycopy(C1, 0, CnDn, 0, 28);
System.arraycopy(D1, 0, CnDn, 28, 28);

// permutation : reduce the key to 48-bit
int[] Kn = new int[48];
for (int i = 0; i < Kn.length; i++) {
    Kn[i] = CnDn[Resources.PC2[i] - 1];
}
```
