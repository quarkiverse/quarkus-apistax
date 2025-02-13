package io.quarkiverse.apistax;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
@ConfigMapping(prefix = "quarkus.apistax")
public interface APIstaxConfiguration {

    /**
     * The APIstax API key. Get one via https://apistax.io
     *
     * @asciidoclet
     */
    String apiKey();

    /**
     * Enables the mock mode.
     * When enabled, requests are not send. Instead, fake data is returned.
     *
     * @asciidoclet
     */
    @WithDefault("false")
    Boolean mock();
}
