package demo;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attribute;
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
 * <p>
 * ModelClassExploring.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class ModelClassExploring extends ApplicationAdapter {

    private static final String TAG = "ModelClassExploring";
    ModelBatch modelBatch;
    PerspectiveCamera perspectiveCamera;

    ModelInstance modelInstance;
    Model model;
    ModelBuilder modelBuilder;

    public Environment environment;

    CameraInputController cameraInputController;


    @Override
    public void create() {

        modelBatch = new ModelBatch();

        perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(new Vector3(0, 0, 9));
        perspectiveCamera.lookAt(new Vector3(0, 0, 0));
        perspectiveCamera.near = 1;
        perspectiveCamera.far = 300;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));

        modelBuilder = new ModelBuilder();

        long attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        //create cone
        model = modelBuilder.createCone(4, 0, 4, 1000,
                new Material(ColorAttribute.createDiffuse(Color.FIREBRICK)), attr);


        cameraInputController = new CameraInputController(perspectiveCamera);
        cameraInputController.update();

        Gdx.input.setInputProcessor(cameraInputController);

    }

    @Override
    public void render() {
        perspectiveCamera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);


        modelInstance = new ModelInstance(model);

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();
    }
}

