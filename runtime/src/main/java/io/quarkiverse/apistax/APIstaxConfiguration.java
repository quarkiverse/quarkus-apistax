package io.quarkiverse.apistax;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "apistax", phase = ConfigPhase.RUN_TIME)
public class APIstaxConfiguration {

    /**
     * The APIstax API key. Get one via https://apistax.io
     *
     * @asciidoclet
     */
    @ConfigItem
    public String apiKey;

    /**
     * Enables the mock mode.
     * When enabled, requests are not send. Instead, fake data is returned.
     *
     * @asciidoclet
     */
    @ConfigItem(defaultValue = "false")
    public Boolean mock;
}
