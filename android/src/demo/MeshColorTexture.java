package demo;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

import javax.microedition.khronos.opengles.GL10;

/**
 * Comment
 * <p/>
 * MeshColorTexture.java
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class MeshColorTexture implements ApplicationListener {


    private static final String TAG = "MeshColorTexture";
    ShaderProgram shaderProgram;
    private Mesh mesh;

    @Override
    public void create() {
        Gdx.app.log("GDX", "create...");

        //create shader program
        String vertexShader =
                "attribute vec4 vPosition; 		\n" +
                        "void main()					\n" +
                        "{								\n" +
                        "	gl_Position = vPosition;	\n" +
                        "}								\n";


        String fragmentShader =
                "#ifdef GL_ES 								\n" +
                        "precision mediump float;					\n" +
                        "#endif 									\n" +
                        "void main()								\n" +
                        "{											\n" +
                        "	gl_FragColor = vec4(1.0,1.0,1.0,1.0);	\n" +
                        "}											\n";


        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

        mesh = new Mesh(true, 3, 3,
                new VertexAttribute(Usage.Position, 3, "vPosition"));

        mesh.setVertices(new float[]{
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0, 0.5f, 0

        });

        mesh.setIndices(new short[]{0, 1, 2});


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw triangle
        shaderProgram.begin();
        mesh.render(shaderProgram, GL20.GL_TRIANGLES);
        shaderProgram.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {


    }

    @Override
    public void dispose() {

    }
}
