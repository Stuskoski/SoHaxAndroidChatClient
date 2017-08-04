package com.sohacks.chatclient.sohackschatclient.Util;

import com.sohacks.chatclient.sohackschatclient.Domain.UserMessage;

import java.util.Comparator;

/**
 * Created by r730819 on 8/4/17.
 */

public class MessagesComparator implements Comparator<UserMessage>{


    //Comparator to sort messages by date
    @Override
    public int compare(UserMessage first, UserMessage second) {
        return Long.compare(first.timeStamp, second.timeStamp);
    }
}
