package net.timeboxing.vaadin.event.impl;

import net.timeboxing.vaadin.component.VaadinComponent;
import net.timeboxing.vaadin.event.AbstractComponentEvent;

public class ConfirmEvent extends AbstractComponentEvent {
    public ConfirmEvent(VaadinComponent producedBy) {
        super(producedBy);
    }

    public ConfirmEvent(VaadinComponent producedBy, Object data) {
        super(producedBy, data);
    }
}
