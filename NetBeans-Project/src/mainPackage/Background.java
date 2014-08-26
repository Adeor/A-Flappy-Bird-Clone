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

public class Background {

	private static float x_Koor = 0;
	private static float y_Koor = 0;
	Texture texture;
	public static float getX_Koor() {
		return x_Koor;
	}

        //MAGIC! Dont touch!
	public void addToX_Koor(double bACKGROUND_SPEED) {
            //System.out.println(Background.getX_Koor()+823*1.7+100-alternativeCommons.WINDOW_WIDTH);
            if (Background.getX_Koor()+823*1.7+100 > alternativeCommons.WINDOW_WIDTH) {
                Background.x_Koor -= bACKGROUND_SPEED;
            }
	}

	public static float getY_Koor() {
		return y_Koor;
	}

	public static float setY_Koor(float d) {
		Background.y_Koor = d;
		return d;
	}
	
	public Background() {
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(ResourceLoader.getResource("src/res/images"+alternativeCommons.adionalPath+"/background.png").toURI())));
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
	}
	
	void draw() {
		texture.bind();
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
        glPushMatrix();
        glTranslatef(x_Koor, y_Koor, 0);
        glBegin(GL_POLYGON);
        	glTexCoord2f(0, 0);
        	glVertex2f(0, 0);
        	glTexCoord2f(1, 0);
        	glVertex2f(1800, 0);
        	glTexCoord2f(1, 1);
        	glVertex2f(1800, 600*1.7f);
        	glTexCoord2f(0, 1);
        	glVertex2f(0, 600*1.7f);
        glEnd();
        glPopMatrix();
	}
	
}
