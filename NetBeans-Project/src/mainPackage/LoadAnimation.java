package mainPackage;


import static org.lwjgl.opengl.GL11.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;
import org.newdawn.slick.util.ResourceLoader;


public class LoadAnimation{

	
	
	////////////////////////////////////////////
	////////////////Variablen///////////////////
	////////////////////////////////////////////
	
    private int anz = 8;
    private BufferedImage bi;
    private ArrayList<BufferedImage> images;
	private int frameCounter = 0;
    
    
    
	////////////////////////////////////////////
	///////////////Konstruktor//////////////////
	////////////////////////////////////////////
    
	public LoadAnimation(String AnimationName, int AnzahlSpalten, int AnzahlReihen){	//TODO
    	//get buffered image
        File imgFile = null;
		try {
			imgFile = new File(ResourceLoader.getResource("src/res/images"+alternativeCommons.adionalPath+"/"+AnimationName).toURI());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block 
			e1.printStackTrace();
		}
        try {
            bi = ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //fill ArrayList
        images = new ArrayList<BufferedImage>();
        BufferedImage newImg;
        int div = anz;
        for(int i=0; i<div; i++){
            for(int j=0; j<div; j++){
                newImg = bi.getSubimage(j*(/*this.getWidth()*/256/div), i*(/*this.getHeight()*/256/div), /*this.getWidth()*/256/div, /*this.getHeight()*/256/div);
                //System.out.println(i*j);
                images.add(newImg);
            }
        }
    }
    
    
    
	////////////////////////////////////////////
	//////////////////Getter////////////////////
	////////////////////////////////////////////
    
    public ArrayList<BufferedImage> getAnimation() {
		return images;
	}
    
    public Image getFrame(int frameIndex) {
		return images.get(frameIndex);
    	
    }
    
    
    
	////////////////////////////////////////////
	/////////////////Methods////////////////////
	////////////////////////////////////////////

	public void update() {
		if (frameCounter  == 25/*x*/) {	//wird alle x frames aktualisiert
			//if (start.AT == AnimationType.FLUG) {
				frameCounter = 0;
				if (start.animationFrameCounter < alternativeCommons.ANZAHL_FLUG_ANIMATIONEN-1) {
					++start.animationFrameCounter;	//Nicht gerade die eleganteste Loesung
				}
				else {
				start.animationFrameCounter = 0;
				}
			/*}
			else if (start.AT == AnimationType.GOTPOINT) {
				frameCounter = 0;
				if (start.animationFrameCounter < alternativeCommons.ANZAHL_GOTPOINT_ANIMATION-1) {
					++start.animationFrameCounter;	//Nicht gerade die eleganteste Loesung
				}
				else {
				start.animationFrameCounter = 0;
				start.AT = AnimationType.FLUG;
				}
			}
			else if (start.AT == AnimationType.DIE) {
				frameCounter = 0;
				if (start.animationFrameCounter < alternativeCommons.ANZAHL_DIE_ANIMATIONEN-1) {
					++start.animationFrameCounter;	//Nicht gerade die eleganteste Loesung
				}
				else {
				start.animationFrameCounter = 0;
				}
			}*/
		}
		else {
			++frameCounter;
		}
	}

	public void drawFrame(float x, float y, int frameIndex) {
		Texture texture = null;
		try {
			texture = BufferedImageUtil.getTexture("", images.get(frameIndex));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		texture.bind();
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
        glPushMatrix();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        	glTexCoord2f(0, 0);
        	glVertex2f(0, 0);
        	glTexCoord2f(1, 0);
        	glVertex2f(alternativeCommons.PLAYER_WIDTH, 0);
        	glTexCoord2f(1, 1);
        	glVertex2f(alternativeCommons.PLAYER_WIDTH, alternativeCommons.PLAYER_HEIGHT);
        	glTexCoord2f(0, 1);
        	glVertex2f(0, alternativeCommons.PLAYER_HEIGHT);
        glEnd();
        glPopMatrix();
	}



} 