package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class CreateEvent extends AbstractComponentEvent {
    public CreateEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public CreateEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
