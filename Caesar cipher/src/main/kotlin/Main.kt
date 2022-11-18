import java.util.Scanner

/*
* it's a simple idea take from user two variables
* the text which is called (plain text) it will be encrypted
* and the key which is just a number
* 1 - locate the first character index in the alphabets
*      ex. {
*               A -> 0  E -> 4  I -> 8  M -> 12  Q -> 16  U -> 20  Y -> 24
*               B -> 1  F -> 5  G -> 9  N -> 13  R -> 17  V -> 21  Z -> 25
*               C -> 2  G -> 6  K -> 10 O -> 14  S -> 18  W -> 22
*               D -> 3  H -> 7  L -> 11 P -> 15  T -> 19  X -> 23
*
*               text = hello
*               h = 7, e = 4, l = 11, o = 14
*           }
*
*  2 - then add the key value to the character value
*        ex.{
*             key = 3 , text = hello
*             7 + 3 = 10 -> k
*             4 + 3 = 7 -> h
*             11 + 3 = 14 -> o
*             11 + 3 = 14 -> o
*             14 + 3 = 17 -> r
*
*             the cipher text = khoor
*           }
*
* E(x) = ( x + n ) \ mod 26.
*
* D(x) = ( x + n ) \ mod 26.
*/

fun main(args: Array<String>) {
    // initialize list of alphabets
    val alpha = mutableListOf<Char>()
    for (i in 'a'..'z') alpha.add(i)
    var input = Scanner(System.`in`)

    var plainText = ""
    var cipherText = ""
    var key = 0
    var select = 0
    var index = 0

    // .trimIndent : Detects a common minimal indent of all the input lines,
    // removes it from every line and also removes the first and the last lines if they are blank
    // (notice difference blank vs empty).
    print("""
        Encrypt & Decrypt with Caesar Cipher select what u want :)
        1 . Encryption
        2 . Decryption
        => 
    """.trimIndent())

    select = input.nextInt()
    while (select != 1 && select != 2){
        print("""
            you select a wrong number |:
            1 . Encryption
            2 . Decryption
            => 
        """.trimIndent())
        select = input.nextInt()
    }
    when (select) {
        1 -> {
            print("Enter the plainText : ")
            // not tested yet to remove any possible spaces in the input text
            plainText = readln().replace("\\s+","")

            print("Enter the key : ")
            key = input.nextInt()
        }
        2 -> {
            print("Enter the cipherText : ")
            plainText = readln().replace("\\s+","")

            print("Enter the key : ")
            key = input.nextInt()
        }
    }

    cipherText = caesarCipher(plainText, alpha, index, key, select)
    println(cipherText)
}

private fun caesarCipher(
    plainText: String,
    alpha: MutableList<Char>,
    index: Int,
    key: Int,
    type:Int
): String {
    var indexTxt = 0
    var index1 = index
    var cipherText1 = ""

    when(type){
        1 -> {
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
        }
        2 -> {
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
        }
    }

    return cipherText1
}