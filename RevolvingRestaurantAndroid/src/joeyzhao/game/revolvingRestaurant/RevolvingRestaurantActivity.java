package joeyzhao.game.revolvingRestaurant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import android.app.Activity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RevolvingRestaurantActivity extends Activity {
	private RevolvingRestaurantView view;
	 private RRDatabase database;
	 SoundManager mSoundManager2 = new SoundManager();
	 
	public RevolvingRestaurantGame firstGame(){
		RevolvingRestaurantGame game=new RevolvingRestaurantGame();
	//	File dir = new File("./assets/levels/");
//		String[] files = dir.list();
		//Arrays.sort(files);
//		for (int i = 0; i < files.length; i++) {
			RevolvingRestaurantState state;
			try {
				state = RevolvingRestaurantState.loadFromStream(
						getAssets().open("levels/level0.txt"));/**"./assets/levels/" + files[0]));**/
				game.addLevel(state);
//
			} catch (IOException e) {
				e.printStackTrace();
			}
//		}
			game.onePlayerGame();	
		return game;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//mSoundManager2.initSounds(getBaseContext());
        //mSoundManager2.addSound(0, R.raw.pia);
		
		TextView messageBox = new TextView(this);
		RevolvingRestaurantGame game=this.firstGame();
		database = new RRDatabase(this);
		view = new RevolvingRestaurantView(this, game , messageBox, database);
		game.addListener(view);
		view.getGame().update();

		FrameLayout frame = new FrameLayout(this);
		frame.addView(view);
		//playSound(0);
		// Do they want a 3d game? we can do that
				//        try {
		//            ActivityInfo ai = getPackageManager().getActivityInfo(
		//                this.getComponentName(), PackageManager.GET_META_DATA);
		//            
		//            boolean is3d = ai.metaData != null ? ai.metaData.getBoolean("joeyzhao.game.revolvingRestaurant.3d", true) : true;
		//            if (is3d) {
		Shape3dRenderer renderer = new Shape3dRenderer();
		GLSurfaceView view3d = new GLSurfaceView(this);

		// We want an 8888 pixel format because that's required for
		// a translucent window.
		// And we want a depth buffer.
		view3d.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		view3d.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		view3d.setZOrderOnTop(true);

		view3d.setRenderer(renderer);
		view.setRenderer(renderer);
		frame.addView(view3d);
		//            }
		//        }
		//        catch (Exception e) {
		//            // no 3d for you!
		//            Log.e("TicTacToe5x5", "3d initialization failed", e);
		//        }

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(messageBox);
		layout.addView(frame);
		setContentView(layout);
	}
	   @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        database.close();
	    }

	@Override
	protected void onResume() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onResume();
	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onPause();
	}

	public boolean onCreateOptionsMenu(Menu menu){
		menu.add("Level 1");
		menu.add("Level 2");
		menu.add("To the infinity!!");
		menu.add("Restart");
		menu.add("Quit");
		menu.add("Reset Records");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		CharSequence title = item.getTitle();
		RevolvingRestaurantGame game = null;
		if (title.equals("Level 1")) {	
			game = this.level1();
			
		}
		else if (title.equals("Level 2")) {
			game = this.level2();
			
		}
		else if (title.equals("To the infinity!!")) {
            game = this.infinity();
            //playSound(0);
        }
		else if (title.equals("Restart")) {
			// we don't need a new game instance, just reset the current game
			game = view.getGame();
			game.restart();
		}
		else if (title.equals("Quit")) {
			finish();
		}
		else if(title.equals("Reset Records")){
        	database.deleteGameRecords();
        	view.invalidate();
        }
		// If they chose one of the valid menu items, update the current
		// game being played by the view
		if (game != null) {
			
			view.setGame(game);
			game.update();/**I DON'T UNDERSTAND**/
		}
		return true;
	}
	public RevolvingRestaurantGame level1() {
		RevolvingRestaurantGame game=new RevolvingRestaurantGame();
		
				RevolvingRestaurantState state;
				try {
					state = RevolvingRestaurantState.loadFromStream(
							getAssets().open("levels/level1.txt"));
					game.addLevel(state);

				} catch (IOException e) {
					e.printStackTrace();
				}

				game.onePlayerGame();	
				
				return game;
	}
	public RevolvingRestaurantGame level2() {
		RevolvingRestaurantGame game=new RevolvingRestaurantGame();
		
				RevolvingRestaurantState state;
				try {
					state = RevolvingRestaurantState.loadFromStream(
							getAssets().open("levels/level2.txt"));
					game.addLevel(state);

				} catch (IOException e) {
					e.printStackTrace();
				}
				game.onePlayerGame();	
				
				return game;
	}
	public RevolvingRestaurantGame infinity() {
		RevolvingRestaurantGame game=new RevolvingRestaurantGame();
		
				RevolvingRestaurantState state;
				try {
					state = RevolvingRestaurantState.loadFromStream(
							getAssets().open("levels/infinity.txt"));
					state.setInfinite();
					game.addLevel(state);

				} catch (IOException e) {
					e.printStackTrace();
				}

				game.onePlayerGame();
				
				return game;
	}
	public void playSound(int value){
        mSoundManager2.playSound(value);
    }
}