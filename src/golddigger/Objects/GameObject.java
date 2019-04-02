
package golddigger.Objects;

/**
 * Abstract class for objects in game
 * @author Duc Nguyen 17974984
 */
public abstract class GameObject {
    private Types type;
    private int value;
    private boolean discovered;

    public GameObject(Types type, int value) {
        this.setType(type);
        this.setValue(value);
        this.setDiscovered(false);
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }
}
