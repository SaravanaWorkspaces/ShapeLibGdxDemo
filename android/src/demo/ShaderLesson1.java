package demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.io.IOException;

/**
 * Comment
 * <p/>
 * ShaderLesson1.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class ShaderLesson1 extends Game{

    //our texture
    Texture tex;

    //our sprite batch
    SpriteBatch batch;

    @Override
    public void create() {

        //this will be ignored in this lesson...
            tex = new Texture(Gdx.files.internal("snakehead.png"));


            String vertexShader =
                            "attribute vec2 Position; 		\n" +
                             "void main()"+
                            "{								\n" +
                            "	gl_Position = vPosition;	\n" +
                            "}								\n";


            String fragmentShader =
                            "void main()								\n" +
                            "{											\n" +
                            "	gl_FragColor = vec4(1.0,1.0,0.0,0.0);	\n" +
                            "}"	;

            ShaderProgram program = new ShaderProgram(vertexShader, fragmentShader);

            batch = new SpriteBatch(4, program);

    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(tex, 10, 10);
        batch.draw(tex, 10, 320, 32, 32);
        batch.end();
    }
}
