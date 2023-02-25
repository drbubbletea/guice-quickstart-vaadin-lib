package net.timeboxing.vaadin.guice;

import com.google.inject.AbstractModule;
import net.timeboxing.vaadin.event.VaadinComponentEventBus;
import net.timeboxing.vaadin.event.impl.DefaultVaadinComponentEventBus;

import java.lang.annotation.Annotation;

public class VaadinComponentEventModule extends AbstractModule {

    private final Class<? extends Annotation> scope;

    /**
     *
     * @param scope
     */
    public VaadinComponentEventModule(Class<? extends Annotation> scope) {
        this.scope = scope;
    }

    @Override
    protected void configure() {
        super.configure();
        bind(VaadinComponentEventBus.class).to(DefaultVaadinComponentEventBus.class).in(scope);
    }
}
