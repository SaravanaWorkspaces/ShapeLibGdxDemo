package demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
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

    /* CameraInputController */
    CameraInputController cameraController;
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


    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));

        perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(new Vector3(0, 5f, 9f));
        perspectiveCamera.lookAt(new Vector3(0, 0, 0));
        perspectiveCamera.near = 1;
        perspectiveCamera.far = 300;
        perspectiveCamera.update();

        modelBatch = new ModelBatch();
        modelBuilder = new ModelBuilder();

        long attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;

        modelBuilder.begin();


        model = modelBuilder.end();

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


    private void drawCylinder() {
        float radius = 2;
        float sides = 2;
        final float theta = (float) (2. * 3.14159) / (float) sides;
        float c = MathUtils.cos(theta);
        float s = MathUtils.sin(theta);

        // coordinates on top of the circle, on xz plane
        float x2 = radius, z2 = 0;


    }
}
