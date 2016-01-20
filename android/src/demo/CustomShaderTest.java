package demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Comment
 * <p/>
 * CustomShaderTest.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class CustomShaderTest implements Shader {
    ShaderProgram shaderProgram;

    Camera camera;
    RenderContext context;

    @Override
    public void init() {
        String vert = Gdx.files.internal("glsl/test.vertex.glsl").readString();
        String frag = Gdx.files.internal("glsl/test.fragment.glsl").readString();
        shaderProgram = new ShaderProgram(vert, frag);
        if (!shaderProgram.isCompiled())
            throw new GdxRuntimeException(shaderProgram.getLog());

    }

    @Override
    public int compareTo(Shader other) {
        return 0;
    }

    @Override
    public boolean canRender(Renderable instance) {
        return false;
    }

    @Override
    public void begin(Camera camera, RenderContext context) {
        this.camera = camera;
        this.context = context;
        shaderProgram.begin();
        shaderProgram.setUniformMatrix("u_projViewTrans", camera.combined);
    }

    @Override
    public void render(Renderable renderable) {
        shaderProgram.setUniformMatrix("u_worldTrans", renderable.worldTransform);
        renderable.meshPart.render(shaderProgram);
    }

    @Override
    public void end() {

    }

    @Override
    public void dispose() {

    }
}
