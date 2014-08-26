package mainPackage;
import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
 
public class LoadFont {
	 
	TrueTypeFont font;
	private boolean antiAlias = true;
	private Font awtFont;

	public LoadFont(String FontName, float size) {
		
		// load font from file
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("src/res/fonts"+alternativeCommons.adionalPath+"/"+FontName);
			
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(size); // set font size
			font = new TrueTypeFont(awtFont, antiAlias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}