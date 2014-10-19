package joeyzhao.game.revolvingRestaurant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class Shape3dRenderer implements GLSurfaceView.Renderer {
    // with a 45degree viewing angle this works out to 2.4142135623731 total depth for
    // the frustrum if we want depth to be 2x2 in the x and y dimension:
    //   ^   /\
    //  2.4 /  \
    //   v ------
    //     <-2 ->
    static final float DEPTH = 2.4142135623731f;
    
    
    private Map<Object, Shape3d> shapes;
    private long timer;
    
    public Shape3dRenderer() {
        shapes = new HashMap<Object, Shape3d>();
        this.timer=System.currentTimeMillis();
    }
    
    // NOTE -- these 4 methods are synchronized to avoid mutation of the list of
  //    shapes while the GL system is rendering the scene
    public synchronized void addShape(Object identifier, Shape3d shape) {
        shapes.put(identifier, shape);
    }
    
    public synchronized Shape3d getShape(Object identifier) {
        return shapes.get(identifier);
    }
    
    public synchronized void removeShape(Object identifier) {
    	shapes.remove(identifier);
    }
    
    public synchronized Set<Object> getAllShapes() {
    	return new HashSet<Object>(shapes.keySet());
    }
    
    public synchronized void clearShapes() {
        shapes.clear();
    }
    
    public synchronized void onDrawFrame(GL10 gl) {
        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT |
                   GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        
        // now draw each 3D shape
        float elapsedTime =(float) ((System.currentTimeMillis() - this.getTimer()))/1000.0f;
        for (Shape3d shape : shapes.values()) {
            recenter(gl); // need to recenter between each shape drawing          
            if (shape.getZ() > 0f) {
            	shape.setPosition(shape.getX(), shape.getY(), Math.max(0f, shape.getZ()-0.5f*elapsedTime));
				shape.setRotation(shape.getAngleX(),shape.getAngleY()+180.0f*elapsedTime,shape.getAngleZ());
				shape.draw(gl);
			}
			else{
				shape.setPosition(shape.getX(), shape.getY(), 0f);
				shape.draw(gl);
			}             
        }
        this.setTimer(System.currentTimeMillis());
    }
    // END synchronization
    
    // Code from here down is basic setup for the 3D scene.
    // You can read more about the basics of 3D scene setup at:
    //  http://www.ntu.edu.sg/home/ehchua/programming/android/Android_3D.html
    
    public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	/**
     * Re-centers the drawing so that drawing each shape does not depend on 
     * other shapes.
     * 
     * @param gl
     */
    private void recenter(GL10 gl) {
        gl.glLoadIdentity();
        // forces anything with Z=0 to draw at this DEPTH
        gl.glTranslatef(0, 0, -DEPTH);
    }
    
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        /*
         * By default, OpenGL enables features that improve quality
         * but reduce performance. One might want to tweak that
         * especially on software renderer.
         */
        gl.glDisable(GL10.GL_DITHER);

        /*
         * Allow GL to check the depth of a polygon in a scene so as
         * to draw triangles in a way that we see the ones "on top"
         */
        gl.glEnable(GL10.GL_DEPTH_TEST);
        
        /*
         * Some one-time OpenGL initialization can be made here
         * probably based on features of this particular context
         */
         gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                 GL10.GL_FASTEST);
 
         gl.glClearColor(0,0,0,0);
         gl.glShadeModel(GL10.GL_SMOOTH);
    }
    
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);

        /*
         * Set our projection matrix. This doesn't have to be done
         * each time we draw, but usually a new projection needs to
         * be set when the viewport is resized.
         */

        //float ratio = (float) width / (float)height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        
        // allow the 2x2x2 box to be visible (and farther z values)
        GLU.gluPerspective(gl, 45, 1, DEPTH-2f, 5f);
//        gl.glFrustumf(-1, 1, -1, 1, 2, 4);
//        GLU.gluLookAt(gl, 0,0,4,0,0,3,0,1,0);
        
        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset
   }
}
