package com.djam.game.music;

import com.badlogic.gdx.audio.Music;
import com.djam.game.assets.Assets;

public enum MusicType {
    Main("music/main.mp3")
    ;

    MusicType(String path) {
        this.MUSIC = Assets.getInstance().getMusic(path);
    }

    public final Music MUSIC;

    public static Music playing;

    public static void play(Music music, boolean loop) {
        if(playing != null) {
            playing.stop();
        }

        playing = music;
        playing.setVolume(0.7f);
        playing.play();

        playing.setLooping(loop);
    }

}
