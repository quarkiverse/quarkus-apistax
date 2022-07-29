package io.quarkiverse.apistax.deployment;

import java.util.stream.Stream;

import org.jboss.jandex.DotName;

import io.quarkiverse.apistax.APIstaxClient;
import io.quarkiverse.apistax.APIstaxException;
import io.quarkiverse.apistax.APIstaxProducer;
import io.quarkiverse.apistax.models.*;
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
    void registerForReflection(CombinedIndexBuildItem index, BuildProducer<ReflectiveClassBuildItem> reflectionClasses)
            throws ClassNotFoundException {

        var modelClasses = Stream.of(
                EpcQrCodePayload.class, GeocodeResult.class, GeocodeResultAddress.class, GeocodeResultPosition.class,
                GeocodeReversePayload.class, GeocodeSearchPayload.class, HtmlPayload.class, VatVerificationPayload.class,
                VatVerificationResult.class, ErrorMessage.class, APIstaxException.class, APIstaxClient.class,
                Class.forName(APIstaxClient.class.getName() + "Impl"))
                .map(aClass -> DotName.createSimple(aClass.getName()))
                .map(DotName::toString)
                .toArray(String[]::new);

        reflectionClasses.produce(new ReflectiveClassBuildItem(true, true, modelClasses));
    }

    @BuildStep
    AdditionalBeanBuildItem produce() {
        return AdditionalBeanBuildItem.unremovableOf(APIstaxProducer.class);
    }
}
