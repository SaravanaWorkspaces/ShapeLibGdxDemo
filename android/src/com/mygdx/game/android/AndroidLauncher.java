package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import demo.ArcCreation;
import demo.Basic3DModel;
import demo.ChartImplementation;
import demo.ChartPiece;
import demo.MeshColorTexture;
import demo.ModelClassExploring;
import demo.ShaderLesson1;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(new ChartPiece(), config);
    }
}
