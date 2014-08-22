package mainPackage;

import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class UI {
	float x;
	float y;
	float height;
	float width;
	Texture texture;
	boolean visible;
	UIGroup UIg;
	
	public UI(float x, float y, String filename, boolean ingame, UIGroup UIg) {
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(ResourceLoader.getResource("res/UI"+alternativeCommons.adionalPath+"/"+filename).toURI())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
		width = texture.getImageWidth();
		height = texture.getImageHeight();
		if (ingame) {
			start.AllIngameUIs.add(this);
		}
		else {
			switch (UIg) {
			case mainMenu:
				start.MainUIs.add(this);
				break;
			case scores:
				start.scoresUIs.add(this);
				break;
			case pause:
				start.PauseUIs.add(this);
				break;
			default:
				break;
			}
		}
		this.UIg = UIg;
	}
	
	void draw() {
		texture.bind();
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
        glPushMatrix();
        glTranslatef(x, y, 0);
        glBegin(GL_POLYGON);
        	glTexCoord2f(0, 0);
        	glVertex2f(0, 0);
        	glTexCoord2f(1, 0);
        	glVertex2f(width, 0);
        	glTexCoord2f(1, 1);
        	glVertex2f(width, height);
        	glTexCoord2f(0, 1);
        	glVertex2f(0, height);
        glEnd();
        glPopMatrix();
	}
	
}
