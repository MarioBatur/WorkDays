package kalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RadnihDana {
	
	private Calendar kalendarPocetak;
	private Calendar kalendarKraj;
	private int brojRadnihDana = 0;
	private int brojBlagdana = 0;
	
	ArrayList<Date> praznici = new ArrayList<>();

	public RadnihDana(Date pocetniDatum, Date zavrsniDatum){
		
		kalendarPocetak = Calendar.getInstance();
		kalendarPocetak.setTime(pocetniDatum);
		
		kalendarKraj = Calendar.getInstance();	    
		kalendarKraj.setTime(zavrsniDatum);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			praznici.add(dateFormat.parse("01.01.2018"));
			praznici.add(dateFormat.parse("06.01.2018"));
			praznici.add(dateFormat.parse("01.04.2018"));
			praznici.add(dateFormat.parse("02.04.2018"));
			praznici.add(dateFormat.parse("01.05.2018"));
			praznici.add(dateFormat.parse("31.05.2018"));
			praznici.add(dateFormat.parse("22.06.2018"));
			praznici.add(dateFormat.parse("25.06.2018"));
			praznici.add(dateFormat.parse("05.08.2018"));
			praznici.add(dateFormat.parse("15.08.2018"));
			praznici.add(dateFormat.parse("08.10.2018"));
			praznici.add(dateFormat.parse("01.11.2018"));
			praznici.add(dateFormat.parse("25.12.2018"));
			praznici.add(dateFormat.parse("26.12.2018"));

		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}
	
	public int brojRadnihDana() {
		
	    do {
	    	// Provjerava je li dan radni (tj. da nije subota, nedjelja ili praznik)
	        if (kalendarPocetak.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && kalendarPocetak.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
	        	 && !praznici.contains(kalendarPocetak.getTime())) {
	             brojRadnihDana++;
	        }
   
	        // Racuna samo blagdane
	        if (praznici.contains(kalendarPocetak.getTime())){
	        	 brojBlagdana++;
	        } 
	        
	    	kalendarPocetak.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    while (kalendarPocetak.getTimeInMillis() <= kalendarKraj.getTimeInMillis());
	    
	    return brojRadnihDana;
	}

	public int getBrojBlagdana() {
		return brojBlagdana;
	}
	
}
