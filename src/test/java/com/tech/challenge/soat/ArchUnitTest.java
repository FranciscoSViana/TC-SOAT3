package com.tech.challenge.soat;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchUnitTest {
    private static final String PROJECT_PACKAGE = "br.com.tech.challenge.soat";

    @Test
    void testNoLayerViolations() {

        JavaClasses jc = new ClassFileImporter().importPackages(PROJECT_PACKAGE);

        var domainLayer = ArchUnitLayerBuilder.createDomainLayer();
        var applicationLayer = ArchUnitLayerBuilder.createApplicationLayer();
        var adaptersLayer = ArchUnitLayerBuilder.createAdaptersLayer();
        var infraLayer = ArchUnitLayerBuilder.createInfraLayer();

        Architectures.LayeredArchitecture arch = layeredArchitecture().consideringAllDependencies();

        arch.layer(domainLayer.getName()).definedBy(domainLayer.getLayersAsVarargs());
        arch.layer(applicationLayer.getName()).definedBy(applicationLayer.getLayersAsVarargs());
        arch.layer(adaptersLayer.getName()).definedBy(adaptersLayer.getLayersAsVarargs());
        arch.layer(infraLayer.getName()).definedBy(infraLayer.getLayersAsVarargs());

        arch.whereLayer(domainLayer.getName()).mayOnlyBeAccessedByLayers(
                applicationLayer.getName(),
                adaptersLayer.getName(),
                infraLayer.getName());

        arch.whereLayer(applicationLayer.getName()).mayOnlyBeAccessedByLayers(
                adaptersLayer.getName(),
                infraLayer.getName());

        arch.whereLayer(adaptersLayer.getName()).mayOnlyBeAccessedByLayers(infraLayer.getName());

        arch.check(jc);
    }
}

