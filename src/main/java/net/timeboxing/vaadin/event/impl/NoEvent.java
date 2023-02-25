package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class NoEvent extends AbstractComponentEvent {

    public NoEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public NoEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
