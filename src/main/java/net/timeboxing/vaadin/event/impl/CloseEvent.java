package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class CloseEvent extends AbstractComponentEvent {
    public CloseEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public CloseEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
