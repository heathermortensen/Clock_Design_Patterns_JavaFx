import java.awt.Button;

public abstract class AbstractClockState 
{
	
	int state; 
	 
	 public  AbstractClockState(Gui v)
	 {		
		 //state 1 is simply displaying the time to the user.
			
			//if (this.equals(null))
//			if (this == null)
//			{
//				int state1 = 1;
//				State1 myState = new State1(v);			
//				v.myState = myState;
//			
//			return v.myState;
//			}
			
			//If the state of the AbstractClockState == 1, then create an object
			//of state 2. State 2 allows us to set hours.
				
//			else if (this.state == 1)
//			{
//				int state2 = 2;
//				State1 myState = new State1(v);
//				v.myState = myState;
//						 
//			return v.myState;
//			}
			
			//If the state of the AbstractClockState == 2, then create an object
			//of state 1.
//			else
//			{
//				//a.incrementState();
//				myState = new State1(this.myState.state);
//				
//			return myState;
//			}
		
//			else
//			{
//				int state1 = 1;
//				State1 myState = new State1(v);
//				v.myState = myState;
//				
//			return v.myState;
//			}

	 }
	 
	protected void verifyState(int stateFromGui) 
	{
			//check state
		if (this.state == stateFromGui)
		{
				System.out.println("");
				System.out.println("New State " + this.state + " Created. Consistent clock state.");
				System.out.println("");
				
		
		}
		else
		{
				System.out.println("");
				System.out.println("ERROR - inconsistent state: New State Created w/ this.state = " + this.state + " = " + stateFromGui);
				
				this.incrementState();
				System.out.println("State incremented");
				System.out.println("");
	
		}
	}
//	public AbstractClockState createState(Gui v)
//	{
//
//	}
	 
	public abstract int changeMode(Button mode);
	
	public int getState()
	{
		return this.state;	
	}
	public int incrementState()
	{
		System.out.println("");
		System.out.println("------------------------------------------------------------ ");
		System.out.println("      ClockState                                             ");
		if (this.state < 4)
		{
			System.out.println("     Initial State = " + this.state);
			this.state = this.state + 1;
		}
		else
		{
			System.out.println("     Initial State = " + this.state);
			this.state = 1;
		}
	
		System.out.println("     AbstractClockState.incrementState() = " + this.state + "       ");
		System.out.println("------------------------------------------------------------");
	return this.state;	
	}
	public void decrementState()
	{
		if (this.state > 1)
		{
			this.state = this.state - 1;
			System.out.println("" );
			System.out.println("State decremented to " + this.state);
			System.out.println("" );
		
		}
		else
		{
			this.state = 4;
			System.out.println("" );
			System.out.println("State decremented to " + this.state);
			System.out.println("" );
		}
		
	}
	 //Concrete. override in state 4.
//	public long timerTick(Gui g)
//	{
//		NewTimer timer = new NewTimer();
//
//		timer.start();
//		
//		long i = timer.getId();
//		System.out.println("thread's id in AbstractClockState " + i);

		
		//timer.registerObserver(o);
		
//	return i;	
//	}
	



}
