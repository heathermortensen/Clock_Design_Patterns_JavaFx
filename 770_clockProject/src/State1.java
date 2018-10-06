import java.awt.Button;

import javax.swing.ButtonGroup;

//state 1 merley displays time to the user.

public class State1 extends AbstractClockState
{
	
	int state = 1;
	
	
	public <T> State1(Gui<T> g) 
	{
		//super(stateFromGui);
		super(g);
		//g.myState.state++;
	  	//g.setState();
		//State1 newState = new State1(g);
		//g.myState = newState;
		
		
		//verifyState(g.myState.state);
		
		
	}

	//Concrete. override in state 4.
	//inherit this from AbstrackClockState
//	public void timerTick()
//	{
//		
//	}

	public void increment(Gui view)
	{
		
	}
	public void decrement()
	{
		//empty methods because this button doesn't exist in state 1.
	}
	@Override
	public int changeMode(Button mode) 
	{
		incrementState();
		
	return this.state;	
	}
	
}
