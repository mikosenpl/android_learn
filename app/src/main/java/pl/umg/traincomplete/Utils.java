package pl.umg.traincomplete;

import java.util.Random;

public class Utils
{
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
