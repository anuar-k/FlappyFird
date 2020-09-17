package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    Score score;
    SpriteBatch batch;
    Texture img;
    Background bg;
    Bird bird;
    Obstackles obstackles;
    boolean gameOver;
    Texture restartTexture;
    int count = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Background();
        bird = new Bird();
        score = new Score();
        obstackles = new Obstackles();
        gameOver = false;
        restartTexture = new Texture("RestartBtn.png");
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bg.render(batch);
        obstackles.render(batch);
        score.render(batch);
        if (!gameOver) {
            bird.render(batch);
        } else {
            batch.draw(restartTexture, 200, 200);
        }
        batch.end();
    }

    public void update() {
        bg.update();
        bird.update();
        obstackles.update();
        score.update(count);

        for (int i = 0; i < Obstackles.obs.length; i++) {
            if (bird.position.x > Obstackles.obs[i].position.x && bird.position.x < Obstackles.obs[i].position.x + 50) {
                if (!Obstackles.obs[i].emptySpace.contains(bird.position)) {
                    gameOver = true;
                }
                count++;
            }
        }

        if (bird.position.y < 0 || bird.position.y > 600) {
            gameOver = true;
            count = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && gameOver) {
            recreate();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
    public void recreate() {
        bird.recreate();
        obstackles.recreate();
        score.recreate();
        gameOver = false;
    }
}