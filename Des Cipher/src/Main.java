import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        * Test cases :
        * [
        *   text1 = "123456ABCD132536"
        *   key1 = "AABB09182736CCDD"
        *   cipher =  "C0B7A8D05F3A829C"
        * ],
        * [
        *   text2 = 8787878787878787
        *   key2 = 0E329232EA6D0D73
        *   cipher = 0000000000000000
        * ]
        */
        /*
        * Generate the key :
        * 1 - get the binary key
        * 2 - permutation & divide the key
        * 3 - preform the rotations
        *      {
        *         1 - rotation
        *         2 - concatenate
        *         3 - permutation
        *         4 - return 48-bit key
        *       }
        *
        */
        DES.apply("123456ABCD132536","AABB09182736CCDD",false);
        DES.apply("C0B7A8D05F3A829C","AABB09182736CCDD",true);
    }

}