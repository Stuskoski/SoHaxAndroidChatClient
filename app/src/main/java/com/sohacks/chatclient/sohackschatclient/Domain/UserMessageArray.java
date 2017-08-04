package com.sohacks.chatclient.sohackschatclient.Domain;

import com.google.firebase.database.DataSnapshot;
import com.sohacks.chatclient.sohackschatclient.Util.MessagesComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by r730819 on 8/4/17.
 */

public class UserMessageArray {

    public ArrayList<UserMessage> messages;

    public UserMessageArray() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserMessageArray(DataSnapshot snapshot){

        messages = new ArrayList<>();

        for(DataSnapshot child : snapshot.getChildren()){
            messages.add(child.getValue(UserMessage.class));
        }

        Collections.sort(messages, new MessagesComparator());

    }

    public UserMessageArray(ArrayList<UserMessage> messages) {
        this.messages = messages;
    }

}


