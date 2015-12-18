package demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * GameScreen.java
 * <p/>
 * Comment
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @category Impiger
 * @package demo
 * @copyright Copyright (C) 2014 Impiger. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
public class GameScreen extends ScreenAdapter {
    private SpriteBatch batch;

    private static final float MOVE_TIME = 1F;
    private float timer = MOVE_TIME;

    private static final int SNAKE_MOVEMENT = 32;
    private int snakeX = 0, snakeY = 0;

    private Texture snakeHead;

    @Override
    public void show() {
        batch = new SpriteBatch();

        snakeHead = new Texture(Gdx.files.internal("snakehead.png"));
    }

    @Override
    public void render(float delta) {
        timer -= delta;
        if (timer <= 0) {
            timer = MOVE_TIME;
            snakeX += SNAKE_MOVEMENT;
        }

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(snakeHead, snakeX, snakeY);
        batch.end();

    }
}
