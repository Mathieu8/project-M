package main;

public class ToSave {
	database.DatabaseServer save = new database.DatabaseServer();
	String emoties;
	String activiteit;
	String opmerking;
	String tijd;
	String dagVanDeWeek;
	int duration;
	
	
	public void setEmoties(String temp) {
		emoties = temp;
	}
	
	public void setActiviteit(String temp) {
		activiteit = temp;
	}
	
	public void setOpmerking(String temp) {
		opmerking = temp;
	}
	
	public void setTijd(String t,String dag, int tempDuration) {
		tijd = t;
		dagVanDeWeek = dag;
		duration = tempDuration;
		
	}
	
	public String returnEmoties() {
		return emoties;
	}

	public String returnActiviteit() {
		return activiteit;
	}
	
	public String returnOpmerking() {
		return opmerking;
	}
	
	public String returnTijd() {
		return tijd;
	}

	public String returnDag() {
		return dagVanDeWeek;
	}
	
	public void save() {
		save.setEmoties(emoties);
		save.setActiviteit(activiteit);
		save.setOpmerking(opmerking);
		save.setTijd(tijd,dagVanDeWeek, duration);
		save.save();
	}
}
