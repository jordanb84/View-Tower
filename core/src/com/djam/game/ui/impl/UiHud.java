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
import com.djam.game.economy.Research;
import com.djam.game.entity.impl.DecorType;
import com.djam.game.entity.impl.NpcType;
import com.djam.game.entity.room.Business;
import com.djam.game.ui.SkinType;
import com.djam.game.ui.Ui;

public class UiHud extends Ui {

    private Business business;

    private Currency currency;

    private NpcShop npcShop;

    private Research research;

    public UiHud(Business business, Currency currency, Research research) {
        super(SkinType.Holo_Dark_Hdpi.SKIN);
        this.business = business;
        this.currency = currency;
        this.research = research;

        this.npcShop = new NpcShop(this.getSkin(), this.business, this.currency, this.research);

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

    public NpcShop(Skin skin, Business business, Currency currency, Research research) {
        for(NpcType npc : NpcType.values()) {
            //ShopButton farmerButton = new ShopButton(skin, npc.TEXTURE, npc.TEXTURE, npc.OVERLAY_TEXTURE);
            if(!npc.LOCKED) {
                ShopButton npcButton = new NpcShopButton(npc, business, currency, skin, npc.TEXTURE, npc.TEXTURE, npc.OVERLAY_TEXTURE);

                this.addActor(npcButton);
            } else {
                LockedNpcShopButton npcButton = new LockedNpcShopButton(npc, business, currency, research, npc.NAME, npc.COST, npc.UNLOCK_COST, skin, npc.TEXTURE, npc.TEXTURE, npc.OVERLAY_TEXTURE);

                this.addActor(npcButton);
            }
        }

        //NpcType npcType, Business business, Currency currency, Research research, String productName, int cost, int unlockCost, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture

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

class ShopButton extends ImageButton {

    public ShopButton(String productName, int cost, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super(new SpriteDrawable(new Sprite(upTexture)), new SpriteDrawable(new Sprite(downTexture)));
        this.getStyle().imageOver = new SpriteDrawable(new Sprite(hoverTexture));

        this.setupListeners(productName, cost);
    }

    public void purchase(InputEvent event, float x, float y) {

    }

    public void setupListeners(String productName, int cost) {
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

}

class LockedShopButton extends ShopButton {

    private int unlockCost;

    private TextTooltip tooltip;

    private Research research;

    private Texture upTexture;
    private Texture downTexture;
    private Texture overlayTexture;

    private SpriteDrawable downDrawable;
    private SpriteDrawable overlayDrawable;

    private boolean purchased;

    public LockedShopButton(Texture lockedSprite, Research research, String productName, int cost, int unlockCost, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super(productName, cost, skin, lockedSprite, lockedSprite, lockedSprite);
        this.unlockCost = unlockCost;
        this.research = research;
        this.tooltip.getActor().setText(productName + " - " + "\n(Locked - Unlock cost: " + this.unlockCost + " research points)");

        this.upTexture = upTexture;
        this.downTexture = downTexture;
        this.overlayTexture = hoverTexture;

        this.downDrawable = new SpriteDrawable(new Sprite(this.downTexture));
        this.overlayDrawable = new SpriteDrawable(new Sprite(this.overlayTexture));
    }

    @Override
    public void setupListeners(String productName, int cost) {
        //this.tooltip = new TextTooltip(productName + " - " +" Cost: " + cost + " coins", SkinType.Arcade.SKIN);
        this.tooltip = new TextTooltip(productName + " - " + "\n(Locked - Unlock cost: " + this.unlockCost + " research points)", SkinType.Arcade.SKIN);
        this.tooltip.setInstant(true);

        this.addListener(this.tooltip);

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(research.getBalance() >= unlockCost) {
                    purchase(event, x, y);
                    if(!purchased) {
                        resetTextures();
                        research.modifyBalance(-unlockCost);
                        purchased = true;
                    }
                }

                if(purchased) {
                    resetTextures();
                }

                System.out.println("Clicked");
            }
        });
    }

    public void resetTextures() {
        ImageButtonStyle style = this.getStyle();

        style.imageDown = this.downDrawable;
        style.imageChecked = this.downDrawable;
        style.imageOver = this.overlayDrawable;
        style.imageUp = downDrawable;

        this.setStyle(style);

        System.out.println("Reset textures");
    }

    public TextTooltip getTooltip() {
        return tooltip;
    }

}

class LockedNpcShopButton extends LockedShopButton {

    private NpcType npcType;

    private Business business;
    private Currency currency;

    public LockedNpcShopButton(NpcType npcType, Business business, Currency currency, Research research, String productName, int cost, int unlockCost, Skin skin, Texture upTexture, Texture downTexture, Texture hoverTexture) {
        super(npcType.LOCKED_SPRITE, research, productName, cost, unlockCost, skin, upTexture, downTexture, hoverTexture);
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

        //        super(npcType.name() + " Employee\n(Place at a room table to build)\n", npcType.COST, skin, upTexture, downTexture, hoverTexture);
        this.getTooltip().getActor().setText(npcType.NAME + " Employee\n(Place at a room table to build)\n- Cost: " + this.npcType.COST);
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