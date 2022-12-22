class DES {
    public static int[] apply(String plainText, String key, boolean isDecrypt) {
        // 1 - convert to binary
        // 64-bit
        int[] textBits = Resources.toBinary(plainText);
        int[] keyBits = Resources.toBinary(key);
        // 2 - permutation
        // Initial permutation step takes input bits and permutes into the
        // 64-bit
        int[] newTextBits = Resources.permutation(textBits,Resources.IP);
        // apply the permutation for the key & divide the Key
        Resources.keyPermutation(keyBits);
        // 3 - divide the key to L0, R0
        // 32-bit
        // 16 rounds will start here
        int[] L0 = new int[32];
        int[] R0 = new int[32];
        // fill the L0, R0 arrays
        System.arraycopy(newTextBits, 0, L0, 0, 32);
        System.arraycopy(newTextBits, 32, R0, 0, 32);
        // 4 - generate Ls, Rs
        for (int n = 0; n < 16; n++) {
            int[] newR ;
            if (isDecrypt) {
                newR = Resources.calculate(R0, Resources.keys[15 - n]);
            } else {
                newR = Resources.calculate(R0, generateKeys(n));
            }
            // xor-ing the L0 and new R0 gives the new L0 value. new L0 is stored
            // in R0 and new R0 is stored in L0, thus exchanging R0 and L0 for the
            // next round.
            int[] newL = Resources.xor(L0, newR);
            L0 = R0;
            R0 = newL;
        }
        // Concatenation
        int[] results = new int[64];
        System.arraycopy(R0, 0, results, 0, 32);
        System.arraycopy(L0, 0, results, 32, 32);
        // permutation the last output
        int[] finalOutput = Resources.permutation(results,Resources.FP);
        // Convert to hexadecimal String
        String hex = Resources.toHex(finalOutput);

        if (isDecrypt) {
            System.out.print("Decrypted text: ");
        } else {
            System.out.print("Encrypted text: ");
        }
        System.out.println(hex.toUpperCase());
        return finalOutput;
    }
    private static int[] generateKeys(int round) {
        // C1 and D1 are the new values of C and D which will be generated in
        // this round.
        int[] C1, D1;
        // The rotation array is used to set how many rotations are to be done
        int rotationTimes = Resources.rotations[round];

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
        Resources.keys[round] = Kn;
        Resources.C = C1;
        Resources.D = D1;
        return Kn;
    }
}