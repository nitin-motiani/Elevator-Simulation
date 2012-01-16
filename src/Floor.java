import java.util.*;

/**
 * @author nitin
 * 
 */
class Floor
{
	private int floorNo;
	private boolean upButtonPressed;		
	private boolean downButtonPressed;
	private List<Person> personsWaitingForUpList = new ArrayList<Person>();
	private List<Person> personsWaitingForDownList = new ArrayList<Person>();

	/**
	 * @return floorNo
	 */
	public int getFloorNo()
	{
		return floorNo;
	}

	/**
	 * @param floorNo
	 */
	public void setFloorNo(int floorNo)
	{
		this.floorNo = floorNo;
	}

	/**
	 * @return upButtonPressed
	 */
	public boolean isUpButtonPressed()
	{
		return upButtonPressed;
	}

	/**
	 * @param upButtonPressed
	 */
	public void setUpButtonPressed(boolean upButtonPressed)
	{
		this.upButtonPressed = upButtonPressed;
	}

	/**
	 * @return downButtonPressed
	 */
	public boolean isDownButtonPressed()
	{
		return downButtonPressed;
	}

	/**
	 * @param downButtonPressed
	 */
	public void setDownButtonPressed(boolean downButtonPressed)
	{
		this.downButtonPressed = downButtonPressed;
	}

	/**
	 * @return personsWaitingForUpList
	 */
	public List<Person> getPersonsWaitingForUpList()
	{
		return personsWaitingForUpList;
	}

	/**
	 * @param personsWaitingForUpList
	 */
	public void setPersonsWaitingForUpList(List<Person> personsWaitingForUpList)
	{
		this.personsWaitingForUpList = personsWaitingForUpList;
	}

	/**
	 * @return personsWaitingForDownList
	 */
	public List<Person> getPersonsWaitingForDownList()
	{
		return personsWaitingForDownList;
	}
	
	/**
	 * @param personsWaitingForDownList
	 */
	public void setPersonsWaitingForDownList(List<Person> personsWaitingForDownList)
	{
		this.personsWaitingForDownList = personsWaitingForDownList;
	}
	
	/**
	 * @brief : called when persons waiting to go up enter an elevator.
	 */
	public void clearUpList()
	{
		personsWaitingForUpList.clear();
	}
	
	/**
	 * @brief : called when persons waiting to go down enter an elevator.
	 */
	public void clearDownList()
	{
		personsWaitingForDownList.clear();
	}

	

	/**
	 * @param person
	 * @brief : A new person arrives at the floor and starts waiting for an elevator.
	 * 			the source floor of the person will be this floor.
	 * 			based on destination floor, the person presses up button or down button
	 */
	public void addPersonToList(Person person)
	{
		if (person.getSourceFloorNo() != this.floorNo)
		{
			return;
		}
		else if (person.getDestinationFloorNo() > person.getSourceFloorNo())
		{
			personsWaitingForUpList.add(person);
			this.setUpButtonPressed(true);
		}
		else
		{
			personsWaitingForDownList.add(person);
			this.setDownButtonPressed(true);
		}

	}
}
