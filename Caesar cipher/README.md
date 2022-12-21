
# Caesar Cipher

one of the earliest and most basic forms of encryption. It is basically a sort of substitution cipher in which each letter of a given text is substituted with a letter that is located a certain number of positions farther down the alphabet. As an illustration, if there was a shift of 1, A would be replaced by B, B by C, and so on. Julius Caesar, who reportedly employed it to communicate with his officials, is said to be the inspiration for the method's moniker.

![](https://higherlogicdownload.s3.amazonaws.com/IMWUC/UploadedImages/92757287-d116-4157-b004-c2a0aba1b048/Caesar_cipher.png)

### How to encrypt? 
- Traverse the given text one character at a time .
- For each character, transform the given character as per the rule, depending on whether we’re encrypting or decrypting the text.
- Return the new string generated

``` kotlin
for (i in plainText.indices) {
    // get the index for each
    // character in the plain text
    indexTxt = alpha.indexOf(plainText[i])
    // add the key value to the character index
    index1 = indexTxt + key
    // check if the new index is
    // not out the alphabets
    cipherText1 += if (index1 > 25) {
        alpha[index1 % 26]
    } else {
        alpha[index1]
        
    }
}
```
### How to decrypt? 
basically the same as the encryption but in the opposite
``` korlin
for (i in plainText.indices) {
    // get the index for each
    // character in the plain text
    indexTxt = alpha.indexOf(plainText[i])
    // subtraction the key value to the character index
    index1 = indexTxt - key
    // check if the new index is
    // not out the alphabets
    cipherText1 += if (index1 < 0) {
        alpha[index1+alpha.size]
    } else {
        alpha[index1]
    }
}
```
