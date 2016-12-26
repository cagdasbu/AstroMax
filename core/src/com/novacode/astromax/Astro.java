package com.novacode.astromax;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Logger;

/**
 * Created by cagdasbu on 03/06/2016.
 */
public class Astro extends Actor {

    Logger logger = new Logger("ASTRO");

    public static int WIDTH = 32;

    public static int HEIGHT = 32;

    public static float GRAVITY = 0f;

    public static float JUMP_VELOCITY = 300f;

    private enum State {ALIVE, DEAD}


    private Vector2 velocity;

    private Vector2 acceleration;

    private float ACC_FACTOR = -.1f;

    private TextureRegion region;

    private State state;

    public Astro() {
        region = AssetFactory.astro;
        setWidth(WIDTH);
        setHeight(HEIGHT);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        state = State.ALIVE;
        setOrigin(Align.center);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        switch (state) {
            case ALIVE:
                actAlive(ACC_FACTOR);
                break;
            case DEAD:
                break;
        }

    }

    private void actAlive(float delta) {
        accelerate(delta);

        if (isAboveCeiling()) {
            setPosition(getX(Align.top), AstroMaxGame.HEIGHT, Align.top);
            //velocity.y = 0;
            //state = State.DEAD;
        }

        if (isBelowGround()) {
            setY(AstroMaxGame.GROUND_LEVEL);
            // setPosition(getX(), AstroMaxGame.GROUND_LEVEL, Align.bottom);
            //  state = State.DEAD;
            velocity.y = 0;
        }
        if (isRighter()) {
            setPosition(AstroMaxGame.WIDTH,getY(Align.right), Align.right);
            velocity.x = 0;
            //  state = State.DEAD;
        }
        if (isLefter()) {
            setX(AstroMaxGame.LEFT_BOUND);
            velocity.x = 0;
            //  state = State.DEAD;
        }
        updatePosition();
    }


    public void freeze() {
        velocity.y = 0;
        velocity.x = 0;
        state = State.DEAD;
    }

    public void move(float targetX, float targetY) {
        velocity.y=5;
        updatePosition();
    }

    private boolean isBelowGround() {
        return getY(Align.bottom) <= AstroMaxGame.GROUND_LEVEL;
    }

    private boolean isAboveCeiling() {
        return getY(Align.top) >= AstroMaxGame.HEIGHT;
    }

    private boolean isRighter() {
        return getX(Align.right) >= AstroMaxGame.WIDTH;

    }

    private boolean isLefter() {
        return getX(Align.left) <= AstroMaxGame.LEFT_BOUND;

    }

    private void updatePosition() {
        setY(getY() + velocity.y);
    }

    private void accelerate(float delta) {
        velocity.add( 0, delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
