package com.ivanzkyanto.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 120; // Jarak yang bisa dirandom
    private static final int TUBE_GAP = 80; // Jarak celah antara Tube atas dgn Tube bawah
    private static final int LOWEST_OPENING = 40;
    private Texture topTube, botTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottube.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
    }


    public static int getFLUCTUATION() {
        return FLUCTUATION;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - botTube.getHeight());
    }
}