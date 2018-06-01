package com.myhexaville.androidtests.chat;

import android.widget.EditText;
import android.widget.ImageView;

import com.myhexaville.androidtests.R;
import com.myhexaville.androidtests.util.LogHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ChatActivityTest {
    private final static String TAG = "LEE: <" + ChatActivityTest.class.getSimpleName() + ">";

    public static final String MESSAGE = "Normal message";
    private ChatActivity activity;
    private EditText messageInput;
    private ImageView sendButton;
    @Mock
    private MessagesAdapter messagesAdapter;
    @Mock
    private List<String> listOfMessages;
    @Mock
    private ChatPresenter presenter;

    @Before
    public void setUp() {
        LogHelper.v(TAG, "setUp");
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.setupActivity(ChatActivity.class);
        messageInput = activity.findViewById(R.id.message_input);
        sendButton = activity.findViewById(R.id.send_button);
        activity.setAdapter(messagesAdapter);
        activity.setListOfMessages(listOfMessages);
        activity.setPresenter(presenter);
        when(listOfMessages.size()).thenReturn(0);
    }

    // Test View

    @Test
    public void clearMessageInput_WasEmpty_ClearedMessageInput() {
        LogHelper.v(TAG, "TEST clearMessageInput_WasEmpty_ClearedMessageInput");
        activity.clearMessageInput();
        assertThat(messageInput.getText().toString(), is(""));
    }

    @Test
    public void clearMessageInput_WastntEmpty_ClearedMessageInput() {
        LogHelper.v(TAG, "TEST clearMessageInput_WastntEmpty_ClearedMessageInput");
        messageInput.setText("Some text");
        activity.clearMessageInput();
        assertThat(messageInput.getText().toString(), is(""));
    }

    @Test
    public void enableSendButton_WasDisabled_ButtonEnabled() {
        LogHelper.v(TAG, "TEST enableSendButton_WasDisabled_ButtonEnabled");
        presetSendButtonAsDisabled();
        activity.enableSendButton();
        assertThatSendButtonIsEnabled();
    }

    @Test
    public void enableSendButton_WasEnabled_ButtonEnabled() {
        LogHelper.v(TAG, "TEST enableSendButton_WasEnabled_ButtonEnabled");
        activity.enableSendButton();
        assertThatSendButtonIsEnabled();
    }

    @Test
    public void disableSendButton_WasEnabled_ButtonDisabled() {
        LogHelper.v(TAG, "TEST disableSendButton_WasEnabled_ButtonDisabled");
        activity.disableSendButton();
        assertThatSendButtonIsDisabled();
    }

    @Test
    public void disableSendButton_WasDisabled_ButtonDisabled() {
        LogHelper.v(TAG, "TEST disableSendButton_WasDisabled_ButtonDisabled");
        presetSendButtonAsDisabled();
        activity.disableSendButton();
        assertThatSendButtonIsDisabled();
    }

    @Test
    public void notifyItemAdded_CalledAdapterMethod() {
        LogHelper.v(TAG, "TEST notifyItemAdded_CalledAdapterMethod");
        activity.notifyItemAdded(4);
        verify(messagesAdapter).notifyItemInserted(4);
    }

    private void presetSendButtonAsDisabled() {
        LogHelper.v(TAG, "presetSendButtonAsDisabled");
        sendButton.setEnabled(false);
        sendButton.setAlpha(0.5f);
    }

    private void assertThatSendButtonIsEnabled() {
        LogHelper.v(TAG, "assertThatSendButtonIsEnabled");
        assertThat(sendButton.getAlpha(), is(1.0f));
        assertThat(sendButton.isEnabled(), is(true));
    }

    private void assertThatSendButtonIsDisabled() {
        LogHelper.v(TAG, "assertThatSendButtonIsDisabled");
        assertThat(sendButton.getAlpha(), is(0.5f));
        assertThat(sendButton.isEnabled(), is(false));
    }


    // Test Listeners

    @Test
    public void typeSomeText_PassedToPresenter() {
        LogHelper.v(TAG, "typeSomeText_PassedToPresenter");
        messageInput.setText(MESSAGE);
        verify(presenter).messageInputTextChanged(MESSAGE);
    }

    @Test
    public void pressedSendButton_EmptyInput_PassedEmptyString() {
        LogHelper.v(TAG, "pressedSendButton_EmptyInput_PassedEmptyString");
        sendButton.performClick();
        verify(presenter).sendMessage("");
    }

    @Test
    public void pressedSendButton_NormalStringInput_PassedCorrectString(){
        LogHelper.v(TAG, "pressedSendButton_NormalStringInput_PassedCorrectString");
        messageInput.setText(MESSAGE);
        sendButton.performClick();
        verify(presenter).sendMessage(MESSAGE);
    }

}
