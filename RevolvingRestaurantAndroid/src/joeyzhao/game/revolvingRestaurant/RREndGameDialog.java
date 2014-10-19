package joeyzhao.game.revolvingRestaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Provides the Graphics User Interface for the game records and code that interacts with
 * the underlying database.  For now, this is a simple dialog box input.
 * 
 * @author jatlas
 *
 */
public class RREndGameDialog implements DialogInterface.OnClickListener {
    RevolvingRestaurantView parentView;
    RRDatabase database;
    EditText input;
    int result;
    String lastInitials;
    
    public RREndGameDialog(RevolvingRestaurantView parentView, RRDatabase database) {
        super();
        this.parentView = parentView;
        this.database = database;
        this.lastInitials = "";
    }
    
    
    /**
     * Gets the last initials entered.  Will return "" if none entered yet.
     * 
     * @return
     */
    public String getLastInitials() {
        return lastInitials;
    }

    @Override
    public void onClick(DialogInterface dialog, int whichButton) {
        String value = this.input.getText().toString();
        // format value to only valid letters
        value = value.replaceAll("[^a-zA-Z]", "");
        // only take the first 3, right padded with spaces
        lastInitials = (value + "   ").substring(0,3).toUpperCase();
        
        // Do something with value!
        database.insertGameRecord(new RRGameRecord(lastInitials, result, System.currentTimeMillis()));
        
        parentView.invalidate();
    }
    
    /**
     * Obtains the new winner/loser by presenting the user with a dialog input
     * that contains a message and an editable text box.
     * 
     * @param highScore
     */
    public void insertGameRecord(RevolvingRestaurantGame game) {
        
            this.result = game.getCurrentState().getCoins();
//        }
//        else if (game.getWinner() instanceof RRHumanPlayer) {
//            this.result = RRGameRecord.WON;
//        }
//        else {
//            this.result = RRGameRecord.LOST;
//        }
        
        AlertDialog.Builder alert = new AlertDialog.Builder(parentView.getContext());    
        alert.setTitle("GAME OVER!");
        alert.setMessage("Please enter your initials:");
        
        // get these before we replace the old edit text
        String lastInitials = getLastInitials();
        
        this.input = new EditText(parentView.getContext());
        this.input.setText(lastInitials);
        
        // Set the view to the EditText 
        alert.setView(this.input);

        alert.setPositiveButton("Ok", this);
        alert.show();
    }

}
