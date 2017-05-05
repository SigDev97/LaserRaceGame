package com.sigdev.sasgame.utils;

/**
 * Created by benedetto.sigillo on 19/04/2017.
 */

import java.util.Random;

public class RandomUtils {

    public static EnemyType getRandomEnemyType() {
        RandomEnum<EnemyType> randomEnum = new RandomEnum<EnemyType>(EnemyType.class);
        return randomEnum.random();
    }

    /**
     * @see [Stack Overflow](http://stackoverflow.com/a/1973018)
     * @param <E>
     */

    private static class RandomEnum<E extends Enum> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }

    public static float randFloat() {

        float minX = 1.25f;
        float maxX = 13.75f;

        Random rand = new Random();

        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

}
