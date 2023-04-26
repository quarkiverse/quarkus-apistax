package io.quarkiverse.apistax.test;

import jakarta.inject.Inject;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.apistax.client.APIstaxClient;
import io.quarkus.test.QuarkusUnitTest;

public class APIstaxTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class))
            .withConfigurationResource("application.properties");

    @Inject
    APIstaxClient client;

    @Test
    public void test() {
        Assertions.assertNotNull(client);
    }
}
