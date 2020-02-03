
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
	public float[] material = { 0.14725F, 0.8995F, 0.2045F, 1.0F,      // ambient 
					0.15164F, 0.87648F, 0.02648F, 1.0F,    // diffuse 
					0.128281F, 0.855802F, 0.266065F, 1.0F, // specular 
					16.0F                                  // shininess 
			};


	public float[][] vertexList =
	{   {0.5f, 0.5f, 0.5f},          //UpRight Front	0
		{-0.5f, 0.5f, 0.5f},         //UpLeft Front		1
		{-0.5f, -0.5f, 0.5f},        //DownLeft Front	2
		{0.5f, -0.5f, 0.5f},         //DownRight Front	3
		{0.5f, 0.5f, -0.5f},         //UpRight Back	4
		{-0.5f, 0.5f, -0.5f},        //UpLeft Back		5
		{-0.5f, -0.5f, -0.5f},       //DownRight Back	6
		{0.5f, -0.5f, -0.5f},        //DownLeft Back	7
		{0f, 1f, 0.5f},			//Top Front Roof	8
		{0f, 1f, -0.5f},			//Top Back Roof		9
		{-0.25f, 0.685f, 0.5f},       //UpMFront Roof	10
		{-0.25f, 0.55f, 0.5f},       //DownMFront ROof	11
		{-0.25f, 0.685f, -0.5f},		//TopM Back Roof	12
		{-0.25f, 0.55f, -0.5f},		//BottmM Back Roof	13
		{-0.25f, -0.5f, 0.5f},		//SlicePtL 14
		{-0.25f, -0.25f, 0.5f},		//BLW 		15
		{-0.25f, 0.3f, 0.5f},		//TLW		16
		{0.25f, 0.3f, 0.5f},		//TRW		17
		{0.25f, -0.25f, 0.5f},		//BRW		18
		{-0.5f, 0f, 0.5f},//MUL 19
		{0.5f, 0f, 0.5f},//MUR 20
		{0.25f, -0.5f, 0.5f},//SlicePtR 21	
		{-0.25f, -0.5f, -0.5f},		//SlicePtL 22
		{-0.25f, -0.25f, -0.5f},		//BLW 		23
		{-0.25f, 0.3f, -0.5f},		//TLW		24
		{0.25f, 0.3f, -0.5f},		//TRW		25
		{0.25f, -0.25f, -0.5f},		//BRW		26
		{-0.5f, 0f, -0.5f},//MUL 27
		{0.5f, 0f, -0.5f},//MUR 28
		{0.25f, -0.5f, -0.5f},//SlicePtR 29
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
	{   {0,8,/*10,11,*/1,19,20},      //front
		{19,2,14,16},
		{17,21,3,20},
		{14,21,18,15},
		{5,/*13,12,*/9,4,28,27},      //back
		{27,6,22,24},
		{25,29,7,28},
		{22,29,26,23},
		{4,0,3,7},      //right
		{7,3,2,6},      //bottom
		{1,5,6,2},      //left
		{4,9,8,0},      //roof right
		{8,9,5,1},      //roof left1
		/*{10,12,13,11},      //roof left2
		{11,13,4,1}, */     //roof left3
		/*THE INVERSES FOR THE INSIDE OF THE HOUSE
		{20,19,1,8,0},      //front
		{16,14,2,19},
		{20,3,21,17},
		{15,18,21,14},
		{6,7,4,9,5},      //back
		{7,3,0,4},      //right
		{6,2,3,7},      //bottom
		{2,6,5,1},      //left
		{0,8,9,4},      //roof right
		{1,5,9,8},      //roof left1
		/*{10,12,13,11},      //roof left2
		{11,13,4,1}, */     //roof left3*/
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

	}
}