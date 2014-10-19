package joeyzhao.game.revolvingRestaurant;

public class Tetrahedron extends Shape3d{
	private static final float[] tetrahedronVertices(float sideLength) {
		float halfSideLength = sideLength/2f;
		float[] vertices = new float[]{
				0,  halfSideLength, 0,  // 0, Top in xy plane
				-halfSideLength, (float) (-halfSideLength/Math.sqrt(3)), 0,  // 1, Bottom Left in xy plane
				halfSideLength, (float) (-halfSideLength/Math.sqrt(3)), 0,  // 2, Bottom Right in xy plane
				0,  0, (float) (2*Math.sqrt(3)/3*halfSideLength),  // 3, acme			
		};
		return vertices;
	}

	public Tetrahedron(float sideLength, float[][]colors) {


		super(tetrahedronVertices(sideLength),
				new short[]{ 0, 1, 2,// bottom
			 3, 0, 1,
			 3, 0, 2,
			 3, 1, 2//the sides
			}, colors);
	}

}


