package mainPackage;

public enum AnimationType {
	FLUG(1, 2), GOTPOINT(3, 3), DIE(5, 6);		//noch 2 reihen frei
	
	final int startRow;
	final int endRow;
	
	private AnimationType(int PARAM_startRow, int PARAM_endRow) {
		startRow = PARAM_startRow;
		endRow = PARAM_endRow;
	}
}
