package dev.n1t.desktopmodule;

import dev.n1t.game.Farmgame;
import com.jme3.system.AppSettings;

/**
 * Used to launch a jme application in desktop environment
 *
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        final Farmgame game = new Farmgame();

        final AppSettings appSettings = new AppSettings(true);

        game.setSettings(appSettings);
        game.start();
    }
}
