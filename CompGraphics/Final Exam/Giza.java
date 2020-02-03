/**
 * Giza
 * Jonny Hughes
 */

import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import java.net.URL;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import com.jogamp.opengl.awt.GLJPanel;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Giza extends GLJPanel implements GLEventListener {

	private Camera camera;
	private GLUT glut = new GLUT();

	private Texture[] textures = new Texture[1];
	private String[] texNames = {"satellite.JPG"};

	/**
	 * A main routine to create and show a window that contains a
	 * panel of type Giza The program ends when  the
	 * user closes the window.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Welcome to the Ancienct Pyramids of Giza");
		Giza panel = new Giza();
		window.setContentPane((Container) panel);
		window.pack();
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);   
		
  
	}
	
	/**
	 * Constructor for class Giza
	 */
	public Giza() {
		super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
		setPreferredSize( new Dimension(800,800) );
		addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
	}



	/**
	 * The display method is called when the panel needs to be redrawn.
	 * The is where the code goes for drawing the image, using OpenGL commands.
	 */
	public void display(GLAutoDrawable drawable) {    
		GL2 gl = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
		camera.apply(gl);
		gl.glClearColor( 0, 0, 0, 1 );  // (In fact, this is the default.)
		gl.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		

		//These are some custom objects which are used to define shapes to draw
		Desert sand = new Desert();
		Pyramid BigPy = new Pyramid();
		Pyramid MedPy = new Pyramid();
		Pyramid SmallPy = new Pyramid();
		float heightMod = 1.5f;
		

		/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					This is the main drawing part
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		
		// The Desert
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.525f, 0f);
		gl.glRotatef(90, 0f, 1f, 0f);
		sand.drawDesert(gl, textures[0]);
		gl.glPopMatrix();


		// Big Pyramid
		gl.glPushMatrix();
		gl.glTranslatef(.435F, -0.525f, -.65F);
		gl.glScalef(.225f, .225f*heightMod, .225f);
		BigPy.drawPy(gl);
		gl.glPopMatrix();

		// Medium Pyramid
		gl.glPushMatrix();
		gl.glTranslatef(-.13F, -0.525f, -0.03F);
		gl.glScalef(.2f, .2f*heightMod, .2f);
		MedPy.drawPy(gl);
		gl.glPopMatrix();

		// Small Pyramid
		gl.glPushMatrix();
		gl.glTranslatef(-0.56F, -0.525f, .63F);
		gl.glScalef(.075f, .075f*heightMod, .075f);
		SmallPy.drawPy(gl);
		gl.glPopMatrix();


		
	} // end display()

	public void init(GLAutoDrawable drawable) {
		   // called when the panel is created
		camera = new Camera(); 	//This is the alternate camera
		camera.lookAt(5,10,30, 0,0,0, 0,1,0);
		camera.setScale(1);
		camera.installTrackball(this);

		GL2 gl = drawable.getGL().getGL2();
		for (int i = 0; i < texNames.length; i++) {
			try {
				URL textureURL;
				textureURL = getClass().getClassLoader().getResource(texNames[i]);
				if (textureURL != null) {
					BufferedImage img = ImageIO.read(textureURL);
					ImageUtil.flipImageVertically(img);
					textures[i] = AWTTextureIO.newTexture(GLProfile.getDefault(), img, true);
					textures[i].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
					textures[i].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				}
			textures[i].enable(gl);
		}

		//This code is from the lights around the teapot example in the book
		gl.glClearColor(0, 0, 0, 1); 
		gl.glEnable(GL2.GL_DEPTH_TEST); 
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_LIGHT1);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE, 1);
		gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, 1);
		gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 64);


	}

	public void dispose(GLAutoDrawable drawable) {
			// called when the panel is being disposed
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
			// called when user resizes the window

	}

}