package code;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Flight {
	private String code;
	private Date startTime;
	private Date endTime;
	private String startPlace;
	private String endPlace;
	private int numberOfFc;
	private int numberOfBc;
	private int numberOfBoughtFc;
	private int numberOfBoughtBc;
	private double priceOfFc;
	private double priceOfBc;
	public Flight(String code,Date startTime,Date endTime,String startPlace,
			String endPlace,int numberOfFc,int numberOfBc,int NumberofBoughtFc,
			int NumberofBoughtBc,double priceOfFc,double priceOfBc
			) {
		this.code=code;
		this.startTime=startTime;
		this.endTime=endTime;
		this.startPlace=startPlace;
		this.endPlace=endPlace;
		this.numberOfFc=numberOfFc;
		this.numberOfBc=numberOfBc;
		this.numberOfBoughtFc=NumberofBoughtFc;
		this.numberOfBoughtBc=NumberofBoughtBc;
		this.priceOfFc=priceOfFc;
		this.priceOfBc=priceOfBc;
	} 
	public Flight() {
		// TODO 自动生成的构造函数存根
	}
	public String getCode() {
		return code;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public int getNumberOfFc() {
	    return numberOfFc;	
	}
	public int getNumberOfBc() {
		return numberOfBc;
	}
	public int getNumberOfBoughtFc() {
		return numberOfBoughtFc;
	}
	public int getNumberOfBoughtBc() {
		return numberOfBoughtBc;
	}
	public double getPriceOfFc() {
		return priceOfFc;
	}
	public double getPriceOfBc() {
		return priceOfBc;
	}

	public boolean equalls(Object objecet) {
		if(objecet instanceof Flight&&
				objecet.toString().equals(this.toString()))
		return true;
		else
			return false;
		
	}
	public void setNumberofBoughtFc(int count) {
		numberOfBoughtFc=count;
	}
	public void setNumberofBoughtBc(int count) {
		numberOfBoughtBc=count;
	}
	public boolean hasFc() {
		if(numberOfFc>numberOfBoughtFc) {
			return true;
		}
		else
			return false;
	}
	public boolean hasBc() {
		if(numberOfBc>numberOfBoughtBc) {
			return true;
		}
		else
			return false;
	}
	public String putInfo() {
		String s=new String();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		s= "code:"+code+"/"
		   +format.format(startTime)+"/"
     	   +format.format(endTime)+"/"
		   +startPlace+"/"
		   +endPlace+"/"
		   +numberOfFc+"/" 
		   +numberOfBc+"/"
		   +numberOfBoughtFc+"/"
		   +numberOfBoughtBc+"/"
		   +priceOfFc+"/"
		   +priceOfBc;
		return s;
	}
	
	public String toString() {
		String s=new String();
		
		s= "code:"+code+"\n\r"
		   +"startTime:"+startTime.toString()+"\n\r"
     	   +"endTime:"+endTime.toString()+"\n\r"
		   +"startPlace:"+startPlace+"\n\r"
		   +"endPlace:"+endPlace+"\n\r"
		   +"numberOfFc:"+numberOfFc+"\n\r" 
		   +"numberOfBc:"+numberOfBc+"\n\r"
		   +"NumberofBoughtFc:"+numberOfBoughtFc+"\n\r"
		   +"NumberofBoughtBc:"+numberOfBoughtBc+"\n\r"
		   +"priceOfFc:"+priceOfFc+"\n\r"
		   +"priceOfBc:"+priceOfBc+"\n\r";
		return s;
	}

}
