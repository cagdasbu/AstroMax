package com.novacode.astromax;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public class AssetFactory {

    private static AssetFactory instance;


    //disposable
    public static TextureAtlas atlas;

    public static SpriteBatch spriteBatch;


    //non disposable
    public static TextureRegion astro;
    public static TextureRegion astroMoving;
    public static TextureRegion background;
    private static TextureRegion btnPlay;

    public static List<TextureRegion> asteroids = new ArrayList<TextureRegion>();


    public static AssetFactory getInstance() {
        return instance == null ? new AssetFactory() : instance;
    }


    public static void load() {
        atlas = new TextureAtlas("pack.atlas");
        spriteBatch = new SpriteBatch();
        astro = atlas.findRegion("astro-stay");
        astroMoving = atlas.findRegion("astro-moving");
        background = atlas.findRegion("bck-1");
        asteroids.add(atlas.findRegion("asteroid"));
        asteroids.add(atlas.findRegion("asteroid-1"));

        //menu button graphics
        btnPlay = atlas.findRegion("btnPlay");
    }


    public static void dispose() {
        if (atlas!=null){
            atlas.dispose();
        }
        if(spriteBatch != null){
        spriteBatch.dispose();
        }
    }

    public static TextureRegion getBackground() {
        return background;
    }

    public static void setBackground(TextureRegion background) {
        AssetFactory.background = background;
    }

    public static TextureRegion getBtnPlay() {
        return btnPlay;
    }
}
