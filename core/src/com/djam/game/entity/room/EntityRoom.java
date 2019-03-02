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

import java.util.ArrayList;
import java.util.List;

public class EntityRoom extends Entity {

    private Rectangle collisionRectangle;

    private Sprite collisionSprite;

    private List<EntityDesk> desks = new ArrayList<EntityDesk>();

    private Sprite deskSprite;

    public EntityRoom(Map map, Vector2 position) {
        super(map, position);
        this.collisionSprite = new Sprite(Assets.getInstance().getTexture("building/room_collision.png"));
        this.collisionSprite.setPosition(position.x, position.y);

        this.collisionRectangle = new Rectangle(collisionSprite.getX(), collisionSprite.getY(), collisionSprite.getWidth(), collisionSprite.getHeight());

        map.addCollisionBody(this.collisionRectangle);

        this.deskSprite = new Sprite(Assets.getInstance().getTexture("building/desk.png"));

        this.generateDesks();
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

        for(EntityDesk desk : this.desks) {
            desk.render(batch, camera);
        }
    }

    @Override
    public void update(OrthographicCamera camera) {
        super.update(camera);
        for(EntityDesk desk : this.desks) {
            desk.update(camera);
        }
    }

    private void generateDesks() {
        float deskWidth = this.deskSprite.getWidth();
        float deskHeight = this.deskSprite.getHeight();

        Vector2 start = new Vector2(this.getPosition().x + deskWidth * 2, this.getPosition().y + this.collisionSprite.getHeight());

        for(int desk = 0; desk < 4; desk++) {
            float offset = deskWidth * (desk * 4);
            Vector2 position = new Vector2(start.x + offset, start.y);

            this.desks.add(new EntityDesk(this.getMap(), position));
        }
    }

}
