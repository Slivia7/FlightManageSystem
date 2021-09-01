package code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.Iterator;

//单例模式
public class Manager extends Role{
	private FlightDB flights;
	private UserDB users;
	public Manager(FlightDB flightDB,UserDB users) {
		// TODO 自动生成的构造函数存根
		super("Manager","123456");
		this.flights=flightDB;
		this.users=users;
	}
    Manager(){
    	super("Manager","123456");
    	flights=new FlightDB();
		users=new UserDB();
 }
	public boolean addFlight(String code,String startTime,String endTime,String startPlace,
			String endPlace,String numberOfFc,String numberOfBc,String priceOfFc,String priceOfBc) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime01=format.parse(startTime);
		Date endTime01=format.parse(endTime);
		int numberOfFc01=Integer.valueOf(numberOfFc);
		int numberOfBc01=Integer.valueOf(numberOfBc);
		double priceOfFc01=Double.valueOf(priceOfFc);
		double priceOfBc01=Double.valueOf(priceOfBc);
    	Flight newFlight=new Flight(code,startTime01,endTime01,startPlace,
    			endPlace,numberOfFc01,numberOfBc01,0,
    			0,priceOfFc01,priceOfBc01);
    	flights.addFlight(newFlight);
    	return true;
    }
    
	public boolean removeFlight(String code) {
		Flight flight=flights.getFlightByCode(code);
		if(flight.getNumberOfBoughtBc()==0&&flight.getNumberOfBoughtFc()==0) {
		flights.removeFlight(flight);
		return true;
	    }
		else 
			return false;
	}
	public int getNumberOfuser() {
		return users.getTotalNumber();
	}
	public double getIncome() {
		java.util.Iterator it=users.getIterator();
		double total=0;
		User user;
		while(it.hasNext()) {
			user=(User) it.next();
			total+=user.geTotalCost();
			}
		return total;
	}
}
