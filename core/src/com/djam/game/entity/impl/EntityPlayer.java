package com.djam.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.animation.DirectionalAnimation;
import com.djam.game.entity.Direction;
import com.djam.game.entity.Entity;
import com.djam.game.entity.EntityLiving;
import com.djam.game.map.Map;

public class EntityPlayer extends EntityLiving {

    private DirectionalAnimation runAnimation;
    private DirectionalAnimation idleAnimation;

    public EntityPlayer(Map map, Vector2 position) {
        super(map, position);
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);

        float speed = 50;

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.move(Direction.LEFT, speed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.move(Direction.RIGHT, speed);
        }
    }

    @Override
    public Animation setupAnimation() {
        Animation idle = new Animation(0.3f);

        idle.addFrame("player/player_idle_0.png");
        idle.addFrame("player/player_idle_1.png");
        idle.addFrame("player/player_idle_2.png");
        idle.addFrame("player/player_idle_3.png");
        idle.addFrame("player/player_idle_4.png");
        idle.addFrame("player/player_idle_5.png");
        idle.addFrame("player/player_idle_6.png");
        idle.addFrame("player/player_idle_7.png");
        idle.addFrame("player/player_idle_8.png");
        idle.addFrame("player/player_idle_9.png");
        idle.addFrame("player/player_idle_10.png");
        idle.addFrame("player/player_idle_11.png");

        Animation run = new Animation(0.3f);

        run.addFrame("player/player_run_0.png");
        run.addFrame("player/player_run_1.png");
        run.addFrame("player/player_run_2.png");
        run.addFrame("player/player_run_3.png");
        run.addFrame("player/player_run_4.png");
        run.addFrame("player/player_run_5.png");
        run.addFrame("player/player_run_6.png");

        Animation runLeft = new Animation(0.3f);

        runLeft.addFrame("player/player_run_0_left.png");
        runLeft.addFrame("player/player_run_1_left.png");
        runLeft.addFrame("player/player_run_2_left.png");
        runLeft.addFrame("player/player_run_3_left.png");
        runLeft.addFrame("player/player_run_4_left.png");
        runLeft.addFrame("player/player_run_5_left.png");
        runLeft.addFrame("player/player_run_6_left.png");

        this.idleAnimation = new DirectionalAnimation(Direction.RIGHT);
        this.idleAnimation.addAnimation(Direction.RIGHT, idle);
        this.idleAnimation.addAnimation(Direction.LEFT, idle);

        this.runAnimation = new DirectionalAnimation(Direction.RIGHT);
        this.runAnimation.addAnimation(Direction.RIGHT, run);
        this.runAnimation.addAnimation(Direction.LEFT, runLeft);

        return idle;
    }
}
