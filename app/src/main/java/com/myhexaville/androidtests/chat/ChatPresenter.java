package com.myhexaville.androidtests.chat;

import android.util.Log;

import java.util.List;

public class ChatPresenter implements ChatContract.Presenter {
    private final static String TAG = "LEE: <" + ChatPresenter.class.getSimpleName() + ">";

    private ChatContract.View view;
    private List<String> listOfMessages;

    public ChatPresenter(ChatContract.View view, List<String> listOfMessages) {
        Log.v(TAG, "ChatPresenter");
        this.view = view;
        this.listOfMessages = listOfMessages;
    }

    public void sendMessage(String message) {
        Log.v(TAG, "sendMessage");
        if (message != null && !message.isEmpty()) {
            listOfMessages.add(message);
            view.notifyItemAdded(listOfMessages.size());
            view.clearMessageInput();
        }
    }

    @Override
    public void messageInputTextChanged(String messageInput) {
        Log.v(TAG, "messageInputTextChanged");
        if (messageInput == null || messageInput.isEmpty()) {
            view.disableSendButton();
        } else {
            view.enableSendButton();
        }
    }
}
