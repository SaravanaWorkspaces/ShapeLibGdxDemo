package demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.math.Vector3;

import javax.microedition.khronos.opengles.GL10;

/**
 * The class helps to demonsterate the simple shading on the sphere.
 * <p/>
 * ShaderTest.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class ShaderTest implements ApplicationListener {

    PerspectiveCamera perspectiveCamera;

    /* ModelBuilder */
    ModelBuilder modelBuilder;
    /* Model */
    Model model;

    /* Renderable */
    Renderable renderable;
    /* RenderContext */
    RenderContext renderContext;
    /* Shader */
    CustomShaderTest shader;


    @Override
    public void create() {

        perspectiveCamera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(new Vector3(2f, 2f, 2f));
        perspectiveCamera.lookAt(new Vector3(0, 0, 0));
        perspectiveCamera.near = 1;
        perspectiveCamera.far = 300;
        perspectiveCamera.update();

        modelBuilder = new ModelBuilder();

        model = modelBuilder.createSphere(2f, 2f, 2f, 20, 20, new Material(),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);

        NodePart blockPart = model.nodes.get(0).parts.get(0);

        renderable = new Renderable();
        blockPart.setRenderable(renderable);
        renderable.environment = null;
        renderable.worldTransform.idt();

        renderContext = new RenderContext(new DefaultTextureBinder(DefaultTextureBinder.WEIGHTED, 1));
        shader = new CustomShaderTest();
        shader.init();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        renderContext.begin();
        shader.begin(perspectiveCamera, renderContext);
        shader.render(renderable);
        shader.end();
        renderContext.end();

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
}
