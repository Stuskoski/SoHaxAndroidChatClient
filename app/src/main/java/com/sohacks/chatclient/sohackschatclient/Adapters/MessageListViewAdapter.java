package com.sohacks.chatclient.sohackschatclient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sohacks.chatclient.sohackschatclient.Domain.UserMessage;
import com.sohacks.chatclient.sohackschatclient.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by r730819 on 8/4/17.
 *
 * Custom adapter for user messages list
 */

public class MessageListViewAdapter extends ArrayAdapter<UserMessage> {

    @BindView(R.id.message_adapter_username) TextView usernameTextView;
    @BindView(R.id.message_adapter_timestamp) TextView timestampTextView;
    @BindView(R.id.message_adapter_message) TextView messageTextView;

    Context mContext;
    ArrayList<UserMessage> mMessages;

    public MessageListViewAdapter(Context context, int resource, ArrayList<UserMessage> messages){
        super(context, resource, messages);
        this.mContext = context;
        this.mMessages = messages;
    }

    /**
     *
     * Constructs a view if it is not present in memory
     *
     * The view is constructed with the UserMessage array
     * acting as the data source.
     *
     * All subviews are then filled out with data and the
     * super view is returned.
     *
     * @param position Position of the row
     * @param view Super view to be return after configured
     * @param viewGroup The view group or section
     * @return Configured super view
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.message_adapter_list, null);
        }

        UserMessage message = getItem(position);

        if(message != null){

            ButterKnife.bind(this, view);

            Date date = new Date(message.timeStamp);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String reportDate = df.format(date);

            timestampTextView.setText(reportDate);
            usernameTextView.setText("User: " + message.user);
            messageTextView.setText("Message: " + message.message);

        }

        return view;
    }
}
