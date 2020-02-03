/**
 * BI-Pyramid - BUT WITH SHADING N SUCH
 * By: Jonny Hughes
 * last edited: IN class 10/14/19
 *      - currently copy of old Bi-pyramid
 *      - working to add material as well as lighting/shading
 */

import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;



public class BiPyramid extends GLJPanel implements GLEventListener{
    
    private int frameNumber = 0;  // The current frame number for an animation.

    private Camera camera;

    /**
     * A main routine to create and show a window that contains a
     * panel of type BiPyramid.  The program ends when  the
     * user closes the window.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Hey! Take a look at my Bi-Pyramid");
        BiPyramid panel = new BiPyramid();
        window.setContentPane((Container) panel);
        window.pack();
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);   
        
  
    }
    
    /**
     * Constructor for class BiPyramid.
     */
    public BiPyramid() {
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
        
        float[] gold = { 0.94725F, 0.0995F, 0.0045F, 1.0F,      // ambient 
                 0.95164F, 0.07648F, 0.02648F, 1.0F,    // diffuse 
                 0.728281F, 0.655802F, 0.666065F, 1.0F, // specular 
                 16.0F                                  // shininess 
        };
    
        
        //Here it be, my Bi-Pyramid
        gl.glScalef(1.0f, 1.0f, -1.0f);
        //gl.glRotatef(33f, 1.0f, 2.0f, 0.0f);

        //gl.glBegin(GL2.GL_TRIANGLES);           // Begin drawing the pyramid with 4 triangles

        float[][] vertexList =
            {   {0.0f, 1.0f, 0.0f},         //0
                {-0.25f, 0.0f, 0.50f},      //1
                {0.25f, 0.0f, 0.50f},       //2 
                {0.57f, 0.0f, 0.0f},        //3 
                {0.25f, 0.0f, -0.50f},      //4
                {-0.25f, 0.0f, -0.50f},     //5
                {-0.57f, 0.0f, 0.0f},       //6
                {0.0f, -1.0f, 0.0f}         //7
            };
         
        int[][] faceList =
            {   {0,1,2},    //0
                {0,2,3},    //1 
                {0,3,4},    //2
                {0,4,5},    //3
                {0,5,6},    //4 
                {0,6,1},    //5
                {7,2,1},    //6
                {7,3,2},    //7
                {7,4,3},    //8
                {7,5,4},    //9
                {7,6,5},    //10
                {7,1,6}     //11
            };
        
        /**float[][] faceColors = 
            {
                {0.698f, 0.18f, 0.239f},     //Carribean Green {0.000f, 0.847f, 0.451f}
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
                {0.698f, 0.18f, 0.239f},     //Well Read
            };*/
            float []faceColor = {0.902f, 0.075f, 0.0f};

            float Vx;
            float Vy;
            float Vz;
            float Wx;
            float Wy;
            float Wz;    
        for (int i = 0; i < faceList.length; i++) {
            //gl.glColor3f( faceColor[0], faceColor[1], faceColor[2]);  // Set color for face number i.
            
            Vx = vertexList[faceList[i][1]][0] - vertexList[faceList[i][0]][0];
            Vy = vertexList[faceList[i][1]][1] - vertexList[faceList[i][0]][1];
            Vz = vertexList[faceList[i][1]][2] - vertexList[faceList[i][0]][2];
            Wx = vertexList[faceList[i][2]][0] - vertexList[faceList[i][0]][0];
            Wy = vertexList[faceList[i][2]][1] - vertexList[faceList[i][0]][1];
            Wz = vertexList[faceList[i][2]][2] - vertexList[faceList[i][0]][2];
            
            gl.glBegin(GL2.GL_TRIANGLES);
            
            gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, gold, 0 );
            gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, gold, 4 );
            gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, gold, 8 );
            gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, gold[12] );
            gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
            for (int j = 0; j < faceList[i].length; j++) {
                int vertexNum = faceList[i][j];  // Index for vertex j of face i.
                float[] vertexCoords = vertexList[vertexNum];  // The vertex itself.
                gl.glVertex3f(vertexCoords[0], vertexCoords[1], vertexCoords[2]);
            }
            gl.glEnd();
        }

        
    } // end display()

    public void init(GLAutoDrawable drawable) {
           // called when the panel is created

           
        camera = new Camera();
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
        //gl.glEnable(GL2.GL_COLOR_MATERIAL);
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