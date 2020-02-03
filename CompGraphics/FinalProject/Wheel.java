
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

public class Wheel{

    private GLUT glut = new GLUT();

	public float[] material = { 0.14725F, 0.8995F, 0.2045F, 1.0F,      // ambient 
					0.15164F, 0.87648F, 0.02648F, 1.0F,    // diffuse 
					0.128281F, 0.855802F, 0.266065F, 1.0F, // specular 
					16.0F                                  // shininess 
			};
	public float []faceColor = {0.902f, 0.075f, 0.0f};

	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

	public void drawWheel(GL2 gl, Texture texture){
		//wheels
        gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, material, 0 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material, 4 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material, 8 );
			gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, material[12] );
		glut.glutSolidCylinder(0.3d, 0.2d, 20, 20);


	}
}