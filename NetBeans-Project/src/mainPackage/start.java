package mainPackage;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;


public class start{
	
	public static final ArrayList<UI> scoresUIs = new ArrayList<UI>();
	public static final ArrayList<UI> MainUIs = new ArrayList<UI>();
	public static final ArrayList<UI> PauseUIs = new ArrayList<UI>();
	public static final ArrayList<updateableUI> UpdateablescoresUIs = new ArrayList<updateableUI>();
	public static final ArrayList<updateableUI> UpdateableMainUIs = new ArrayList<updateableUI>();
	public static final ArrayList<updateableUI> UpdateablePauseUIs = new ArrayList<updateableUI>();
	public static ArrayList<UI> AllIngameUIs = new ArrayList<UI>();
    static ArrayList<updateableUI> AllUpdateableIngameUIs = new ArrayList<updateableUI>();
    public static String[] topTen = new String[10];
	static Player player;
    static Background background;
    static ArrayList<Saeule> saeulen = new ArrayList<Saeule>();
    static ArrayList<LoadSound> sounds = new ArrayList<LoadSound>();
	private static boolean springt = false;
	private static int nochNachOben = 0;
	private static int pxBisZurNaechstenSaeule = alternativeCommons.START_FREIRAUM;
	private static boolean first = true;
	private static int sAGO = 1;
	static LoadSound jumpSound;
	static LoadSound gotPointSound;
	static LoadFont defaultFont;
	static LoadAnimation playerAnimation;
	public static int animationFrameCounter = 0;
	static AnimationType AT = AnimationType.FLUG;
	static String erstens = "---";
	static String zweitens = "---";
	static String dritens = "---";
	static String viertens = "---";
	static String fuenftens = "---";
	static String sechstens = "---";
	static String siebentens = "---";
	static String achttens = "---";
	static String neuntens = "---";
	static String zehntens = "---";
	private static boolean firstGame = true;
	
    public static void main(String[] args) {
    	//fill arrayList:
        	topTen[0] = erstens;
        	topTen[1] = zweitens;
        	topTen[2] = dritens;
        	topTen[3] = viertens;
        	topTen[4] = fuenftens;
        	topTen[5] = sechstens;
        	topTen[6] = siebentens;
        	topTen[7] = achttens;
        	topTen[8] = neuntens;
        	topTen[9] = zehntens;
    	
    	getOpenGLStarting();
    }

    
	static UIGroup kollisionsabfrage() {
		if (alternativeCommons.Kollision) {
			//Unten
			if (alternativeCommons.PLAYER_HEIGHT+player.getY_Koor()>=alternativeCommons.WINDOW_HEIGHT) {
				System.out.println("GAME OVER mit Score: "+sAGO );
				firstGame = false;
				return resetGame();
		        
			}
			//Oben
			else if (player.getY_Koor()<=0) {
				System.out.println("GAME OVER mit Score: "+sAGO );
				firstGame = false;
				return resetGame();
			}
			//Koll. fuer Saeulen
			for (int i = 0; i < saeulen.size(); i++) {
				if (!(saeulen.get(i).getxKoor()<player.getX_Koor()-70 ||
					  saeulen.get(i).getxKoor()>player.getX_Koor()+alternativeCommons.PLAYER_WIDTH)) {
					//System.out.println(player.getY_Koor() + " - " + saeulen.get(i).getHeightUp());
					if ( saeulen.get(i).getYkoordown()<=player.getY_Koor()+alternativeCommons.PLAYER_HEIGHT ||
						 saeulen.get(i).getHeightUp()>=player.getY_Koor() ) {
							System.out.println("GAME OVER mit Score: "+sAGO );
					    	readAndWriteScore.write(""+sAGO);
					    	firstGame = false;
							return resetGame();
					}
				}
			}
			//Kollision fuer Ingame - UI
    		while (Mouse.next()) {
    			if (Mouse.isButtonDown(0)) {
    				for (int i = 0; i < AllUpdateableIngameUIs.size(); i++) {
    					if (!(AllUpdateableIngameUIs.get(i).x+AllUpdateableIngameUIs.get(i).width<Mouse.getX() ||
    							AllUpdateableIngameUIs.get(i).x>Mouse.getX())) {
    						if (!(AllUpdateableIngameUIs.get(i).y+AllUpdateableIngameUIs.get(i).height<alternativeCommons.WINDOW_HEIGHT-Mouse.getY() ||
        							AllUpdateableIngameUIs.get(i).y>alternativeCommons.WINDOW_HEIGHT-Mouse.getY())) {
    							switch (AllUpdateableIngameUIs.get(i).BF) {
								case toPause:
							        return UIGroup.pause;
								default:
									break;
								}
    						}
    					}
    				}
    			}
    		}
		}
		return null;
	}

