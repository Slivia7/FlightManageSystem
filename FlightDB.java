package code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
//µ¥ÀýÄ£Ê½
public class FlightDB {
	private Vector<Flight> flightDB;

	public FlightDB() {
		flightDB=new Vector<Flight>();	
	}
	
	public Iterator getIterator() {
		Iterator it=flightDB.iterator();
		return it;
	}
	
	public Flight getFlightByCode(String code) {
		Iterator it=getIterator(); 
		Flight flight=new Flight();
		while(it.hasNext()) {
			flight=(Flight)it.next();
			if(flight.getCode().equals(code)) {
				return flight;
			}
		}
		return null;	
	}
	public Vector<Flight> getFlightByPrice(double Minprice,double Maxprice) {
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getPriceOfFc()<Maxprice&&f.getPriceOfBc()>Minprice) {
			flights.add(f);
			}
		}
		return flights;
	}
	
	public Vector<Flight> getFlightByStartPlace(String startPlace) {
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getStartPlace().equals(startPlace)) {
			flights.add(f);
			}
		}
		return flights;
	}
	
	public Vector<Flight> getFlightByEndPlace(String endPlace) {
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getEndPlace().equals(endPlace)) {
			flights.add(f);
			}
		}
		return flights;
		
	}

	public Vector<Flight> getFlightByStartTime(String s) throws ParseException {
		Date startTime=transform(s);
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getStartTime().equals(startTime)) {
			flights.add(f);
			}
		}
		return flights;
		
	}
	public Vector<Flight> getFlightByEndTime(String s) throws ParseException {
        Date endTime=transform(s);
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getEndTime().equals(endTime)) {
			flights.add(f);
			}
		}
		return flights;
		
	}
	public Vector<Flight> getFlightBySpareFc() {
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getNumberOfFc()-f.getNumberOfBoughtFc()!=0) {
			flights.add(f);
			}
		}
		return flights;
		
	}
	public Vector<Flight> getFlightBySpareBc() {
		Iterator it=getIterator(); 
		Vector<Flight> flights=new Vector<Flight>();
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(f.getNumberOfBc()-f.getNumberOfBoughtBc()!=0) {
			flights.add(f);
			}
		}
		return flights;
	}
	public void addFlight(Flight newFlight) {
		flightDB.add(newFlight);
		
//		flightDB.addElement(newFlight);
	}
	public void removeFlight(Flight flight){
		flightDB.remove(flight);
	}
	private Date transform(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date=format.parse(s);
	    return date;
	}
}
