package com.sohacks.chatclient.sohackschatclient.Util;

import com.sohacks.chatclient.sohackschatclient.Domain.UserMessage;

import java.util.Comparator;

/**
 * Created by r730819 on 8/4/17.
 *
 * Custom comparator class
 */

public class MessagesComparator implements Comparator<UserMessage>{


    /**
     * Comparator to sort messages by date
     *
     * @param first First message in array
     * @param second Second message in array
     * @return comparator value
     */
    @Override
    public int compare(UserMessage first, UserMessage second) {
        return Long.compare(first.timeStamp, second.timeStamp);
    }
}
