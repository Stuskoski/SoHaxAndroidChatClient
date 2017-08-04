package com.sohacks.chatclient.sohackschatclient.Util;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by r730819 on 8/4/17.
 */

public class KeyboardUtility {

    // Attaches an event to hide the soft keyboard
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
