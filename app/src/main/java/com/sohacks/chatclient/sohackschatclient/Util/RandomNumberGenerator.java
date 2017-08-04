package com.sohacks.chatclient.sohackschatclient.Util;

import java.util.Random;

/**
 * Created by r730819 on 8/4/17.
 */

public class RandomNumberGenerator {

    //Returns a random int between 1 and 1000000 for message ID
    public int generateMessageID(){
        Random random = new Random();
        return random.nextInt(1000000);
    }

}
