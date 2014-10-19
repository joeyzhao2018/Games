package joeyzhao.game.revolvingRestaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RRDatabase {
	public static final String DATABASE_NAME = "RR";
	public static final String TABLE_NAME = "Game_Records";
	Context context;
	SQLiteDatabase database;

	/**
	 * Creates a database for the TicTacToe application. Creates/opens a connection
	 * to the underlying Android database.
	 * 
	 * @param context
	 */
	private int highest;
	public int getHighest() {
		return highest;
	}

	public void setHighest(int highest) {
		this.highest = highest;
	}

	public RRDatabase(Context context) {
		super();
		this.context = context;
		this.database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		
		createGameRecordsTable();
		String query="SELECT Player, Result, Date" +
				" FROM " + TABLE_NAME 
				+ " ORDER BY Result DESC";
		Cursor c = database.rawQuery(query, null);
		int resultColumn = c.getColumnIndex("Result");
		if (c.moveToFirst()) {
			this.highest=c.getInt(resultColumn);			
			}
	}

	public void close() {
		this.database.close();
	}

	/**
	 * Creates a table in the database if it does not exist already.
	 */
	private void createGameRecordsTable() {
		database.execSQL("CREATE TABLE IF NOT EXISTS "
				+ TABLE_NAME
				+ " (Player TEXT,"
				+ " Result INTEGER, Date INTEGER);");
	}

	/**
	 * Resets the database table to empty by deleting all rows.
	 */
	public void deleteGameRecords() {
		database.execSQL("DELETE FROM " + TABLE_NAME);
	}

	/**
	 * Inserts a single record (row) into the database table.
	 * 
	 * @param record
	 */
	public void insertGameRecord(RRGameRecord record) {
		database.execSQL("INSERT INTO "
				+ TABLE_NAME
				+ " (Player, Result, Date)"
				+ " VALUES ('" + record.getPlayer()
				+ "', " + record.getResult()
				+ ", " + record.getDate() + ")");  
		if(record.getResult()>this.highest){
			this.highest=record.getResult();
		}
	}

	/**
	 * Returns a query that selects the current game records for a specific player
	 * by querying the table for game records with Player1 equal to name.
	 * 
	 * @return
	 */
	public String getSelectGameRecordsQuery(String name) {
		return "SELECT Player, Result, Date" +
				" FROM " + TABLE_NAME +" WHERE Player = '"+name+"'"
				+ " ORDER BY Date DESC";
	}

	/**
	 * Queries the GameRecords table using the given query and adds the result rows
	 * to a list of records that is returned.
	 * 
	 * @return
	 */
	public List<RRGameRecord> selectGameRecords(String query) {
		Cursor c = database.rawQuery(query, null);
		try {
			/* Get the indices of the Columns we will need */
			int playerColumn = c.getColumnIndex("Player");
			int resultColumn = c.getColumnIndex("Result");
			int dateColumn = c.getColumnIndex("Date");

			List<RRGameRecord> records = new ArrayList<RRGameRecord>();
			if (c.moveToFirst()) {
				int i = 1;
				do {
					RRGameRecord record = new RRGameRecord(
							c.getString(playerColumn),
							c.getInt(resultColumn),
							c.getLong(dateColumn));
					records.add(record);
					i++;
				}
				while (c.moveToNext());
			}

			return records;
		}
		finally {
			c.close();
		}
	}
	public Map<String, Integer> getScoreSummary(String player){
		String query= getSelectGameRecordsQuery(player);
		List<RRGameRecord> records=selectGameRecords(query);
		Integer highestScore=0;
		;
		for(RRGameRecord r:records){
			if(r.getResult()>highestScore){
				highestScore=r.getResult();
			}
			
		}
		Map<String, Integer> result=new HashMap<String, Integer>();
		if(records.size()==0){
			result.put("last", 0);
		}
		else{
		result.put("last", records.get(0).getResult());}
		result.put("top",highestScore);
		
		return result;
	}
}
