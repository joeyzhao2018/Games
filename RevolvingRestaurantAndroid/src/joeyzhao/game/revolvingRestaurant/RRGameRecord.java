package joeyzhao.game.revolvingRestaurant;

public class RRGameRecord {
	
	private String player;
	private int result;
	private long date;
	
	
	public RRGameRecord() {
		super();
	}
	public RRGameRecord(String player, int result, long date) {
		super();
		this.player = player;
		this.result = result;
		this.date = date;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}


}
