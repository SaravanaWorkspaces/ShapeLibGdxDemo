package utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Comment
 * <p/>
 * ColorUtils.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package utils
 * @copyright Copyright (C) 2015 Impiger. All rights reserved.
 */
public class ColorUtils {

    public static Color[] color = {
            Color.BLUE, Color.BLACK, Color.BROWN,
            Color.CLEAR, Color.CORAL, Color.DARK_GRAY,
            Color.FIREBRICK, Color.FOREST, Color.GOLD,
            Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
            Color.ORANGE, Color.RED, Color.YELLOW,
            Color.WHITE, Color.VIOLET, Color.PINK,
            Color.MAROON, Color.LIME, Color.CHARTREUSE
    };

    /**
     * Util Method returns color for the shape.
     */
    public static int getColorArraySize() {
        return color.length;
    }
}
