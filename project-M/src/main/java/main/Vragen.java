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
			/*
			 * hier worden als eerste de emotie gevraagd en daarna opgeslagen daarna
			 * activiteit en als laatste opmerkingen
			 */
		} catch (IOException e) {
			System.out.println("Error");// niet goed als je hier komt
		}
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		save.setTijd(timeStamp); // opslaan van de tijd
		System.out.println("Emoties is = " + save.returnEmoties());
		System.out.println("activiteit is = " + save.returnActiviteit());
		System.out.println("opmerking is = " + save.returnOpmerking());
		System.out.println("tijd is = " + save.returnTijd());
		save.save(); // opslaan in een bestandje
	}

	public void emoties() throws IOException {
		System.out.println("voer U emotie in, U heeft de keuze uit: ");
		System.out.println("1) blij 2) boos 3) bang 4) bedroefd 5) beschaamde");
		String tempString = emotiesEnActiviteiten();
		save.setEmoties(tempString);
		/*
		 * als eerste wat tekst om uit te leggen wat je aan het doen bent daarna krijg
		 * je een tempString, die 1,2,3,4 of 5 als waarde heeft daarna sla je die op in
		 * ToSave.
		 */
	}

	public void activiteit() throws IOException {
		System.out.println("voer U activiteit in, U heeft de keuze uit: ");
		System.out.println("1) werk 2) sociaal 3) sporten 4) relaxen 5) reizen");
		String tempString = emotiesEnActiviteiten();
		save.setActiviteit(tempString);
		/*
		 * als eerste wat tekst om uit te leggen wat je aan het doen bent daarna krijg
		 * je een tempString, die 1,2,3,4 of 5 als waarde heeft daarna sla je die op in
		 * ToSave.
		 */
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
				/*
				 * stap 1 vraag van de gebruiker een invoer stap 2 controleer of de input langer
				 * dan 140 tekens is stap 2a zo ja ga naar stap 3 2b zo nee ga terug naar stap 1
				 * stap 3 sla de opmerking op in ToSave
				 */
			}
			System.out.println("graag een opmerking in minder dan 140 tekens");
		}
		save.setOpmerking(string);
	}

	public String emotiesEnActiviteiten() throws IOException {
		boolean tempBoolean = true;
		String tempSTring = "";
		while (tempBoolean) {
			tempSTring = getString(); // vraagt een string van de gebruier
			if ((tempSTring.length() == 1) & (goedNumbers.contains(tempSTring))) {
				tempBoolean = false;
				break;
				/*
				 * stap 1 vraag aan de gebruiker een invoer stap 2 controleer of de input een
				 * waarde heeft en of de invoer een geheel getal is tussen de 1 en de 5 stap 2a
				 * zo ja geef de invoer terug stap 2b zo nee, ga terug naar stap 1
				 */
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
		/*
		 * een method om elke keer van de gebruiker iets te vragen
		 * 
		 */
	}
}
