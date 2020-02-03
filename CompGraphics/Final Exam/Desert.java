
import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Desert{
	 public float[] material = { 1.0F, 1.0F, 1.0F, 1.0F,      // ambient 
                    			1.0F, 1.0F, 1.0F, 1.0F,    // diffuse 
                    			1.0F, 1.0F, 1.0F, 1.0F, // specular 
                    			16F,                                  // shininess 
            };


	public float[][] verticies =
	{   {1f, 0f, 1f},         //0
		{1f, 0f, -1f},      //1
		{-1f, 0f, -1f},       //2 
		{-1f, 0f, 1f},        //3 
	};

	public double[][][] texCoords = {
		{{1.0, 1.0}, {0.0, 1.0}, {0.0, 0.0}, {1.0, 0.0}},     //front
	};

	public int[][] faces =
	{   {0,1,2,3},    //0
	};
	
	public float Vx;
	public float Vy;
	public float Vz;
	public float Wx;
	public float Wy;
	public float Wz;  

	//This is the main drawing function
	public void drawDesert(GL2 gl, Texture texture){
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

			gl.glActiveTexture(GL2.GL_TEXTURE0);
			texture.enable(gl);
			texture.bind(gl);      
			gl.glUniform1i(texture.getTextureObject(gl),0);

			gl.glBegin(GL2.GL_POLYGON);
			gl.glNormal3f(Vy*Wz - Vz*Wy, Vz*Wx - Vx*Wz, Vx*Wy - Vy*Wx);
			for (int j = 0; j < faces[i].length; j++) {
				int v = faces[i][j];  // Index for vertex j of face i.
				float[] vLocations = verticies[v];  // The vertex itself.
				gl.glMultiTexCoord2d(GL2.GL_TEXTURE0, texCoords[i][j][0], texCoords[i][j][1]);
				gl.glMultiTexCoord2d(GL2.GL_TEXTURE1, texCoords[i][j][0], texCoords[i][j][1]);
				gl.glVertex3f(vLocations[0], vLocations[1], vLocations[2]);
			}
			gl.glEnd();
			gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);
		}
	}
}