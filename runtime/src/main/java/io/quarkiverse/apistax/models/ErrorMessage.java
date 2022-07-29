package io.quarkiverse.apistax.models;

import java.util.List;

public class ErrorMessage {

    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
