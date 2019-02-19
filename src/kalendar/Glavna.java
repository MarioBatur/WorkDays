package kalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Glavna {

	public static void main(String[] args) {
		
		String pocetniDatumString;
		String zavrsniDatumString;
		
		Date pocetniDatum = null;
		Date zavrsniDatum = null;
		
		Date najmanjiDatum = null;
		Date najveciDatum = null;
		
		int brojRadnihDana = 0;
		int brojBlagdana = 0;
		
		String[] pocetak, kraj;
		String danPocetni, mjesecPocetni, danZavrsni, mjesecZavrsni;
				
		Scanner unos = new Scanner(System.in); 
		
		System.out.println("Unesite poèetni datum u formatu: DD.MM.YYYY, najranije 01.01.2018");
		pocetniDatumString = unos.next();
	
		System.out.println("Unesite završni datum u formatu: DD.MM.YYYY, najkasnije 31.12.2018");
		zavrsniDatumString = unos.next();

		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			pocetniDatum = dateFormat.parse(pocetniDatumString);
			zavrsniDatum = dateFormat.parse(zavrsniDatumString);
			najmanjiDatum = dateFormat.parse("01.01.2018");
			najveciDatum = dateFormat.parse("31.12.2018");
			
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		// Iz unesenog datuma vadim dan i mjesec
		pocetak = pocetniDatumString.split(Pattern.quote("."));
		danPocetni = pocetak[0];
		mjesecPocetni = pocetak[1];
		
		kraj = zavrsniDatumString.split(Pattern.quote("."));
		danZavrsni = kraj[0];
		mjesecZavrsni = kraj[1];
		
		Calendar kalendarPocetni = Calendar.getInstance();
		Calendar kalendarZavrsni = Calendar.getInstance();
		kalendarPocetni.setTime(pocetniDatum);
		kalendarZavrsni.setTime(zavrsniDatum);
		
		// Provjerava postoje li uneseni dan i mjesec
		if(Integer.parseInt(danPocetni) < 1 || Integer.parseInt(danPocetni) > kalendarPocetni.getActualMaximum(Calendar.DAY_OF_MONTH)
			|| Integer.parseInt(mjesecPocetni) < 1 || Integer.parseInt(mjesecPocetni) > 12
			|| Integer.parseInt(danZavrsni) < 1 || Integer.parseInt(danZavrsni) > kalendarPocetni.getActualMaximum(Calendar.DAY_OF_MONTH) 
			|| Integer.parseInt(mjesecZavrsni) < 1 || Integer.parseInt(mjesecZavrsni) > 12)
		{
			System.out.println("Pogreška! Unesen je nepostojeæi datum");
			System.exit(0);
		}

		// Prvi datum mora biti prije drugog
		if(pocetniDatum.after(zavrsniDatum)){
			System.out.println("Pogreška! Prvi datum mora biti prije drugog");
			System.exit(0);
		}
		
		// Samo 2018. godina
		if(pocetniDatum.before(najmanjiDatum) || pocetniDatum.after(najveciDatum) || zavrsniDatum.before(najmanjiDatum) || zavrsniDatum.after(najveciDatum)){
			System.out.println("Pogreška! Datumi moraju biti unutar intervala 01.01.2018 do 31.12.2018");
			System.exit(0);
		}
		
		RadnihDana radnihDana = new RadnihDana(pocetniDatum, zavrsniDatum);
		brojRadnihDana = radnihDana.brojRadnihDana();
		brojBlagdana = radnihDana.getBrojBlagdana();
		
		System.out.println("Broj radnih dana je: " + brojRadnihDana);
		System.out.println("Broj blagdana je: " + brojBlagdana);
		
		unos.close();
	}
}