	private static UIGroup resetGame() {
        player.setY_Koor(alternativeCommons.PLAYER_START_KOOR_Y);
        sAGO = -1;
        saeulen.clear();
        animationFrameCounter = 0;
        pxBisZurNaechstenSaeule = alternativeCommons.START_FREIRAUM;
        nochNachOben = 0;
		return UIGroup.mainMenu;
	}


	static void ingameInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			programmBeenden();
		}
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState()) {
				springt = true;
				nochNachOben = alternativeCommons.SPRUNG_HOEHE;
				jumpSound.start();
			}
		}
		
	}


	static void drawAll() {
		//background
		background.draw();
		//UI
        //saeulen
        for (int i = 0; i < saeulen.size(); i++) {
        	saeulen.get(i).draw();
		}
	    playerAnimation.drawFrame(player.getX_Koor(), player.getY_Koor(), animationFrameCounter+(AT.startRow-1)*8);
		for (int i = 0; i < AllUpdateableIngameUIs.size(); i++) {
			AllUpdateableIngameUIs.get(i).draw();
		}
	    //Score
        drawScore();
	}


	private static void drawScore() {
		defaultFont.font.drawString(alternativeCommons.WINDOW_WIDTH/2, alternativeCommons.WINDOW_HEIGHT/4, sAGO+"");
		
	}

	static void syncronisieren() {
		// Wait until we reach FPS frames-per-second.
        Display.sync(alternativeCommons.FPS);
	}


	static void clear() {
        // Clear the 2D contents of the window.
		glClear(GL_COLOR_BUFFER_BIT);
	}


	static void update() {
		updateBackground();
		updateSaeulen();
		updatePlayer();
		playerAnimation.update();
		updateUI();
		updateSound();
        // Update the contents of the display and check for input.
        Display.update();
	}


	private static void updateUI() {
		//Update InagemUI
	}


	private static void updateSound() {
		if (jumpSound.isAlive) {
			jumpSound.update();
		}
		
	}


	private static void updateBackground() {
		background.addToX_Koor(alternativeCommons.BACKGROUND_SPEED);
	}


	private static void updatePlayer() {
		if (springt) {
			player.setY_Koor(player.getY_Koor()-alternativeCommons.SPRUNG_GESCHWINDIGKEIT);
		}
		else {
			player.setY_Koor(player.getY_Koor()+alternativeCommons.FALL_GESCHWINDIGKEIT);
		}
	}


	private static void updateSaeulen() {
		for (int i = 0; i < saeulen.size(); i++) {
			saeulen.get(i).setxKoor(saeulen.get(i).getxKoor()-1);
		}
	}


	static void programmBeenden() {
		//ReleaseTextures
	        background.texture.release();
	        for (int i = 0; i < saeulen.size(); i++) {
				saeulen.get(i).texture.release();
			}
        //closeSoundStream
        	for (int i = 0; i < sounds.size(); i++) {
        		sounds.get(i).closeStream();
			}
        	AL.destroy();
        //DestroyDisplay
        	Display.destroy();
		//ExitProgramm
        	System.exit(0);
	}


	static void initGL() {
		glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, alternativeCommons.WINDOW_WIDTH, alternativeCommons.WINDOW_HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}


	static void initWorld() {
		background = new Background();
		player = new Player();
	}


	static void initDisplay() {
		try {
            // Sets the width of the display to 640 and the height to 480
            Display.setDisplayMode(new DisplayMode(alternativeCommons.WINDOW_WIDTH, alternativeCommons.WINDOW_HEIGHT));
            // Sets the title of the display to "Jump and Run Builder"
            Display.setTitle("Jump and Run");
            // Creates and shows the display
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
	}

	@SuppressWarnings("unused")
	public static void getOpenGLStarting() {
		initDisplay();
		initWorld();
		initGL();
		//Init Font
		defaultFont = new LoadFont("font.ttf", 36f);
		//Init Sound
		jumpSound = new LoadSound("jump.wav");
		gotPointSound = new LoadSound("gotPoint.wav");
		//InitAnimation
		playerAnimation = new LoadAnimation("animationRaster.png", 8, 8);
		//Init Main Menu
		UI mainMenuUI = new UI(33, 28, "menu-groß.png", false, UIGroup.mainMenu);
			updateableUI mainMenuButton1 = new updateableUI(70, 45, "button.png", ButtonFunktion.toIngame, false, UIGroup.mainMenu);
			updateableUI mainMenuButton2 = new updateableUI(70, 235, "button.png", ButtonFunktion.toscores, false, UIGroup.mainMenu);
			updateableUI mainMenuButton3 = new updateableUI(70, 425, "button.png", ButtonFunktion.endProgramm, false, UIGroup.mainMenu);
		//Init Main Menu
		UI scoresUI = new UI(33, 28, "menu-groß.png", false, UIGroup.scores);
			UI scoresMiniUI = new UI(33+20, 28+20, "menu-klein.png", false, UIGroup.scores);
			updateableUI backwardsButton = new updateableUI(70, 425, "button.png", ButtonFunktion.toMainMenu, false, UIGroup.scores);
		//Init ingame ui
		updateableUI pauseButton = new updateableUI(10, 10, "pause.png", ButtonFunktion.toPause, true, UIGroup.ingame);
		//Init pause UI
		updateableUI resumeButton = new updateableUI(10, 10, "resume.png", ButtonFunktion.toResumeIngame, false, UIGroup.pause);
		//UI pauseWindow = new UI(80, 80, "menu-klein.png", false, UIGroup.pause);
			updateableUI resumeGUIButton = new updateableUI(60, 260, "smallButton.png", ButtonFunktion.toResumeIngame, false, UIGroup.pause);
			updateableUI endGUIButton = new updateableUI(240, 260, "smallButton.png", ButtonFunktion.toMainMenu, false, UIGroup.pause);
			//Def UI group:
			UIGroup ThatUI = UIGroup.mainMenu;
			//start
		while (ThatUI != null) {
			ThatUI = menuInput(ThatUI);
			System.out.println("UIStatus: "+ThatUI);
		}
	}

	static UIGroup playGame() {
            clear();
            ingameInput();
                drawAll();
                UIGroup kTest = kollisionsabfrage();
                if (kTest != null) {
					return kTest;
				}
                sprungabfrage();
                saeulenGeneration();
                update();
            syncronisieren();
			return null;
	}

	private static UIGroup menuInput(UIGroup UIg) {
        while (UIg != UIGroup.ingame) {
        	if (Display.isCloseRequested()) {
				programmBeenden();
			}
    		while (Mouse.next()) {
    			if (Mouse.isButtonDown(0)) {
    				switch (UIg) {
					case mainMenu:
	    				for (int i = 0; i < UpdateableMainUIs.size(); i++) {
	    					if (!(UpdateableMainUIs.get(i).x+UpdateableMainUIs.get(i).width<Mouse.getX() ||
	    							UpdateableMainUIs.get(i).x>Mouse.getX())) {
	    						if (!(UpdateableMainUIs.get(i).y+UpdateableMainUIs.get(i).height<alternativeCommons.WINDOW_HEIGHT-Mouse.getY() ||
	    								UpdateableMainUIs.get(i).y>alternativeCommons.WINDOW_HEIGHT-Mouse.getY())) {
	    							switch (UpdateableMainUIs.get(i).BF) {
									case toIngame:
										resetGame();
										if (firstGame) {
											sAGO = 0;
										}
										return UIGroup.ingame;
									case toscores:
								    	String[] asdf = readAndWriteScore.read();
								    	int[] asdfAsIn = new int[asdf.length];
								    	for (int j = 0; j < asdf.length; j++) {
									    	asdfAsIn[j] = Integer.parseInt(asdf[j]);
										}
								    	Arrays.sort(asdfAsIn);
								    	int[] temp = asdfAsIn.clone();
								    	for (int j = 0; j < temp.length; j++) {
								    		asdfAsIn[j] = temp[temp.length-j-1];
										}
								    	for (int k = 0; k < asdfAsIn.length && k < 10; k++) {
								    		if (k != 9) {
								    			topTen[k] = " "+asdfAsIn[k];
											}
								    		else {
								    			topTen[k] = ""+asdfAsIn[k];
											}
										}
										return UIGroup.scores;
									case endProgramm:
										programmBeenden();
										break;
									default:
										break;
									}
	    						}
	    					}
	    				}
						break;
					case scores:
	    				for (int i = 0; i < UpdateablescoresUIs.size(); i++) {
	    					if (!(UpdateablescoresUIs.get(i).x+UpdateablescoresUIs.get(i).width<Mouse.getX() ||
	    							UpdateablescoresUIs.get(i).x>Mouse.getX())) {
	    						if (!(UpdateablescoresUIs.get(i).y+UpdateablescoresUIs.get(i).height<alternativeCommons.WINDOW_HEIGHT-Mouse.getY() ||
	    								UpdateablescoresUIs.get(i).y>alternativeCommons.WINDOW_HEIGHT-Mouse.getY())) {
	    							switch (UpdateablescoresUIs.get(i).BF) {
									case toMainMenu:
										return UIGroup.mainMenu; 
									default:
										break;
									}
	    						}
	    					}
	    				}
						break;
					case pause:
	    				for (int i = 0; i < UpdateablePauseUIs.size(); i++) {
	    					if (!(UpdateablePauseUIs.get(i).x+UpdateablePauseUIs.get(i).width<Mouse.getX() ||
	    							UpdateablePauseUIs.get(i).x>Mouse.getX())) {
	    						if (!(UpdateablePauseUIs.get(i).y+UpdateablePauseUIs.get(i).height<alternativeCommons.WINDOW_HEIGHT-Mouse.getY() ||
	    								UpdateablePauseUIs.get(i).y>alternativeCommons.WINDOW_HEIGHT-Mouse.getY())) {
	    							switch (UpdateablePauseUIs.get(i).BF) {
									case toResumeIngame:
										return UIGroup.ingame; 
									case toMainMenu:
										return UIGroup.mainMenu; 
									default:
										break;
									}
	    						}
	    					}
	    				}
						break;
					default:
						break;
					}

    			}
    		}
            clear();
            drawGUIAndBackground(UIg);
            drawUIText(UIg);
            if (UIg != UIGroup.pause) {
                updateMenuGUIAndBackground(UIg);
			}
            else {
                updateMenuGUI(UIg);
			}
            syncronisieren();
        }
        while (UIg == UIGroup.ingame) {
        	if (Display.isCloseRequested()) {
				programmBeenden();
			}
        	UIGroup UIReturn = playGame();
			if (UIReturn != null) {
				return UIReturn;
			}
		}
		programmBeenden();
		return null;
	}


	private static void drawUIText(UIGroup UIg) {
		//System.out.println(UIg);
		switch (UIg) {
		case mainMenu:
    		defaultFont.font.drawString(128, 95, "Neues Spiel", Color.white);
    		defaultFont.font.drawString(160, 95+190, "Scores", Color.white);
    		defaultFont.font.drawString(145, 95+190+190, "Beenden", Color.white);
			break;
		case scores:
			for (int i = 0; i < 10; i++) {
	    		defaultFont.font.drawString(70, 50+30*i, (i+1)+". "+topTen[i], Color.black);
			}
    		defaultFont.font.drawString(90, 95+190+190, "Zum Hauptmenü", Color.white);
			break;
		case pause:
    		defaultFont.font.drawString(83, 300, "Weiter", Color.white);
    		defaultFont.font.drawString(256, 300, "Beenden", Color.white);
			break;
		default:
			break;
		}
		
	}


	private static void updateMenuGUIAndBackground(UIGroup UIg) {
		updateBackground();
		switch (UIg) {
		case mainMenu:
			for (int i = 0; i < UpdateableMainUIs.size(); i++) {
				UpdateableMainUIs.get(i).update(Mouse.getX(), alternativeCommons.WINDOW_HEIGHT - Mouse.getY());
			}
			break;
		case scores:
			for (int i = 0; i < UpdateablescoresUIs.size(); i++) {
				UpdateablescoresUIs.get(i).update(Mouse.getX(), alternativeCommons.WINDOW_HEIGHT - Mouse.getY());
			}
			break;
		case pause:
			for (int i = 0; i < UpdateablePauseUIs.size(); i++) {
				UpdateablePauseUIs.get(i).update(Mouse.getX(), alternativeCommons.WINDOW_HEIGHT - Mouse.getY());
			}
			break;
		default:
			break;
		}
		updateSound();
        // Update the contents of the display and check for input.
        Display.update();
	}
	
	private static void updateMenuGUI(UIGroup UIg) {
		switch (UIg) {
		case mainMenu:
			for (int i = 0; i < UpdateableMainUIs.size(); i++) {
				UpdateableMainUIs.get(i).update(Mouse.getX(), alternativeCommons.WINDOW_HEIGHT - Mouse.getY());
			}
			break;
		case scores:
			for (int i = 0; i < UpdateablescoresUIs.size(); i++) {
				UpdateablescoresUIs.get(i).update(Mouse.getX(), alternativeCommons.WINDOW_HEIGHT - Mouse.getY());
			}
			break;
		case pause:
			for (int i = 0; i < UpdateablePauseUIs.size(); i++) {
				UpdateablePauseUIs.get(i).update(Mouse.getX(), alternativeCommons.WINDOW_HEIGHT - Mouse.getY());
			}
			break;
		default:
			break;
		}
		updateSound();
        // Update the contents of the display and check for input.
        Display.update();
	}


	private static void drawGUIAndBackground(UIGroup UIg) {
		background.draw();
		switch (UIg) {
		case mainMenu:
			for (int i = 0; i < MainUIs.size(); i++) {
				MainUIs.get(i).draw();
			}
			break;
		case scores:
			for (int i = 0; i < scoresUIs.size(); i++) {
				scoresUIs.get(i).draw();
			}
			break;
		case pause:
			for (int i = 0; i < PauseUIs.size(); i++) {
				PauseUIs.get(i).draw();
			}
			break;
		default:
			break;
		}
	}


	private static void saeulenGeneration() {
		if (pxBisZurNaechstenSaeule > 0) {
        	--pxBisZurNaechstenSaeule;
		}
    	else {                   
    		Saeule s = new Saeule();
        	saeulen.add(s);
        	pxBisZurNaechstenSaeule = alternativeCommons.WIDTH_SAEULE + alternativeCommons.WIDTH_FREIRAUM;
        	if (!first) {
    			++sAGO ;
    			//AT = AnimationType.GOTPOINT;
    			gotPointSound.start();
			}
        	else {
				first  = false;
			}
		}
    	for (int i = 0; saeulen.size()>3; ++i) {
    		saeulen.remove(i);
		}
	}


	private static void sprungabfrage() {
		if (nochNachOben > 0) {
			--nochNachOben;
		}
		else {
			nochNachOben = 0;
			springt = false;
		}
	}
	
	
}