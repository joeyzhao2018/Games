package joeyzhao.game.revolvingRestaurant;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Shape3dActivity extends Activity {
    private GLSurfaceView view;
    private Shape3dRenderer renderer;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create our Preview view and set it as the content of our
        // Activity
        view = new GLSurfaceView(this);
        // We want an 8888 pixel format because that's required for
        // a translucent window.
        // And we want a depth buffer.
        view.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        view.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        view.setZOrderOnTop(true);
        renderer = new Shape3dRenderer();

        setup3DScene();
        
        view.setRenderer(renderer);
        setContentView(view);
    }
    
    private void setup3DScene() {
        // Here we add some shapes to the scene
        // colors are made from 4 values:
        //                          R  G  B  A, where A is an opaque flag
        //                          |  |  |  |
        //                          v  v  v  v
        float[] red = new float[] { 1, 0, 0, 1 };
        float[] blue = new float[] { 0, 0, 1, 1f };
        float[] green = new float[] { 0, 1, 0, 1f };

        Shape3d shape1 = new Prism(0.5f, new float[][]{blue,blue,blue,blue,red,red});
        Shape3d shape2 = new Tetrahedron(0.7f, new float[][]{green,green,green,blue});

        shape1.setPosition(-0.3f, 0, 1f);
        shape2.setPosition(0.3f, 0, 1f); // will be a little above and to the
                                           // "right" of first square
        
        shape2.setRotation(10.0f,0,0);
        
       

        renderer.addShape(1, shape1);
        renderer.addShape(2, shape2);
        
    }
}
