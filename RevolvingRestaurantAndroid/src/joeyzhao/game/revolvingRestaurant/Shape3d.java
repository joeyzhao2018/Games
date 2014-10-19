package joeyzhao.game.revolvingRestaurant;
import java.nio.ByteBuffer;

import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * A vertex shaded 3d shape.
 */
public class Shape3d {
	//rotation angle
	private float angleX;
	private float angleY;
	private float angleZ;
	
    public float getAngleX() {
		return angleX;
	}


	public float getAngleY() {
		return angleY;
	}


	public float getAngleZ() {
		return angleZ;
	}

	

	// Our vertices.
    private float[] vertices;

    // The order to connect them.
    private short[] indices;
    
    // The colors
    private float[] colors;

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;
    
    // Our color buffer
    private FloatBuffer colorBuffer;
    
    // Position within scene
    private float x;
    private float y;
    private float z;
    
    /**
     * Creates a Shape3d with the given vertices, indices and colors.
     * If colors has less rows than the number of vertices, the colors
     * are repeated for each set of vertices.  Thus you can create a Shape3d
     * with the same color for each vertex (such as red) like this:
     * 
     * new Shape3d(vertices, indices, new float[][]{{1,0,0,1}})
     * 
     * because the color in row 1 of colors will be repeated for each vertex.
     * 
     * @param vertices
     * @param indices
     * @param colors
     */
    public Shape3d(float[] vertices, short[] indices, float[][] colors) {
        // initialize x, y, and z to 0
        x = 0;
        y = 0;
        z = 0; 
        
        // copy the color to each colorPerVertice
        float[] colorPerVertice = new float[vertices.length*4];
        for (int i = 0; i < vertices.length; i++) {
            System.arraycopy(colors[i%colors.length], 0, colorPerVertice, i*4, 4);
        }
        
        this.vertices = vertices;
        this.indices = indices;
        this.colors = colorPerVertice;
        
        // a float is 4 bytes, therefore we multiply the number of
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(this.vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(this.vertices);
        vertexBuffer.position(0);

        // short is 2 bytes, therefore we multiply the number of
        // vertices with 2.
        ByteBuffer ibb = ByteBuffer.allocateDirect(this.indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(this.indices);
        indexBuffer.position(0);
        
        // an float is 4 bytes, therefore we multiply the number of
        // colors with 4.
        ByteBuffer cbb = ByteBuffer.allocateDirect(this.colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(this.colors);
        colorBuffer.position(0);
    }

    /**
     * This function draws our square on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        
        transform(gl);
        
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
        
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        
        }
    
    /**
     * This method can be overridden to provide additional transformations
     * prior to drawing the triangles for the object
     * 
     * @param gl
     */
    protected void transform(GL10 gl) {
        gl.glTranslatef(x, y, z);
        gl.glRotatef(angleX, 0.1f,0,0);
        gl.glRotatef(angleY,0,0.1f,0);
        gl.glRotatef(angleZ, 0, 0, 0.1f);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
    
    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setRotation(float x, float y,float z){
    	this.angleX=x;
    	this.angleY=y;
    	this.angleZ=z;
    }
}
