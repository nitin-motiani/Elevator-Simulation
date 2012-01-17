/**
 * @author nitin
 * @brief : Event when a person arrives at a floor to get an elevator
 */
class PersonArrives extends Event
{
	private Person person;
	private Floor floor;
	
	
	/**
	 * @return person
	 */
	public Person getPerson()
	{
		return person;
	}


	/**
	 * @return floor
	 */
	public Floor getFloor()
	{
		return floor;
	}


	/**
	 * @param person 
	 */
	public void setPerson(Person person)
	{
		this.person = person;
	}


	/**
	 * @param floor
	 */
	public void setFloor(Floor floor)
	{
		this.floor = floor;
	}

	@Override
	void happen()
	{
		floor.addPersonToList(person);
		System.out.println("Person No. " + person.getPersonNo() + 
				" arrives at floor No. : " + floor.getFloorNo());
	}
	
}
