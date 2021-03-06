package com.sohacks.chatclient.sohackschatclient;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sohacks.chatclient.sohackschatclient.Adapters.MessageListViewAdapter;
import com.sohacks.chatclient.sohackschatclient.Domain.UserMessage;
import com.sohacks.chatclient.sohackschatclient.Domain.UserMessageArray;
import com.sohacks.chatclient.sohackschatclient.Util.KeyboardUtility;
import com.sohacks.chatclient.sohackschatclient.Util.RandomNumberGenerator;
import com.sohacks.chatclient.sohackschatclient.Util.StringValidator;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagingActivity extends AppCompatActivity {

    //Variables
    FirebaseDatabase database;
    DatabaseReference messageReference;
    UserMessageArray messageArray;

    @BindView(R.id.message_text) EditText messageText;
    @BindView(R.id.send_button) Button sendButton;
    @BindView(R.id.message_list_view) ListView messageListView;
    @BindString(R.string.invalid_message_title) String invalidMsgTitle;
    @BindString(R.string.invalid_message_string) String invalidMsgString;
    @BindString(R.string.ok_string) String okString;
    @BindString(R.string.chat_lobby_title) String chatLobbyTitle;
    @BindString(R.string.messages_ref) String firebaseMessagesString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        createFirebaseConnections();
        createButtonListeners();
        attachCloseKeyBoardListeners(findViewById(R.id.message_parent));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setTitle(chatLobbyTitle);
    }

    /**
     * Creates the firebase references as well as creates the listeners and
     * attaches them.
     */
    private void createFirebaseConnections(){
        database = FirebaseDatabase.getInstance();
        messageReference = database.getReference(firebaseMessagesString);

        ValueEventListener messageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageArray = new UserMessageArray(dataSnapshot);
                setupListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                finish();
            }
        };

        messageReference.addValueEventListener(messageListener);
    }

    /**
     * Creates button listeners
     */
    private void createButtonListeners(){
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToFirebase();
            }
        });
    }

    /**
     * Recursively traverses all views and attaches listeners
     * to them to close the keyboard.
     *
     * Only attached if they are not edit text views.
     *
     * @param view View to attach to
     */
    public void attachCloseKeyBoardListeners(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    KeyboardUtility.hideSoftKeyboard(MessagingActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                attachCloseKeyBoardListeners(innerView);
            }
        }
    }

    /**
     * Sets up the list view by giving it the context, layout, and messages array list
     *
     * Sets the selection to the last index after populating list.
     */
    private void setupListView(){


        messageListView.setAdapter(new MessageListViewAdapter(MessagingActivity.this, R.layout.message_adapter_list, messageArray.messages));
        messageListView.setSelection(messageArray.messages.size());
    }

    /**
     * Sends the message to firebase by creating the object
     * with identifier and sending it on it's way.
     */
    private void sendMessageToFirebase(){

        if(StringValidator.validateStringForEmpty(messageText.getText().toString())){

            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();

            String newChild = String.format("%s-%d", ts, new RandomNumberGenerator().generateMessageID());

            messageReference
                    .child(newChild)
                    .setValue(new UserMessage(messageText.getText().toString()));

            messageText.setText("");

        }else{
            displayInvalidMessageAlert();
        }
    }

    /**
     * Displays a simple ok alert for an invalid message
     */
    private void displayInvalidMessageAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(MessagingActivity.this).create();
        alertDialog.setTitle(invalidMsgTitle);
        alertDialog.setMessage(invalidMsgString);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, okString,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
