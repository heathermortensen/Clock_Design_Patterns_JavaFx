

//This is the abstract class implemented by the concrete thread(TimerTick)
public interface Subject {
	
	public void registerObserver(Observer o);
	
	public void removeObserver(Observer o);

	public void notifyObservers(int i);

	

	//void notifyObservers(int i, int j, int k);

//	void notifyObservers(int i, Object o);

//	void notifyObservers(int i, int j, int k, Gui view);

}
