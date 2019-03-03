package com.djam.game.ui.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.djam.game.assets.Assets;
import com.djam.game.economy.Currency;
import com.djam.game.entity.impl.DecorType;
import com.djam.game.entity.impl.NpcType;
import com.djam.game.entity.room.Business;
import com.djam.game.ui.SkinType;
import com.djam.game.ui.Ui;

public class UiHud extends Ui {

    private Business business;

    private Currency currency;

    private NpcShop npcShop;

    public UiHud(Business business, Currency currency) {
        super(SkinType.Holo_Dark_Hdpi.SKIN);
        this.business = business;
        this.currency = currency;

        this.npcShop = new NpcShop(this.getSkin(), this.business, this.currency);

        this.getRootTable().setPosition(25, Gdx.graphics.getHeight() - 60);

        this.getRootTable().addActor(new Label("Shop", this.getSkin()));
        this.getRootTable().addActor(new Label(" ", this.getSkin()));

        this.getRootTable().row();
        this.getRootTable().row();

        this.getRootTable().addActor(this.npcShop);
        this.npcShop.setPosition(this.npcShop.getX() + 20, npcShop.getY() - 10);


    }

    @Override
    public void create() {

    }

}

class NpcShop extends HorizontalGroup {

    public NpcShop(Skin skin, Business business, Currency currency) {
        for(NpcType npc : NpcType.values()) {
            //ShopButton farmerButton = new ShopButton(skin, npc.TEXTURE, npc.TEXTURE, npc.OVERLAY_TEXTURE);
            ShopButton npcButton = new NpcShopButton(npc, business, currency, skin, npc.TEXTURE, npc.TEXTURE, npc.OVERLAY_TEXTURE);

            this.addActor(npcButton);

            Texture roomShopIcon = Assets.getInstance().getTexture("ui/room_icon.png");
            Texture roomShopIconHover = Assets.getInstance().getTexture("ui/room_icon_hover.png");

            RoomShopButton roomShopButton = new RoomShopButton(business, currency, skin, roomShopIcon, roomShopIcon, roomShopIconHover);

            //roomShopButton.setPosition(roomShopButton.getX() + 100, roomShopButton.getY() - 5);

            this.addActor(roomShopButton);

            Texture paintingShopIcon = Assets.getInstance().getTexture("ui/painting_icon.png");
            Texture paintingShopIconHover = Assets.getInstance().getTexture("ui/painting_icon_hover.png");

            DecorShopButton paintingShopButton = new DecorShopButton(DecorType.Painting, business, currency, skin, paintingShopIcon, paintingShopIcon, paintingShopIconHover);

            this.addActor(paintingShopButton);
        }
    }

}

class ShopButton extends ImageButton {

    public ShopButton(String productName, int cost, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super(new SpriteDrawable(new Sprite(upTexture)), new SpriteDrawable(new Sprite(downTexture)));
        this.getStyle().imageOver = new SpriteDrawable(new Sprite(hoverTexture));

        TextTooltip tooltip = new TextTooltip(productName + " - " +" Cost: " + cost + " coins", SkinType.Arcade.SKIN);
        tooltip.setInstant(true);

        this.addListener(tooltip);

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                purchase(event, x, y);
            }
        });
    }

    public void purchase(InputEvent event, float x, float y) {

    }

}

class NpcShopButton extends ShopButton {

    private NpcType npcType;

    private Business business;
    private Currency currency;

    public NpcShopButton(NpcType npcType, Business business, Currency currency, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super(npcType.name() + " Employee\n(Place at a room table to build)\n", npcType.COST, skin, upTexture, downTexture, hoverTexture);
        this.npcType = npcType;

        this.business = business;
        this.currency = currency;
    }

    @Override
    public void purchase(InputEvent event, float x, float y) {
        super.purchase(event, x, y);
        this.business.stopPlacing();
        /**this.business.stopPlacingRoom();
        this.business.startPlacingNpc(this.npcType);**/
        this.business.startPlacingNpc(this.npcType);
    }

}

class RoomShopButton extends ShopButton {

    private Business business;
    private Currency currency;

    public RoomShopButton(Business business, Currency currency, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super("Employee Room\n(Place at a grey area to build)\n", business.getRoomPrice(), skin, upTexture, downTexture, hoverTexture);
        this.business = business;
        this.currency = currency;
    }

    @Override
    public void purchase(InputEvent event, float x, float y) {
        super.purchase(event, x, y);
        //this.business.startPlacingNpc(this.npcType);
        this.business.stopPlacing();
        /**this.business.stopPlacingNpc();
        this.business.startPlacingRoom();**/
        this.business.startPlacingRoom();
    }

}

class DecorShopButton extends ShopButton {

    private Business business;
    private Currency currency;

    private DecorType decorType;

    public DecorShopButton(DecorType decorType, Business business, Currency currency, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super(decorType.NAME + "\n(" + decorType.DESCRIPTION + ")\n", decorType.PRICE, skin, upTexture, downTexture, hoverTexture);
        this.business = business;
        this.currency = currency;
        this.decorType = decorType;
    }

    @Override
    public void purchase(InputEvent event, float x, float y) {
        super.purchase(event, x, y);
        //this.business.startPlacingNpc(this.npcType);
        this.business.stopPlacing();

        this.business.startPlacingDecor(this.decorType);
    }

}