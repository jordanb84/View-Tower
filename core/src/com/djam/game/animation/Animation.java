package com.djam.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;

import java.util.ArrayList;
import java.util.List;

public class Animation {

    private List<Frame> frames = new ArrayList<Frame>();

    private float frameDuration;

    private int frameIndex;

    private float frameElapsed;

    private boolean complete;

    public Animation(float frameDuration, String ... frames) {
        this.frameDuration = frameDuration;

        for(String frame : frames) {
            this.frames.add(new Frame(new Sprite(Assets.getInstance().getTexture(frame))));
        }
    }

    public Animation(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera, Vector2 position) {
        this.getCurrentFrame().render(batch, position);
    }

    public void render(SpriteBatch batch, OrthographicCamera camera, Vector2 position, float alpha) {
        this.getCurrentFrame().render(batch, position, alpha);
    }

    public void update(OrthographicCamera camera) {
        this.frameElapsed += 1 * Gdx.graphics.getDeltaTime();

        if(this.frameElapsed >= this.frameDuration) {
            this.nextFrame();
        }

        //System.out.println("Frame " + this.frameIndex + " at " + this.frameElapsed + "/" + this.frameDuration);
    }

    public void nextFrame() {
        this.frameElapsed = 0;

        this.frameIndex++;

        if(this.frameIndex >= this.frames.size()) {
            this.complete = true;
            this.frameIndex = 0;
        }
    }

    public Frame getCurrentFrame() {
        return this.frames.get(this.frameIndex);
    }

    public void addFrame(String path) {
        this.frames.add(new Frame(new Sprite(Assets.getInstance().getTexture(path))));
    }

    public void addFrames(String ... paths) {
        for(String path : paths) {
            this.frames.add(new Frame(new Sprite(Assets.getInstance().getTexture(path))));
        }
    }

    public void addFrames(String prefix, String ... paths) {
        for(String path : paths) {
            this.frames.add(new Frame(new Sprite(Assets.getInstance().getTexture(prefix + path))));
        }
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
