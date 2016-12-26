package com.novacode.astromax;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

import java.util.Random;

/**
 * Created by cagdasbu on 03/06/2016.
 */
public class Asteroid extends Actor {

    public static int WIDTH = 32;

    public static int HEIGHT = 32;

    public static float GRAVITY = 0f;

    private Vector2 velocity;

    private Vector2 acceleration;

    private TextureRegion region;

    public enum Rotation {Clockwise, CounterClockwise} ;

    public enum AsteroidSize {Small, Medium, Large}

    private Rotation rotation = Rotation.Clockwise;


    public Asteroid() {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        acceleration = new Vector2(0, 0);
        setOrigin(Align.center);
        initAndRandomize();
    }


    private void initAndRandomize() {
        region = new TextureRegion(AssetFactory.asteroids.get(new Random().nextInt(2)));
        rotation = Rotation.values()[new Random().nextInt(1)];
        level(new Random().nextInt(5));
        velocity = new Vector2( -1 * new Random().nextInt(20), 0);
        setY((getY() + 30) % AstroMaxGame.HEIGHT);
    }



    public Asteroid rotation(Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    public Asteroid level(int level) {
        velocity = new Vector2( -10 - (10 * level), 0);
        return this;
    }

    public Asteroid size(AsteroidSize size) {
        if (size == null) {

            switch (size) {
                case Small:
                    setWidth(16);
                    setHeight(16);
                    break;
                case Medium:
                    break;
                case Large:
                    setWidth(48);
                    setHeight(48);
                    break;

            }
        }
        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setRotation(rotation == Rotation.Clockwise ? getRotation()+new Random().nextInt(2) : getRotation() - new Random().nextInt(2));

        actAlive(delta);

    }

    private void actAlive(float delta) {
        accelerate(delta);
        updatePosition(delta);


        if (isOffScreen()) {
            setX(AstroMaxGame.WIDTH);
            initAndRandomize();
        } else {
            //todo dispose or relocate
        }
    }

    private boolean isOffScreen() {
        return getX(Align.right) <= AstroMaxGame.LEFT_BOUND;
    }


    private void updatePosition(float delta) {
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
    }

    private void accelerate(float delta) {
        velocity.add(acceleration.x * delta, acceleration.y * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
