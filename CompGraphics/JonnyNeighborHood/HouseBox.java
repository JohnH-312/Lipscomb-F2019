
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;


import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class HouseBox{
	public float[] material = { 0.94725F, 0.0995F, 0.0045F, 0.0F,      // ambient 
					0.95164F, 0.07648F, 0.02648F, 0.0F,    // diffuse 
					0.728281F, 0.655802F, 0.666065F, 0.0F, // specular 
					16.0F                                  // shininess 
			};


	public float[][] vertexList =
	{   {0.5f, 0.75f, 0.5f},          //0
		{-0.5f, 0.75f, 0.5f},         //1
		{-0.5f, -0.5f, 0.5f},        //2 
		{0.5f, -0.5f, 0.5f},         //3 
		{0.5f, 0.75f, -0.5f},         //4
		{-0.5f, 0.75f, -0.5f},        //5
		{-0.5f, -0.5f, -0.5f},       //6
		{0.5f, -0.5f, -0.5f},        //7
		{0f, 1f, 0.5f},			//8
		{0f, 1f, -0.5f},			//9
	};

	public double[][][] texCoordList = {
		{{1.0, 0.8}, {0.5, 1.0}, {0.0, 0.8}, {0.0, 0.0}, {1.0, 0.0}},     //front
		{{1.0, 0.8}, {0.5, 1.0}, {0.0, 0.8}, {0.0, 0.0}, {1.0, 0.0}},      //back
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //right
		{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}},     //bottom
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //left
		{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}},       //roof right
		{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}},        //roof left},
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
	public float []faceColor = {0.902f, 0.075f, 0.0f};

	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

	public void drawHouse(GL2 gl, Texture texture){
		//box
		for (int i = 0; i < faceList.length; i++) {
			Vx = vertexList[faceList[i][1]][0] - vertexList[faceList[i][0]][0];
			Vy = vertexList[faceList[i][1]][1] - vertexList[faceList[i][0]][1];
			Vz = vertexList[faceList[i][1]][2] - vertexList[faceList[i][0]][2];
			Wx = vertexList[faceList[i][2]][0] - vertexList[faceList[i][0]][0];
			Wy = vertexList[faceList[i][2]][1] - vertexList[faceList[i][0]][1];
			Wz = vertexList[faceList[i][2]][2] - vertexList[faceList[i][0]][2];

			texture.bind(gl);

			gl.glBegin(GL2.GL_POLYGON);

			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, material, 0 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material, 4 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material, 8 );
			gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, material[12] );
			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < faceList[i].length; j++) {
				int vertexNum = faceList[i][j];  // Index for vertex j of face i.
				float[] vertexCoords = vertexList[vertexNum];  // The vertex itself.
				gl.glTexCoord2d(texCoordList[i][j][0], texCoordList[i][j][1]);
				gl.glVertex3f(vertexCoords[0], vertexCoords[1], vertexCoords[2]);
			}
			gl.glEnd();
			gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
		}
		Door mydoor = new Door();
		mydoor.drawDoor(gl);

	}
}