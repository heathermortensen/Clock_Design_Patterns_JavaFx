import javafx.stage.Stage;

public class State2Display implements Observer,DisplayStuff{

	public void display() throws InterruptedException {
		
		Stage primaryStage = null;
		Gui view = new Gui(primaryStage);

		view.createView(primaryStage);
		
	}

	public void update(int seconds)  {

		//update data here, see lynda tutorial min 3:52
		
		try {
			
			display();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}



}
