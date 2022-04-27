package com.inditex.catalogservice.util;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

import java.security.SecureRandom;

public class RandomTestUtil {

    public static final int MIN_COLLECTION_SIZE = 2;
    public static final int MAX_COLLECTION_SIZE = 3;

    public static final int MIN_STRING_SIZE = 5;
    public static final int MAX_STRING_SIZE = 10;

    public static EasyRandom buildFastEasyRandom() {
        return RandomTestUtil
                .buildEasyRandom(MIN_COLLECTION_SIZE, MAX_COLLECTION_SIZE, MIN_STRING_SIZE, MAX_STRING_SIZE);
    }

    public static EasyRandom buildEasyRandom(int collectionMin, int collectionMax, int stringMin, int stringMax,
                                             String... excludeFields) {
        EasyRandomParameters randomParameters = new EasyRandomParameters()
                .collectionSizeRange(collectionMin, collectionMax)
                .stringLengthRange(stringMin, stringMax)
                .randomizationDepth(collectionMax)
                .seed(new SecureRandom().nextInt());

        for (String field : excludeFields) {
            randomParameters.excludeField(FieldPredicates.named(field));
        }

        return new EasyRandom(randomParameters);
    }
}
