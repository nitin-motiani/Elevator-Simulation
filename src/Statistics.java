/**
 * @author nitin
 * @brief : Statistics of all the persons arrived, all those who gave up, and totalWaitingTime of others
 */
class Statistics
{
	private int noOfPersons;			//total number of persons arrived
	private int noOfPersonsGaveUp;		//total number of persons who gave up and left
	private int totalWaitingTime;		//total waiting time of the others

	/**
	 * @return noOfPersons
	 */
	public int getNoOfPersons()
	{
		return noOfPersons;
	}

	/**
	 * @return noOfPersonsGaveUp
	 */
	public int getNoOfPersonsGaveUp()
	{
		return noOfPersonsGaveUp;
	}

	/**
	 * @return totalWaitingTime
	 */
	public int getTotalWaitingTime()
	{
		return totalWaitingTime;
	}

	/**
	 * @param noOfPersons
	 */
	public void setNoOfPersons(int noOfPersons)
	{
		this.noOfPersons = noOfPersons;
	}

	/**
	 * @param noOfPersonsGaveUp
	 */
	public void setNoOfPersonsGaveUp(int noOfPersonsGaveUp)
	{
		this.noOfPersonsGaveUp = noOfPersonsGaveUp;
	}

	/**
	 * @param totalWaitingTime
	 */
	public void setTotalWaitingTime(int totalWaitingTime)
	{
		this.totalWaitingTime = totalWaitingTime;
	}

	/**
	 * @brief : initially everything is 0.
	 */
	public Statistics()
	{
		noOfPersons = 0;
		noOfPersonsGaveUp = 0;
		totalWaitingTime = 0;
	}

	
	public void printStatistics()
	{
		System.out.println("Total Persons Entered : "+ noOfPersons);
		System.out.println("Total Number of Persons who gave up : "	+ noOfPersonsGaveUp);
		System.out.println("Total Waiting Time : "+ totalWaitingTime);
	}

}