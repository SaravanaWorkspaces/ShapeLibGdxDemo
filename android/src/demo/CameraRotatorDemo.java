package demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

/**
 * This class renders 3D square. I'm Just making camera rotation on the screen.
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2015 Impiger. All rights reserved.
 */
public class CameraRotatorDemo implements ApplicationListener {

    private static final float ROTATION_MAX = 10f;
    private static final float ROTATION_MIN = 5f;

    private static final int X_ROTATION = 0;
    private static final int Y_ROTATION = 1;
    private static final int Z_ROTATION = 2;
    private static final int XY_ROTATION = 3;
    private static final int XYZ_ROTATION = 4;

    private static final String TAG = "CameraRotatorDemo";
    public Environment environment;
    public PerspectiveCamera cam;

    public ModelBatch modelBatch;

    public Model model;
    public ModelInstance instance;


    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(getVector(XY_ROTATION));
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                Usage.Position | Usage.Normal);

        instance = new ModelInstance(model);

    }

    @Override
    public void render() {

        cam.position.set(getVector(XYZ_ROTATION));
        cam.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    private Vector3 getVector(int type) {
        Vector3 vector3 = new Vector3();
        vector3.x = ROTATION_MAX;
        vector3.y = ROTATION_MIN;
        vector3.z = ROTATION_MAX;
        switch (type) {
            case X_ROTATION:
                float currentX = cam.position.x;
                vector3.x = currentX < 100 ? currentX + 1 : ROTATION_MAX;
                break;
            case Y_ROTATION:
                float currentY = cam.position.y;
                vector3.y = currentY < 100 ? currentY + 1 : ROTATION_MIN;
                break;
            case Z_ROTATION:
                float currentZ = cam.position.z;
                vector3.z = currentZ < 100 ? currentZ + 1 : ROTATION_MAX;
                break;
            case XY_ROTATION:
                currentX = cam.position.x;
                currentY = cam.position.y;
                vector3.x = currentX < 100 ? currentX + 1 : ROTATION_MAX;
                vector3.y = currentY < 100 ? currentY + 1 : ROTATION_MIN;
                break;
            case XYZ_ROTATION:
                currentX = cam.position.x;
                currentY = cam.position.y;
                currentZ = cam.position.z;
                vector3.x = currentX < 100 ? currentX + 1 : ROTATION_MAX;
                vector3.y = currentY < 100 ? currentY + 1 : ROTATION_MIN;
                vector3.z = currentZ < 100 ? currentZ + 1 : ROTATION_MAX;
                break;

        }
        return vector3;
    }
}
