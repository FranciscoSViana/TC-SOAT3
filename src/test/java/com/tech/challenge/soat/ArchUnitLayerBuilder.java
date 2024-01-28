package com.tech.challenge.soat;

import java.util.HashSet;

public class ArchUnitLayerBuilder {

    private static final String DOMAIN_LAYER_NAME = "domain";

    private static final String APPLICATION_LAYER_NAME = "application";

    private static final String ADAPTERS_LAYER_NAME = "adapters";

    private static final String INFRA_LAYER_NAME = "infra";

    private ArchUnitLayerBuilder() {
    }

    public static ArchUnitLayer createDomainLayer() {
        var domainLayers = new HashSet<String>();

        domainLayers.add("enums");
        domainLayers.add("constants");
        domainLayers.add("exceptions");
        domainLayers.add("models");
        domainLayers.add("factory");
        domainLayers.add("ports");
        domainLayers.add("providers");
        domainLayers.add("repositories");
        domainLayers.add("services");
        domainLayers.add("utils");
        domainLayers.add("validators");

        return ArchUnitLayer.builder()
                .name(DOMAIN_LAYER_NAME)
                .layers(domainLayers)
                .build();
    }

    public static ArchUnitLayer createApplicationLayer() {
        var applicationLayers = new HashSet<String>();

        applicationLayers.add("providers");
        applicationLayers.add("services");
        applicationLayers.add("utils");

        return ArchUnitLayer.builder()
                .name(APPLICATION_LAYER_NAME)
                .layers(applicationLayers)
                .build();
    }

    public static ArchUnitLayer createAdaptersLayer() {
        var adapterLayers = new HashSet<String>();

        adapterLayers.add("controllers");
        adapterLayers.add("exceptions");
        adapterLayers.add("gateways");
        adapterLayers.add("factory");
        adapterLayers.add("mappers");
        adapterLayers.add("models");

        return ArchUnitLayer.builder()
                .name(ADAPTERS_LAYER_NAME)
                .layers(adapterLayers)
                .build();
    }

    public static ArchUnitLayer createInfraLayer() {
        var infraLayers = new HashSet<String>();

        infraLayers.add("client");
        infraLayers.add("config");

        return ArchUnitLayer.builder()
                .name(INFRA_LAYER_NAME)
                .layers(infraLayers)
                .build();
    }
}
