package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Load {
	public FlightDB flightDB;
	public UserDB userDB;
    public Manager manager;
    public Load() {
    	flightDB=new FlightDB();
        userDB=new UserDB();
        manager=new Manager(flightDB,userDB);
    }
    //从文件里读出数据
    public void LoadedFlightDB() throws IOException {
    	try {
    		BufferedReader reader=new BufferedReader(new FileReader(new File("FlightDB.txt")));
    		String in=reader.readLine();
    	    String[] group=new String[11];
    	    String code;
    	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    Date startTime;
    	    Date endTime;
    	    String startPlace;
    		String endPlace;
    		int numberOfFc;
    		int numberOfBc;
    		int numberOfBoughtFc;
    		int numberOfBoughtBc;
    		double priceOfFc;
    		double priceOfBc;
    	    while(in!=null) {
    	    	in=in.substring(5);
    	    	group=in.split("/");
    	    	code=group[0];
    	    	startTime=format.parse(group[1]);
    			endTime=format.parse(group[2]);
    			startPlace=group[3];
    			endPlace=group[4];
    		    numberOfFc=Integer.valueOf(group[5]);
    			numberOfBc=Integer.valueOf(group[6]);
    			numberOfBoughtFc=Integer.valueOf(group[7]);
    			numberOfBoughtBc=Integer.valueOf(group[8]);
    			priceOfFc=Double.valueOf(group[9]);
    			priceOfBc=Double.valueOf(group[10]);
    			flightDB.addFlight(new Flight(code,startTime,endTime,startPlace,
    	    			endPlace,numberOfFc,numberOfBc,numberOfBoughtFc,
    	    			numberOfBoughtBc,priceOfFc,priceOfBc));
    			in=reader.readLine();
    	    }
    	    reader.close();
           } catch (FileNotFoundException | ParseException e) {
    		// TODO 自动生成的 catch 块
    		e.printStackTrace();
    	}
    }
    //读数据
    public void LoadedUserDB() throws IOException {
    	BufferedReader reader=new BufferedReader(new FileReader(new File("UserDB.txt")));
	    String in=reader.readLine();
	    String[] group;
	    String name;
	    String password;
	    double wallet;
	    HashMap<Flight,String> prepaid;
	    while(in!=null) {
	    	prepaid = new HashMap<Flight,String>();
	    	group=in.split("/");
	    	System.out.println(group.length);
	    	name=group[0];
	    	password=group[1];
	    	wallet=Double.valueOf(group[2]);
	    	for(int i=0;i<group.length;i++) 
	    		System.out.println(group[i]);
	    	for(int i=3;i<group.length;i+=2) {
	    		prepaid.put(flightDB.getFlightByCode(group[i]), group[i+1]);
	    		//prepaid.put(f, group[i+1]);
	    	}
	    	
	    	userDB.addUser(new User(name,password,flightDB,wallet,prepaid));
	    	in=reader.readLine();
	    }
	    reader.close();
    }    //读取数据
    public void putInformation() throws IOException {
    	PrintWriter pw1 = new PrintWriter(new FileWriter("FlightDB.txt"));
    	PrintWriter pw2 = new PrintWriter(new FileWriter("UserDB.txt"));
    	Iterator it=flightDB.getIterator();
    	Flight f=new Flight();
    	while (it.hasNext()) {
    		f=(Flight) it.next();
    		pw1.write(f.putInfo());
    		pw1.write("\n");
    	}
     	pw1.close();
     	Iterator it2=userDB.getIterator();
     	User u;
     	while (it2.hasNext()) {
    		u=(User) it2.next();
    		pw2.write(u.putInfo());
    		pw2.write("\n");
    	}
     	pw2.close();
    	}
	public void mainX() throws IOException {
		LoadedFlightDB();
		LoadedUserDB();
		//userDB.addUser(new User("nmsl","123456",flightDB,10000));
		//userDB.getUser("nmsl").addFlight("WI4312", "头等舱");
		//System.out.println(userDB.getUser("nmsl").putInfo());
		//测试FlightDB的功能
//		Vector<Flight> flights=test01.flightDB.getFlightByPrice(0,20000);
//		for(Flight f:flights) {
//			System.out.println(f.toString());	
//		}
//		System.out.println(flights.get(1));		Vector<Flight> flights=test01.flightDB.getFlightByEndTime(endTime)
		//System.out.println(test01.userDB.getTotalNumber());
		//test01.manager.addFight(null);
	}

}
