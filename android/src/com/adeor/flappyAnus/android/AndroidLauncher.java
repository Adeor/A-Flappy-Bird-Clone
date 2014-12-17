package com.adeor.flappyAnus.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.adeor.flappyAnus.FlappyAnus;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			config.useCompass=false;
			config.useAccelerometer=false;
		initialize(new FlappyAnus(), config);
	}
}