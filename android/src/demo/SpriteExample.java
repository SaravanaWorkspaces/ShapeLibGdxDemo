package demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Comment
 * <p/>
 * SpriteExample.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class SpriteExample implements ApplicationListener {

    private static final String TAG = "SpriteExample";
    private Texture badlogic;
    private SpriteBatch spriteBatch;

    private OrthographicCamera cam;

    private Pixmap badLogicPixmap;

    @Override
    public void create() {
        badLogicPixmap = new Pixmap(Gdx.files.internal("badlogic.jpg"));
        badLogicPixmap.setColor(Color.RED);
        badlogic = new Texture(flipPixmap(badLogicPixmap));

        spriteBatch = new SpriteBatch();

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        cam.update();
        /*Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/

        spriteBatch.begin();
        spriteBatch.draw(badlogic, 20, 20);
        spriteBatch.end();
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

    public Pixmap flipPixmap(Pixmap src) {
        final int width = src.getWidth();
        final int height = src.getHeight();
        Pixmap flipped = new Pixmap(width, height, src.getFormat());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x < 50){
                    flipped.setColor(Color.BLUE);
                    flipped.drawPixel(x, y);
                }else{
                    flipped.drawPixel(x, y, src.getPixel(width - x - 1, y));
                }

            }
        }
        return flipped;
    }
}
