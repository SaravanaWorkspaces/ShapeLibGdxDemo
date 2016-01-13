package demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import javax.microedition.khronos.opengles.GL10;

/**
 * This renders 2D texture on the screen. And works to interpolate action.
 * <p/>
 * Scene2DSample.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class Scene2DSample extends ApplicationAdapter {

    Stage stage;

    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        MyActor myActor = new MyActor();

        MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(100, 0);
        action.setDuration(2f);
        action.setInterpolation(Interpolation.linear);
        myActor.addAction(action);
        stage.addActor(myActor);


    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));

        public MyActor() {
            setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(texture, this.getX(), getY());
        }
    }
}

