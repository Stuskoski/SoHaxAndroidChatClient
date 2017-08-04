package com.sohacks.chatclient.sohackschatclient.Domain;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by r730819 on 8/4/17.
 */

@IgnoreExtraProperties
public class UserMessage {

    public String message;
    public long timeStamp;
    public String user;

    public UserMessage() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserMessage(String message) {
        this.message = message;
        this.timeStamp = System.currentTimeMillis();
        this.user = UserInformation.username;
    }

}
