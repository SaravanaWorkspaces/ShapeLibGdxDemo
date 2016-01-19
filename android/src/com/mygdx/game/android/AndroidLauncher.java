package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import demo.Basic3DModel;
import demo.HumanWalk;
import demo.Loading3DScene;
import demo.MeshColorTexture;
import demo.ModelHandling3D;
import demo.Scene2DSampleGroup;
import demo.SnakeGame;
import demo.SpriteExample;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(new Basic3DModel(), config);
    }
}
