package net.timeboxing.guice.vaadin.guice.component.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import net.timeboxing.vaadin.component.AbstractVaadinComponent;
import net.timeboxing.vaadin.component.ComponentFor;
import net.timeboxing.vaadin.component.ComponentPurpose;
import net.timeboxing.vaadin.component.Source;

import javax.inject.Inject;

@ComponentFor(forClass = User.class, purpose = ComponentPurpose.EDIT)
public class UserEditComponent extends AbstractVaadinComponent {

    private final User user;
    private final Component component;

    @Inject
    public UserEditComponent(@Source User user) {
        this.user = user;
        this.component = new Label("test");
    }

    public User user() {
        return user;
    }


    @Override
    public Component get() {
        return component;
    }
}
