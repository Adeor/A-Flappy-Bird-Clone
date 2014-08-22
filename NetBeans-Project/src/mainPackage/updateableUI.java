package mainPackage;

public class updateableUI extends UI{

	boolean enabled;
	ButtonFunktion BF;
	
	public updateableUI(float x, float y, String filename, ButtonFunktion BF, boolean ingame, UIGroup UIg) {
		super(x, y, filename, ingame, UIg);
		if (ingame) {
			start.AllUpdateableIngameUIs.add(this);
		}
		else {
			switch (UIg) {
			case mainMenu:
				start.UpdateableMainUIs.add(this);
				break;
			case scores:
				start.UpdateablescoresUIs.add(this);
				break;
			case pause:
				start.UpdateablePauseUIs.add(this);
				break;
			default:
				break;
			}
		}
		this.BF = BF;
	}

	public void update(float MouseX, float MouseY) {
		if (kollision(MouseX, MouseY)) {
			//
		}
	}
	
	boolean kollision(float MouseX, float MouseY) {
		//Kollisionsabfrage
		//..........
		//sonst:
		return false;
	}
	
}
