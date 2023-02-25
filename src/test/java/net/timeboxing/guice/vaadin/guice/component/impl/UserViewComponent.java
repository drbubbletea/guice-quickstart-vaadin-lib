package net.timeboxing.guice.vaadin.guice.component.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import net.timeboxing.vaadin.component.*;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purpose = ComponentPurpose.VIEW)
public class UserViewComponent extends AbstractVaadinComponent {

    private final User user;
    private final Component component;
    private final ComponentPurpose purpose;

    private final TestService testService;

    @Inject
    public UserViewComponent(@Source User user, @Purpose ComponentPurpose purpose, TestService testService) {
        this.user = user;
        this.purpose = purpose;
        this.testService = testService;
        this.component = new Label("test");
    }

    public User user() {
        return user;
    }

    @Override
    public Component get() {
        return component;
    }

    public ComponentPurpose purpose() {
        return purpose;
    }

    public TestService testService() {
        return testService;
    }
}
