package main;

public class ToSave {
	String emoties;
	String activiteit;
	String opmerking;
	String tijd;
	
	public void setEmoties(String temp) {
		emoties = temp;
	}
	
	public void setActiviteit(String temp) {
		activiteit = temp;
	}
	
	public void setOpmerking(String temp) {
		opmerking = temp;
	}
	
	public void setTijd(String temp) {
		tijd = temp;
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
}
