package com.novacode.astromax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.novacode.astromax.screen.GamePlayScreen;
import com.novacode.astromax.screen.MenuScreen;

public class AstroMaxGame extends Game {

    public static final int WIDTH = 300;

    public static final int HEIGHT = 480;

    public static final int GROUND_LEVEL = 0;

    public static final int LEFT_BOUND = 0;

    public static final float BUTTON_HEIGHT = AstroMaxGame.HEIGHT/12;

    public static final float BUTTON_WIDTH = AstroMaxGame.WIDTH * .8f;

    public static final float COLLISION_SENSITIVITY = 30;

    SpriteBatch batch;

    Texture img;

    @Override
    public void create() {
        AssetFactory.load();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        AssetFactory.dispose();
    }
}
