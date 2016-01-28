package demo;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

/**
 * Comment
 * <p/>
 * Basic3DModel.java
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class Basic3DModel extends ApplicationAdapter {

    private static final String TAG = "Basic3DModel";
    public Environment environment;
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    Model model;
    private ModelInstance modelInstance;
    private CameraInputController cameraController;
    private String[] meshParts = new String[]{"front", "back", "bottom", "top", "left", "right"};
    // pos 0 - 4 : Triangle, pos - 5 :
    private Vector3[] front_vector = new Vector3[]{
            new Vector3(-2f, -2f, -2f),
            new Vector3(-2f, 2f, -2f),
            new Vector3(2f, 2f, -2),
            new Vector3(2f, -2f, -2f),
            new Vector3(0, 0, -1)};

    private Vector3[] back_vector = new Vector3[]{
            new Vector3(-2f, 2f, 2f),
            new Vector3(-2f, -2f, 2f),
            new Vector3(2f, -2f, 2f),
            new Vector3(2f, 2f, 2f),
            new Vector3(0, 0, 1)
    };
    private Vector3[] bottom_vector = new Vector3[]{
            new Vector3(-2f, -2f, 2f),
            new Vector3(-2f, -2f, -2f),
            new Vector3(2f, -2f, -2f),
            new Vector3(2f, -2f, 2f),
            new Vector3(0, -1, 0)
    };
    private Vector3[] top_vector = new Vector3[]{
            new Vector3(-2f, 2f, -2f),
            new Vector3(-2f, 2f, 2f),
            new Vector3(2f, 2f, 2f),
            new Vector3(2f, 2f, -2f),
            new Vector3(0, 1, 0)
    };
    private Vector3[] left_vector = new Vector3[]{
            new Vector3(-2f, -2f, 2f),
            new Vector3(-2f, 2f, 2f),
            new Vector3(-2f, 2f, -2f),
            new Vector3(-2f, -2f, -2f),
            new Vector3(-1, 0, 0)
    };
    private Vector3[] right_vector = new Vector3[]{
            new Vector3(2f, -2f, -2f),
            new Vector3(2f, 2f, -2f),
            new Vector3(2f, 2f, 2f),
            new Vector3(2f, -2f, 2f),
            new Vector3(1, 0, 0)
    };

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        //environment.add(new DirectionalLight().set(0.8f, 8f, 8f, -1f, -0.8f, -0.2f));


        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 0f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();

        modelBuilder.begin();

        long attr = Usage.Position | Usage.Normal |Usage.TextureCoordinates;

        modelBuilder.node().id = meshParts[0];
        modelBuilder.part(meshParts[0], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("char1.png")))))
                .rect(front_vector[0], front_vector[1], front_vector[2], front_vector[3], front_vector[4]);

        modelBuilder.node().id = meshParts[1];
        modelBuilder.part(meshParts[1], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("char2.jpg")))))
                .rect(back_vector[0], back_vector[1], back_vector[2], back_vector[3], back_vector[4]);

        modelBuilder.node().id = meshParts[2];
        modelBuilder.part(meshParts[2], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("char3.jpg")))))
                .rect(bottom_vector[0], bottom_vector[1], bottom_vector[2], bottom_vector[3], bottom_vector[4]);

        modelBuilder.node().id = meshParts[3];
        modelBuilder.part(meshParts[3], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("badlogic.jpg")))))
                .rect(top_vector[0], top_vector[1], top_vector[2], top_vector[3], top_vector[4]);

        modelBuilder.node().id = meshParts[4];
        modelBuilder.part(meshParts[4], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("snakehead.png")))))
                .rect(left_vector[0], left_vector[1], left_vector[2], left_vector[3], left_vector[4]);

        modelBuilder.node().id = meshParts[5];
        modelBuilder.part(meshParts[5], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("char3.jpg")))))
                .rect(right_vector[0], right_vector[1], right_vector[2], right_vector[3], right_vector[4]);

        // Add Separate Model

        model = modelBuilder.end();

        modelInstance = new ModelInstance(model);
        cameraController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(cameraController);

    }

    @Override
    public void render() {

        cameraController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);


        modelInstance = new ModelInstance(model);

        modelBatch.begin(cam);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }


}
