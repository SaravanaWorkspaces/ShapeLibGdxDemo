package demo;

import com.badlogic.gdx.Game;

/**
 * SnakeGame.java
 * <p/>
 * Comment
 *
 * @author saravanakumar.chinra
 * @version 1.0
 * @category Impiger
 * @package com.mygdx.game.android
 * @copyright Copyright (C) 2014 Impiger. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */
public class SnakeGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
