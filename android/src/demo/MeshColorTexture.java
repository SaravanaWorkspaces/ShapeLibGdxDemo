package demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

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
                        "	gl_FragColor = vec4(1.0,1.0,0.0,0.0);	\n" +
                        "}											\n";


        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

        mesh = createFullScreenQuad();

       /* mesh.setVertices(new float[]{
                -0.001f, -0.5f, 0,
                 0.5f,   -0.5f, 0,
                 0.0f,    0.5f, 0,
        });*/

       //mesh.setIndices(new short[]{0, 1, 2});


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    public Mesh createFullScreenQuad() {

        float[] verts = new float[20];
        int i = 0;

        verts[i++] = -1; // x1
        verts[i++] = -1; // y1
        verts[i++] = 0;
        verts[i++] = 0f; // u1
        verts[i++] = 0f; // v1

        verts[i++] = 1f; // x2
        verts[i++] = -1; // y2
        verts[i++] = 0;
        verts[i++] = 1f; // u2
        verts[i++] = 0f; // v2

        verts[i++] = 1f; // x3
        verts[i++] = 1f; // y2
        verts[i++] = 0;
        verts[i++] = 1f; // u3
        verts[i++] = 1f; // v3

        verts[i++] = -1; // x4
        verts[i++] = 1f; // y4
        verts[i++] = 0;
        verts[i++] = 0f; // u4
        verts[i++] = 1f; // v4

        Mesh mesh = new Mesh( true, 4, 0,  // static mesh with 4 vertices and no indices
                new VertexAttribute( Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE ),
                new VertexAttribute( Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0" ) );

        mesh.setVertices( verts );

        return mesh;
    }
}
