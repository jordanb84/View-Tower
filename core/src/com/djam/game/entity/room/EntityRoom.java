package com.djam.game.entity.room;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.assets.Assets;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

public class EntityRoom extends Entity {

    private Rectangle collisionRectangle;

    private Sprite collisionSprite;

    public EntityRoom(Map map, Vector2 position) {
        super(map, position);
        this.collisionSprite = new Sprite(Assets.getInstance().getTexture("building/room_collision.png"));
        this.collisionSprite.setPosition(position.x, position.y);

        this.collisionRectangle = new Rectangle(collisionSprite.getX(), collisionSprite.getY(), collisionSprite.getWidth(), collisionSprite.getHeight());

        map.addCollisionBody(this.collisionRectangle);
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("building/room.png");

        return animation;
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        super.render(batch, camera);
        this.collisionSprite.draw(batch);
    }
}
