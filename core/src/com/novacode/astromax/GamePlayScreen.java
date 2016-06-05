package com.novacode.astromax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePlayScreen extends ScreenAdapter {


    private AstroMaxGame astroMaxGame;

    private OrthographicCamera camera;

    private Stage gamePlayStage;

    private Astro astro;

    private Image background;

    private List<Asteroid> asteroids = new ArrayList<Asteroid>();

    public GamePlayScreen(AstroMaxGame game) {
        this.astroMaxGame = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, AstroMaxGame.WIDTH, AstroMaxGame.HEIGHT);
        gamePlayStage = new Stage(new StretchViewport(AstroMaxGame.WIDTH, AstroMaxGame.HEIGHT));

        astro = new Astro();
        astro.setPosition(AstroMaxGame.WIDTH * .5f, AstroMaxGame.HEIGHT * .5f, Align.center);

        background = new Image(Assets.background);

        asteroids = new ArrayList<Asteroid>(5);
        gamePlayStage.addActor(background);
        gamePlayStage.addActor(astro);

        for (int i = 0; i < 5; i++) {
            Asteroid asteroid = new Asteroid()
                    .level(new Random().nextInt(4))
                    .size(Asteroid.AsteroidSize.values()[new Random().nextInt(Asteroid.AsteroidSize.values().length)]);

            asteroid.setPosition((AstroMaxGame.WIDTH - asteroid.getWidth()) * new Random().nextFloat(),
                    AstroMaxGame.HEIGHT + (1 + new Random().nextFloat()),
                    Align.left);
            asteroids.add(asteroid);
            gamePlayStage.addActor(asteroid);
        }
        initInputProcessor();

    }


    private void initInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                System.out.println("screenX = [" + screenX + "], screenY = [" + screenY + "], pointer = [" + pointer + "], button = [" + button + "]");
                Vector2 touched = gamePlayStage.getViewport().unproject(new Vector2(screenX, screenY));
                System.out.println("touched = " + touched);
                System.out.println(gamePlayStage.getWidth());
                System.out.println(gamePlayStage.getHeight());
                astro.move(touched.x, touched.y);
                System.out.println("---------------");

                return true;
            }


            @Override
            public boolean keyTyped(char character) {

                switch (character) {
                    case 'w':
                        // astro.moveBy(0,20f);
                        astro.move(astro.getX(), astro.getY() + 20f);
                        break;
                    case 'd':
                        //astro.moveBy(20f,0);
                        astro.move(astro.getX() + 20f, astro.getY());
                        break;
                    case 's':
                        //astro.moveBy(0,-20f);
                        astro.move(astro.getX(), astro.getY() - 20f);
                        break;
                    case 'a':
                        //astro.moveBy(-20f,0);
                        astro.move(astro.getX() - 20f, astro.getY());
                        break;
                }
                return true;
            }
        });

    }

    private void checkCollision() {

    }

    @Override
    public void render(float delta) {
        checkCollision();
        gamePlayStage.act();
        gamePlayStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }


    @Override
    public void dispose() {
        gamePlayStage.dispose();
    }
}
