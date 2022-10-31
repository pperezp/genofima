package cl.prezdev.genofima;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@Builder
public class IconRequest {
    private int width;
    private int height;
    private String value;
    private Color color;
    private Color background;

    public boolean hasNotValue() {
        return value == null;
    }
}
