package com.sohacks.chatclient.sohackschatclient.Util;

/**
 * Created by r730819 on 8/4/17.
 *
 * String validator class
 */

public class StringValidator {

    /**
     * Validates a string for a null check as well as an empty string
     * @param stringToValidate String validated
     * @return Bool for validated string
     */
    public static boolean validateStringForEmpty(String stringToValidate){

        if(stringToValidate == null){
            return false;
        }

        if(stringToValidate.length() <= 0){
            return false;
        }

        return true;

    }
}
