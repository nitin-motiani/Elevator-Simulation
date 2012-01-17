/**
 * @author nitin
 * 
 */
class Person
{
	private int personNo; 					//unique number to identify the persons created in simulation
	private int sourceFloorNo;				//person waits at this floor for elevator
	private int destinationFloorNo;			//person wants to go to this floor
	private int maxWaitingTime;				//maximum time person will wait for elevator
	private int timePastInWaiting;			//the time person has spent in waiting. initially it is 0.


	/**
	 * @return  personNo
	 */
	public int getPersonNo()
	{
		return personNo;
	}

	/**
	 * @param personNo 
	 */
	public void setPersonNo(int personNo)
	{
		this.personNo = personNo;
	}

	/**
	 * @return sourceFloorNo
	 */
	public int getSourceFloorNo()
	{
		return sourceFloorNo;
	}

	/**
	 * @param sourceFloorNo
	 */
	public void setSourceFloorNo(int sourceFloorNo)
	{
		this.sourceFloorNo = sourceFloorNo;
	}

	/**
	 * @return destinationFloorNo
	 */
	public int getDestinationFloorNo()
	{
		return destinationFloorNo;
	}

	/**
	 * @param destinationFloorNo
	 */
	public void setDestinationFloorNo(int destinationFloorNo)
	{
		this.destinationFloorNo = destinationFloorNo;
	}

	/**
	 * @return maxWaitingTiime
	 */
	public int getMaxWaitingTime()
	{
		return maxWaitingTime;
	}

	/**
	 * @param maxWaitingTime
	 */
	public void setMaxWaitingTime(int maxWaitingTime)
	{
		this.maxWaitingTime = maxWaitingTime;
	}

	/**
	 * @return timePastInWaiting
	 */
	public int getTimePastInWaiting()
	{
		return timePastInWaiting;
	}

	/**
	 * @param timePastInWaiting
	 */
	public void setTimePastInWaiting(int timePastInWaiting)
	{
		this.timePastInWaiting = timePastInWaiting;
	}

	/**
	 * @brief : increment waiting time of the person by 1
	 */
	public void incrementWaitingTime()
	{
		timePastInWaiting = timePastInWaiting + 1;
	}

	/**
	 * @return true or false whether person will give up or not
	 * @brief : if person has waited for its maxWaitingTime, it will give up 
	 */
	public boolean giveUpAndLeave()
	{
		if (maxWaitingTime == timePastInWaiting)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
