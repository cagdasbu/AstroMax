package com.novacode.astromax;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cagdasbu on 03/06/2016.
 */
public class Assets {

    //disposable
    public static TextureAtlas atlas;

    public static SpriteBatch spriteBatch;


    //non disposable
    public static TextureRegion astro;
    public static TextureRegion astroMoving;
    public static TextureRegion background;
    public static List<TextureRegion> asteroids = new ArrayList<TextureRegion>();



    public static void load() {
        atlas = new TextureAtlas("pack.atlas");
        spriteBatch = new SpriteBatch();
        astro = atlas.findRegion("astro-stay");
        astroMoving = atlas.findRegion("astro-moving");
        background = atlas.findRegion("bck-1");
        asteroids.add(atlas.findRegion("asteroid"));
        asteroids.add(atlas.findRegion("asteroid-1"));
    }


    public static void dispose() {
        if (atlas!=null){
            atlas.dispose();
        }
        if(spriteBatch != null){
        spriteBatch.dispose();
        }
    }



}
