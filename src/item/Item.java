package src.item;

import java.io.Serializable;
import javafx.scene.image.Image;

public abstract class Item implements Serializable {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Image render();

    public abstract String getParent();
}
