package mainPackage;


public class alternativeCommons {

	static final byte ANZAHL_FLUG_ANIMATIONEN = 8;	//1-16
	static final byte ANZAHL_DIE_ANIMATIONEN = 12;	//1-16
	static final byte ANZAHL_GOTPOINT_ANIMATION = 2;	//1-16
	static String PROGRAMM_NAME = "JaR";
	static int WINDOW_WIDTH = 400;	//360
	static int FPS = 80;
	static int WINDOW_HEIGHT = 600;	//240
	static int PLAYER_HEIGHT = 32;
	static int PLAYER_WIDTH = 32;
	static int PLAYER_START_KOOR_X = (WINDOW_WIDTH-PLAYER_WIDTH)/2;
	static int PLAYER_START_KOOR_Y = (WINDOW_HEIGHT-PLAYER_HEIGHT)/2;
	static float SPRUNG_GESCHWINDIGKEIT = 1.75f;	//px pro frame
	static float FALL_GESCHWINDIGKEIT = 2.8f;
	static int SPRUNG_HOEHE = 30;			//in px
	static int WIDTH_SAEULE = 70;
	static int WIDTH_FREIRAUM = 140;
	static int START_FREIRAUM = 0;
	static int LUCKENGROSSE = 120;
	static double BACKGROUND_SPEED = 0.1; 	//bewegung nach links pro Frame
	static boolean Kollision = true;
	static String adionalPath = "";
	
	public alternativeCommons(int SH, int PD, int LG, boolean KOL, int FramesPerSek, boolean alternative) {
		SPRUNG_HOEHE = SH;
		PLAYER_HEIGHT = PD;
		PLAYER_WIDTH = PD;
		LUCKENGROSSE = LG;
		Kollision = KOL;
		FPS = FramesPerSek;
		if (alternative) {
			adionalPath = "Alternative";
		}
	}
	
}
