package joeyzhao.game.revolvingRestaurant;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class RevolvingRestaurantDemo {
//copy from MagnetoDemo
	 public static void main(String[] args) throws Exception {
	        File dir = new File("./assets/levels/");
	        String[] files = dir.list();
	        Arrays.sort(files);
	        
	        RevolvingRestaurantGame game = new RevolvingRestaurantGame();
	        game.addActor(new TimeActor(game));
	        game.addActor(new RevolvingRestaurantAI());
	  
	        
	        for (int i = 0; i < files.length; i++) {
	        	RevolvingRestaurantState state = RevolvingRestaurantState.loadFromStream(
	                new FileInputStream("./assets/levels/" + files[i]));
	            game.addLevel(state);
	        }
	        
	        // now have the AI play/demo the game
	        Scanner in = new Scanner(System.in);
	        while (!game.isEnded()) {
	            game.getCurrentState().print();
	            game.update();
	            if (game.getCurrentState().isEnd()) {
	                // one level is complete, prompt user to press a key to continue
	             /**I changed the following sentence**/
	            	System.out.println("finished,I don't have time to separate two different endings");//
	                in.nextLine();
	                game.nextLevel();
	            }
	            
	            // sleep for 1 second between updates otherwise it would constantly update
	            Thread.sleep(1000);
	        }
	        game.getCurrentState().print();
	        System.out.printf("Congratulations, completed all levels in %.1f total seconds!",
	            game.getCurrentState().getTimer());
	    }
}
