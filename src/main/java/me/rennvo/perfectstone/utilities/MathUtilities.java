package me.rennvo.perfectstone.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtilities {

    public static double random() {
        return ThreadLocalRandom.current().nextDouble(0, 100);
    }

}
