package joeyzhao.game.revolvingRestaurant;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestRevolvingRestaurant {
	 
	public static RevolvingRestaurantDish sushi= new RevolvingRestaurantDish('@',3);
	public static RevolvingRestaurantCustomer joey= new RevolvingRestaurantCustomer('&',1,2);
	public static RevolvingRestaurantItems empty= new RevolvingRestaurantEmptySpace(' ');
	public static RevolvingRestaurantItems desk= new RevolvingRestaurantTable('#');
	
	public static List<RevolvingRestaurantCustomer> availableCustomers=new ArrayList<RevolvingRestaurantCustomer>();
	public static List<RevolvingRestaurantDish> availableDishes=new ArrayList<RevolvingRestaurantDish>();
	

	
	public static final RevolvingRestaurantItems [][] restaurant1=
		{{empty,empty,joey,empty,empty},
		{desk,sushi,desk,sushi,empty},
		{empty,desk,empty,desk,empty},
		{empty,desk,sushi,desk,empty},
		{empty,empty,joey,empty,empty},
		};
	public static final RevolvingRestaurantItems [][] restaurant2=
		{{empty,joey,empty,empty,empty},
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
	public static RevolvingRestaurantState state1=new RevolvingRestaurantState(restaurant1,availableCustomers,availableDishes);
	public static RevolvingRestaurantState state2=new RevolvingRestaurantState(restaurant2,availableCustomers,availableDishes);
	public static RevolvingRestaurantState state3=new RevolvingRestaurantState(restaurant3,availableCustomers,availableDishes);
	public static RevolvingRestaurantState state4=new RevolvingRestaurantState(bigRestaurant,availableCustomers,availableDishes);

	
	@Test
	 public void test_isEnd() {
		
	
		assertTrue(state1.isEnd());
		state1.getAvailableDishes().add(sushi);
		assertFalse(state2.isEnd());
		assertTrue(state3.isEnd());
		assertTrue(state4.isEnd());

	}
	@Test
	public void test_getHeuristicScore() {

		assertEquals(-5,state1.getHeuristicScore());
		assertEquals(-3,state2.getHeuristicScore());
		assertEquals(-4,state3.getHeuristicScore());
		assertEquals(-3,state4.getHeuristicScore());

			
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		File dir = new File("./assets/levels/");
        String[] files = dir.list();
        Arrays.sort(files);
		RevolvingRestaurantState state = RevolvingRestaurantState.loadFromStream(
                new FileInputStream("./assets/levels/" + files[0]));
		
		String a=state.getAvailableCustomers().get(3).toString();
		System.out.println(a);
		state1.print();
		state2.print();
		state3.print();
		state4.print();
	}
	// start for DL2://
	
	@Test
	public void test_copy(){
		RevolvingRestaurantState state=new RevolvingRestaurantState(restaurant1,availableCustomers,availableDishes);
		RevolvingRestaurantState copy=state.copy();
		
		RevolvingRestaurantItems [][] restaurant=state.getRestaurant();
		RevolvingRestaurantItems [][] restaurantCopy=copy.getRestaurant();
		assertNotSame(restaurant, restaurantCopy);
        for (int row = 0; row < restaurant.length; row++) {
            assertNotSame(restaurant[row], restaurantCopy[row]);
        }
	}
	
	@Test
	/**the following test can test AddDishMove And TableMove and TableRevolver**/
	public void test_AddDishMoveAndTableMoveAndTableRevolver(){
		state1.getAvailableDishes().add(sushi);
		
		
		assertEquals(state1.getRestaurant()[1][0],sushi);
		
		
		
		//assertEquals(state1.getRestaurant()[1][0],desk);// though this is not true, it doesn't really affect the result
		assertEquals(state1.getRestaurant()[1][1],sushi);
		assertEquals(state1.getRestaurant()[1][2],sushi);
	}
	
	@Test
	
	public void test_AddCustomerMove(){
		AddCustomerMove move=new AddCustomerMove(joey,0,1);
		move.make(state1);
		assertEquals(state1.getNumberOfCustomers(),3);
		assertEquals(state1.getRestaurant()[0][1],joey);
	}
		
	@Test
	public void test_TimeMove(){
		TimeMove timeMove=new TimeMove(1000l);
		
		
		assertEquals(((RevolvingRestaurantDish)state2.getRestaurant()[1][1]).getPieces(),3);
		assertEquals(((RevolvingRestaurantDish)state2.getRestaurant()[3][2]).getPieces(),3);
		
		timeMove.make(state2);
		assertEquals(((RevolvingRestaurantDish)state2.getRestaurant()[1][1]).getPieces(),3);
		assertEquals(((RevolvingRestaurantDish)state2.getRestaurant()[3][2]).getPieces(),3);
		
		}

		
		
	}



