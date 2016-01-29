package demo;

import android.graphics.Point;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector3;

import javax.microedition.khronos.opengles.GL10;

/**
 * Just rendered simple rect to make circle (pending)
 * <p/>
 * ImmediateMode.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class ImmediateMode implements ApplicationListener {

    PerspectiveCamera cam;
    float orbitSpeed = 5.0f;
    ImmediateModeRenderer20 renderer;


    @Override
    public void create() {
        cam = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        renderer = new ImmediateModeRenderer20(false, false, 0);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        cam.position.set(new Vector3(0, 0,9));
        cam.lookAt(0, 0, 0);
        cam.near = 1;
        cam.far = 300;

        cam.update();
        renderer.begin(cam.combined, GL20.GL_TRIANGLES);
        drawTris();
        renderer.end();
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

    public void drawTris() {
        tri( 0, 0, 1);
        tri( 1, 0, 1);
        tri( 0.966f, 0.259f, 1);

    }

    void tri(float x, float y, float z) {
        renderer.vertex(x, y, z);
    }

    private void setColor(Color color){
        renderer.color(color);
    }
}
