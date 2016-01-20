package demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import controller.CameraController;
import utils.ColorUtils;

/**
 * This handles the class to render the objects on the screen.
 * 1. Changing the model material color By {@link ModelHandling3D#changeObjectColor()}
 * </p>
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2015 Impiger. All rights reserved.
 */
public class ModelHandling3D extends Game implements ApplicationListener, InputProcessor {

    private static final String TAG = "ModelHandling3D";

    private static final String inputFile = "test/boxanim.g3dj";
    private final int COLOR = 0;
    private final int POSITION = 1;
    private final int LIGHTING = 3;
    private final int SCALING = 4;
    private final int CAMERA = 5;

    /* 3D camera */
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public Environment environment;
    public ModelInstance modelInstance;

    /* Custom controller to handle the camera events */
    CameraController cameraController;
    private int colorIterator = 0;
    private int colorArraySize = 0;

    /* Text rendering on the screen*/
    private SpriteBatch batch;
    private BitmapFont font;
    private String selectedOperation = "";

    @Override
    public void create() {
        modelBatch = new ModelBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.Diffuse, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0f, 0f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cameraController = new CameraController(cam);

        assets = new AssetManager();
        assets.load(inputFile, Model.class);

        colorArraySize = ColorUtils.getColorArraySize();

        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);


    }

    @Override
    public void render() {

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (assets.update()) {
            performAction(CAMERA);
        }

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();

        batch.begin();
        font.draw(batch, "Drag To Perform actions " + selectedOperation, 10, Gdx.graphics.getHeight() - 50);
        batch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        instances.clear();
        assets.dispose();
    }

    @Override
    public void resume() {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
    }

    /**
     * This method initiates the operation by the hardcode input.
     *
     * @param type action type.
     *             <ol>
     *             <li>Change Color</li>
     *             <li>Change Position</li>
     *             <li>Change Lighting</li>
     *             <li>Change Scaling</li>
     *             <li>Change Camera Movements</li>
     *             </ol>
     */
    private void performAction(int type) {
        switch (type) {
            case COLOR:
                selectedOperation = "Change The color";
                changeObjectColor();
                break;
            case POSITION:
                selectedOperation = "Change The Position";
                changeObjectPosition();
                break;
            case LIGHTING:
                selectedOperation = "Lighting";
                renderObject();
                break;
            case SCALING:
                selectedOperation = "Scaling the view";
                break;
            case CAMERA:
                selectedOperation = "Moving Camera Up/Down";
                renderObject();
                cameraController.updateCamera();
                break;
        }
    }

    /**
     * This method just renders the 3D object on the screen.
     */
    private void renderObject() {
        instances = new Array<ModelInstance>();
        Model model = assets.get(inputFile, Model.class);

        modelInstance = new ModelInstance(model);

        instances.add(modelInstance);
    }

    /**
     * Changing position by vector
     */
    private void changeObjectPosition() {
        Model model = assets.get(inputFile, Model.class);
        modelInstance = new ModelInstance(model, new Vector3(colorIterator, 0, 0));
        instances = new Array<ModelInstance>();
        instances.add(modelInstance);
    }

    /**
     * Changing Color of Object By tapping On the Screen.
     */
    private void changeObjectColor() {
        Model model = assets.get(inputFile, Model.class);
        modelInstance = new ModelInstance(model);
        modelInstance.materials.get(0).set(ColorAttribute.
                createDiffuse(ColorUtils.color[colorIterator]));
        instances = new Array<ModelInstance>();
        instances.add(modelInstance);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cameraController.changeCameraRotation();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (colorIterator < colorArraySize - 1)
            colorIterator++;
        else
            colorIterator = 0;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}