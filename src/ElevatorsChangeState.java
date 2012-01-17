import java.util.List;


/**
 * @author nitin
 * @brief : Event will execute in every time unit, and change 
 * the states of all the elevators in that time unit.
 */
class ElevatorsChangeState extends Event
{
	
	private List<Elevator> elevatorList;
	private List<Floor> floorList;
	private Statistics statistics;
	private int numberOfFloors;
	@SuppressWarnings("unused")
	private int numberOfElevators;
	

	/**
	 * @param elevatorList
	 * @param floorList
	 * @param statistics
	 * @param numberOfFloors
	 * @param numberOfElevators
	 */
	public ElevatorsChangeState(List<Elevator> elevatorList,
			List<Floor> floorList, Statistics statistics, int numberOfFloors,
			int numberOfElevators)
	{
		super();
		this.elevatorList = elevatorList;
		this.floorList = floorList;
		this.statistics = statistics;
		this.numberOfFloors = numberOfFloors;
		this.numberOfElevators = numberOfElevators;
	}


	@Override
	void happen()
	{
		int elevatorNumber = 1;
		for (Elevator elevator : elevatorList)
		{
			executeNextMove(elevator);
			elevatorNumber++;
		}
	}
	
	
	/**
	 * @param elevator
	 * @param personsList
	 * @param resultingElevatorState
	 * 
	 * @brief : All the persons in the list will enter the elevator, and elevatorState of the 
	 * 			elevator is set to resultingElevatorState
	 */
	private void passengersEnterElevator(Elevator elevator, List<Person> personsList, 
			ElevatorState resultingElevatorState)
	{
		for (Person person : personsList)
		{
			elevator.addPassengerToList(person);
			statistics.setTotalWaitingTime(statistics.getTotalWaitingTime() + person.getTimePastInWaiting());
			System.out.println("Person No. " + person.getPersonNo() + " at floor " 
					+ person.getSourceFloorNo() + " entered elevator number " 
					+ elevator.getElevatorNumber());
		}
		elevator.setElevatorState(resultingElevatorState);
	}
	
	/**
	 * @param elevator
	 * @param floor
	 * 
	 * @brief : The passengers, whose destination is floor, 
	 * 			will exit from the elevator.
	 */
	private void unloadElevator(Elevator elevator,Floor floor)
	{
		int currentFloorNo = floor.getFloorNo();
		elevator.unLoadPassengers(currentFloorNo);
		
		System.out.println("Passengers exit elevator number " + elevator.getElevatorNumber() +
				" at floor number " + currentFloorNo);
		
		if (elevator.getElevatorState() == ElevatorState.unloadingGoingUp)
		{
			elevator.setElevatorState(ElevatorState.waitingUp);
		}
		else if (elevator.getElevatorState() == ElevatorState.unloadingGoingDown)
		{
			elevator.setElevatorState(ElevatorState.waitingDown);
		}
		
	}
	
