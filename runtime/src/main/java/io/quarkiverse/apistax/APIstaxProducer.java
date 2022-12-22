package io.quarkiverse.apistax;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.apistax.client.APIstaxClient;

@ApplicationScoped
public class APIstaxProducer {

    @Inject
    APIstaxConfiguration configuration;

    @Produces
    @ApplicationScoped
    public APIstaxClient produceClient() {
        return new APIstaxClient.Builder()
                .apiKey(configuration.apiKey)
                .build();
    }
}
