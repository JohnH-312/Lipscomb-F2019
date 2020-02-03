
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

public class Pyramid{
    public float[] material = { 0.86F, 0.552F, 0.306F, 1.0F,      // ambient 
                    			0.86F, 0.552F, 0.306F, 1.0F,    // diffuse 
                    			0.86F, 0.552F, 0.306F, 1.0F,  // specular 
                    			16F,                                  // shininess 
            };


    public float[][] verticies ={   {0.0f, 1.0f, 0.0f},         //0
									{-1.0f, 0.0f, 1.0f},      //1
									{1.0f, 0.0f, 1.0f},       //2 
									{1.0f, 0.0f, -1.0f},        //3 
									{-1.0f, 0.0f, -1.0f},      //4
    };

    public int[][] faces = {   	{0,1,2},    //0
        						{0,2,3},    //1 
       						 	{0,3,4},    //2
        						{0,4,1},    //3
    };
    
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

	public void drawPy(GL2 gl){
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, material, 0 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, material, 4 );
		gl.glMaterialfv( GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material, 8 );
		gl.glMaterialf( GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, material[12] );
		for (int i = 0; i < faces.length; i++) {
			Vx = verticies[faces[i][1]][0] - verticies[faces[i][0]][0];
			Vy = verticies[faces[i][1]][1] - verticies[faces[i][0]][1];
			Vz = verticies[faces[i][1]][2] - verticies[faces[i][0]][2];
			Wx = verticies[faces[i][2]][0] - verticies[faces[i][0]][0];
			Wy = verticies[faces[i][2]][1] - verticies[faces[i][0]][1];
			Wz = verticies[faces[i][2]][2] - verticies[faces[i][0]][2];

			gl.glBegin(GL2.GL_TRIANGLES);

			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < faces[i].length; j++) {
				int v = faces[i][j];  // Index for vertex j of face i.
				float[] vLocations = verticies[v];  // The vertex itself.
				gl.glVertex3f(vLocations[0], vLocations[1], vLocations[2]);
			}
		}
        gl.glEnd();
	}
}