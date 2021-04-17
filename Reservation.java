
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	String name;
	String passport;
	String seat;
	String seatType;
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
	}
	
	public Reservation(String flightNum, String name, String passport)
	{
		this.flightNum = flightNum;
		this.name = name;
		this.passport = passport;
	}
	
	public Reservation(String flightNum, String info, String name, String passport, String seat, String seatType)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.name = name;
		this.passport = passport;
		this.seat = seat;
		this.seatType = seatType;
	}
	
	public String getPassengerName() 
	{
		return name;
	}
	public String getPassengerPassport() 
	{
		return passport;
	}
	public String getFlightNum()
	{
		return flightNum;
	}
	public String getSeat()
	{
		return seat;
	}
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}
	
	public boolean equals(Object other)
	{
		Reservation otherRes = (Reservation) other;
		return flightNum.equals(otherRes.flightNum)&&  name.equals(otherRes.name) && passport.equals(otherRes.passport); 
	}

	public void print()
	{
		System.out.println(flightInfo + " " + name + " " + seat);
	}
}
