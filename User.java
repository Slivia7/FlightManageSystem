package code;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class User extends Role{
	private FlightDB flights;
	private double wallet;
	private Set<Flight> myflight=new HashSet<Flight>(); 
	private Map<Flight,String> prepaid=new HashMap<Flight,String>();
//注册调用这个
	public User(String name,String password,FlightDB flights,double wallet) {
		// TODO 自动生成的构造函数存根
		super(name,password);
		this.flights=flights;
		this.wallet=wallet;
	}
	//登陆调用这个
	public User(String name,String password,FlightDB flights,double wallet,HashMap<Flight,String> prepaid) {
		// TODO 自动生成的构造函数存根
		super(name,password);
		this.flights=flights;
		this.wallet=wallet;
		this.prepaid=prepaid;
	}

	@SuppressWarnings("unchecked")
	public Iterator getIterator() {
		myflight= prepaid.keySet();
		Iterator it=myflight.iterator();
		return it;
	}
	public void displayPepaid() {
		Iterator it=getIterator();
		System.out.println("您当前余额为："+wallet);
		System.out.println("您目前的行程有");
		while(it.hasNext()) {
		System.out.println(it.next().toString());
		}
	}
	//支付三步走——钱少座位少加入订单
	public int addFlight(String code,String seat) {//需要输入订哪种座位,每人只能买一个航班的一张票
		Flight newFlight=flights.getFlightByCode(code);
		if(seat.equals("头等舱")&&newFlight.hasFc()) {
		  if(wallet-newFlight.getPriceOfFc()>=0) {
			  wallet=wallet-newFlight.getPriceOfFc();
		      newFlight.setNumberofBoughtFc(newFlight.getNumberOfBoughtFc()+1);
		      this.prepaid.put(newFlight, seat);
		}
		  else {
				System.out.println("failed");
			  return 1;//说明余额不足
		  }
		}
		else if(seat.equals("商务舱")&&newFlight.hasBc()) {
			if(wallet-newFlight.getPriceOfBc()>=0) {
			wallet=wallet-newFlight.getPriceOfBc();
		    newFlight.setNumberofBoughtBc(newFlight.getNumberOfBoughtBc()+1);
		    this.prepaid.put(newFlight, seat);
		}
			else {
				System.out.println("failed");
				  return 1;//说明余额不足
			  }	
		}
		else {
			System.out.println("failed2");
			return 2;//说明没有空余座位
		}
		System.out.println("success");
		return 0;//说明支付成功
	}
	//退订三步走——钱多座位多移除订单
	public boolean cancelFlight(String code) {
		Flight flight=flights.getFlightByCode(code);
		String seat=prepaid.get(flight);
		if(seat.equals("头等舱")) {
			wallet+=flight.getPriceOfFc();
			flight.setNumberofBoughtFc(flight.getNumberOfBoughtFc()-1);
			prepaid.remove(flight);
			return true;
		}
		else if(seat.equals("商务舱")){
			wallet+=flight.getPriceOfBc();
			flight.setNumberofBoughtBc(flight.getNumberOfBoughtBc()-1);
			prepaid.remove(flight);
			return true;	
		}
		else {
			return false;
		}
		
		
	}
	//根据航班号
	public Flight getFlightbyCode(String code) {
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
	//根据价钱范围
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
	//根据目的地
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
	//根据出发地点
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
	//根据出发时间
	public Vector<Flight> getFlightByStartTime(Date startTime) {
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
	//根据到达时间
	public Vector<Flight> getFlightByEndTime(Date endTime) {
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
	
	public double geTotalCost() {
		double total = 0;
		Iterator it =getIterator(); 
		Flight f=new Flight();
		while(it.hasNext()) {
			f=(Flight) it.next();
			if(prepaid.get(f).equals("头等舱")) {
				total+=f.getPriceOfFc();
			}
			else if (prepaid.get(f).equals("商务舱")) {
				total+=f.getPriceOfBc();
			}
			else
			{
				return -1;
			}
		}
		return total;
	}
	public String putInfo() {
		String s;
		String a="";
		Iterator it=getIterator();
		Flight f;
		while(it.hasNext()) {
			f=(Flight)it.next();
			a+="/"+f.getCode();
			a+="/"+prepaid.get(f);
		}
		s=this.getName()+"/"+this.getPassword()+"/"+wallet+a;
		return s;
	}
	public double getWallet() {
		return wallet;
	}
}
