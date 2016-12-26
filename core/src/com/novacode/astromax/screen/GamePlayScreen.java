package com.novacode.astromax.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.novacode.astromax.AssetFactory;
import com.novacode.astromax.Asteroid;
import com.novacode.astromax.Astro;
import com.novacode.astromax.AstroMaxGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePlayScreen extends ScreenAdapter {


    private AstroMaxGame game;

    private OrthographicCamera camera;

    private Stage gamePlayStage;

    private Astro astro;

    private Image background;

    private boolean isGameOver = false;

    private List<Asteroid> asteroids = new ArrayList<Asteroid>();

    public GamePlayScreen(AstroMaxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, AstroMaxGame.WIDTH, AstroMaxGame.HEIGHT);
        gamePlayStage = new Stage(new StretchViewport(AstroMaxGame.WIDTH, AstroMaxGame.HEIGHT));

        astro = new Astro();
        astro.setPosition(AstroMaxGame.WIDTH * .5f, AstroMaxGame.HEIGHT * .5f, Align.center);

        background = new Image(AssetFactory.background);

        asteroids = new ArrayList<Asteroid>(5);
        gamePlayStage.addActor(background);
        gamePlayStage.addActor(astro);

        for (int i = 0; i < 5; i++) {
            Asteroid asteroid = new Asteroid()
                    .level(new Random().nextInt(4))
                    .size(Asteroid.AsteroidSize.values()[new Random().nextInt(Asteroid.AsteroidSize.values().length)]);

            asteroid.setPosition(
                    (AstroMaxGame.WIDTH +1 + new Random().nextFloat()),
                    (AstroMaxGame.HEIGHT - asteroid.getHeight()) * new Random().nextFloat(),
                    Align.bottom);
            asteroids.add(asteroid);
            gamePlayStage.addActor(asteroid);
        }
        initInputProcessor();

    }


    private void initInputProcessor() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(!isGameOver) {
                    Vector2 touched = gamePlayStage.getViewport().unproject(new Vector2(screenX, screenY));
                    astro.move(touched.x, touched.y);
                    return true;
                }
                return false;
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

    private boolean checkCollision() {
        for(Actor asteroid : asteroids) {
            if(Math.abs(astro.getX(Align.center) - asteroid.getX(Align.center)) < (astro.getWidth()/3 + asteroid.getWidth()/3)
                    && Math.abs(astro.getY(Align.center) - asteroid.getY(Align.center)) < (astro.getHeight()/3 + asteroid.getHeight()/3)) {
                System.out.println("collision checked = ");
                System.out.println("astro x = " + astro.getX() );
                System.out.println("astro y = " + astro.getY() );
                System.out.println("astroid x = " + asteroid.getX() );
                System.out.println("astroid y = " + asteroid.getY() );
                isGameOver = true;
                astro.freeze();
                return true;

            }
        }
        return false;
    }

    @Override
    public void render(float delta) {
        if(checkCollision()){
            game.setScreen(new MenuScreen(game));
        }

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
