                                                                                     # Vaadin Library

## `ComponentAdapter` & `VaadinComponent`s

`ComponentFor` usages supported:
1. ComponentPurpose Enum
    * For convenience, `ComponentPurpose` is included with several example values which reflect possible component purposes.
    * Example: `@ComponentFor(forClass = User.class, purpose = ComponentPurpose.VIEW)`
1. Custom Purpose Type & Value Strings
    * Example: `@ComponentFor(forClass = User.class, purposeType = "ANOTHER", purposeValue = "BAR")`

## `ComponentEvent`s & `VaadinComponentEventBus`
