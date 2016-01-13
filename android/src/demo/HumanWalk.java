package demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Used texture regions to render the
 * <p/>
 * HumanWalk.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class HumanWalk extends ApplicationAdapter {

    Texture texture;
    SpriteBatch spriteBatch;

    int SPACE = 10;
    int OBJECT_SIZE = 75;


    TextureRegion[] textureRegion = new TextureRegion[6];
    int renderCount = 0;
    int nextImage = 0;

    @Override
    public void create() {

        spriteBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("hum.png"));

        textureRegion[0] = new TextureRegion(texture, 0, 0, OBJECT_SIZE, texture.getHeight());
        textureRegion[1] = new TextureRegion(texture, textureRegion[0].getRegionX() + OBJECT_SIZE, 0, OBJECT_SIZE, texture.getHeight());
        textureRegion[2] = new TextureRegion(texture, textureRegion[1].getRegionX() + OBJECT_SIZE, 0, OBJECT_SIZE, texture.getHeight());
        textureRegion[3] = new TextureRegion(texture, textureRegion[2].getRegionX() + OBJECT_SIZE, 0, OBJECT_SIZE, texture.getHeight());
        textureRegion[4] = new TextureRegion(texture, textureRegion[3].getRegionX() + OBJECT_SIZE, 0, OBJECT_SIZE, texture.getHeight());
        textureRegion[5] = new TextureRegion(texture, textureRegion[4].getRegionX() + OBJECT_SIZE, 0, OBJECT_SIZE, texture.getHeight());
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        if (renderCount % 30 == 0) {
            if (nextImage < 5) {
                nextImage++;
                spriteBatch.draw(textureRegion[nextImage], textureRegion[nextImage].getRegionX(), 0);
            } else
                nextImage = 0;
        }
        renderCount++;


        spriteBatch.end();
    }
}
