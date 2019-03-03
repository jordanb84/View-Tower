package sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.djam.game.assets.Assets;

public enum SoundType {
    Click("sound/click.ogg")
    ;

    SoundType(String path) {
        this.SOUND = Assets.getInstance().getSound(path);
    }

    public final Sound SOUND;

    public static void play(Sound sound) {
        sound.play(0.7f);
    }

}
