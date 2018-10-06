//
//  File: Gui.java
//  Author: Heather Mortensen
//  Class: SIES 770
//  Date: 10/10/17
//  Project: Clock Project
//  
//  Dx: 
//  
// 
// timer w/ thread
//https://www.youtube.com/watch?v=JYYH11OhhGs


import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

import javax.security.auth.Subject;
//import java.swing.util.Timer;
import javax.swing.Timer;

import javax.swing.text.View;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//https://www.developer.com/java/data/understanding-java-observable-and-javafx-observable.html

@SuppressWarnings("restriction")
public class Gui<T> extends BorderPane implements Observer
{
	int initialState = 0;
	int newSecondValue = 0;
	
	//Set the time on the three Labels for hours, min, seconds.
	Calendar myCalendar = new GregorianCalendar();
	
	private int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
	private int minuite = myCalendar.get(Calendar.MINUTE);
	private int second = myCalendar.get(Calendar.SECOND);
	
    AbstractClockState myState;
	//AbstractClockState myState(getState());
	
	//Labels
	
			Label paintColonLabel_1 = new Label(" : ");
			Label paintColonLabel_2 = new Label(" : ");
			
			Label paintHoursLabel = new Label("" + hour);
			Label paintMinuitesLabel = new Label("" + minuite);
			Label paintSecondsLabel = new Label("" + second);
	
		//Buttons
			
			//NO FUNCTIONALITY
			Button increment = new Button("+");
			Button decrement = new Button("-");
			Button mode = new Button("Change Mode");
			
			//NO FUNCTIONALITY
			Button cancel = new Button("Cancel");
			
			
	


	public Gui(Stage primaryStage) throws InterruptedException 
	{
		createView(primaryStage);

	}


	@SuppressWarnings("restriction")
	public void createView(Stage primaryStage) throws InterruptedException
	{
		//color codes at: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#typecolor
	
		
		// Create my root pane

				BorderPane myBorderPane = new BorderPane();
			
				
		//make all the HBoxes 
				
				//This HBox makes the clock display
				
				HBox HBox0 = new HBox();
				HBox0 = addHBox0(paintColonLabel_1, paintColonLabel_2, paintHoursLabel, paintMinuitesLabel, paintSecondsLabel);
				

		
		// format my root pane
				
				//This HBox makes the button display for state 1.
				
				HBox HBox1 = new HBox();
				HBox1 = addHBox1(mode, cancel, increment, decrement);
				
				myBorderPane.setCenter(addAllHBoxes(HBox0, HBox1, initialState));
		
				//This HBox makes the button display for state 2.
		
				
				
		// create scene and place it in the stage

				Scene scene = new Scene(myBorderPane, 650, 300);
				
				primaryStage.setScene(scene);
				primaryStage.setTitle(" Simple Clock ");
				primaryStage.show();
				
				//scene.addEventHandler(DragEvent.DRAG_EXITED, handler);
		
	}

	private VBox addAllHBoxes(HBox HBox0,HBox HBox1, int state) throws InterruptedException 
	{
		VBox vbox = new VBox(25);
		
		//make background NAVY BLUE
		vbox.setStyle("-fx-background-color:#272744");
		//#2f4f4f
		
		//state = 0
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(HBox0, HBox1);  
		
		
		
		
//		System.out.println("==========================");
//		System.out.println("current State of Gui = " + myState.state);
//		System.out.println("==========================");
	
		

		
		return vbox;
	}
	
//	Dependency Injection Setter Method
//This Di needs to be refractored.
//	
//	AbstractClockState create_DI_Class(AbstractClockState a)
//	{
//		a.createState();
//		
//	
//		
//
//
//		
//	}


	//This is state 1. Change the outline for other states and add the hbox2,3,4.
	private HBox addHBox0(Label colonLbl1,Label colonLbl2, Label paintHoursLbl,Label paintMinuitesLbl,Label paintSecondsLbl) //throws InterruptedException 
	{
		
		HBox hbox1 = new HBox(25);
	
		hbox1.setVisible(true);
		hbox1.setPadding(new Insets(15, 15, 15, 15));
		
		
		//Add Colon
		colonLbl1.setVisible(true);
		colonLbl1.setStyle("-fx-text-fill: white");
		colonLbl1.setFont(new Font("Arial", 90));
		
		//Add 2nd Colon
		colonLbl2.setVisible(true);
		colonLbl2.setStyle("-fx-text-fill: white");
		colonLbl2.setFont(new Font("Arial", 90));
	
		//Add the Label that displays hours
		paintHoursLbl.setVisible(true);
		paintHoursLbl.setStyle("-fx-text-fill: green");
		paintHoursLbl.setFont(new Font("Arial", 90));
		
		//////////////////////////////////////////////////////////////////////////
		//Make a square border around the Label
		//////////////////////////////////////////////////////////////////////////
		//paintHoursLbl.setStyle("-fx-border-color: white;");
		
		
		//Add the Label that displays minuites
		paintMinuitesLbl.setVisible(true);
		paintMinuitesLbl.setStyle("-fx-text-fill: green");
		paintMinuitesLbl.setFont(new Font("Arial", 90));

		//Add the Label that displays Seconds
		paintSecondsLbl.setVisible(true);
		paintSecondsLbl.setStyle("-fx-text-fill: green");
		paintSecondsLbl.setFont(new Font("Arial", 90));
		
		
		hbox1.setAlignment(Pos.CENTER);	
		
		//getChildren is inherited from scene - this line determines
		//    the order that components are placed on the screen. 
		
		hbox1.getChildren().addAll(paintHoursLbl, colonLbl1, paintMinuitesLbl, colonLbl2, paintSecondsLbl);
		
		return hbox1;
	}
	
