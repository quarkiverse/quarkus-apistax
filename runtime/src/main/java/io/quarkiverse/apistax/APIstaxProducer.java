package io.quarkiverse.apistax;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import io.apistax.client.APIstaxClient;
import io.apistax.client.APIstaxClientMock;

@ApplicationScoped
public class APIstaxProducer {

    @Inject
    APIstaxConfiguration configuration;

    @Produces
    @ApplicationScoped
    public APIstaxClient produceClient() {
        if (configuration.mock()) {
            return new APIstaxClientMock();
        } else {
            return new APIstaxClient.Builder()
                    .apiKey(configuration.apiKey())
                    .build();
        }
    }
}
