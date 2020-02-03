
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Road{
	public float[] material = { 0.64725F, 0.5995F, 0.4045F, 1.0F,      // ambient 
					0.65164F, 0.57648F, 0.42648F, 1.0F,    // diffuse 
					0.628281F, 0.555802F, 0.466065F, 1.0F, // specular 
					1.0F                                  // shininess 
			};


	public float[][] vertexList =
	{   {1f, 0f, 1f},         //0
		{1f, 0f, -1f},      //1
		{-1f, 0f, -1f},       //2 
		{-1f, 0f, 1f},        //3 
	};

	public double[][][] texCoordList = {
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //front
	};

	public int[][] faceList =
	{   {0,1,2,3},    //0
	};
	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  


	//This is the main drawing function
	public void drawRoad(GL2 gl, Texture texture){
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, material, 0 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material, 4 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material, 8 );
		gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, material[12] );
		for (int i = 0; i < faceList.length; i++) {
			Vx = vertexList[faceList[i][1]][0] - vertexList[faceList[i][0]][0];
			Vy = vertexList[faceList[i][1]][1] - vertexList[faceList[i][0]][1];
			Vz = vertexList[faceList[i][1]][2] - vertexList[faceList[i][0]][2];
			Wx = vertexList[faceList[i][2]][0] - vertexList[faceList[i][0]][0];
			Wy = vertexList[faceList[i][2]][1] - vertexList[faceList[i][0]][1];
			Wz = vertexList[faceList[i][2]][2] - vertexList[faceList[i][0]][2];

			gl.glActiveTexture(GL2.GL_TEXTURE0);
			texture.enable(gl);
			texture.bind(gl);      
			gl.glUniform1i(texture.getTextureObject(gl),0);

			gl.glBegin(GL2.GL_POLYGON);
			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < faceList[i].length; j++) {
				int vertexNum = faceList[i][j];  // Index for vertex j of face i.
				float[] vertexCoords = vertexList[vertexNum];  // The vertex itself.
				gl.glMultiTexCoord2d(GL2.GL_TEXTURE0, texCoordList[i][j][0], texCoordList[i][j][1]);
				gl.glMultiTexCoord2d(GL2.GL_TEXTURE1, texCoordList[i][j][0], texCoordList[i][j][1]);
				gl.glVertex3f(vertexCoords[0], vertexCoords[1], vertexCoords[2]);
			}
			gl.glEnd();
			gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
		}
	}
}