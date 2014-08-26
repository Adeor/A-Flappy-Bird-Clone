package mainPackage;


import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Saeule{

	private final Random r1;
	private final Random r2;
	private final static int width = alternativeCommons.WIDTH_SAEULE;
	private final static int yKoorUp = 0;
	private int heightUp;
	private final int yKoorDown;
	private int heightDown;
	private int xKoor = alternativeCommons.WINDOW_WIDTH;
	Texture texture;
	private Texture top;
	
	public Saeule() {
		r1 = new Random();
		heightUp += r1.nextInt(200);
		
		r2 = new Random();
		heightUp += r2.nextInt(200)+1;
		
		heightDown= alternativeCommons.WINDOW_HEIGHT-(heightUp+120);
		
		yKoorDown = heightUp+alternativeCommons.LUCKENGROSSE;	//100 = guter wert statt alternativeCommons.LUCKENGROSSE
		
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(ResourceLoader.getResource("src/res/images"+alternativeCommons.adionalPath+"/texture.png").toURI())));
			top = TextureLoader.getTexture("PNG", new FileInputStream(new File(ResourceLoader.getResource("src/res/images"+alternativeCommons.adionalPath+"/top.png").toURI())));
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

	public int getHeightUp() {
		return heightUp;
	}

	public int getWidth() {
		return width;
	}

	public int getYkoorup() {
		return yKoorUp;
	}
	
	public int getYkoordown() {
		return yKoorDown;
	}

	public int getHeightDown() {
		return heightDown;  
	}

	public int getxKoor() {
		return xKoor;
	}

	public void setxKoor(int xKoor) {
		this.xKoor = xKoor;
	}

	public void draw() {
		//UP
		//Draw top
			top.bind();
			glBindTexture(GL_TEXTURE_2D, top.getTextureID());
	        glPushMatrix();
	        //TODO Flip
	        glTranslatef(xKoor, yKoorUp+heightUp, 0);
	        glBegin(GL_QUADS);
	        	glTexCoord2f(0, 0);
	        	glVertex2f(0, 0);
	        	glTexCoord2f(1, 0);
	        	glVertex2f(70, 0);
	        	glTexCoord2f(1, 1);
	        	glVertex2f(70, -120);
	        	glTexCoord2f(0, 1);
	        	glVertex2f(0, -120);
	        glEnd();
	        glPopMatrix();
    //draw texture
	        texture.bind();
			glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
	        glPushMatrix();
	        glTranslatef(xKoor, yKoorUp, 0);
	        glBegin(GL_QUADS);
	        	glTexCoord2f(0, 0);
	        	glVertex2f(0, 0);
	        	glTexCoord2f(1, 0);
	        	glVertex2f(70, 0);
	        	glTexCoord2f(1, 1);
	        	glVertex2f(70, heightUp-100);
	        	glTexCoord2f(0, 1);
	        	glVertex2f(0, heightUp-100);
	        glEnd();
	        glPopMatrix();
		//DOWN
			//Draw top
				top.bind();
				glBindTexture(GL_TEXTURE_2D, top.getTextureID());
		        glPushMatrix();
		        glTranslatef(xKoor, yKoorDown, 0);
		        glBegin(GL_QUADS);
		        	glTexCoord2f(0, 0);
		        	glVertex2f(0, 0);
		        	glTexCoord2f(1, 0);
		        	glVertex2f(70, 0);
		        	glTexCoord2f(1, 1);
		        	glVertex2f(70, 120);
		        	glTexCoord2f(0, 1);
		        	glVertex2f(0, 120);
		        glEnd();
		        glPopMatrix();
        //draw texture
		        texture.bind();
				glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		        glPushMatrix();
		        glTranslatef(xKoor, yKoorDown+100, 0);
		        glBegin(GL_QUADS);
		        	glTexCoord2f(0, 0);
		        	glVertex2f(0, 0);
		        	glTexCoord2f(1, 0);
		        	glVertex2f(70, 0);
		        	glTexCoord2f(1, 1);
		        	glVertex2f(70, heightDown-90);
		        	glTexCoord2f(0, 1);
		        	glVertex2f(0, heightDown-90);
		        glEnd();
		        glPopMatrix();
	}
	
}
