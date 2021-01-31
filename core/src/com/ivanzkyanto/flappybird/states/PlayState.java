package com.ivanzkyanto.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ivanzkyanto.flappybird.MyGame;
import com.ivanzkyanto.flappybird.sprites.Bird;
import com.ivanzkyanto.flappybird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int TUBE_X_START = 240;
    private static final int GROUND_X_START = -100;
    private static final int GROUND_Y_OFFSET = -55;
    private static final int BG_X_START_OFFSET = -100;

    private Bird bird;
    private Texture bg;
    private Vector2 bgPos1, bgPos2;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(40, 200);
        cam.setToOrtho(false, MyGame.WIDTH / 2f, MyGame.HEIGHT / 2f);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        tubes = new Array<>();

        bgPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2) + BG_X_START_OFFSET, 0);
        bgPos2 = new Vector2(bgPos1.x + bg.getWidth(), bgPos1.y);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2 + GROUND_X_START, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(groundPos1.x + ground.getWidth(), groundPos1.y);

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + TUBE_X_START));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateBg();
        updateGround();
        bird.update(dt, cam.viewportHeight);
        cam.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())) gsm.set(new PlayState(gsm));
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) gsm.set(new PlayState(gsm));

        bgPos1.add(1, 0);
        bgPos2.add(1, 0);

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, bgPos1.x, bgPos1.y);
        sb.draw(bg, bgPos2.x, bgPos2.y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosBotTube().y);
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();

//        debug();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube tube : tubes) tube.dispose();
        System.out.println("Play state disposed");
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) groundPos1.add(ground.getWidth() * 2, 0);
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) groundPos2.add(ground.getWidth() * 2, 0);
    }

    private void updateBg() {
        if (cam.position.x - (cam.viewportWidth / 2) > bgPos1.x + bg.getWidth()) bgPos1.add(bg.getWidth() * 2, 0);
        if (cam.position.x - (cam.viewportWidth / 2) > bgPos2.x + bg.getWidth()) bgPos2.add(bg.getWidth() * 2, 0);
    }
//    private void debug() {
//        System.out.println("camPositionX : " + cam.position.x);
//    }
}
