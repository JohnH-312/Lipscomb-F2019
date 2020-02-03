/**
 * This is now going to be my Road Scene
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

public class RoadScene extends GLJPanel implements GLEventListener, KeyListener{
	
	private int frameNumber = 0;  // The current frame number for an animation.

	private Camera2 camera;
	private GLUT glut = new GLUT();

	public float[] Tmaterial = { 0.44725F, 0.2995F, 0.2045F, 1.0F,      // ambient 
					0.45164F, 0.27648F, 0.02648F, 1.0F,    // diffuse 
					0.428281F, 0.25802F, 0.266065F, 1.0F, // specular 
					16.0F                                  // shininess 
			};

	private Texture[] textures = new Texture[7];
	private String[] texNames = {"RoadTex.jpg", "SkyImg/back.png", "SkyImg/bottom.png", "SkyImg/front.png", "SkyImg/left.png", "SkyImg/right.png", "SkyImg/top.png"};

	/**
	 * A main routine to create and show a window that contains a
	 * panel of type RoadScene  The program ends when  the
	 * user closes the window.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Hey! Take a look at this Road Scene");
		RoadScene panel = new RoadScene();
		window.setContentPane((Container) panel);
		window.pack();
		window.setLocation(50,50);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);   
		
  
	}
	
	/**
	 * Constructor for class RoadScene.
	 */
	public RoadScene() {
		super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
		setPreferredSize( new Dimension(800,800) );
		addKeyListener(this);
		addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
	}

	

	//-------------------- GLEventListener Methods -------------------------

	public static float distDriven1 = 0.0f;
	public static float distDriven2 = 0.0f;
	public static float distDriven3 = 0.0f;
	public static float distDriven4 = 0.0f;
	public static float distDriven5 = 0.0f;
	public static float distDriven6 = 0.0f;
	public static float yMove = 0.0f;
	public static float xMove = 0.0f;

	/**
	 * The display method is called when the panel needs to be redrawn.
	 * The is where the code goes for drawing the image, using OpenGL commands.
	 */
	public void display(GLAutoDrawable drawable) {    

		//updates the variables which determine the location/speed of cars
		distDriven1 = 0.0015f + distDriven1%.45f;
		distDriven2 = 0.0015f + distDriven2%.45f;
		distDriven3 = 0.00175f + distDriven3%.45f;
		distDriven4 = 0.0015f + distDriven4%.45f;
		distDriven5 = 0.001f + distDriven5%.45f;
		distDriven6 = 0.0015f + distDriven6%.45f;

		
		GL2 gl = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
		camera.apply(gl);
		gl.glClearColor( 0, 0, 0, 1 );  // (In fact, this is the default.)
		gl.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		

		//These are some custom objects which are used to define shapes to draw
		Road road = new Road();
		Car1 c1 = new Car1();
		Car1 c2 = new Car1();
		Car1 c3 = new Car1();
		Car1 c4 = new Car1();
		Car1 c5 = new Car1();
		Car1 c6 = new Car1();
		Frog frogger = new Frog();
		

		/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					This is the main drawing part
		 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		
		// The ground 
		gl.glPushMatrix();
		gl.glTranslatef(0f, -0.0525f, 0f);
		gl.glScalef(.20f, .1f, .20f);
		gl.glRotatef(90, 0f, 1f, 0f);
		road.drawRoad(gl, textures[0]);
		gl.glPopMatrix();


		// Car 1
		gl.glPushMatrix();
		gl.glTranslatef(.195F-distDriven1, -0.0375f, -.065F);
		gl.glScalef(.025f, .025f, .025f);
		gl.glRotatef(180, 0f, 1f, 0f);	//other side of road, flipped
		c1.drawCar1(gl, textures[0]);
		gl.glPopMatrix();

		// Car 2
		gl.glPushMatrix();
		gl.glTranslatef(.13F-distDriven2, -0.0375f, -0.065F);
		gl.glScalef(.025f, .025f, .025f);
		gl.glRotatef(180, 0f, 1f, 0f);	//other side of road, flipped
		c2.drawCar1(gl, textures[0]);
		gl.glPopMatrix();

		// Car 3
		gl.glPushMatrix();
		gl.glTranslatef(.16F-distDriven3, -0.0375f, -.025F);
		gl.glScalef(.025f, .025f, .025f);
		gl.glRotatef(180, 0f, 1f, 0f);	//other side of road, flipped
		c3.drawCar1(gl, textures[0]);
		gl.glPopMatrix();

		// Car 4
		gl.glPushMatrix();
		gl.glTranslatef(-.295F+distDriven4, -0.0375f, .065F);
		gl.glScalef(.025f, .025f, .025f);
		c4.drawCar1(gl, textures[0]);
		gl.glPopMatrix();

		// Car 5
		gl.glPushMatrix();
		gl.glTranslatef(-.26F+distDriven5, -0.0375f, 0.03F);
		gl.glScalef(.025f, .025f, .025f);
		c5.drawCar1(gl, textures[0]);
		gl.glPopMatrix();

		// Car 6
		gl.glPushMatrix();
		gl.glTranslatef(-.23F+distDriven6, -0.0375f, .065f);
		gl.glScalef(.025f, .025f, .025f);
		c6.drawCar1(gl, textures[0]);
		gl.glPopMatrix();

		//Frog
		gl.glPushMatrix();
		gl.glTranslatef(-.0F+xMove, -0.0475f, .1f + yMove);
		gl.glScalef(.0175f, .0175f, .0175f);
		gl.glRotatef(-90, 0f, 1f, 0f);	//other side of road, flipped
		frogger.drawFrog(gl);
		gl.glPopMatrix();


		
	} // end display()

	public void init(GLAutoDrawable drawable) {
		   // called when the panel is created
		camera = new Camera2(); 	//This is the alternate camera
		camera.lookAt(5,10,30, 0,0,0, 0,1,0);
		camera.setScale(.12);
		camera.installTrackball(this);

		// Sets up animator object
		FPSAnimator animator = new FPSAnimator(30);
		animator.add(drawable);
		animator.start();

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
	
// ------------ Support for keyboard handling  ------------
// 		FROM ECK JOGL Starter.java

    /**
     * Called when the user presses any key on the keyboard, including
     * special keys like the arrow keys, the function keys, and the shift key.
     * Note that the value of key will be one of the constants from
     * the KeyEvent class that identify keys such as KeyEvent.VK_LEFT,
     * KeyEvent.VK_RIGHT, KeyEvent.VK_UP, and KeyEvent.VK_DOWN for the arrow
     * keys, KeyEvent.VK_SHIFT for the shift key, and KeyEvent.VK_F1 for a
     * function key.
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();  // Tells which key was pressed.

		if (key == KeyEvent.VK_S) {
			yMove += 0.035;
		}

		if (key == KeyEvent.VK_W) {
			yMove -= 0.035;
		}

		if (key == KeyEvent.VK_A) {
			xMove -= 0.035;
		}

		if (key == KeyEvent.VK_D) {
			xMove += 0.035;
		}
        // TODO:  Add code to respond to key presses.
        //display.repaint();  // Causes the display() function to be called.
    }

    /**
     * Called when the user types a character.  This function is called in
     * addition to one or more calls to keyPressed and keyTyped. Note that ch is an
     * actual character such as 'A' or '@'.
     */
    public void keyTyped(KeyEvent e) { 
        char ch = e.getKeyChar();  // Which character was typed.
        // TODO:  Add code to respond to the character being typed.
        //display.repaint();  // Causes the display() function to be called.
    }

    /**
     * Called when the user releases any key.
     */
    public void keyReleased(KeyEvent e) { 
    }

	
}