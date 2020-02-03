
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.FPSAnimator;

public class BiPy{
    public float[] material = { 0.94725F, 0.0995F, 0.0045F, 1.0F,      // ambient 
                    0.95164F, 0.07648F, 0.02648F, 1.0F,    // diffuse 
                    0.728281F, 0.655802F, 0.666065F, 1.0F, // specular 
                    16.0F                                  // shininess 
            };


    public float[][] vertexList =
    {   {0.0f, 1.0f, 0.0f},         //0
        {-0.25f, 0.0f, 0.50f},      //1
        {0.25f, 0.0f, 0.50f},       //2 
        {0.57f, 0.0f, 0.0f},        //3 
        {0.25f, 0.0f, -0.50f},      //4
        {-0.25f, 0.0f, -0.50f},     //5
        {-0.57f, 0.0f, 0.0f},       //6
        {0.0f, -1.0f, 0.0f}         //7
    };

    public int[][] faceList =
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
    public float []faceColor = {0.902f, 0.075f, 0.0f};

    
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

    

	public void drawBiPy(GL2 gl){
		for (int i = 0; i < faceList.length; i++) {
			//gl.glColor3f( faceColor[0], faceColor[1], faceColor[2]);  // Set color for face number i.

			Vx = vertexList[faceList[i][1]][0] - vertexList[faceList[i][0]][0];
			Vy = vertexList[faceList[i][1]][1] - vertexList[faceList[i][0]][1];
			Vz = vertexList[faceList[i][1]][2] - vertexList[faceList[i][0]][2];
			Wx = vertexList[faceList[i][2]][0] - vertexList[faceList[i][0]][0];
			Wy = vertexList[faceList[i][2]][1] - vertexList[faceList[i][0]][1];
			Wz = vertexList[faceList[i][2]][2] - vertexList[faceList[i][0]][2];

			gl.glBegin(GL2.GL_TRIANGLES);

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
		}
        gl.glEnd();
	}
}