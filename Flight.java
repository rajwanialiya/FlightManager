import java.util.ArrayList;
import java.util.Random;

/**		
 // ---- EXCEPTIONS ---
 */
//
class DuplicatePassengerException extends Exception{
	public DuplicatePassengerException(Passenger p){
		super("Duplicate Passenger " + p.getName() + p.getPassport());
	}
}

class PassengerNotInManifestException extends Exception{
	public PassengerNotInManifestException(Passenger p){
		super("Passenger" + p.getName() + p.getPassport() + "not in flight manifest");
	}
}

class SeatOccupiedException extends Exception{
	public SeatOccupiedException(String seat){
		super("Seat" + seat + "already occupied");
	}
}


public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};
	public static enum Type {SHORTHAUL, MEDIUMHAUL, LONGHAUL};
	public static enum SeatType {ECONOMY, FIRSTCLASS, BUSINESS};

	private String flightNum;
	private String airline;
	private String origin, dest;
	private String departureTime;
	private Status status;
	private int flightDuration;
	protected Aircraft aircraft;
	protected int numPassengers;
	protected Type type;
	protected ArrayList<Passenger> manifest;
	protected TreeMap <String, Passenger> seatMap;
	}
	
	protected Random random = new Random();
	
	private String errorMessage = "";
	  
	public String getErrorMessage()
	{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Flight()
	{
		this.flightNum = "";
		this.airline = "";
		this.dest = "";
		this.origin = "Toronto";
		this.departureTime = "";
		this.flightDuration = 0;
		this.aircraft = null;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
	}
	
	public Flight(String flightNum)
	{
		this.flightNum = flightNum;
	}
	
	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
	}
	
	public Type getFlightType()
	{
		return type;
	}
	public Aircraft getAircraft() {
		return aircraft;
	}
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getNumPassengers()
	{
		return numPassengers;
	}
	public void setNumPassengers(int numPassengers)
	{
		this.numPassengers = numPassengers;
	}

	
	
	public void assignSeat(Passenger p)
	{
		int seat = random.nextInt(aircraft.numEconomySeats);
		p.setSeat("ECO"+ seat);
	}
	
	public String getLastAssignedSeat()
	{
		if (!manifest.isEmpty())
			return manifest.get(manifest.size()-1).getSeat();
		return "";
	}
	
	public boolean seatsAvailable(String seatType)
	{
		if (!seatType.equalsIgnoreCase("ECO")) return false;
		return numPassengers < aircraft.numEconomySeats;
	}
	
	public boolean cancelSeat(String name, String passport, String seatType)
	{
		if (!seatType.equalsIgnoreCase("ECO")) 
		{
			errorMessage = "Flight " + flightNum + " Invalid Seat Type " + seatType;
			return false; 	
		}

		Passenger p = new Passenger(name, passport);
		
		if (manifest.indexOf(p) == -1) 
		{
			throw PassengerNotInManifestException;
		}

		manifest.remove(p);
		if (numPassengers > 0) numPassengers--;
		return true;
	}
	
	public boolean reserveSeat(String name, String passport, String seatType)
	{
		if (numPassengers >= aircraft.getNumSeats())
		{
			errorMessage = "Flight " + flightNum + " Full";
			return false;
		}

		if(seatMap.containsKey(seatType)){
			throw new SeatOccupiedException;
		}

		if (!seatType.equalsIgnoreCase("ECO")) 
		{
			errorMessage = "Flight " + flightNum + " Invalid Seat Type Request";
			return false;
		}	
		// Check for duplicate passenger
		Passenger p = new Passenger(name, passport, "", seatType);
	
		if (manifest.indexOf(p) >=  0)
		{
			throw DuplicatePassengerException;
		}
		seatMap.put(seatType);
		assignSeat(p);
		manifest.add(p);
		numPassengers++;
		return true;
	}

	public void printPassengerManifest(){
		for(int i = 0; i < manifest.size(); i++){
			System.out.println(manifest.get(i));
		}
	}

	public void printSeat(){

	}
	
	public boolean equals(Object other)
	{
		Flight otherFlight = (Flight) other;
		return this.flightNum.equals(otherFlight.flightNum);
	}
	
	public String toString()
	{
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
	}
}
