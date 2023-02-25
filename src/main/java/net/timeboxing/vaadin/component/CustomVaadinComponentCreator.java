package net.timeboxing.vaadin.component;

import com.google.inject.Injector;

import javax.inject.Inject;


public class CustomVaadinComponentCreator extends AbstractVaadinComponentCreator {

    public CustomVaadinComponentCreator(Class<?> componentClass) {
        super(componentClass);
    }

    @Inject
    public void initialize(Injector injector) {
        this.injector = injector;
    }

    public VaadinComponent create(Object source, Object... purpose) {
        if (purpose.length != 2 || !String.class.isAssignableFrom(purpose[0].getClass()) || !String.class.isAssignableFrom(purpose[1].getClass())) {
            throw new ComponentAdapterException("Only custom purpose type and value is supported in this VaadinComponentCreator");
        }
        return get(source, purpose[1]);
    }
}
