public class Resources {

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

    // Permuted Choice 2 table
    public static final byte[] PC2 = {
            14, 17, 11, 24, 1,  5,
            3,  28, 15, 6,  21, 10,
            23, 19, 12, 4,  26, 8,
            16, 7,  27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };

    // Array to store the number of rotations that are to be done on each round
    public static final byte[] rotations = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    // Expansion (aka P-box) table
    public static final byte[] E = {
            32, 1,  2,  3,  4,  5,
            4,  5,  6,  7,  8,  9,
            8,  9,  10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1
    };

    // S-boxes (i.e. Substitution boxes)
    public static final byte[][] S = { {
            14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
            0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
            4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
            15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
    }, {
            15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
            3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
            0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
            13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
    }, {
            10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
            13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
            13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
            1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
    }, {
            7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
            13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
            10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
            3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
    }, {
            2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
            14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
            4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
            11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
    }, {
            12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
            10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
            9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
            4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
    }, {
            4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
            13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
            1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
            6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
    }, {
            13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
            1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
            7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
            2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
    } };

    // Permutation table
    public static final byte[] P = {
            16, 7,  20, 21,
            29, 12, 28, 17,
            1,  15, 23, 26,
            5,  18, 31, 10,
            2,  8,  24, 14,
            32, 27, 3,  9,
            19, 13, 30, 6,
            22, 11, 4,  25
    };

    // Final permutation (aka Inverse permutation) table
    public static final byte[] FP = {
            40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25
    };

    public static int[] C = new int[28];
    public static int[] D = new int[28];
    public static int[][] keys = new int[16][48];
    public static int[] toBinary(String value){
        int[] bits = new int[64];
        // 16 = value length
        for(int i=0 ; i < 16 ; i++) {
            // convert each char to int then converted to binary
            String s = Integer.toBinaryString(Integer.parseInt(String.valueOf(value.charAt(i)), 16));
            // to make sure every value are equal like 5, A
            // 5 => 101, A => 1010
            // make it equal by adding '0' to the left
            // for the values less than 4. why 4 ?
            // every 1-digit in hex = 4-digits binary
            while(s.length() < 4) {
                s = "0" + s;
            }
            // but the binary digits in the array
            for(int j=0 ; j < 4 ; j++) {
                // (4 * i) ??
                // as I explain up all the values must be equal, and it must be 4
                bits[(4*i)+j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        return bits;
    }
    public static int[] permutation(int[] bits, byte[] table){
        /*
        * 0 -> 52-bit
        * key  [64-bit => 56-bit]
        * text [64-bit => 64-bit] then [64-bit => 64-bit]
        */
        int[] newBits = new int[bits.length];
        for(int i=0 ; i < newBits.length ; i++) {
            newBits[i] = bits[table[i]-1];
        }
        return newBits;
    }
    public static String toHex(int[] finalOutput) {
        int i;
        String hex = "";
        for(i=0 ; i < 16 ; i++) {
            String bin = "";
            for(int j=0 ; j < 4 ; j++) {
                bin += finalOutput[(4*i)+j];
            }
            int decimal = Integer.parseInt(bin, 2);
            hex += Integer.toHexString(decimal);
        }
        return hex;
    }

    public static void keyPermutation(int[] keyBits){
        int i;
        for (i = 0; i < 28; i++) {
            Resources.C[i] = keyBits[Resources.PC1[i] - 1];
        }
        for (; i < 56; i++) {
            Resources.D[i - 28] = keyBits[Resources.PC1[i] - 1];
        }
    }

    public static int[] calculate(int[] R, int[] key) {

        int[] R0 = new int[48];
        for (int i = 0; i < 48; i++) {
            R0[i] = R[Resources.E[i] - 1];
        }
        int[] temp = xor(R0, key);
        return sBlock(temp);
    }
    public static int[] xor(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] ^ b[i];
        }
        return result;
    }
    private static int[] sBlock(int[] bits) {
        // S-boxes are applied in this method.
        int[] R0 = new int[32];
        // We know that input will be of 32 bits, hence we will loop 32/4 = 8
        // times (divided by 4 as we will take 4 bits of input at each
        // iteration).
        for (int i = 0; i < 8; i++) {
            // S-box requires a row and a column, which is found from the
            // input bits. The first and 6th bit of the current iteration
            // (i.e. bits 0 and 5) gives the row bits.
            int[] row = new int[2];
            row[0] = bits[6 * i];
            row[1] = bits[(6 * i) + 5];
            String sRow = row[0] + "" + row[1];
            // Similarly column bits are found, which are the 4 bits between
            // the two row bits (i.e. bits 1,2,3,4)
            int[] column = new int[4];
            column[0] = bits[(6 * i) + 1];
            column[1] = bits[(6 * i) + 2];
            column[2] = bits[(6 * i) + 3];
            column[3] = bits[(6 * i) + 4];
            String sColumn = column[0] + "" + column[1] + "" + column[2] + "" + column[3];
            // Converting binary into decimal value, to be given into the
            // array as input
            int iRow = Integer.parseInt(sRow, 2);
            int iColumn = Integer.parseInt(sColumn, 2);
            int x = Resources.S[i][(iRow * 16) + iColumn];
            // We get decimal value of the S-box here, but we need to convert
            // it into binary:
            String s = Integer.toBinaryString(x);

            while (s.length() < 4) {
                s = "0" + s;
            }
            // The binary bits are appended to the R0
            for (int j = 0; j < 4; j++) {
                R0[(i * 4) + j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        return Resources.permutation(R0,Resources.P);
    }
    public static int[] leftShift(int[] bits, int n) {
        int[] result = new int[bits.length];
        System.arraycopy(bits, 0, result, 0, bits.length);
        for (int i = 0; i < n; i++) {
            int temp = result[0];
            System.arraycopy(result, 1, result, 0, bits.length - 1);
            result[bits.length - 1] = temp;
        }
        return result;
    }
}