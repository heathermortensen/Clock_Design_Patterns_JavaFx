//import Gui.TimerTick;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class RunClock<T> extends Application implements Subject
{
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	int clockState = 0;
	private int i = 0;
	

	public static void main(String[] args) throws InterruptedException
	{
		//this method is from inside the Application class.
		//It sets up your program as a javaFx Application, then calls start().
		
		// Application.launch() -> Application.start()
		
		Application.launch(args);
		
		//1. Create subject (the thread)
		//My subject is created inside the gui constructor.
		
		//2. Pass the subject to each view. I currently only have one
		//	in Gui.java and an unimplemented State2Display.
		
		//3. Set the data inside the subject
		//	Thats running on its ownd inside the thread.
		
		//lets test and see if I can set the data 
		
	
		//timer thread is started inside of here.
		//Must override this eventually.
		//timer.start() is inside of AbstractClockState implements ClockStateInterface
		//timerTick() method of AbstractClockState
		//State1 one = new State1();
		//one.timerTick();
		//It runs from GUI start()
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//The window is called the stage. The stuff inside the 
		//window is called the scene.
		
		//This view object receives the stage and creates the scene.
		
		//OBSERVER IS CREATED HERE
		Gui view = new Gui<Object>(primaryStage);
		view.createView(primaryStage);
		
		//add this as an observer to observe state changes
		//observers.add(view);
		
		//this.clockState = view.getState();
		outputClockState(view);
		
		//view.myState.incrementState();
		
		NewTimer t = new NewTimer();
		
		t.registerObserver(view);
		
		t.start();
	
		outputThreadID(t);
		
	    extracted(view);
	    
	    
	    //Dependency Inject occurs here................Nope. Not quite.
	    //Note: can never write AbstractClockState = new ....
	    
	    view.myState = new State1(view);

	    extractedIncrement(view);
	    extractedDecrement(view);
	}

	private void outputThreadID(NewTimer t) {
		long i = t.getId();
		System.out.println("thread's id in AbstractClockState " + i);
	}

	private void outputClockState(Gui view) 
	{
		this.clockState = view.getState();
		
		System.out.println("--------------------------");
		System.out.println("Clock State = " + getClockState());
		System.out.println("--------------------------");
	}
	
	private int getClockState() 
	{
		return this.clockState;
	}
/////////////////////////////////////////////////////////////////////////////
//					CHANGE MODE BUTTON
////////////////////////////////////////////////////////////////////////////
	private void extracted(Gui view) 
	{
		view.mode.setOnAction(new EventHandler<ActionEvent>() 
		{
	            @Override
	            public void handle(ActionEvent event) {
	
	            	view.myState.incrementState();
	            	 clockState++;
	            	
	            	//outputs error regarding inconsistent clock state
	            	view.myState.verifyState(getClockState());
	            	
	            	int state = view.myState.getState();
	
	        		notifyObservers(state);
        		
	            	outputChangeButtonWasPressed(state);
   
	                setButtonsVisible(view);
	                
	               
	                highlightTimeForIncrementing(view);
	                
	                int totalSeconds = view.newSecondValue;
	                int initialTime = view.getSeconds();	              
	               
	               System.out.println("-------------------------------");
	               System.out.println("  Update  Gui @ " + totalSeconds + " seconds");
	               System.out.println("-------------------------------");

	            	int remainderSeconds = totalSeconds % 60;
	            	
	            	int min = calculateCurrentMinuites(view, totalSeconds);
	            	   
	            	resetMinLabel(view, min);
	            	  
	            	resetSecondsLabel(view, totalSeconds, remainderSeconds);

	                
	            }

				private void resetSecondsLabel(Gui view, int totalSeconds, int remainderSeconds) 
				{			
						view.setSeconds(remainderSeconds);
						view.paintSecondsLabel.setText("" + remainderSeconds);		
				}

				private void resetMinLabel(Gui view, int min) 
				{	
						view.paintMinuitesLabel.setText("" + min);	
				}

				private int calculateCurrentMinuites(Gui view, int totalSeconds) 
				{
					//int minuitesCounted = (totalSeconds)/60;  
					if (totalSeconds > 59)
					{
						int countedMin = totalSeconds/60;
						int currentMin =  view.getMin() + countedMin;
						
						return currentMin;
					}
					else 
					{
						int currentMin = view.getMin();
						
						return currentMin;
					}
				
				}

				private void highlightTimeForIncrementing(Gui view) 
				{
					//redraw HBox1 with new time in seconds box and highted hours 
	                //redraw HBox 0 with new time
	                //addHbox2 instead of Hbox 1.
	                
	                view.paintHoursLabel.setStyle("-fx-border-color: white;");
				}

				private void setButtonsVisible(Gui view) 
				{
					view.cancel.setVisible(true);
	                view.decrement.setVisible(true);
	                view.increment.setVisible(true);
				}

				private void outputChangeButtonWasPressed(int state) 
				{
					System.out.println("");
	            	System.out.println("      ====================================");
	                System.out.println("          Change Mode button was pressed!  ");
	                System.out.println("             Clock State = " + state );
	                System.out.println("      ====================================");
	                System.out.println("");
				}
				
	        });
	}
/////////////////////////////////////////////////////////////////////////////
//				CANCEL BUTTON
////////////////////////////////////////////////////////////////////////////	
	private void extractedCancel(Gui view) {
		view.cancel.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            	outputCancelButtonPressed();
	              
	                setButtonsVisible(view);
	                
	                //redraw HBox1 with new time in seconds box and highted hours 
	                //redraw HBox 0 with new time
	                //addHbox2 instead of Hbox 1.
	                
	                view.paintHoursLabel.setStyle("-fx-border-color: white;");
	                
	              
	                
	                
	            }

				private void outputCancelButtonPressed() {
					System.out.println("      ====================================");
	                System.out.println("Cancel was pressed!");
	                System.out.println("      ====================================");
				}

				private void setButtonsVisible(Gui view) 
				{
					view.cancel.setVisible(true);
	                view.decrement.setVisible(true);
	                view.increment.setVisible(true);
				}
	        });
	}
