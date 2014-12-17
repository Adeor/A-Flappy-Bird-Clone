package com.adeor.flappyAnus;

import com.adeor.flappyAnus.Screens.SplashScreen;
import com.adeor.flappyAnus.Helpers.AssetLoader;
import com.badlogic.gdx.Game;

public class FlappyAnus extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}