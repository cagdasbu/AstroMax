package com.novacode.astromax;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;

public class AstroMaxGame extends Game {

    public static final int WIDTH = 300;

    public static final int HEIGHT = 480;

    public static final int GROUND_LEVEL = 0;

    public static final int LEFT_BOUND = 0;

    SpriteBatch batch;

    Texture img;

    @Override
    public void create() {
        Assets.load();
        setScreen(new GamePlayScreen(this));
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}