/////////////////////////////////////////////////////////////////////////////
//					INCREMENT BUTTON
////////////////////////////////////////////////////////////////////////////
	private void extractedIncrement(Gui view) {
		view.increment.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            
	                
	                view.paintHoursLabel.setStyle("-fx-border-color: white;");
                
	                outputIncrementButtonPressed();
	              
	                resetHrsLabel(view);
	               
	            	extracted(view);
	            	
	            	 int totalSeconds = view.newSecondValue;
		                int initialTime = view.getSeconds();	              
		               
		               System.out.println("-------------------------------");
		               System.out.println("  Update  Gui @ " + totalSeconds + " seconds");
		               System.out.println("-------------------------------");

		            	int remainderSeconds = totalSeconds % 60;
	
//////////////////////////////////////////////////////////////////////////////////
//
//	Duplicate code here!!!!!!!!!!!
//calculateCurrentMinuites(view, totalSeconds)
//resetMinLabel(view, min);
//resetSecondsLabel(view, totalSeconds, remainderSeconds);
//////////////////////////////////////////////////////////////////////////////////
	            	
		            	
		            	int min = calculateCurrentMinuites(view, totalSeconds);
		            	   
		            	resetMinLabel(view, min);
		            	  
		            	resetSecondsLabel(view, totalSeconds, remainderSeconds);
	            	
	                
	                
	            }
	    		private void outputIncrementButtonPressed() 
	    		{
	    			System.out.println(" ");
					System.out.println("     =======================================");
	                System.out.println("	Increment hours button was pressed!");
	                System.out.println("     =======================================");
	                System.out.println(" ");
				}

				private void resetHrsLabel(Gui view) 
				{	
						int hours = view.getHrs()+1;
						view.setHrs(hours);
						view.paintHoursLabel.setText("" + hours);	
				}
				//duplicate method
				private int calculateCurrentMinuites(Gui view, int totalSeconds) 
				{
					//int minuitesCounted = (totalSeconds)/60;  
					if (totalSeconds > 59)
					{
						int countedMin = totalSeconds/60;
						int currentMin =  view.getMin() + countedMin;
						
						return currentMin;
					}
					else 
					{
						int currentMin = view.getMin();
						
						return currentMin;
					}
				
				}
				//duplicate method
				private void resetMinLabel(Gui view, int min) 
				{	
						view.paintMinuitesLabel.setText("" + min);	
				}
				//duplicate method
				private void resetSecondsLabel(Gui view, int totalSeconds, int remainderSeconds) 
				{			
						view.setSeconds(remainderSeconds);
						view.paintSecondsLabel.setText("" + remainderSeconds);		
				}
	        });
	}
	private void extractedDecrement(Gui view) {
		view.decrement.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                
	                view.paintHoursLabel.setStyle("-fx-border-color: white;");
	                
	               
	                
	                outputIncrementButtonPressed();
	              
	                resetHrsLabel(view);
	                
	                
	            }
	    		private void outputIncrementButtonPressed() 
	    		{
	    			System.out.println(" ");
					System.out.println("     =======================================");
	                System.out.println("	Decrement hours button was pressed!");
	                System.out.println("     =======================================");
	                System.out.println(" ");
				}

				private void resetHrsLabel(Gui view) 
				{	
						int hours = view.getHrs() - 1;
						view.setHrs(hours);
						view.paintHoursLabel.setText("" + hours);	
				}
 
	            
	        });
	}
////////////////////////////////////////////////////////
//		Subject methods
///////////////////////////////////////////////////////
	@Override
	public void registerObserver(Observer o) 
	{
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) 
	{

		int j = observers.indexOf(o);

		if (i >= 0)
			observers.remove(j);

		System.out.println("observer # " + j + "was deleted.");
		
	}

	@Override
	public void notifyObservers(int i) 
	{
		for (Observer observer: observers)
		{
			try {
				observer.update(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

//	@Override
//	public void notifyObservers(int i) 
//	{
//		
//		for (Observer observer: observers)
//		{
//
//		try{
//			//observer.update(totalTime, this);
//			observer.update(getClockState(), this);
//			System.out.println("update called by RunClock " + this.getClass());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//passes in current state
//		}
//		
//	}

//	@Override
//	public void notifyObservers(int i) 
//	{
//	
//		 paintSecondsLbl.setText("" + i%60);
//	}
	
	
	

}
