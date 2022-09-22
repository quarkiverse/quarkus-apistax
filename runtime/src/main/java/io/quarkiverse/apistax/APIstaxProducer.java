package io.quarkiverse.apistax;

import io.apistax.client.APIstaxClient;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class APIstaxProducer {

    @Inject
    APIstaxConfiguration configuration;

    @Produces
    @Dependent
    public APIstaxClient produceClient() {
        return new APIstaxClient.Builder()
                .apiKey(configuration.apiKey)
                .build();
    }
}
