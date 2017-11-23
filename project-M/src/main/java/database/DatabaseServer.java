package database;

public class DatabaseServer {
	String[] save = new String[5];
	
	public void setEmoties(String temp) {
		save[0] = temp;
	}
	
	public void setActiviteit(String temp) {
		save[1] = temp;
	}
	
	public void setOpmerking(String temp) {
		save[2] = temp;
	}
	
	public void setTijd(String t,String dag) {
		save[3] = t;
		save[4] = dag;
	}
	
	public void save() {
		
		
		
	}

}
