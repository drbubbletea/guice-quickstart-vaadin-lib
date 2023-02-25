package net.timeboxing.guice.vaadin.guice.component.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import net.timeboxing.vaadin.component.AbstractVaadinComponent;
import net.timeboxing.vaadin.component.ComponentFor;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purposeType = "TEST", purposeValue = "FOO")
public class CustomUserTestFooComponent extends AbstractVaadinComponent {

    @Inject
    public CustomUserTestFooComponent() {
        /* NOOP */
    }
    private final Component component = new Label("testing");
    @Override
    public Component get() {
        return component;
    }
}
