

import org.junit.Test;
import static org.junit.Assert.*;


public class TestRevolvingRestaurant {
	 
	public static RevolvingRestaurantDish sushi= new RevolvingRestaurantDish('@',3);
	public static RevolvingRestaurantCustomer joey= new RevolvingRestaurantCustomer('&',1,2);
	public static RevolvingRestaurantItems empty= new RevolvingRestaurantItems(' ');
	public static RevolvingRestaurantItems desk= new RevolvingRestaurantItems('-');
	
	public static final RevolvingRestaurantItems [][] restaurant1=
		{{empty,empty,joey,empty,empty},
		{desk,sushi,desk,sushi,empty},
		{empty,desk,empty,desk,empty},
		{empty,desk,sushi,desk,empty},
		{empty,empty,joey,empty,empty},
		};
	public static final RevolvingRestaurantItems [][] restaurant2=
		{{empty,empty,joey,empty,empty},
		{sushi,sushi,desk,empty,empty},
		{empty,desk,joey,desk,empty},
		{empty,desk,sushi,desk,empty},
		{empty,empty,joey,empty,empty},
		};
	public static final RevolvingRestaurantItems [][] restaurant3=
		{{empty,empty,joey,empty,empty},
		{sushi,sushi,desk,sushi,empty},
		{empty,sushi,empty,desk,empty},
		{empty,desk,empty,desk,empty},
		{empty,joey,joey,joey,empty},
		};
	
	public static final RevolvingRestaurantItems [][] bigRestaurant=
		{{empty,empty,joey,empty,empty,empty,empty,joey,empty,empty},
		{sushi,sushi,desk,sushi,desk,desk,sushi,sushi,desk,joey},
		{empty,sushi,empty,empty,joey,empty,empty,empty,sushi,joey},
		{empty,desk,joey,empty,empty,empty,empty,empty,desk,empty},
		{empty,desk,empty,empty,joey,empty,empty,joey,desk,joey},
		{empty,desk,sushi,desk,desk,desk,desk,sushi,desk,empty},	
		{empty,empty,joey,empty,empty,empty,joey,joey,empty,empty},
		};
	
	@Test
	 public void test_isEnd() {
		RevolvingRestaurantState state1=new RevolvingRestaurantState(restaurant1);
		RevolvingRestaurantState state2=new RevolvingRestaurantState(restaurant2);
		RevolvingRestaurantState state3=new RevolvingRestaurantState(restaurant3);
		RevolvingRestaurantState state4=new RevolvingRestaurantState(bigRestaurant);

		assertFalse(state1.isEnd());
		assertFalse(state2.isEnd());
		assertTrue(state3.isEnd());
		assertTrue(state4.isEnd());



	}
	@Test
	public void test_getHeuristicScore() {
		RevolvingRestaurantState state1=new RevolvingRestaurantState(restaurant1);
		RevolvingRestaurantState state2=new RevolvingRestaurantState(restaurant2);
		RevolvingRestaurantState state3=new RevolvingRestaurantState(restaurant3);
		RevolvingRestaurantState state4=new RevolvingRestaurantState(bigRestaurant);

		assertEquals(-5,state1.getHeuristicScore());
		assertEquals(-3,state2.getHeuristicScore());
		assertEquals(-4,state3.getHeuristicScore());
		assertEquals(-3,state4.getHeuristicScore());

			
	}
	
	public static void main(String[] args) {
		RevolvingRestaurantState state1=new RevolvingRestaurantState(restaurant1);
		RevolvingRestaurantState state2=new RevolvingRestaurantState(restaurant2);
		RevolvingRestaurantState state3=new RevolvingRestaurantState(restaurant3);
		RevolvingRestaurantState state4=new RevolvingRestaurantState(bigRestaurant);

		state1.print();
		state2.print();
		state3.print();
		state4.print();
	}

}
