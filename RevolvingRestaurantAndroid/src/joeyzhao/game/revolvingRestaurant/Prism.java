package joeyzhao.game.revolvingRestaurant;

public class Prism extends Shape3d{

	private static final float[] prismVertices(float sideLength) {
		float halfSideLength = sideLength/2f;
		float[] vertices = new float[]{
				-halfSideLength,  halfSideLength, 0,  // 0, Top Left
				-halfSideLength, -halfSideLength, 0,  // 1, Bottom Left
				halfSideLength, -halfSideLength, 0,  // 2, Bottom Right
				halfSideLength,  halfSideLength, 0,  // 3, Top Right
				0,halfSideLength, (float) (halfSideLength*Math.sqrt(3.0)), //4. roof 1
				0,-halfSideLength,(float) (halfSideLength*Math.sqrt(3.0)),//5. roof 2

		};
		return vertices;
	}

	public Prism(float sideLength, float[][] colors) {


		super(prismVertices(sideLength),
				new short[]{ 0, 1, 2, 0, 2, 3 // the two triangles
			, 1, 2, 5, 0, 3, 4//the two triangular sides
			,0,1,5,0,4,5//one of the rectangular sides
			,2,3,4,2,4,5//the other one
			}, colors);
	}

}
