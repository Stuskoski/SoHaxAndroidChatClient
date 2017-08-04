package com.sohacks.chatclient.sohackschatclient;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sohacks.chatclient.sohackschatclient.Domain.UserMessage;
import com.sohacks.chatclient.sohackschatclient.Domain.UserMessageWrapper;
import com.sohacks.chatclient.sohackschatclient.Util.KeyboardUtility;
import com.sohacks.chatclient.sohackschatclient.Util.RandomNumberGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagingActivity extends AppCompatActivity {

    //Variables
    FirebaseDatabase database;
    DatabaseReference messageReference;

    @BindView(R.id.message_text) EditText messageText;
    @BindView(R.id.send_button) Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        createFirebaseConnections();
        createButtonListeners();
        setupUI(findViewById(R.id.message_parent));
    }

    private void createFirebaseConnections(){
        database = FirebaseDatabase.getInstance();
        messageReference = database.getReference("messages");
    }

    private void createButtonListeners(){
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToFirebase();
            }
        });
    }

    public void setupUI(View view) {

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
                setupUI(innerView);
            }
        }
    }



    private void sendMessageToFirebase(){

        if(validateMessage()){

            String newChild = String.format("%d", new RandomNumberGenerator().generateMessageID());

            messageReference
                    .child(newChild)
                    .setValue(new UserMessage(messageText.getText().toString()));

            messageText.setText("");

        }else{
            displayInvalidUsernameAlert();
        }
    }

    private boolean validateMessage(){
        String message = messageText.getText().toString();

        if(message.length() == 0){
            return false;
        }

        return true;
    }

    private void displayInvalidUsernameAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(MessagingActivity.this).create();
        alertDialog.setTitle("Invalid Message");
        alertDialog.setMessage("A message must not be empty.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
