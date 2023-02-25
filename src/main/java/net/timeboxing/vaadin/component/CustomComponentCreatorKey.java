package net.timeboxing.vaadin.component;

import com.google.common.base.Objects;

public class CustomComponentCreatorKey {

    private final Class<?> forClass;

    private final String purpose;
    private final String value;

    public CustomComponentCreatorKey(Class<?> forClass, String purpose, String value) {
        this.forClass = forClass;
        this.purpose = purpose;
        this.value = value;
    }

    @Override
    public String toString() {
        return "CustomComponentCreatorKey{" +
                "forClass=" + forClass +
                ", purpose='" + purpose + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomComponentCreatorKey that = (CustomComponentCreatorKey) o;
        return Objects.equal(forClass, that.forClass) && Objects.equal(purpose, that.purpose) && Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(forClass, purpose, value);
    }
}
