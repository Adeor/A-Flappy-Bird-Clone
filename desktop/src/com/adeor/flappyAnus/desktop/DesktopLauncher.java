package com.adeor.flappyAnus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adeor.flappyAnus.FlappyAnus;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = "FlappyAnus";
			config.width = 1080 / 3;
			config.height = 1920 / 3;
		new LwjglApplication(new FlappyAnus(), config);
	}
}
