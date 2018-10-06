//subject (thread) will call this when it needs to update the gui when it changes
public interface Observer {
	
	//public void update();

	public void update(int iNew) throws InterruptedException;

//	public void update(int totalTime);

	

}
