
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;


import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Car1{

	private GLUT glut = new GLUT();
	public float[] material = { 0.94725F, 0.2995F, 0.2045F, 1.0F,      // ambient 
					0.95164F, 0.27648F, 0.02648F, 1.0F,    // diffuse 
					0.928281F, 0.255802F, 0.266065F, 1.0F, // specular 
					16.0F                                  // shininess 
			};
	public float[] Cmaterial = { 0.04725F, 0.0995F, 0.0045F, 1.0F,      // ambient 
					0.05164F, 0.07648F, 0.02648F, 1.0F,    // diffuse 
					0.028281F, 0.005802F, 0.066065F, 1.0F, // specular 
					16.0F                                  // shininess 
			};

	public float[][] vertexList =
	{   {0.75f, 0f, 0.35f},          //0
		{-0.75f, 0f, 0.35f},         //1
		{-0.75f, -0.25f, 0.35f},        //2 
		{0.75f, -0.25f, 0.35f},         //3 
		{0.75f, 0f, -0.35f},         //4
		{-0.75f, 0f, -0.35f},        //5
		{-0.75f, -0.25f, -0.35f},       //6
		{0.75f, -0.25f, -0.35f},        //7
		{0.3f, 0.2f, 0.35f},			//8
		{0.3f, 0.2f, -0.35f},			//9
	};

	public int[][] faceList =
	{   {0,8,1,2,3},      //front
		{5,9,4,7,6},      //back
		{4,0,3,7},      //right
		{7,3,2,6},      //bottom
		{1,5,6,2},      //left
		{4,9,8,0},      //roof right
		{9,5,1,8},      //roof left
	};
	public float []faceColor = {0.902f, 0.975f, 0.9f};

	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

	public void drawWheel(GL2 gl, Texture texture){
		glut.glutSolidCylinder(0.5d, 0.25d, 20, 20);
	}

	public void drawCar1(GL2 gl, Texture texture){
		//wheels

		//This resets the material to a generic Cmaterial
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, Cmaterial, 0 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, Cmaterial, 4 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, Cmaterial, 8 );
		gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, Cmaterial[12] );
		gl.glEnable(GL2.GL_COLOR_MATERIAL);

		gl.glColor3f(0.0f, 0.0f, 0.0f);

		//wheel 1
		gl.glPushMatrix();
		gl.glTranslatef(.56F, -0.375f, .35F);
		gl.glScalef(.25f, .25f, .25f);
		drawWheel(gl, texture);
		gl.glPopMatrix();

		// wheel 2
		gl.glPushMatrix();
		gl.glTranslatef(.56F, -0.375f, -.4F);
		gl.glScalef(.25f, .25f, .25f);
		drawWheel(gl, texture);
		gl.glPopMatrix();

		// wheel 3
		gl.glPushMatrix();
		gl.glTranslatef(-.56F, -0.375f, -.4F);
		gl.glScalef(.25f, .25f, .25f);
		drawWheel(gl, texture);
		gl.glPopMatrix();

		// wheel 4
		gl.glPushMatrix();
		gl.glTranslatef(-.56F, -0.375f, .35F);
		gl.glScalef(.25f, .25f, .25f);
		drawWheel(gl, texture);
		gl.glPopMatrix();

		gl.glDisable(GL2.GL_COLOR_MATERIAL);

		// main body of vehicle drawn
		for (int i = 0; i < faceList.length; i++) {
			Vx = vertexList[faceList[i][1]][0] - vertexList[faceList[i][0]][0];
			Vy = vertexList[faceList[i][1]][1] - vertexList[faceList[i][0]][1];
			Vz = vertexList[faceList[i][1]][2] - vertexList[faceList[i][0]][2];
			Wx = vertexList[faceList[i][2]][0] - vertexList[faceList[i][0]][0];
			Wy = vertexList[faceList[i][2]][1] - vertexList[faceList[i][0]][1];
			Wz = vertexList[faceList[i][2]][2] - vertexList[faceList[i][0]][2];

			gl.glBegin(GL2.GL_POLYGON);
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, material, 0 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material, 4 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material, 8 );
			gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, material[12] );
			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < faceList[i].length; j++) {
				int vertexNum = faceList[i][j];  // Index for vertex j of face i.
				float[] vertexCoords = vertexList[vertexNum];  // The vertex itself.
				gl.glVertex3f(vertexCoords[0], vertexCoords[1], vertexCoords[2]);
			}
			gl.glEnd();
		}

	}
}