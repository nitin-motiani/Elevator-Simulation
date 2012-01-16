import java.util.*;
/**
 * @author nitin
 * 
 */
class Elevator 
{
	private int elevatorNumber;					//unique for every elevator
	private int currentFloorNo;
	private ElevatorState elevatorState;		//current state of the elevator
	private List<Person> passengersList = new ArrayList<Person>();		//list of passengers in elevator
	private SortedSet<Integer> pushedButtons = new TreeSet<Integer>();	//pushed buttons in elevator
	
	
	/**
	 * @return elevatorNumber
	 */
	public int getElevatorNumber()
	{
		return elevatorNumber;
	}


	/**
	 * @param elevatorNumber 
	 */
	public void setElevatorNumber(int elevatorNumber)
	{
		this.elevatorNumber = elevatorNumber;
	}
	
	
	/**
	 * @param currentFloorNo
	 */
	public void setCurrentFloorNo(int currentFloorNo)
	{
		this.currentFloorNo = currentFloorNo;
	}

	
	/**
	 * @return currentFloorNo
	 */
	public int getCurrentFloorNo()
	{
		return currentFloorNo;
	}

	
	/**
	 * @param elevatorState
	 */
	public void setElevatorState(ElevatorState elevatorState)
	{
		this.elevatorState = elevatorState;
	}

	
	public ElevatorState getElevatorState()
	{
		return elevatorState;
	}


	/**
	 * @return passengerList
	 */
	public List<Person> getPassengersList()
	{
		return passengersList;
	}


	/**
	 * @param passengersList
	 */
	public void setPassengersList(List<Person> passengersList)
	{
		this.passengersList = passengersList;
	}


	/**
	 * @return pushedButtons
	 */
	public SortedSet<Integer> getPushedButtons()
	{
		return pushedButtons;
	}


	/**
	 * @param pushedButtons
	 */
	public void setPushedButtons(SortedSet<Integer> pushedButtons)
	{
		this.pushedButtons = pushedButtons;
	}
	
	/**
	 * @param floorNo
	 * 
	 * press button for floorNo
	 */
	public void pressButton(int floorNo)
	{
		pushedButtons.add(floorNo);
	}
	
	/**
	 * @param person : person should be at currentFloorNo of the elevator
	 * 
	 */
	public void addPassengerToList(Person person)
	{
		if (this.currentFloorNo != person.getSourceFloorNo())
		{
			return;
		}
		else 
		{
			this.passengersList.add(person);
			this.pressButton(person.getDestinationFloorNo());
		}
	}
	
	
	/**
	 * @param floorNo : elevator should be at floorNO
	 * 
	 * @brief : unload passengers whose destination is that floor.
	 */
	public void unLoadPassengers(int floorNo)
	{
		List<Person> personsList = new ArrayList<Person>();
		SortedSet<Integer> buttonsList = new TreeSet<Integer>();
		
		if (this.currentFloorNo != floorNo)
		{
			return;
		}
		
		for (Person person : passengersList)
		{
			if (person.getDestinationFloorNo() != floorNo)
			{
				personsList.add(person);
			}
		}
		this.setPassengersList(passengersList);
		
		//remove floorNo from pushedButtons
		for (Integer button : pushedButtons)
		{
			if (button != floorNo)
			{
				buttonsList.add(button);
			}
		}
		this.setPushedButtons(buttonsList);
		
	}
	
	
	/**
	 * @param floorNo
	 * @return true or false whether any passenger is to be unloaded at floorNo
	 */
	public boolean unloadAtThisFloor(int floorNo)
	{
		if (pushedButtons.contains(floorNo))
		{
			return true;
		}
		return false;
	}


	
	
	
}
