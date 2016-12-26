package com.novacode.astromax.screen;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.novacode.astromax.AssetFactory;
import com.novacode.astromax.AstroMaxGame;


public class MenuScreen extends ScreenAdapter {


    private AstroMaxGame game;
    private Stage menuStage;

    public MenuScreen(final AstroMaxGame game) {
        this.game = game;
        menuStage = new Stage(new StretchViewport(AstroMaxGame.WIDTH, AstroMaxGame.HEIGHT));

        Image background = new Image(AssetFactory.getInstance().getBackground());

        menuStage.addActor(background);

        Image btnPlay = new Image(AssetFactory.getInstance().getBtnPlay());
        btnPlay.setWidth(AstroMaxGame.BUTTON_WIDTH);
        btnPlay.setHeight(AstroMaxGame.BUTTON_HEIGHT);
        btnPlay.setPosition(AstroMaxGame.WIDTH * .5f, AstroMaxGame.HEIGHT * .5f, Align.center);
        btnPlay.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.setScreen(new GamePlayScreen(game));
                return true;
            }
        });
        menuStage.addActor(btnPlay);


        Gdx.input.setInputProcessor(menuStage);

    }


    @Override
    public void render(float delta) {
        menuStage.act();
        menuStage.draw();
    }
}
