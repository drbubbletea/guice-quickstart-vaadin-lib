package net.timeboxing.vaadin.event;

import net.timeboxing.vaadin.component.VaadinComponent;

import java.util.Optional;

public abstract class AbstractComponentEvent implements ComponentEvent {

    private final VaadinComponent producedBy;
    private final Object data;

    protected AbstractComponentEvent(VaadinComponent producedBy) {
        this.producedBy = producedBy;
        this.data = null;
    }

    protected AbstractComponentEvent(VaadinComponent producedBy, Object data) {
        this.producedBy = producedBy;
        this.data = data;
    }

    @Override
    public VaadinComponent producedBy() {
        return producedBy;
    }

    @Override
    public <T> Optional<T> data(Class<T> dataClass) {
        if (null == data) {
            return Optional.empty();
        }
        if (dataClass.isAssignableFrom(data.getClass())) {
            return Optional.of(dataClass.cast(data));
        } else {
            throw new VaadinEventException(String.format("Data class '%s' not of specified class '%s'", data.getClass().getName(), dataClass.getName()));
        }
    }
}
