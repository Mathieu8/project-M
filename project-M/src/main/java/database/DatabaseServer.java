package database;

public class DatabaseServer {
	String[] save = new String[6];

	public void setEmoties(String temp) {
		save[0] = temp;
	}

	public void setActiviteit(String temp) {
		save[1] = temp;
	}

	public void setOpmerking(String temp) {
		save[2] = temp;
	}

	public void setTijd(String t, String dag, int duration) {
		save[3] = t;
		save[4] = dag;
		save[5] = Integer.toString(duration);
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
			for(int i=0;i<6;i++) {
				String temp = save[i];
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
		if (!(save[0].length() == 1 && "12345".contains(save[0]))) {
			return false;
		}
		if (!(save[1].length() == 1 && "12345".contains(save[1]))) {
			return false;
		}
		if (save[2].length() >= 140) {
			return false;
		}

		if (save[3].length() == 19) {
			for (int i = 0; i < 19; i++) {
				switch (i) {
				case 2:
				case 5:
					if (save[3].charAt(i) != '/') {
						return false;
					}
					break;
				case 10:
					if (save[3].charAt(i) != ' ') {
						return false;
					}
					break;
				case 13:
				case 16:
					if (save[3].charAt(i) != ':') {
						return false;
					}
					break;
				default:
					if (!"0123456789".contains(Character.toString(save[3].charAt(i)))) {
						return false;
					}
				}

			}
		} 

		{
			if (save[4].length() == 8) {
				if (save[4].charAt(0) != '1') {
					return false;
				} else if (save[4].charAt(0) != '5') {
					return false;
				} else if (save[4].charAt(0) != '7') {
					return false;
				}
			}
			if (save[4].length() == 9) {
				if (save[4].charAt(0) != '2') {
					return false;
				}
			}
			if (save[4].length() == 10) {
				if (save[4].charAt(0) != '4') {
					return false;
				} else if (save[4].charAt(0) != '6') {
					return false;
				}
			}
			if (save[4].length() == 11) {
				if (save[4].charAt(0) != '3') {
					return false;
				}
			}
		}

		for (int i = 0; i < save[5].length(); i++) {
			if (!"1234567890".contains(Character.toString(save[5].charAt(i)))) {
				return false;
			}
		}

		return true;
	}

}