	/**
	 * @param elevator
	 * @param floor : the floor at which elevator currently is 
	 * 
	 * @brief : if elevator can take passengers from that floor, it will take them.
	 * 			else if it has passengers or it has not reached highest or lowest 
	 * 			floor, it will keep moving.
	 * 			otherwise it will stop at that floor
	 */
	private void loadOrMoveElevator(Elevator elevator, Floor floor)
	{
		int currentFloorNo = floor.getFloorNo();

		
		/*
		 * if elevator is moving up, and persons waiting to go up are on that floor,
		 * they will enter the elevator in that time unit.
		 * 
		 * else if elevator is empty, and persons waiting to go down are on that
		 * floor, they will enter the elevator in that time unit.
		 * 
		 * else elevator will move up one floor in that time unit.
		 * 
		 */
		if (elevator.getElevatorState() == ElevatorState.waitingUp
				|| elevator.getCurrentFloorNo() == 0)
		{
			floor.setUpButtonPressed(false);
			if (!floor.getPersonsWaitingForUpList().isEmpty())
			{
				passengersEnterElevator(elevator, floor.getPersonsWaitingForUpList(),
						ElevatorState.movingUp);
				floor.clearUpList();
			}
			else if (!floor.getPersonsWaitingForDownList().isEmpty()
					&& currentFloorNo != 0
					&& elevator.getPassengersList().isEmpty())
			{
				floor.setDownButtonPressed(false);
				if (!floor.getPersonsWaitingForDownList().isEmpty())
				{
					passengersEnterElevator(elevator, floor.getPersonsWaitingForDownList(), 
							ElevatorState.movingDown);
					floor.clearDownList();
				}
				
			}
			else if (!elevator.getPassengersList().isEmpty() && currentFloorNo != numberOfFloors - 1)
			{
				elevator.setCurrentFloorNo(currentFloorNo + 1);
				System.out.println("Elevator No. "+ elevator.getElevatorNumber() + " moves to " + (currentFloorNo + 1));
				if (elevator.unloadAtThisFloor(currentFloorNo + 1) )
				{
					elevator.setElevatorState(ElevatorState.unloadingGoingUp);
				}
				else if (currentFloorNo + 1 == numberOfFloors - 1)
				{
					elevator.setElevatorState(ElevatorState.waitingUp);
				}
				else
				{
					elevator.setElevatorState(ElevatorState.movingUp);
				}
				
			}
		}
		
		/*
		 * if elevator is moving down, and persons waiting to go down are on that floor,
		 * they will enter the elevator in that time unit.
		 * 
		 * else if elevator is empty, and persons waiting to go up are on that
		 * floor, they will enter the elevator in that time unit.
		 * 
		 * else elevator will move down one floor in that time unit.
		 * 
		 */
		else if (elevator.getElevatorState() == ElevatorState.waitingDown
				|| elevator.getCurrentFloorNo() == numberOfFloors - 1)
		{
			floor.setDownButtonPressed(false);
			if (!floor.getPersonsWaitingForDownList().isEmpty())
			{
				passengersEnterElevator(elevator, floor.getPersonsWaitingForDownList(), 
						ElevatorState.movingDown);
				floor.clearDownList();
			}
			else if (!floor.getPersonsWaitingForUpList().isEmpty()
					&& currentFloorNo != numberOfFloors - 1
					&& elevator.getPassengersList().isEmpty())
			{
				floor.setUpButtonPressed(false);
				if (!floor.getPersonsWaitingForUpList().isEmpty())
				{
					passengersEnterElevator(elevator, floor.getPersonsWaitingForUpList(), 
							ElevatorState.movingUp);
					floor.clearUpList();
				}
			}
			else if (!elevator.getPassengersList().isEmpty() && currentFloorNo != 0)
			{
				elevator.setCurrentFloorNo(currentFloorNo - 1);
				System.out.println("Elevator No. "+ elevator.getElevatorNumber() + " moves to " + (currentFloorNo - 1));
				if (elevator.unloadAtThisFloor(currentFloorNo - 1))
				{
					elevator.setElevatorState(ElevatorState.unloadingGoingDown);
				}
				else if (currentFloorNo - 1 == 0)
				{
					elevator.setElevatorState(ElevatorState.waitingDown);
				}
				else
				{
					elevator.setElevatorState(ElevatorState.movingDown);
				}
			}
		}

		//in the end, if there is no one inside elevator, it will stop.
		if (elevator.getPushedButtons().isEmpty())
		{
			elevator.setElevatorState(ElevatorState.stationary);
		}
		
	}
	
	
	/**
	 * @param elevator : elevator is stationary
	 * @brief : if there is any button pressed on any floor, the elevator will go there
	 * 			it will look for the direction of nearest such floor, and will move in that
	 * 			direction.
	 * 			if the nearest floor is at same distance on both side, the priority order is following : 
	 * 			
	 * 			1. floor is up, and up button is pressed.
	 * 			2. floor is down, and down button is pressed.
	 * 			3. floor is up, and down button is pressed.
	 * 			4. floor is down, and up button is pressed.
	 *  
	 */
	private void moveElevator(Elevator elevator )
	{
		int currentFloorNo = elevator.getCurrentFloorNo();
		Floor currentFloor = floorList.get(currentFloorNo);
		
		if (currentFloor.isUpButtonPressed())
		{
			currentFloor.setUpButtonPressed(false);
			if (!currentFloor.getPersonsWaitingForUpList().isEmpty())
			{
				passengersEnterElevator(elevator, currentFloor.getPersonsWaitingForUpList(), 
						ElevatorState.movingUp);
				currentFloor.clearUpList();
			}
			return;
		}
		else if (currentFloor.isDownButtonPressed())
		{
			currentFloor.setDownButtonPressed(false);
			if (!currentFloor.getPersonsWaitingForDownList().isEmpty())
			{
				passengersEnterElevator(elevator, currentFloor.getPersonsWaitingForDownList(), 
						ElevatorState.movingDown );
				currentFloor.clearDownList();
			}
			return;
		}
		
		int floorsToMove = 1;
		while (currentFloorNo + floorsToMove < numberOfFloors ||
				currentFloorNo - floorsToMove >= 0)
		{
			int upperFloorNo = currentFloorNo + floorsToMove;
			int lowerFloorNo = currentFloorNo - floorsToMove;
			Floor upperFloorToCheck = null;
			Floor lowerFloorToCheck = null;
			
			if (upperFloorNo < numberOfFloors) 
			{
				upperFloorToCheck = floorList.get(upperFloorNo);
			}
			if (lowerFloorNo >= 0)
			{
				lowerFloorToCheck = floorList.get(lowerFloorNo);
			}
			
			if (upperFloorToCheck != null && lowerFloorToCheck != null)
			{
				if (upperFloorToCheck.isUpButtonPressed())
				{
					elevator.setElevatorState(ElevatorState.movingUp);
					return;
				}
				else if (lowerFloorToCheck.isDownButtonPressed())
				{
					elevator.setElevatorState(ElevatorState.movingDown);
					return;
				}
				else if (upperFloorToCheck.isDownButtonPressed())
				{
					elevator.setElevatorState(ElevatorState.movingUp);
					return; 
				}
				else if (lowerFloorToCheck.isUpButtonPressed())
				{
					elevator.setElevatorState(ElevatorState.movingDown);
					return;
				}
				
			}
			else if (upperFloorToCheck != null)
			{
				if (upperFloorToCheck.isDownButtonPressed() || 
						upperFloorToCheck.isUpButtonPressed())
				{
					elevator.setElevatorState(ElevatorState.movingUp);
					return; 
				}
				
			}
			else if (lowerFloorToCheck != null)
			{
				if (lowerFloorToCheck.isDownButtonPressed() || 
						lowerFloorToCheck.isUpButtonPressed())
				{
					elevator.setElevatorState(ElevatorState.movingDown);
					return;
				}
				
			}
			
			floorsToMove++;
		}
		
	}

