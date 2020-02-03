/**
 * This is now going to be my neighborhood
 * Jonny Hughes
 */

import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;

import java.net.URL;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import com.jogamp.opengl.awt.GLJPanel;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class NeighborHood extends GLJPanel implements GLEventListener{
	
	private int frameNumber = 0;  // The current frame number for an animation.

	private Camera2 camera;

	private Texture[] textures = new Texture[1];

	/**
	 * A main routine to create and show a window that contains a
	 * panel of type NeighborHood  The program ends when  the
	 * user closes the window.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Hey! Take a look at this neighborhood");
		NeighborHood panel = new NeighborHood();
		window.setContentPane((Container) panel);
		window.pack();
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);   
		
  
	}
	
	/**
	 * Constructor for class NeighborHood.
	 */
	public NeighborHood() {
		super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
		setPreferredSize( new Dimension(500,500) );
		addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
	}

	

	//-------------------- GLEventListener Methods -------------------------

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
		HouseBox h1 = new HouseBox();
		Floor floor = new Floor();
		House2 h2 = new House2();
		


		/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					This is the main drawing part
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		
		//Here is the neighborhood
		gl.glScalef(1.0f, 1.0f, -1.0f);     //This swaps z and -z


		// The ground 
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.125f, 0f);
		gl.glScalef(1.25f, 1f, 1.25f);
		floor.drawFloor(gl);
		gl.glPopMatrix();


		// house 1
		gl.glPushMatrix();
		gl.glTranslatef(.95F, 0F, .3F);
		gl.glScalef(.25f, .25f, .25f);
		h1.drawHouse(gl, textures[0]);
		gl.glPopMatrix();

		// House 2
		gl.glPushMatrix();
		gl.glTranslatef(.3F, 0F, -0.1F);
		gl.glScalef(.25f, .25f, .25f);
		h1.drawHouse(gl, textures[0]);
		gl.glPopMatrix();

		// House 3
		gl.glPushMatrix();
		gl.glTranslatef(.6F, 0F, .7F);
		gl.glScalef(.25f, .25f, .25f);
		h1.drawHouse(gl, textures[0]);
		gl.glPopMatrix();

		// House 4
		gl.glPushMatrix();
		gl.glTranslatef(-.95F, 0F, .3F);
		gl.glScalef(.25f, .25f, .25f);
		h2.drawHouse(gl);
		gl.glPopMatrix();

		// House 5
		gl.glPushMatrix();
		gl.glTranslatef(-.6F, 0F, -0.1F);
		gl.glScalef(.25f, .25f, .25f);
		h2.drawHouse(gl);
		gl.glPopMatrix();

		// House 6
		gl.glPushMatrix();
		gl.glTranslatef(-.3F, 0F, .7f);
		gl.glScalef(.25f, .25f, .25f);
		h2.drawHouse(gl);
		gl.glPopMatrix();

		//The hidden Gem
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -0.3f, 0.0f);
		gl.glScalef(0.1f, 0.1f, 0.1f);
		BiPy thegem = new BiPy();
		thegem.drawBiPy(gl);
		gl.glPopMatrix();

		
	} // end display()

	public void init(GLAutoDrawable drawable) {
		   // called when the panel is created

		   
		camera = new Camera2(); 	//This is the alternate camera
		camera.lookAt(5,10,30, 0,0,0, 0,1,0);
		camera.setScale(1.2);
		camera.installTrackball(this);

		   //This code is from the lights around the teapot example in the book
		   GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0, 0, 0, 1);  
		gl.glEnable(GL2.GL_DEPTH_TEST); 
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, 1);
		gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 64);

		try {
			URL textureURL;
			textureURL = getClass().getClassLoader().getResource("bricks.jpg");
			if (textureURL != null) {
				//textures[0] = TextureIO.newTexture(textureURL, true, null);  // Alternative loader, gives upside down textures!
				BufferedImage img = ImageIO.read(textureURL);
				ImageUtil.flipImageVertically(img);
				textures[0] = AWTTextureIO.newTexture(GLProfile.getDefault(), img, true);
				textures[0].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
				textures[0].setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
            }
		textures[0].enable(gl);
	}

	public void dispose(GLAutoDrawable drawable) {
			// called when the panel is being disposed
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
			// called when user resizes the window

	}
	
	
}