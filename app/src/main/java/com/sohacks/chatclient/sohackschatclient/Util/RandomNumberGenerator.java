package com.sohacks.chatclient.sohackschatclient.Util;

import java.util.Random;

/**
 * Created by r730819 on 8/4/17.
 *
 * RNG ftl
 */

public class RandomNumberGenerator {

    private int maxInt = 1000000;

    /**
     * Returns a random int between 0 and 999,999 for message ID
     * @return random int
     */
    public int generateMessageID(){
        Random random = new Random();
        return random.nextInt(maxInt);
    }
}