	/**
	 * @param elevator
	 * @brief : execute next move of the elevator in that time unit.
	 */
	private void executeNextMove(Elevator elevator)
	{
		int currentFloorNo;
		Floor floor;

		/*
		 * if elevator is moving up, move it up by one floor.
		 */
		if (elevator.getElevatorState() == ElevatorState.movingUp)
		{
			currentFloorNo = elevator.getCurrentFloorNo() + 1;
			System.out.println("Elevator No. "+ elevator.getElevatorNumber() + " moves to " + currentFloorNo);
			elevator.setCurrentFloorNo(currentFloorNo);
			floor = floorList.get(currentFloorNo);
			
			/*
			 * if there are passengers to exit at the floor, unload them.
			 */
			if (elevator.unloadAtThisFloor(currentFloorNo))
			{
				elevator.setElevatorState(ElevatorState.unloadingGoingUp);
			}
			/*
			 * else if its the highest floor, or if up button is pressed at the floor,
			 * stop for them
			 */
			else if (floor.isUpButtonPressed()
					|| currentFloorNo == numberOfFloors - 1)
			{
				elevator.setElevatorState(ElevatorState.waitingUp);
			}
		}
		/*
		 * if elevator is moving down, move it down by one floor.
		 */
		else if (elevator.getElevatorState() == ElevatorState.movingDown)
		{
			currentFloorNo = elevator.getCurrentFloorNo() - 1;
			System.out.println("Elevator No. "+ elevator.getElevatorNumber() + " moves to " + currentFloorNo);
			elevator.setCurrentFloorNo(currentFloorNo);
			floor = floorList.get(currentFloorNo);

			/*
			 * if there are passengers to exit at the floor, unload them.
			 */
			if (elevator.unloadAtThisFloor(currentFloorNo) )
			{
				elevator.setElevatorState(ElevatorState.unloadingGoingDown);
			}
			/*
			 * else if its the lowest floor, or if down button is pressed at the floor,
			 * stop for them
			 */
			else if (floor.isDownButtonPressed()
					|| currentFloorNo == 0)
			{
				elevator.setElevatorState(ElevatorState.waitingDown);
			}
			
		}
		/*
		 * if stopped in last time unit, load passengers
		 * or move if there are none to load.
		 * if no passenger in the elevator, make it stationary
		 */
		else if (elevator.getElevatorState() == ElevatorState.waitingUp || 
				elevator.getElevatorState() == ElevatorState.waitingDown)
		{
			currentFloorNo = elevator.getCurrentFloorNo();
			floor = floorList.get(currentFloorNo);
			loadOrMoveElevator(elevator, floor);
		}
		/*
		 * if passengers to be unloaded, unload them in this time unit.
		 */
		else if (elevator.getElevatorState() == ElevatorState.unloadingGoingDown || 
				elevator.getElevatorState() == ElevatorState.unloadingGoingUp)
		{
			currentFloorNo = elevator.getCurrentFloorNo();
			floor = floorList.get(currentFloorNo);
			unloadElevator(elevator,floor);
		}
		/*
		 * if elevator is stationary, check if it can be moved in this time unit.
		 */
		else
		{
			moveElevator(elevator);
		}
		
	}

}
