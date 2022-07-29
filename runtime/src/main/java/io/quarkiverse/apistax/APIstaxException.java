package io.quarkiverse.apistax;

import java.util.List;
import java.util.Objects;

public class APIstaxException extends RuntimeException {

    private List<String> messages;

    public APIstaxException(Throwable cause) {
        super(cause);
    }

    public APIstaxException(List<String> messages) {
        super(String.join(",", Objects.requireNonNullElseGet(messages, List::of)));
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
