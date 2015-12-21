package shape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * Shapes.java
 * <p/>
 * Comment
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @category Impiger
 * @package shape
 * @copyright Copyright (C) 2014 Impiger. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
public class Shapes {
    public static final int GROUND = 0;
    public static final int SPHERE = 1;
    public static final int CYLINDER = 3;


    public static Model drawShapes(int type) {
        return getShape(type);
    }

    private static Model getShape(int type) {
        ModelBuilder modelBuilder = new ModelBuilder();
        switch (type) {
            case GROUND:
                modelBuilder.begin();
                modelBuilder.node().id = "ground";
                modelBuilder.part("box",
                        GL20.GL_TRIANGLES,
                        Usage.Position
                                | Usage.Normal,
                        new Material(ColorAttribute.createDiffuse(Color.YELLOW)))
                        .box(5f, 1f, 5f);

                return modelBuilder.end();

            case SPHERE:
                modelBuilder.begin();
                modelBuilder.node().id = "ball";
                modelBuilder.part("sphere",
                        GL20.GL_TRIANGLES,
                        Usage.Position
                                | Usage.Normal,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)))
                        .sphere(1f, 1f, 1f, 10, 10);

                return modelBuilder.end();

            case CYLINDER:
                modelBuilder.begin();
                modelBuilder.node().id = "ball";
                modelBuilder.part("sphere",
                        GL20.GL_TRIANGLES,
                        Usage.Position
                                | Usage.Normal,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)))
                        .sphere(1f, 1f, 1f, 10, 10);

                return modelBuilder.end();
        }

        return null;
    }


}
