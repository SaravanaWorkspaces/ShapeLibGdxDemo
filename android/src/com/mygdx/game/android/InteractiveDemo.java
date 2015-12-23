package com.mygdx.game.android;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import shape.Shapes;

public class InteractiveDemo implements ApplicationListener {

    private static final String TAG = "InteractiveDemo";
    PerspectiveCamera cam;
    ModelBatch modelBatch;

    Array<ModelInstance> instances;

    Environment environment;

    ModelInstance groundModelInstance;
    ModelInstance ballModelInstance;
    ModelInstance cylinderModelInstance;

    Model groundModel;

    Model ballModel;

    Model cylinderModel;

    int iterator = 0;

    @Override
    public void create() {
        modelBatch = new ModelBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(3f, 7f, 10f);
        cam.lookAt(0f, 4f, 0f);
        cam.update();

        instances = new Array<ModelInstance>();

        groundModel = Shapes.drawShapes(Shapes.GROUND);
        groundModelInstance = new ModelInstance(groundModel, "ground");
        groundModelInstance.transform.setToTranslation(0f, 0f, 0f);

        ballModel = Shapes.drawShapes(Shapes.SPHERE);
        ballModelInstance = new ModelInstance(ballModel, "ball");
        ballModelInstance.transform.setToTranslation(0f, 9f, 0f);

        cylinderModel = Shapes.drawShapes(Shapes.CYLINDER);
        cylinderModelInstance = new ModelInstance(cylinderModel, "cylinder");
        cylinderModelInstance.transform.setTranslation(0f, 7f, 0f);


        instances = new Array<ModelInstance>();
        instances.add(groundModelInstance);


    }

    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        iterator++;

        cam.rotate(new Vector3(20,0,0), 0);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    private int getState() {

        return 0;
    }
}
