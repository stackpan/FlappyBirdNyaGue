package com.ivanzkyanto.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector2 position;
    private Vector2 velocity; // Velocity = kecepatan
    private Texture bird;

    public Bird(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0); // Kecepatan awal
        bird = new Texture("bird.png");
    }

    public void update(float dt, float camViewportHeight) {
        if (position.y > 0) velocity.add(0, GRAVITY);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y);
        if (position.y > (camViewportHeight - bird.getHeight())) position.y = (camViewportHeight - bird.getHeight());
        if (position.y < 0) position.y = 0;
        velocity.scl(1/dt);
    }

    public void jump() {
        velocity.y = 280;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }
}
