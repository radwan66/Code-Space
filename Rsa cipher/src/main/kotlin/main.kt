import kotlin.math.pow

fun main(args: Array<String>) {
    val p = 3
    val q = 7
    var m = 12.0
    var n = p * q
    val z = ((p - 1) * (q - 1)).toDouble()
    var e = _getValue(2.0,z,'e')
    var d = _getValue(e,z,'d').toInt()
    var c = m.pow(e) % n
    var m2 = c.pow(d) % n

    println(c)
    println(m2)

}
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

fun _primeNumber(num: Int): Boolean {
    var flag = true
    for (i in 2..num / 2) {
        if (num % i == 0) {
            flag = false
            break
        }
    }
    return flag
}
