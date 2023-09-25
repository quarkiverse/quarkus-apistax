package io.quarkiverse.apistax.deployment;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
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
        var classes = index.getIndex()
                .getSubpackages("io.apistax")
                .stream()
                .flatMap(subPkg -> index.getIndex()
                        .getClassesInPackage(subPkg)
                        .stream()
                        .map(ClassInfo::name)
                )
                .map(DotName::toString)
                .toArray(String[]::new);

        var buildItem = ReflectiveClassBuildItem.builder(classes)
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
