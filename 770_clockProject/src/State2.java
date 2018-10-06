import java.awt.Button;

public class State2 extends AbstractClockState 
{
	int state = 2;

	public <T> State2(Gui<T> g) 
	{
		super(g);
		//g.myState.state++;
		//State2 newState = new State2(g);
		//g.myState = newState;
		//verifyState(g.myState.state);
	}

	@Override
	public int changeMode(Button mode) {
		
		
		return this.state;
	}

}
