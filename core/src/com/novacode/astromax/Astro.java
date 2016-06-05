package com.novacode.astromax;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
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

    private TextureRegion region;

    private State state;

    public Astro() {
        region = Assets.astro;
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
                actAlive(delta);
                break;
            case DEAD:
                break;
        }

    }

    private void actAlive(float delta) {
        updatePosition(delta);

        if (isAboveCeiling()) {
            setPosition(getX(Align.top), AstroMaxGame.HEIGHT, Align.top);
            velocity.y = 0;
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

        setRotation(velocity.x == 0 && velocity.y == 0 ? 0 :(float)Math.toDegrees(Math.atan2(velocity.y, velocity.x))-90f);

        System.out.println("targetX = [" + targetX + "], targetY = [" + targetY + "]");
        System.out.println("targetX = [" + getX() + "], targetY = [" + getY() + "]");

        System.out.println("velocity = " + velocity);
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
