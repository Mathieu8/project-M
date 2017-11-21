package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vragen {
	ToSave save = new ToSave();
	String goedNumbers = "12345";

	public void runQuestions() {
		try {
			emoties();
			activiteit();
			opmerking();
		} catch (IOException e) {
			System.out.println("Error in Emoties");
		}
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("Emoties is = " + save.returnEmoties());
		System.out.println("activiteit is = " + save.returnActiviteit());
		System.out.println("opmerking is = " + save.returnOpmerking());
		System.out.println("tijd is = " + timeStamp);
		
	}

	public void emoties() throws IOException {
		System.out.println("voer U emotie in, U heeft de keuze uit: ");
		System.out.println("1) blij 2) boos 3) bang 4) bedroefd 5) beschaamde");
		String tempString = emotiesEnActiviteiten();
		save.setEmoties(tempString);
	}

	public void activiteit() throws IOException {
		System.out.println("voer U activiteit in, U heeft de keuze uit: ");
		System.out.println("1) werk 2) sociaal 3) sporten 4) relaxen 5) reizen");
		String tempString = emotiesEnActiviteiten();
		save.setActiviteit(tempString);
	}

	public void opmerking() throws IOException {
		System.out.println("Opmerkingen:");
		String string = "";
		boolean tempBoolean = true;
		while (tempBoolean) {
			string = getString();
			if (string.length() < 140) {
				tempBoolean = false;
				break;
			}
			System.out.println("graag een opmerking in minder dan 140 tekens");
		}
		save.setOpmerking(string);
	}

	public String emotiesEnActiviteiten() throws IOException {
		boolean tempBoolean = true;
		String tempSTring = "";
		while (tempBoolean) {
			tempSTring = getString();
			if ((tempSTring.length() == 1) & (goedNumbers.contains(tempSTring))) {
				tempBoolean = false;
				break;
			}
			System.out.println("s.v.p. een getal tussen de 1 en de 5.");
		}
		return tempSTring;
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
