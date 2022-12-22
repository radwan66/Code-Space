
# RSA Cipher
The concept of RSA is based on the fact that big integers are hard to factor in. The public key is made up of two numbers, one of which is the product of two enormous prime numbers. The same two prime numbers are also used to create the private key. Therefore, the private key is compromised if someone is able to factorize the huge integer. As a result, the key size completely determines how strong encryption is, and doubling or tripling the key size significantly boosts encryption strength. RSA keys are frequently 1024 or 2048 bits large, however, experts predict that 1024-bit keys will soon be broken. But as of right now, it appears to be an impossible feat.

![](https://media.geeksforgeeks.org/wp-content/uploads/20200518124317/RSA3.png)

### Steps
it's simple all we need just calculate the `E` and `D` 
```kotlin
fun _getValue(ee: Double, z: Double,type:Char): Double{
    var e = ee
    var d = 0.0
    if (type == 'e') {
        while (true) {
            if (_gdc(e, z) == 1.0) break else e++
        }
        return e
    }else{
        var i = 0
        while (d != e){
            d = (1 + (i * z))
            if (d % e == 0.0) {
                d = (d / e);
            }
            i++
        }
        return d
    }
}
```
```kotlin
fun _gdc(ee: Double, zz: Double): Double {
    var e = ee
    var z = zz
    var temp: Double
    while (true){
        temp = e % z;
        if (temp == 0.0) return z
        e = z
        z = temp
    }
}
```