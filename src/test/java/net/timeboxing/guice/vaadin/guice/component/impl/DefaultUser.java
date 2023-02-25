package net.timeboxing.guice.vaadin.guice.component.impl;

public class DefaultUser implements User {

    private final Integer id;

    public DefaultUser(Integer id) {
        this.id = id;
    }

    @Override
    public Integer id() {
        return id;
    }
}
