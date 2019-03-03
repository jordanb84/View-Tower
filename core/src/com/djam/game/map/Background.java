package com.djam.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.djam.game.assets.Assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Background {

    //private HashMap<Float, Sprite> layers = new HashMap<Float, Sprite>();

    private List<BackgroundLayer> layers = new ArrayList<BackgroundLayer>();

    private Map map;

    private int width;

    private OrthographicCamera camera;

    public Background(Map map) {
        /**this.addLayer(1, "background/1.png");
        this.addLayer(0.9f, "background/2.png");
        this.addLayer(0.8f, "background/3.png");
        this.addLayer(0.7f, "background/4.png");
        this.addLayer(0.6f, "background/5.png");
        this.addLayer(0.5f, "background/6.png");
        this.addLayer(0.4f, "background/7.png");**/
        /**this.addLayer(1, "background/layer_2_grass.png");
        this.addLayer(0.5f, "background/layer_1_mountain.png");
        this.addLayer(0.2f, "background/layer_0_sky.png");**/

        //this.addLayer(0.2f, "background/layer_0_sky.png");
        //this.addLayer(0.5f, "background/layer_1_mountain.png");
        //this.addLayer(1, "background/layer_2_grass.png");

        this.addLayer(0.4f, "background/7.png");
        this.addLayer(0.5f, "background/6.png");
        this.addLayer(0.6f, "background/5.png");
        this.addLayer(0.7f, "background/4.png");
        this.addLayer(0.8f, "background/3.png");
        this.addLayer(0.9f, "background/2.png");
        this.addLayer(1, "background/1.png");

        this.map = map;

        this.width = 960;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 960, 640);
    }

    /**
     *  this.backgroundLayer.setPosition(0 - cameraPosition.x * 0.1f, cameraPosition.y - Gdx.graphics.getHeight());
     *  this.centerLayer.setPosition(0 - cameraPosition.x * 0.5f, cameraPosition.y - Gdx.graphics.getHeight());
     *  this.foregroundLayer.setPosition(0 - cameraPosition.x, cameraPosition.y - Gdx.graphics.getHeight());
     * @param offsetMultiplier
     * @param spritePath
     */

    public void addLayer(float offsetMultiplier, String spritePath) {
        //this.layers.put(offsetMultiplier, new Sprite(Assets.getInstance().getTexture(spritePath)));
        this.layers.add(new BackgroundLayer(offsetMultiplier, new Sprite(Assets.getInstance().getTexture(spritePath))));
    }

    //use separate cam, add max amount you can go right/left

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        //batch.setProjectionMatrix(this.map.getTextCamera().combined);
        batch.setProjectionMatrix(this.camera.combined);
        Vector2 cameraPosition = new Vector2(camera.position.x, camera.position.y);
        //System.out.println(cameraPosition.x + "/" + cameraPosition.y); //<102 >999

        if(cameraPosition.x < 102) {
            cameraPosition.x = 102;
        }

        if(cameraPosition.x > 999) {
            cameraPosition.x = 999;
        }

        if(cameraPosition.y < 5) {
            //cameraPosition.y = 5;
        }

        if(cameraPosition.y > 210) {
            cameraPosition.y = 210;
        }

        for(BackgroundLayer layer : this.layers) {
            //System.out.println("Rendering " + layerEntry.getKey());
            float offsetMultiplier = layer.getOffsetMultiplier();
            Sprite sprite = layer.getSprite();

            //sprite.setSize(this.width + this.width / 4, 640);

            sprite.setPosition(-cameraPosition.x * offsetMultiplier + this.width / 24, 10 - (cameraPosition.y * 2));

            sprite.draw(batch);

        }

        //this.layers.get(1).setPosition(10, 10);
        //this.layers.get(1).draw(batch);

        batch.setProjectionMatrix(camera.combined);
    }

}
