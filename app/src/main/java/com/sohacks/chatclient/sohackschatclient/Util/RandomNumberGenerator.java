package com.sohacks.chatclient.sohackschatclient.Util;

import java.util.Random;

/**
 * Created by r730819 on 8/4/17.
 */

public class RandomNumberGenerator {

    public int generateMessageID(){
        Random random = new Random();
        return random.nextInt(1000000);
    }

}
