/**
 * @author nitin
 *
 * @brief : enum for different states of the elevator.
 */
enum ElevatorState
{
	stationary,				
	movingUp,
	movingDown,
	waitingUp,				//Elevator has stopped at some floor in between the process of moving up.
	waitingDown,			//Elevator has stopped at some floor in between the process of moving down.
	unloadingGoingUp,		//Elevator is unloading passengers at a floor in between moving up.
	unloadingGoingDown;		//Elevator is unloading passengers at a floor in between moving up.
}
