package net.timeboxing.guice.vaadin.guice.component.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import net.timeboxing.vaadin.component.AbstractVaadinComponent;
import net.timeboxing.vaadin.component.ComponentFor;
import net.timeboxing.vaadin.component.Purpose;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purposeType = "ANOTHER", purposeValue = "BAR")
public class CustomUserAnotherBarComponent extends AbstractVaadinComponent {

    private final String purpose;
    @Inject
    public CustomUserAnotherBarComponent(@Purpose String purpose) {
        this.purpose = purpose;
    }
    private final Component component = new Label("testing");
    @Override
    public Component get() {
        return component;
    }

    public String purpose() {
        return purpose;
    }
}
