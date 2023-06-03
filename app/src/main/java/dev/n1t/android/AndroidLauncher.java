package dev.n1t.android;

import com.jme3.app.AndroidHarness;
import dev.nt.game.Farmgame;


public class AndroidLauncher extends AndroidHarness {

    public AndroidLauncher() {
        appClass = Farmgame.class.getCanonicalName();
    }
}
