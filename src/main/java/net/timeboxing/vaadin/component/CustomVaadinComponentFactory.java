package net.timeboxing.vaadin.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Map;
import java.util.Optional;

public class CustomVaadinComponentFactory implements VaadinComponentFactory {

    private static final Logger LOG = LoggerFactory.getLogger(CustomVaadinComponentFactory.class);

    private final Provider<Map<CustomComponentCreatorKey, CustomVaadinComponentCreator>> creatorsProvider;

    @Inject
    public CustomVaadinComponentFactory(Provider<Map<CustomComponentCreatorKey, CustomVaadinComponentCreator>> provider) {
        this.creatorsProvider = provider;
    }

    @Override
    public Optional<VaadinComponent> get(Object source, Object... purpose) {
        if (purpose.length != 2 || !String.class.isAssignableFrom(purpose[0].getClass()) || !String.class.isAssignableFrom(purpose[1].getClass())) {
            throw new ComponentAdapterException("Only purpose ComponentPurpose is supported in this VaadinComponentFactory");
        }
        String type = (String) purpose[0];
        String purposeVal = (String) purpose[1];

        CustomComponentCreatorKey key = new CustomComponentCreatorKey(source.getClass(), type, purposeVal);
        LOG.debug("Looking for creator for {}", key);

        CustomVaadinComponentCreator creator = creatorsProvider.get().getOrDefault(key, null);
        VaadinComponent result = null;
        if (creator != null) {
            result = creator.create(source, purpose);
        }
        if (result == null) {
            // find creator for interface
            Class<?>[] interfaces = source.getClass().getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                key = new CustomComponentCreatorKey(interfaces[i], type, purposeVal);
                LOG.debug("Looking for creator for {}", key);
                creator = creatorsProvider.get().getOrDefault(key, null);
                if (creator != null) {
                    result = creator.create(source, purpose);
                    break;
                }
            }
        }
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
