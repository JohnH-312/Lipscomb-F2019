import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;

public class Door{

	public float[] dmaterial = { 0.94725F, 0.8995F, 0.7045F, 1.0F,      // ambient 
			0.95164F, 0.87648F, 0.2648F, 1.0F,    // diffuse 
			0.728281F, 0.655802F, 0.666065F, 1.0F, // specular 
			16.0F                                  // shininess 
	};



	public float[][] dvertexList =
	{
		{0f, -0.5f, -0.501f},
		{0.18f, -0.5f, -0.501f},
		{0.18f, -0.2f, -0.501f},
		{0f, -0.2f, -0.501f},
	};


	public int[][] dfaceList =
	{
		{3,2,1,0},
	};



	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

	public void drawDoor(GL2 gl){
		//box
		for (int i = 0; i < dfaceList.length; i++) {
			Vx = dvertexList[dfaceList[i][1]][0] - dvertexList[dfaceList[i][0]][0];
			Vy = dvertexList[dfaceList[i][1]][1] - dvertexList[dfaceList[i][0]][1];
			Vz = dvertexList[dfaceList[i][1]][2] - dvertexList[dfaceList[i][0]][2];
			Wx = dvertexList[dfaceList[i][2]][0] - dvertexList[dfaceList[i][0]][0];
			Wy = dvertexList[dfaceList[i][2]][1] - dvertexList[dfaceList[i][0]][1];
			Wz = dvertexList[dfaceList[i][2]][2] - dvertexList[dfaceList[i][0]][2];

			gl.glBegin(GL2.GL_POLYGON);

			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, dmaterial, 0 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, dmaterial, 4 );
			gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, dmaterial, 8 );
			gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, dmaterial[12] );
			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < dfaceList[i].length; j++) {
				int vertexNum = dfaceList[i][j];  // Index for vertex j of face i.
				float[] vertexCoords = dvertexList[vertexNum];  // The vertex itself.
				gl.glVertex3f(vertexCoords[0], vertexCoords[1], vertexCoords[2]);
			}
		}
		gl.glEnd();

	}
}