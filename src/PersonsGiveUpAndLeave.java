import java.util.ArrayList;
import java.util.List;

/**
 * @author nitin
 * @brief : Event when persons, who have waited for their maxWaitingTime, give and leave
 */
class PersonsGiveUpAndLeave extends Event
{

	private List<Floor> floorList;			//list of floors
	Statistics statistics;					//to be updated based on number of people who gave up
	
	/**
	 * @param floorList
	 * @param statistics
	 */
	public PersonsGiveUpAndLeave(List<Floor> floorList, Statistics statistics)
	{
		super();
		this.floorList = floorList;
		this.statistics = statistics;
	}


	@Override
	public void happen()
	{

		/*
		 * For all the persons at all the floors, increment timePastInWaiting, as one time unit has passed.
		 * After that remove those, who have give up,  from the lists of people waiting for elevator
		 */
		for (Floor floor : floorList)
		{
			List<Person> personsWaitingForUpList = new ArrayList<Person>();
			List<Person> personsWaitingForDownList = new ArrayList<Person>();

			for (Person person : floor.getPersonsWaitingForDownList())
			{
				person.incrementWaitingTime();
				if (!person.giveUpAndLeave())
				{
					personsWaitingForDownList.add(person);
				}
				else
				{
					statistics.setNoOfPersonsGaveUp(statistics.getNoOfPersonsGaveUp() + 1);		
					System.out.println("Person No. " + person.getPersonNo() + " at floor No. " + floor.getFloorNo() + " gives up and leaves.");
				}

			}
			for (Person person : floor.getPersonsWaitingForUpList())
			{
				person.incrementWaitingTime();
				if (!person.giveUpAndLeave())
				{
					personsWaitingForUpList.add(person);
				}
				else
				{
					statistics.setNoOfPersonsGaveUp(statistics.getNoOfPersonsGaveUp() + 1);
					System.out.println("Person No. " + person.getPersonNo() + " at floor No. " + floor.getFloorNo() + " gives up and leaves.");
				}

			}

			floor.setPersonsWaitingForUpList(personsWaitingForUpList);
			floor.setPersonsWaitingForDownList(personsWaitingForDownList);

		}
	}

}
