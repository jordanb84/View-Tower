package com.djam.game.entity.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
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
        this.getDirectionalAnimation().update(camera);

        //System.out.println(this.getPosition().x);

        float speed = 50;

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.move(Direction.LEFT, speed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.move(Direction.RIGHT, speed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.position.add(1, 0, 0);
            camera.update();
        }

        boolean stopped = false;
        if(this.getPosition().x > 767) {
            this.getPosition().set(767, this.getPosition().y);
            this.setDirectionalAnimation(this.idleAnimation);
            stopped = true;
        }

        if(this.getPosition().x < 4) {
            this.getPosition().set(4, this.getPosition().y);
            this.setDirectionalAnimation(this.idleAnimation);
            stopped = true;
        }

        if(this.isMoving()) {
            if(!stopped) {
                //System.out.println("MOVING");
                this.setDirectionalAnimation(this.runAnimation);
                camera.position.set(this.getPosition().x, this.getPosition().y, 0);
                camera.update();
            }
        } else {
            this.setDirectionalAnimation(this.idleAnimation);

            //System.out.println("Not moving. Direction: " + this.getDirection().name());
            if(this.getDirection() == Direction.LEFT || this.getDirection() == Direction.RIGHT) {
                this.idleAnimation.setDirection(this.getDirection());
            }
        }

        if(!(this.getDirection() == Direction.DOWN) && !(this.getDirection() == Direction.UP)) {
            this.getDirectionalAnimation().setDirection(this.getDirection());
        }

        for(EntityLadder ladder : this.getMap().getLadders()) {
            if(this.getBody().overlaps(ladder.getBody())) {
                //Vector2 newPosition = new Vector2(this.getPosition().x, this.getPosition().y);

                if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                    //newPosition.add(0, 69);
                    this.jump(new Vector2(this.getPosition().x, this.getPosition().y + 69), ladder);
                }
                if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                    //newPosition.add(0, -67);
                    this.jump(new Vector2(this.getPosition().x, this.getPosition().y -67), ladder);
                }


            }
        }

        this.updatePhysics();
    }

    public void jump(Vector2 newPosition, EntityLadder currentLadder) {
        Rectangle newPositionBody = new Rectangle(newPosition.x, newPosition.y, this.getWidth(), this.getHeight());

        for(EntityLadder ladder : this.getMap().getLadders()) {
            if(ladder != currentLadder) {
                if(newPositionBody.overlaps(ladder.getBody())) {
                    this.getPosition().set(newPosition.x, newPosition.y);
                }
            }
        }
    }

    @Override
    public Animation setupAnimation() {
        Animation idle = new Animation(0.2f);

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

        Animation idleLeft = new Animation(0.2f);

        idleLeft.addFrame("player/player_idle_0_left.png");
        idleLeft.addFrame("player/player_idle_1_left.png");
        idleLeft.addFrame("player/player_idle_2_left.png");
        idleLeft.addFrame("player/player_idle_3_left.png");
        idleLeft.addFrame("player/player_idle_4_left.png");
        idleLeft.addFrame("player/player_idle_5_left.png");
        idleLeft.addFrame("player/player_idle_6_left.png");
        idleLeft.addFrame("player/player_idle_7_left.png");
        idleLeft.addFrame("player/player_idle_8_left.png");
        idleLeft.addFrame("player/player_idle_9_left.png");
        idleLeft.addFrame("player/player_idle_10_left.png");
        idleLeft.addFrame("player/player_idle_11_left.png");

        Animation run = new Animation(0.2f);

        run.addFrame("player/player_run_0.png");
        run.addFrame("player/player_run_1.png");
        run.addFrame("player/player_run_2.png");
        run.addFrame("player/player_run_3.png");
        run.addFrame("player/player_run_4.png");
        run.addFrame("player/player_run_5.png");
        run.addFrame("player/player_run_6.png");

        Animation runLeft = new Animation(0.2f);

        runLeft.addFrame("player/player_run_0_left.png");
        runLeft.addFrame("player/player_run_1_left.png");
        runLeft.addFrame("player/player_run_2_left.png");
        runLeft.addFrame("player/player_run_3_left.png");
        runLeft.addFrame("player/player_run_4_left.png");
        runLeft.addFrame("player/player_run_5_left.png");
        runLeft.addFrame("player/player_run_6_left.png");

        this.idleAnimation = new DirectionalAnimation(Direction.RIGHT);
        this.idleAnimation.addAnimation(Direction.RIGHT, idle);
        this.idleAnimation.addAnimation(Direction.LEFT, idleLeft);

        this.runAnimation = new DirectionalAnimation(Direction.RIGHT);
        this.runAnimation.addAnimation(Direction.RIGHT, run);
        this.runAnimation.addAnimation(Direction.LEFT, runLeft);

        this.setDirectionalAnimation(this.idleAnimation);

        System.out.println("Setup animations");

        return idle;
    }
}
