package demo;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.CubemapAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

import controller.CameraController;

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

    private Array<ModelInstance> modelInstances;
    private CameraInputController cameraController;
    private String[] meshParts = new String[]{"front", "back", "bottom", "top", "left", "right"};

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 8f, 8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 0f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();

        modelInstances = new Array<>();

        modelBuilder.begin();
        /*modelBuilder.part("box", GL20.GL_TRIANGLES,
                Usage.Position | Usage.Normal | Usage.TextureCoordinates,
                new Material(ColorAttribute.createDiffuse(Color.GOLD)))
                .box(5f, 5f, 5f);*/

        long attr = Usage.Position | Usage.Normal | Usage.TextureCoordinates;

        modelBuilder.part(meshParts[0], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("badlogic.jpg")))))
                .rect(-2f, -2f, -2f, -2f, 2f, -2f, 2f, 2f, -2, 2f, -2f, -2f, 0, 0, -1);

        modelBuilder.part(meshParts[1], GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)))
                .rect(-2f, 2f, 2f, -2f, -2f, 2f, 2f, -2f, 2f, 2f, 2f, 2f, 0, 0, 1);

        modelBuilder.part(meshParts[2], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("badlogic.jpg")))))
                .rect(-2f, -2f, 2f, -2f, -2f, -2f, 2f, -2f, -2f, 2f, -2f, 2f, 0, -1, 0);

        modelBuilder.part(meshParts[3], GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(Color.RED)))
                .rect(-2f, 2f, -2f, -2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, -2f, 0, 1, 0);

        modelBuilder.part(meshParts[4], GL20.GL_TRIANGLES, attr,
                new Material(TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("snakehead.png")))))
                .rect(-2f, -2f, 2f, -2f, 2f, 2f, -2f, 2f, -2f, -2f, -2f, -2f, -1, 0, 0);

        modelBuilder.part(meshParts[5], GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(Color.YELLOW)))
                .rect(2f, -2f, -2f, 2f, 2f, -2f, 2f, 2f, 2f, 2f, -2f, 2f, 1, 0, 0);

        model = modelBuilder.end();

        modelInstances.add(new ModelInstance(model));

        cameraController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(cameraController);

    }



    @Override
    public void render() {

        cameraController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        float[] vertex = new float[model.meshParts.get(0).mesh.getVertexSize()];
        model.meshParts.get(0).mesh.getVertices(vertex);


        modelBatch.begin(cam);
        modelBatch.render(modelInstances, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }

}
