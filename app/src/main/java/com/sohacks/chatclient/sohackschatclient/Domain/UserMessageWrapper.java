package com.sohacks.chatclient.sohackschatclient.Domain;

import com.sohacks.chatclient.sohackschatclient.Util.RandomNumberGenerator;

/**
 * Created by r730819 on 8/4/17.
 */

public class UserMessageWrapper {

    public int messageId;
    public UserMessage message;

    public UserMessageWrapper() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserMessageWrapper(String message) {

        this.messageId = new RandomNumberGenerator().generateMessageID();
        this.message = new UserMessage(message);
    }


}
