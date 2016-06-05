package com.novacode.astromax.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.novacode.astromax.AstroMaxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = AstroMaxGame.WIDTH;
		config.height = AstroMaxGame.HEIGHT;
		new LwjglApplication(new AstroMaxGame(), config);
	}
}
