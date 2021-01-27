package com.ivanzkyanto.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ivanzkyanto.flappybird.MyGame;
import com.ivanzkyanto.flappybird.sprites.Bird;

public class PlayState extends State {

    private Bird bird;
    private Texture bg;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 200);
        cam.setToOrtho(false, MyGame.WIDTH / 2, MyGame.HEIGHT / 2);
        bg = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt, cam.viewportHeight);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
