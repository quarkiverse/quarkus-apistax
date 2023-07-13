package io.quarkiverse.apistax.deployment;

import java.util.stream.Stream;

import org.jboss.jandex.DotName;

import io.apistax.client.APIstaxClient;
import io.apistax.client.APIstaxClientImpl;
import io.apistax.client.APIstaxException;
import io.apistax.models.*;
import io.quarkiverse.apistax.APIstaxProducer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.ExtensionSslNativeSupportBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

class APIstaxProcessor {

    private static final String FEATURE = "apistax";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    public void build(BuildProducer<ExtensionSslNativeSupportBuildItem> sslSupport) {
        sslSupport.produce(new ExtensionSslNativeSupportBuildItem(FEATURE));
    }

    @BuildStep
    void registerForReflection(CombinedIndexBuildItem index, BuildProducer<ReflectiveClassBuildItem> reflectionClasses) {
        var modelClasses = Stream.of(
                EpcQrCodePayload.class, GeocodeResult.class, GeocodeResultAddress.class, GeocodeResultPosition.class,
                GeocodeReversePayload.class, GeocodeSearchPayload.class, HtmlPayload.class, VatVerificationPayload.class,
                VatVerificationResult.class, ErrorMessage.class, APIstaxException.class, APIstaxClient.class,
                APIstaxClientImpl.class)
                .map(aClass -> DotName.createSimple(aClass.getName()))
                .map(DotName::toString)
                .toArray(String[]::new);

        var buildItem = ReflectiveClassBuildItem.builder(modelClasses)
                .methods(true)
                .fields(true)
                .build();

        reflectionClasses.produce(buildItem);
    }

    @BuildStep
    AdditionalBeanBuildItem produce() {
        return AdditionalBeanBuildItem.unremovableOf(APIstaxProducer.class);
    }
}
