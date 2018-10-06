import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Timer;

public class NewTimer extends Thread implements Runnable, Subject
{
	

//////////////////////////////////////////////////////////////////
//my TimerTick gets passed into a thread in gui constructor and 
//becpmes the 'subject' in the observer pattern.
//////////////////////////////////////////////////////////////////

//This represents the threads job

private int i = 0;
private int sumOfSeconds = 0;
private ArrayList<Observer> observers = new ArrayList<Observer>();
int delay = 1000; //milliseconds

public int hr;
int min; 
int sec;

boolean stopWasRequested = false;

//		Big Problem here

public void run()
{
	Calendar myCalendar = new GregorianCalendar();
	
	 int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
	 int  minuite = myCalendar.get(Calendar.MINUTE);
	 int second = myCalendar.get(Calendar.SECOND);
	 
	 this.hr = hour;
	 this.min = minuite;
	 this.sec = second;
			
		System.out.println(" Inital seconds in GUI = " +  second);

		this.sumOfSeconds = second % 60;
		
		//this.registerObserver(this.);
	//	this.registerObserver((Observer) this.getContextClassLoader());
	
		ActionListener taskPerformer = new ActionListener() {
			
			

			public void actionPerformed(ActionEvent evt) {
	        //...Perform a task...
	
				System.out.println(" ");
				
				int sumOfSeconds = second + i;
				
				System.out.print(" Time = " + second + " + " + i + " = " );
				System.out.print(sumOfSeconds);
				notifyObservers(sumOfSeconds);
				int sum = (i + second) % 60;

				i++;
			
			}
			/////////////////////////////////////////////////
			// nested methods
			//////////////////////////////////////////////////
		

		};


	//Here is the first output, the second is in RunClock.java
////////////////////////////////////////////////////////////////////
		new Timer(delay, taskPerformer).start();
		//int i = getThisTimerValue();
		
		
/////////////////////////////////////////////////////////////////////
		//System.out.println("Timer is running?");
		
//	}


}//close run()



public int getThisTimerValue()
{
return this.sumOfSeconds;
}

public void shutDown()
{
	stopWasRequested = true;
}


public void registerObserver(Observer o) 
{

	observers.add(o);

}

public void removeObserver(Observer o) 
{
	int j = observers.indexOf(o);

	if (i >= 0)
		observers.remove(j);

	System.out.println("observer # " + j + "was deleted.");

}

public void notifyObservers(int totalTime)
{

	for (Observer observer: observers)
	{

		try {
			observer.update(totalTime);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
	//passes in current state inside class RunClock
	}
}

//@Override
//public void notifyObservers(int i) 
//{
//	for (Observer observer: observers)
//	{
//		try {
//			observer.update(i);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}



public int getTime()
{
return this.i;
}

public void setTime(int i) throws InterruptedException
{
	this.i = i;
	System.out.println("     setTime = " + getTime() + " seconds running");
	notifyObservers(i);

}















}//close 



