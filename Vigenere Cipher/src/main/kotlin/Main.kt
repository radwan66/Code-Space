import java.util.Scanner

/*
* it's a more like the Caesar cipher take from user two variables
* the text which is called (plain text) it will be encrypted
* and the key which is also text but the key must be
* the same size as the text.
* if the user inter hello and for the key he entered hi
* u have to make the key = text size how??
* add the key at itself character by character like this:
* ex.{
*       hello = 5 characters
*       hi = 2 characters
*       --------------------
*       hihih
*
*       repeat every character in the key until it equal the text
*    }
* 1 - locate the every character index in the alphabets
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
*  2 - do the same thing to the key locate all of it then plus the two indexes
*        ex.{
*             key = hihih , text = hello
*             7 + 7 = 14 -> o
*             4 + 8 = 12 -> m
*             11 + 7 = 18 -> s
*             11 + 8 = 19 -> t
*             14 + 7 = 21 -> v
*
*             the cipher text = omstv
*           }
*
* E(x) = ( x + n ) \ mod 26.
*
* D(x) = ( x + n ) \ mod 26.
*/


fun main(args: Array<String>) {

    // initialize list of alphabets
    val alpha = mutableListOf<Char>()
    for(i in 'a'..'z') alpha.add(i)
    // initialize Scanner to define what the user wants
    val input = Scanner(java.lang.System.`in`)
    var select: Int

    var text = ""
    var key = ""

    print("""
        Encrypt & Decrypt with Vigenere Cipher select what u want :)
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
            text = readln()

            print("Enter the key : ")
            key = readln()
            key = makeKey(key,text)
        }
        2 -> {
            print("Enter the cipherText : ")
            text = readln()

            print("Enter the key : ")
            key = readln()
            key = makeKey(key,text)
        }
    }

    var cipherText = vigenereCipher(text, alpha, key,select)
    println(cipherText)
}

private fun vigenereCipher(
    text: String,
    alpha: MutableList<Char>,
    key: String,
    type: Int
): String {
    var txtIndex = 0
    var index = 0
    var keyIndex = 0
    var cipherText = ""

    when (type) {
     1 ->   {
         for (i in text.indices) {
            // get the index for each
            // character in the plain text
            txtIndex = alpha.indexOf(text[i])
            // character in the key
            keyIndex = alpha.indexOf(key[i])
            // get the new character index
            index = txtIndex + keyIndex
            /*
              in case the new character index
              is bigger than 26 which is the
              total characters in the alphabets
            */
            if (index >= alpha.size) {
                index %= 26
                cipherText += alpha[index]
            } else {
                cipherText += alpha[index]
            }
        }
    }
    2 -> {
        for (i in text.indices) {
            // get the index for each
            // character in the plain text
            txtIndex = alpha.indexOf(text[i])
            // character in the key
            keyIndex = alpha.indexOf(key[i])
            // check which is the bigger first
            // get the new character index
            index = txtIndex - keyIndex
            if (index < 0) {
                cipherText += alpha[alpha.size + index]
            } else {
                cipherText += alpha[index]
            }
        }
    }
}
    return cipherText
}

private fun makeKey(key: String, text: String): String {
    var myKey = key

    // make sure the key is the same length
    // as the plain text
    if (myKey.length != text.length) {
        var i = 0
        while (myKey.length < text.length) {
            myKey += myKey[i]
            i++
        }
    }
    return myKey
}