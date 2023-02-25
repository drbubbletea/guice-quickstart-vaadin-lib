package net.timeboxing.vaadin.component;

import com.google.inject.Injector;

import javax.inject.Inject;

public class DefaultVaadinComponentCreator extends AbstractVaadinComponentCreator {

    public DefaultVaadinComponentCreator(Class<?> componentClass) {
        super(componentClass);
    }

    @Inject
    public void initialize(Injector injector) {
        this.injector = injector;
    }

    public VaadinComponent create(Object source, Object... purpose) {
        if (purpose.length != 1 || !ComponentPurpose.class.isAssignableFrom(purpose[0].getClass())) {
            throw new ComponentAdapterException("Only purpose ComponentPurpose is supported in this VaadinComponentCreator");
        }
        return get(source, purpose[0]);
    }

}
