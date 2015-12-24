package com.mygdx.game.android;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Loading3DScene.java
 * <p/>
 * Comment
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package com.mygdx.game.android
 * @copyright Copyright (C) 2015 Impiger. All rights reserved.
 */
public class Loading3DScene implements ApplicationListener {

    public PerspectiveCamera perspectiveCamera;
    public ModelBatch modelBatch;
    public AssetManager assetManager;

    private Array<ModelInstance> modelInstances;

    private Environment environment;

    private ModelInstance shipModelInstance;
    private ModelInstance spaceModelInstance;

    private Array<ModelInstance> invadersModelInstance;
    private Array<ModelInstance> blocksModelInstance;


    private boolean loading = false;

    @Override
    public void create() {

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(0f, 7f, 10f);
        perspectiveCamera.lookAt(0, 0, 0);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();

        modelInstances = new Array<ModelInstance>();

        invadersModelInstance = new Array<ModelInstance>();
        blocksModelInstance = new Array<ModelInstance>();

        assetManager = new AssetManager();
        assetManager.load("test/squreup.obj", Model.class);
        assetManager.load("data/spacesphere.obj", Model.class);
        assetManager.load("data/invader.obj", Model.class);
        assetManager.load("data/block.obj", Model.class);

        //assetManager.load("data/invaderscene.g3db", Model.class);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        if (!loading && assetManager.update())
            doneLoading();
        perspectiveCamera.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(perspectiveCamera);
        modelBatch.render(modelInstances, environment);
       if (spaceModelInstance != null)
            modelBatch.render(spaceModelInstance);
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

    }

    private void doneLoading() {
        shipModelInstance = new ModelInstance(assetManager.get("test/squreup.obj", Model.class));
        shipModelInstance.transform.setToRotation(Vector3.Y, 180).trn(0, 0, 6f);
        modelInstances.add(shipModelInstance);

        Model blockModel = assetManager.get("data/block.obj", Model.class);
        for (float x = -5f; x <= 5f; x += 2f) {
            ModelInstance block = new ModelInstance(blockModel);
            block.transform.setToTranslation(x, 0, 3f);
            modelInstances.add(block);
            blocksModelInstance.add(block);
        }

        Model invaderModel = assetManager.get("data/invader.obj", Model.class);
        for (float x = -5f; x <= 5f; x += 2f) {
            for (float z = -8f; z <= 0f; z += 2f) {
                ModelInstance invader = new ModelInstance(invaderModel);
                invader.transform.setToTranslation(x, 0, z);
                modelInstances.add(invader);
                invadersModelInstance.add(invader);
            }
        }

        spaceModelInstance = new ModelInstance(assetManager.get("data/spacesphere.obj", Model.class));

        loading = false;
    }
}
