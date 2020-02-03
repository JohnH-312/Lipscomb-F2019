
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;


import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class SkyBox{

	private GLUT glut = new GLUT();

	public float[][] vertexList =
	{   {0.5f, 0f, 0.35f},          //UpRight Front	0
		{-0.5f, 0f, 0.35f},         //UpLeft Front		1
		{-0.5f, -0.5f, 0.35f},        //DownLeft Front	2
		{0.5f, -0.5f, 0.35f},         //DownRight Front	3
		{0.5f, 0f, -0.35f},         //UpRight Back	4
		{-0.5f, 0f, -0.35f},        //UpLeft Back		5
		{-0.5f, -0.5f, -0.35f},       //DownLeft Back	6
		{0.5f, -0.5f, -0.35f},        //DownRight Back	7
	};

	public double[][][] texCoordList = {
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //front
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},      //back
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //right
		{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}},     //bottom
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //left
		{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}},       //roof right
		{{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}},        //roof left},
	};

	public int[][] faceList =
	{   {0,1,2,3},      //Right Side of Car
		{4,5,6,7},		//Left Side of Car
		{4,0,3,7},		//Front of Car
		{1,5,6,2},		//Back of Car
		{3,2,6,7},		//Bottom of Car
		{1,0,4,5},		//top of Car
	};
	public float []faceColor = {0.902f, 0.975f, 0.9f};

	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  
	public void drawBox(GL2 gl, Texture[] textures){
		
		//box
		for (int i = 0; i < faceList.length; i++) {
			Vx = vertexList[faceList[i][1]][0] - vertexList[faceList[i][0]][0];
			Vy = vertexList[faceList[i][1]][1] - vertexList[faceList[i][0]][1];
			Vz = vertexList[faceList[i][1]][2] - vertexList[faceList[i][0]][2];
			Wx = vertexList[faceList[i][2]][0] - vertexList[faceList[i][0]][0];
			Wy = vertexList[faceList[i][2]][1] - vertexList[faceList[i][0]][1];
			Wz = vertexList[faceList[i][2]][2] - vertexList[faceList[i][0]][2];


            gl.glActiveTexture(GL2.GL_TEXTURE0);
			textures[i+1].enable(gl);
			textures[i+1].bind(gl);      
			gl.glUniform1i(textures[i].getTextureObject(gl),0);
			gl.glBegin(GL2.GL_POLYGON);

			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < faceList[i].length; j++) {
				int vertexNum = faceList[i][j];  // Index for vertex j of face i.
				float[] vertexCoords = vertexList[vertexNum];  // The vertex itself.
                gl.glMultiTexCoord2d(GL2.GL_TEXTURE0, texCoordList[i][j][0], texCoordList[i][j][1]);
				gl.glVertex3f(vertexCoords[0], vertexCoords[1], vertexCoords[2]);
			}
			gl.glEnd();
		}

	}
}