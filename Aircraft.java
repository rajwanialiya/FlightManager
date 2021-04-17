
public class Aircraft implements Comparable<Aircraft>
{
  int numEconomySeats;
  int numFirstClassSeats;
  
	String model;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

  public int compareTo(Aircraft other)
  {
  	if (this.numEconomySeats == other.numEconomySeats)
  		return this.numFirstClassSeats - other.numFirstClassSeats;
  	
  	return this.numEconomySeats - other.numEconomySeats; 
	}
	
	public String[][] setSeatLayout() 
	{
		int totalSeats = getTotalSeats();
		int firstClassSeats = getNumFirstClassSeats();
		String letter[] = {"A", "B", "C", "D"};
		String[][] seatLayout = new String[4][totalSeats/4];
		
		// POPULATE 2D ARRAY SEAT LAYOUT
		for (int i = 0; i < 4; i++) {
			String ch = letter[i];
			for (int j = 0; j < totalSeats/4; j++){
				seatLayout[i][j] = (j+1) + ch;
			}
		}

		while (firstClassSeats > 0) {
			for (int j; j < totalSeats/4; j++) {
				for (int i; )
			}
					seatLayout[i][j] = seatLayout[i][j] + "+";
					firstClassSeats--;
		}

		// PRINT SEAT LAYOUT IN THE REQUIRED FORMAT
		for (int i = 0; i < 4; i++) {
			if (i == 2) {
				System.out.println();
			}
			for (int j = 0; j < totalSeats/4; j++) {
				System.out.print(seatLayout[i][j] + " ");
			}
			System.out.println();
		}

		return seatLayout;
	}
}
