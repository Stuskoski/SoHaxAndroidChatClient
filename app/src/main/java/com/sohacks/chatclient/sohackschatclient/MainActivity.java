package com.sohacks.chatclient.sohackschatclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sohacks.chatclient.sohackschatclient.Domain.UserInformation;
import com.sohacks.chatclient.sohackschatclient.Util.StringValidator;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_edit_text) EditText mainEditText;
    @BindView(R.id.main_continue_btn) Button continueButton;
    @BindString(R.string.invalid_username_title) String invalidUsernameTitle;
    @BindString(R.string.invalid_username_string) String invalidUsernameString;
    @BindString(R.string.ok_string) String okString;

    /**
     * On create called when the activity is
     * instantiated for the first time
     *
     * Very similar to the main method
     * @param savedInstanceState If the view is refreshed, information is
     *                           stored in this bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createButtonListeners();
    }

    /**
     * Creates the button listeners to proceed to next page
     */
    public void createButtonListeners(){
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserNameAndProceed();
            }
        });
    }


    /**
     * Saves the username to memory if it was validated correctly
     */
    public void saveUserNameAndProceed(){
        if(StringValidator.validateStringForEmpty(mainEditText.getText().toString())){
            UserInformation.username = mainEditText.getText().toString();
            proceedToMessageScreen();
        }else{
            displayInvalidUsernameAlert();
        }
    }

    /**
     * Displays a static alert dialog to info user of invalid username
     */
    private void displayInvalidUsernameAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(invalidUsernameTitle);
        alertDialog.setMessage(invalidUsernameString);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, okString,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /**
     * Proceeds to the next screen
     */
    private void proceedToMessageScreen(){
        Intent intent = new Intent(this, MessagingActivity.class);
        startActivity(intent);
    }
}
