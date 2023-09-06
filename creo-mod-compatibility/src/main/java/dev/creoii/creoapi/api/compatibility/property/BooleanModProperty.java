package dev.creoii.creoapi.api.compatibility.property;

public class BooleanModProperty extends ModProperty<Boolean> {
    public boolean value;

    public BooleanModProperty(String name) {
        super(name);
    }

    public BooleanModProperty(String name, boolean defaultValue) {
        this(name);
        value = defaultValue;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }
}
