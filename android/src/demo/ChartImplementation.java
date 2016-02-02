package demo;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

/**
 * <p/>
 * ChartImplementation.java
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class ChartImplementation extends ApplicationAdapter {

    private static final String TAG = "ChartImplementation";
    /* CameraInputController */
    CameraInputController cameraController;
    DirectionalShadowLight shadowLight;
    /* Environment */
    private Environment environment;
    /* Camera */
    private PerspectiveCamera perspectiveCamera;
    /* ModelBuilder */
    private ModelBuilder modelBuilder;
    /* Model */
    private Model model;
    /* ModelInstance */
    private ModelInstance modelInstance;
    /* Modelbatch */
    private ModelBatch modelBatch;

    /* Model Values */
    private float width = 10f;
    private float height = 0.3f;
    private float depth = 10f;
    private int division = 1000;


    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new PointLight().set(Color.ORANGE, new Vector3(0, 0, 0), 10f));

        perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(new Vector3(0, 10f, 9f));
        perspectiveCamera.lookAt(new Vector3(0, 0, 0));
        perspectiveCamera.near = 1;
        perspectiveCamera.far = 100;
        perspectiveCamera.update();

        modelBatch = new ModelBatch();
        modelBuilder = new ModelBuilder();


        long attr = VertexAttributes.Usage.Position;

        modelBuilder.begin();
        Color c = new Color(135 / 255f, 206 / 255f, 235 / 255f, 1);
        modelBuilder.part("1", GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(c)))
                .cylinder(width, height, depth, division, 0, 45);

        modelBuilder.part("2", GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(Color.RED)))
                .cylinder(width, height, depth, division, 45, 90);

        modelBuilder.part("3", GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(c)))
                .cylinder(width, height, depth, division, 90, 180);

        modelBuilder.part("4", GL20.GL_TRIANGLES, attr,
                new Material(ColorAttribute.createDiffuse(c)))
                .cylinder(width, height, depth, division, 180, 360);

        model = modelBuilder.end();


        Log.d(TAG, "Total Mesh " + model.meshParts.size);

        modelInstance = new ModelInstance(model);

        cameraController = new CameraInputController(perspectiveCamera);
        Gdx.input.setInputProcessor(cameraController);

    }

    @Override
    public void render() {

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1);

        modelInstance = new ModelInstance(model);

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(modelInstance, environment);
        modelBatch.end();
    }
}
