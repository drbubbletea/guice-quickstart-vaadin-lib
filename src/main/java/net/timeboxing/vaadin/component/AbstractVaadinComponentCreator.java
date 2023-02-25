package net.timeboxing.vaadin.component;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public abstract class AbstractVaadinComponentCreator {
    private final Class<?> componentClass;
    protected final Constructor<?> constructor;

    private final Type[] genericTypes;
    private final Annotation[][] parameterAnnotations;

    protected Injector injector;

    protected AbstractVaadinComponentCreator(Class<?> componentClass) {
        this.componentClass = componentClass;
        this.constructor = getConstructor();
        this.genericTypes = constructor.getGenericParameterTypes();
        this.parameterAnnotations = constructor.getParameterAnnotations();
    }

    protected Object[] getParameters(Object source, Object purpose) {
        Object[] parameters = new Object[constructor.getParameterCount()];
        for (int i = 0; i < parameters.length; i++) {
            boolean found = false;
            for (Annotation annotation: parameterAnnotations[i]) {
                if (Source.class == annotation.annotationType()) {
                    parameters[i] = source;
                    found = true;
                    break;
                } else if (Purpose.class == annotation.annotationType()) {
                    parameters[i] = purpose;
                    found = true;
                    break;
                }
            }
            if (!found) {
                parameters[i] = injector.getInstance(Key.get(TypeLiteral.get(genericTypes[i])));
            }
        }
        return parameters;
    }
    protected Constructor getConstructor() {
        for (Constructor<?> ctor: componentClass.getConstructors()) {
            if (ctor.isAnnotationPresent(Inject.class)) {
                return ctor;
            }
        }
        throw new ComponentAdapterException(String.format("No constructor annotated with javax.inject.Inject found in %s", componentClass.getName()));
    }

    protected VaadinComponent get(Object source, Object purpose) {
        try {
            Object[] parameters = getParameters(source, purpose);
            Object instance = constructor.newInstance(parameters);
            // handle any injection outside of the constructor
            injector.injectMembers(instance);
            return (VaadinComponent) instance;
        } catch (Exception e) {
            throw new ComponentAdapterException("Failed to create component instance", e);
        }
    }
}
