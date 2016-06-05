package com.novacode.astromax;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
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
        region = new TextureRegion(Assets.asteroids.get(new Random().nextInt(2)));
        rotation = Rotation.values()[new Random().nextInt(1)];
        level(new Random().nextInt(5));
        velocity = new Vector2(0, -1 * new Random().nextInt(20));
        setX((getX() + 30) % AstroMaxGame.WIDTH);
    }



    public Asteroid rotation(Rotation rotation) {
        this.rotation = rotation;
        return this;
    }
    public Asteroid level(int level) {
        velocity = new Vector2(0, -10 - (10 * level));
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
            setY(AstroMaxGame.HEIGHT);
            initAndRandomize();
        } else {
            //todo dispose or relocate
        }
    }



    public void move(float targetX, float targetY) {

        velocity.add(Math.abs(targetX - getX()) > 20 ? targetX > getX() ? 10 : -10 : 0 ,
                Math.abs(targetY - getY()) > 20 ? targetY > getY() ?  10 : - 10 : 0);

        if(Math.abs(velocity.x) > 50 || Math.abs(velocity.y) > 50) {
            region = Assets.astroMoving;
            this.setWidth(64);
            this.setHeight(64);
        } else {
            region = Assets.astro;
            this.setWidth(32);
            this.setHeight(32);
        }


        System.out.println("targetX = [" + targetX + "], targetY = [" + targetY + "]");
        System.out.println("targetX = [" + getX() + "], targetY = [" + getY() + "]");

        System.out.println("velocity = " + velocity);
    }


    private boolean isOffScreen() {
        return getY(Align.top) <= AstroMaxGame.GROUND_LEVEL;
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
