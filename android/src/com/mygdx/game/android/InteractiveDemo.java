package com.mygdx.game.android;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.Array;

import shape.Shapes;

public class InteractiveDemo implements ApplicationListener {

    PerspectiveCamera cam;
    ModelBatch modelBatch;

    Array<ModelInstance> instances;

    Environment environment;

    ModelInstance ground;
    ModelInstance ball;

    boolean collision;

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

        ground = new ModelInstance(Shapes.drawShapes(Shapes.GROUND), "ground");

        ball = new ModelInstance(Shapes.drawShapes(Shapes.SPHERE), "ball");
        ball.transform.setToTranslation(0, 9f, 0);

        instances = new Array<ModelInstance>();
        instances.add(ground);
        instances.add(ball);

    }


    public void render() {

        final float delta = Math.min(1f/30f, Gdx.graphics.getDeltaTime());

        if (!collision) {
            ball.transform.translate(0f, -delta, 0f);
            //collision = checkCollision();
        }

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }
    boolean checkCollision() {
        return false;
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
}
