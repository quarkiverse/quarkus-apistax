package io.quarkiverse.apistax.http;

import java.net.http.HttpRequest;
import java.util.function.Consumer;

public class ClientInterceptor implements Consumer<HttpRequest.Builder> {

    private final String apiKey;

    public ClientInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void accept(HttpRequest.Builder builder) {
        builder.header("Authorization", "Bearer " + apiKey);
        builder.header("User-Agent", "quarkus-apistax");
    }
}
