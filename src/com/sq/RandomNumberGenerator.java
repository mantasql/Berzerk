package com.sq;

public class RandomNumberGenerator {
    public static short seed;

    public static short generateRandomNumber(int newSeed) {
        //Linear congruential generator
        seed = (short)((newSeed * 7) + 12627);
        return (short)(seed >> 8);
    }

    public static short generateRandomNumber() {
        return generateRandomNumber(seed);
    }
}
