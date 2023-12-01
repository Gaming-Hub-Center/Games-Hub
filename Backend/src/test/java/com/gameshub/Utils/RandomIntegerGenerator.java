package com.gameshub.Utils;

import java.util.*;

public class RandomIntegerGenerator {

    public static int generateRandomIntegerInRange(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
