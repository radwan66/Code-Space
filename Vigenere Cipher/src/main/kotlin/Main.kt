import java.util.Scanner

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