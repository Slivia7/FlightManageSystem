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
//ע��������
	public User(String name,String password,FlightDB flights,double wallet) {
		// TODO �Զ����ɵĹ��캯�����
		super(name,password);
		this.flights=flights;
		this.wallet=wallet;
	}
	//��½�������
	public User(String name,String password,FlightDB flights,double wallet,HashMap<Flight,String> prepaid) {
		// TODO �Զ����ɵĹ��캯�����
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
		System.out.println("����ǰ���Ϊ��"+wallet);
		System.out.println("��Ŀǰ���г���");
		while(it.hasNext()) {
		System.out.println(it.next().toString());
		}
	}
	//֧�������ߡ���Ǯ����λ�ټ��붩��
	public int addFlight(String code,String seat) {//��Ҫ���붩������λ,ÿ��ֻ����һ�������һ��Ʊ
		Flight newFlight=flights.getFlightByCode(code);
		if(seat.equals("ͷ�Ȳ�")&&newFlight.hasFc()) {
		  if(wallet-newFlight.getPriceOfFc()>=0) {
			  wallet=wallet-newFlight.getPriceOfFc();
		      newFlight.setNumberofBoughtFc(newFlight.getNumberOfBoughtFc()+1);
		      this.prepaid.put(newFlight, seat);
		}
		  else {
				System.out.println("failed");
			  return 1;//˵������
		  }
		}
		else if(seat.equals("�����")&&newFlight.hasBc()) {
			if(wallet-newFlight.getPriceOfBc()>=0) {
			wallet=wallet-newFlight.getPriceOfBc();
		    newFlight.setNumberofBoughtBc(newFlight.getNumberOfBoughtBc()+1);
		    this.prepaid.put(newFlight, seat);
		}
			else {
				System.out.println("failed");
				  return 1;//˵������
			  }	
		}
		else {
			System.out.println("failed2");
			return 2;//˵��û�п�����λ
		}
		System.out.println("success");
		return 0;//˵��֧���ɹ�
	}
	//�˶������ߡ���Ǯ����λ���Ƴ�����
	public boolean cancelFlight(String code) {
		Flight flight=flights.getFlightByCode(code);
		String seat=prepaid.get(flight);
		if(seat.equals("ͷ�Ȳ�")) {
			wallet+=flight.getPriceOfFc();
			flight.setNumberofBoughtFc(flight.getNumberOfBoughtFc()-1);
			prepaid.remove(flight);
			return true;
		}
		else if(seat.equals("�����")){
			wallet+=flight.getPriceOfBc();
			flight.setNumberofBoughtBc(flight.getNumberOfBoughtBc()-1);
			prepaid.remove(flight);
			return true;	
		}
		else {
			return false;
		}
		
		
	}
	//���ݺ����
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
	//���ݼ�Ǯ��Χ
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
	//����Ŀ�ĵ�
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
	//���ݳ����ص�
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
	//���ݳ���ʱ��
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
	//���ݵ���ʱ��
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
			if(prepaid.get(f).equals("ͷ�Ȳ�")) {
				total+=f.getPriceOfFc();
			}
			else if (prepaid.get(f).equals("�����")) {
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
