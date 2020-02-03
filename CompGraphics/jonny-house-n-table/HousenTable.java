/**
 * This is now going to be my HousenTable
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

public class HousenTable extends GLJPanel implements GLEventListener{
	
	private int frameNumber = 0;  // The current frame number for an animation.

	private Camera camera;
	private GLUT glut = new GLUT();

	public float[] Tmaterial = { 0.44725F, 0.2995F, 0.2045F, 1.0F,      // ambient 
					0.45164F, 0.27648F, 0.02648F, 1.0F,    // diffuse 
					0.428281F, 0.25802F, 0.266065F, 1.0F, // specular 
					16.0F                                  // shininess 
			};

	private Texture[] textures = new Texture[1];

	/**
	 * A main routine to create and show a window that contains a
	 * panel of type HousenTable  The program ends when  the
	 * user closes the window.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Hey! Take a look at this HousenTable");
		HousenTable panel = new HousenTable();
		window.setContentPane((Container) panel);
		window.pack();
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);   
		
  
	}
	
	/**
	 * Constructor for class HousenTable.
	 */
	public HousenTable() {
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
		Floor floor = new Floor();
		HouseBox house = new HouseBox();
		


		/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					This is the main drawing part
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		
		//Here is the HousenTable

		//Light
		float[] ambientLight = { 1f, 0.f, 1.f, 0f };  // weak RED ambient 
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambientLight, 0); 

		float[] diffuseLight = { 1f,2f,1f,0f };  // multicolor diffuse 
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuseLight, 0); 

		float[] positionLight = {0f, 0.8f, 0f, 0f};
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, positionLight, 0);

		// The ground 
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.525f, 0f);
		gl.glScalef(1.25f, 1f, 1.25f);
		floor.drawFloor(gl);
		gl.glPopMatrix();

		// House
		gl.glPushMatrix();
		house.drawHouse(gl);
		gl.glPopMatrix();

		//Table
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, Tmaterial, 0 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, Tmaterial, 4 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, Tmaterial, 8 );
		gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, Tmaterial[12] );
		gl.glPushMatrix();
		gl.glRotatef(90, 1f, 0f, 0f);
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, 0.25f);
		glut.glutSolidCylinder(0.03d, 0.2d, 20, 20);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0f, 0f, 0.25f);
		glut.glutSolidCylinder(0.2d, 0.02d, 20, 20);
		gl.glPopMatrix();
		gl.glPopMatrix();

		//The hidden Gem
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -0.2f, 0.0f);
		gl.glRotatef(60, 1f, 0f, 1f);
		gl.glScalef(0.1f, 0.1f, 0.1f);
		BiPy thegem = new BiPy();
		thegem.drawBiPy(gl);
		gl.glPopMatrix();

		
	} // end display()

	public void init(GLAutoDrawable drawable) {
		   // called when the panel is created

		   
		camera = new Camera(); 	//This is the alternate camera
		camera.lookAt(5,10,30, 0,0,0, 0,1,0);
		camera.setScale(1.2);
		camera.installTrackball(this);

		   //This code is from the lights around the teapot example in the book
		   GL2 gl = drawable.getGL().getGL2();
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