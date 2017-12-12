package database;

public class DatabaseServer {
	String[] data = new String[6];

	public void setEmoties(String temp) {
		data[0] = temp;
	}

	public void setActiviteit(String temp) {
		data[1] = temp;
	}

	public void setOpmerking(String temp) {
		data[2] = temp;
	}

	public void setTijd(String t, String dag, int duration) {
		data[3] = t;
		data[4] = dag;
		data[5] = Integer.toString(duration);
	}

	public void save() {
		//Save excel = new Save(); 
		/*
		 * Checks of wat we gaan opslaan wel in de juiste formaat is Dit is handig als
		 * je het op een een server opslaat, om te controleren of alles de juiste
		 * formaat heeft. zodat niet iemand in plaats van 5 sterren, 5 miljoen sterren
		 * kan opslaan
		 */
		if (check()) {
			Save save = new Save();
			save.intialize();
			save.save(data);
			
			for(int i=0;i<6;i++) {
				String temp = data[i];
				System.out.println(temp);
				/* een of andere method( int i, String temp)
				 * die de string temp opslaat op kolum i+1 en de tij waar we zijn gebleven
				 * als het hier mee klaar is de rijnummer een omhoog gooit 
				 */
				
			}

			System.out.println("no Error back end");
		} else {
			System.out.println("Error back end");
		}

	}

	public boolean check() {
		if (!(data[0].length() == 1 && "12345".contains(data[0]))) {
			return false;
		}
		if (!(data[1].length() == 1 && "12345".contains(data[1]))) {
			return false;
		}
		if (data[2].length() >= 140) {
			return false;
		}

		if (data[3].length() == 19) {
			for (int i = 0; i < 19; i++) {
				switch (i) {
				case 2:
				case 5:
					if (data[3].charAt(i) != '/') {
						return false;
					}
					break;
				case 10:
					if (data[3].charAt(i) != ' ') {
						return false;
					}
					break;
				case 13:
				case 16:
					if (data[3].charAt(i) != ':') {
						return false;
					}
					break;
				default:
					if (!"0123456789".contains(Character.toString(data[3].charAt(i)))) {
						return false;
					}
				}

			}
		} 

		{
			if (data[4].length() == 8) {
				if ("157".indexOf(data[4].charAt(0)) == -1) {
					return false;
				}
			}
			if (data[4].length() == 9) {
				if (data[4].charAt(0) != '2') {
					return false;
				}
			}
			if (data[4].length() == 10) {
				if ("46".indexOf(data[4].charAt(0)) == -1) {
					return false;
				}
			}
			if (data[4].length() == 11) {
				if (data[4].charAt(0) != '3') {
					return false;
				}
			}
		}

		for (int i = 0; i < data[5].length(); i++) {
			if (!"1234567890".contains(Character.toString(data[5].charAt(i)))) {
				return false;
			}
		}

		return true;
	}

}
