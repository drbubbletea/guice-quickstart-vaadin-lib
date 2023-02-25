package net.timeboxing.vaadin.component;

import java.util.Optional;

public interface VaadinComponentFactory {
    Optional<VaadinComponent> get(Object source, Object... purpose);
}
