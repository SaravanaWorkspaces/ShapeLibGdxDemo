package demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * This helps to render group and performs operations.
 * <p/>
 * Scene2DSampleGroup.java
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @company Impiger
 * @package demo
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class Scene2DSampleGroup extends ApplicationAdapter {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;


    private Stage stage;
    private Group group;


    @Override
    public void create() {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));

        group = new Group();
        stage.addActor(group);



        Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        Image image1 = new Image(texture);
        Image image2 = new Image(texture);

        image1.setColor(new Color(1, 0, 0, 1));
        image2.setColor(new Color(0, 0, 1, 1));

        group.addActor(image1);
        group.addActor(image2);

        // Images are positioned relative to the group...
        image1.setPosition(0, 0);
        image2.setPosition(texture.getWidth() / 2, 0);

        // Group is positioned relative to the stage...
        group.setPosition(WIDTH / 2 - image1.getWidth() / 2,
                HEIGHT / 2 - image1.getHeight() / 2);


        group.setOrigin(image1.getWidth() / 2, image1.getHeight() / 2);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if (Gdx.input.isTouched()) {
            group.rotateBy(90);
        }
    }
}
