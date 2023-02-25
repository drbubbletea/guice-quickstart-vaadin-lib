package net.timeboxing.guice.vaadin.guice.component.impl;

public class DefaultTestService implements TestService {
    @Override
    public String callMe() {
        return "12345";
    }
}
