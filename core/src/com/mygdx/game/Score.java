package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

public class Score {
    int score = 0;
    private Texture textureNum;
    private Vector2 posNum;

    public Score() {
        textureNum = new Texture("0z.png");
        posNum = new Vector2(100, 100);
    }

    public void render(SpriteBatch batch) {
        ArrayList<Texture> txt = getTexture(score);
        int secondNumberPos = (int) posNum.x;

        if (txt.size() == 1) {
            batch.draw(txt.get(0), posNum.x, posNum.y);
        }
        if (txt.size() > 1) {
            for (int j = 0; j < txt.size(); j++) {
                batch.draw(txt.get(j), secondNumberPos, posNum.y);
                secondNumberPos += 50;
            }
        }
    }

    public void update(int score) {
        this.score = score / 24;
    }

    public ArrayList<Texture> getTexture(int scr) {
        String s = Integer.toString(scr);
        String[] buffer = s.split("");
        ArrayList<String> number = new ArrayList<>(Arrays.asList(buffer));
        ArrayList<Texture> textures = new ArrayList<>(buffer.length);

        for (int i = 0; i < number.size(); i++) {
            String s1 = number.get(i) + "z.png";
            textures.add(new Texture(s1));
        }
        return textures;
    }

    public void recreate() {
        score = 0;
    }
}