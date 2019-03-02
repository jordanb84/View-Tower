package com.djam.game.entity.room;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.animation.Animation;
import com.djam.game.assets.Assets;
import com.djam.game.entity.Entity;
import com.djam.game.map.Map;

public class EntityRoomBlank extends EntityRoom {

    private Sprite roomSprite;

    public EntityRoomBlank(Map map, Vector2 position) {
        super(map, position);
        this.roomSprite = new Sprite(Assets.getInstance().getTexture("building/room.png"));
        this.roomSprite.setAlpha(0.2f);
    }

    @Override
    public Animation setupAnimation() {
        Animation animation = new Animation(1);

        animation.addFrame("building/room_invisible.png");

        return animation;
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        this.roomSprite.setPosition(this.getPosition().x, this.getPosition().y);
        this.roomSprite.draw(batch);

        this.getAnimation().render(batch, camera, this.getPosition(), 0.4f);
    }

}
