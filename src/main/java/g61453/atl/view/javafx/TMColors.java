package g61453.atl.view.javafx;

import javafx.scene.paint.Color;

public enum TMColors {
    GREEN(Color.rgb(73, 183, 114)),
    PINK(Color.rgb(127, 104, 173)),
    YELLOW(Color.rgb(255, 190, 18)),
    RED(Color.rgb(231, 76, 60)),
    ORANGE(Color.rgb(235, 92, 24)),
    SKYBLUE(Color.rgb(101, 178, 209)),
    DARKBLUE(Color.rgb(38, 61, 78));

    private final Color light;
    private final Color dark;
    private final double factor;

    TMColors(Color light) {
        this.factor = 0.2;
        this.light = light;
        this.dark = lightToDark();
    }

    public Color getLight() {
        return light;
    }

    public Color getDark() {
        return dark;
    }

    private Color lightToDark() {
        double r = light.getRed() * (1 - factor);
        double g = light.getGreen() * (1 - factor);
        double b = light.getBlue() * (1 - factor);
        double o = light.getOpacity();
        return new Color(r, g, b, o);
    }
}
