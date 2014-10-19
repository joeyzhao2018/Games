package joeyzhao.game.revolvingRestaurant;

public class Square extends Shape3d {
    /**
     * Computes the 4 vertices required for a 2d square in the xy plane of sideLength,
     * assuming a center of 0, 0, 0.
     *  
     * @param sideLength
     * @return
     */
    private static final float[] squareVertices(float sideLength) {
        float halfSideLength = sideLength/2f;
        float[] vertices = new float[]{
                    -halfSideLength,  halfSideLength, 0,  // 0, Top Left
                    -halfSideLength, -halfSideLength, 0,  // 1, Bottom Left
                     halfSideLength, -halfSideLength, 0,  // 2, Bottom Right
                     halfSideLength,  halfSideLength, 0};  // 3, Top Right
        return vertices;
    }
    
    public Square(float sideLength, float[] color) {
        super(squareVertices(sideLength),
              new short[]{ 0, 1, 2, 0, 2, 3 }, // the two triangles
              new float[][]{color});
    }

}
