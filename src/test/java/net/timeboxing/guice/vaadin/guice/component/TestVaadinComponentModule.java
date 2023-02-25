package net.timeboxing.guice.vaadin.guice.component;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import net.timeboxing.guice.vaadin.guice.component.impl.DefaultTestService;
import net.timeboxing.guice.vaadin.guice.component.impl.TestService;
import net.timeboxing.vaadin.guice.VaadinComponentModule;

public class TestVaadinComponentModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        install(new VaadinComponentModule("net.timeboxing"));
        bind(TestService.class).to(DefaultTestService.class).in(Scopes.SINGLETON);
    }
}