	///////////////////////////////
	//This method is for State 1.
	///////////////////////////////
	private HBox addHBox1(Button mode, Button cancel, Button increment, Button decrement) 
	{
		
		HBox hbox1 = new HBox(25);
	
		hbox1.setVisible(true);
		hbox1.setPadding(new Insets(15, 15, 15, 15));
		
		mode.setVisible(true);
		
		cancel.setVisible(false);
		increment.setVisible(false);
		decrement.setVisible(false);
		//getChildren is inherited from scene
		hbox1.getChildren().addAll(mode, cancel, increment, decrement);
		
		hbox1.setAlignment(Pos.CENTER);
		
		return hbox1;
	}
	
	////////////////////////////////////
	//This method is for State 2.
	////////////////////////////////////
	private HBox addHBox2(Button increment, Button decrement, Button mode, Button cancel, int state) 
	{
		
		HBox hbox2 = new HBox(25);
	
		hbox2.setVisible(true);
		hbox2.setPadding(new Insets(15, 15, 15, 15));
		
		increment.setVisible(true);
		decrement.setVisible(true);
		mode.setVisible(true);
		cancel.setVisible(true);
		
		//////////////////////////////////////////////////////////////////////////
		//Make a square border around the Label
		//////////////////////////////////////////////////////////////////////////
		
		//Check documentation for how state is defined.
		if (state == 1)
		{
			paintHoursLabel.setStyle("-fx-border-color: white");
			paintHoursLabel.setStyle("-fx-text-fill: white");
		}
		else if (state == 2)
		{
			paintMinuitesLabel.setStyle("-fx-border-color: white");
			paintMinuitesLabel.setStyle("-fx-text-fill: white");
			
			//update the gui here in change mode button in RunClock.java
		}
		else if (state == 3)
		{
			paintSecondsLabel.setStyle("-fx-border-color: white");
			paintSecondsLabel.setStyle("-fx-text-fill: white");
			
			//update the gui here in change mode button in RunClock.java
		}

		//getChildren is inherited from scene
		hbox2.getChildren().addAll(increment, decrement, mode, cancel);
		
		hbox2.setAlignment(Pos.CENTER);
		
		return hbox2;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	//This method is for State 3.
	//////////////////////////////////////////////////////////////////////////////
//	private HBox addHBox3(Button increment, Button decrement, Button mode, Button cancel) 
//	{
//		
//		HBox hbox3 = new HBox(25);
//	
//		hbox3.setVisible(true);
//		hbox3.setPadding(new Insets(15, 15, 15, 15));
//		
//		increment.setVisible(true);
//		decrement.setVisible(true);
//		mode.setVisible(true);
//		cancel.setVisible(true);
//		
//		//////////////////////////////////////////////////////////////////////////
//		//Make a square border around the Label
//		//////////////////////////////////////////////////////////////////////////
//		paintMinuitesLbl.setStyle("-fx-border-color: white");
//		paintMinuitesLbl.setStyle("-fx-text-fill: white");
//
//		//getChildren is inherited from scene
//		hbox3.getChildren().addAll(increment, decrement, mode, cancel);
//		
//		hbox3.setAlignment(Pos.CENTER);
//		
//		return hbox3;
//	}
	
	///////////////////////////////////////////////////////////////////////////////
	//This method is for State 4.
	//////////////////////////////////////////////////////////////////////////////
//	private HBox addHBox4(Button increment, Button decrement, Button mode, Button cancel) 
//	{
//		
//		HBox hbox4 = new HBox(25);
//	
//		hbox4.setVisible(true);
//		hbox4.setPadding(new Insets(15, 15, 15, 15));
//		
//		increment.setVisible(true);
//		decrement.setVisible(true);
//		mode.setVisible(true);
//		cancel.setVisible(true);
//		
//		//////////////////////////////////////////////////////////////////////////
//		//Make a square border around the Label
//		//////////////////////////////////////////////////////////////////////////
//		paintSecondsLbl.setStyle("-fx-border-color: white");
//		paintSecondsLbl.setStyle("-fx-text-fill: white");
//		//update();
//		//getChildren is inherited from scene
//		hbox4.getChildren().addAll(increment, decrement, mode, cancel);
//		
//		hbox4.setAlignment(Pos.CENTER);
//		
//		return hbox4;
//	}
	
	public void incrementSeconds()
	{

		this.second = this.second + 1;
		
	}

	public int getSeconds()
	{
		int i = this.second;
		return i;
	}
	
	public void setSeconds(int i)
	{
		this.second = i;
	}
	
	public int getMin()
	{
		int i = this.minuite;
		return i;
	}
	public int getHrs()
	{
		int i = this.hour;
		return i;
	}
	public void setHrs(int h)
	{
		if (h==24)
		{
			this.hour = 0;
		}
		else if (h>24)
		{
			this.hour = h/24;
		}
		else
		{
			this.hour = h;
		}
	}
	
	public int getState()
	{	
		if (myState == null)
		{
			return 0;
		}
		else
		return this.myState.state;
	}
	
	public void setState()
	{
		this.myState.state = this.myState.state ++;
		
	}
	


	public void update(int iNew) 
	{
		//if (o instanceof NewTimer)
		//{
			System.out.println("   notified Gui that time = " + iNew);
		//}
		//else if (o instanceof Gui)
		//{
		//	System.out.println("notified Gui that Clock State = " + iNew);
		//}
		
		   this.newSecondValue = iNew;
		  
		   //paintSecondsLbl.setText("" );
		 
		
	}






} //close Gui
	
