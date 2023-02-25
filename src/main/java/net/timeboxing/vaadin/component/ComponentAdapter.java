package net.timeboxing.vaadin.component;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Optional;

/**
 * Static implementation of the adapter pattern for our VaadinComponents.
 */
public class ComponentAdapter {


    private static Provider<DefaultVaadinComponentFactory> factoryProvider;
    private static Provider<CustomVaadinComponentFactory> customFactoryProvider;

    private ComponentAdapter() {
        /* NOOP */
    }

    @Inject
    public static void initialize(Provider<DefaultVaadinComponentFactory> provider, Provider<CustomVaadinComponentFactory> customProvider) {
        factoryProvider = provider;
        customFactoryProvider = customProvider;
    }

    /**
     * Find a suitable VaadinComponent based on the ComponentPurpose value provided.
     */
    @Deprecated
    public static Optional<VaadinComponent> adapt(Object source, ComponentPurpose purpose) {
        if (factoryProvider == null) {
            throw new ComponentAdapterException("Default component factory not initialized");
        }
        return factoryProvider.get().get(source, purpose);
    }

    /**
     * Find a suitable VaadinComponent based on the type and purpose provided.
     */
    @Deprecated
    public static Optional<VaadinComponent> adapt(Object source, String type, String purpose) {
        if (factoryProvider == null) {
            throw new ComponentAdapterException("Custom component factory not initialized");
        }
        return customFactoryProvider.get().get(source, type, purpose);
    }

    /**
     * Find a suitable VaadinComponent based on the ComponentPurpose value provided. If found, sets the specified parent
     * as the parent of the VaadinComponent and adds the VaadinComponent as a child of the specified parent.
     */
    public static Optional<VaadinComponent> adapt(VaadinComponent parent, Object source, ComponentPurpose purpose) {
        Optional<VaadinComponent> candidate = adapt(source, purpose);
        candidate.ifPresent(component -> {
            component.addParent(parent);
            parent.addChild(component);
        });
        return candidate;
    }

    /**
     * Find a suitable VaadinComponent based on the type and purpose provided. If found, sets the specified parent
     * as the parent of the VaadinComponent and adds the VaadinComponent as a child of the specified parent.
     */
    public static Optional<VaadinComponent> adapt(VaadinComponent parent, Object source, String type, String purpose) {
        Optional<VaadinComponent> candidate = adapt(source, type, purpose);
        candidate.ifPresent(component -> {
            component.addParent(parent);
            parent.addChild(component);
        });
        return candidate;
    }

}
