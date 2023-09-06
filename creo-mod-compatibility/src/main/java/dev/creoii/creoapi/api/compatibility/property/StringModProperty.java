package dev.creoii.creoapi.api.compatibility.property;

public class StringModProperty extends ModProperty<String> {
    public String value;

    public StringModProperty(String name) {
        super(name);
    }

    public StringModProperty(String name, String defaultValue) {
        this(name);
        value = defaultValue;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
