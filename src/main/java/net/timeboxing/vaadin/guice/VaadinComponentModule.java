package net.timeboxing.vaadin.guice;

import com.google.common.base.Strings;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import net.timeboxing.vaadin.component.*;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class VaadinComponentModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(VaadinComponentModule.class);

    private final String packageToScan;

    private final TypeLiteral<DefaultComponentCreatorKey> componentCreatorKeyTypeLiteral = TypeLiteral.get(DefaultComponentCreatorKey.class);
    private final TypeLiteral<CustomComponentCreatorKey> customComponentCreatorKeyTypeLiteral = TypeLiteral.get(CustomComponentCreatorKey.class);
    private final TypeLiteral<DefaultVaadinComponentCreator> componentCreatorTypeLiteral = TypeLiteral.get(DefaultVaadinComponentCreator.class);
    private final TypeLiteral<CustomVaadinComponentCreator> customVaadinComponentCreatorTypeLiteral = TypeLiteral.get(CustomVaadinComponentCreator.class);

    public VaadinComponentModule(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    @Override
    protected void configure() {
        requestStaticInjection(ComponentAdapter.class);

        Reflections reflections = new Reflections(packageToScan);
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(ComponentFor.class);
        MapBinder<DefaultComponentCreatorKey, DefaultVaadinComponentCreator> defaultCreators = MapBinder.newMapBinder(binder(), componentCreatorKeyTypeLiteral, componentCreatorTypeLiteral);
        MapBinder<CustomComponentCreatorKey, CustomVaadinComponentCreator> customCreators = MapBinder.newMapBinder(binder(), customComponentCreatorKeyTypeLiteral, customVaadinComponentCreatorTypeLiteral);
        for (Class<?> component : components) {
            LOG.debug("Found class {}", component.getCanonicalName());
            ComponentFor annotation = component.getAnnotation(ComponentFor.class);
            Class<?> forClass = annotation.forClass();
            if (Strings.isNullOrEmpty(annotation.purposeType()) && Strings.isNullOrEmpty(annotation.purposeValue())) {
                ComponentPurpose purpose = annotation.purpose();
                DefaultComponentCreatorKey key = new DefaultComponentCreatorKey(forClass, purpose);
                DefaultVaadinComponentCreator creator = new DefaultVaadinComponentCreator(component);
                defaultCreators.addBinding(key).toInstance(creator);
                LOG.debug("Bound creator: {}", key);
            } else {
                String purpose = annotation.purposeType();
                String value = annotation.purposeValue();
                CustomComponentCreatorKey key = new CustomComponentCreatorKey(forClass, purpose, value);
                CustomVaadinComponentCreator creator = new CustomVaadinComponentCreator(component);
                customCreators.addBinding(key).toInstance(creator);
                LOG.debug("Bound creator: {}", key);
            }
        }
    }
}
