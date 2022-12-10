package main.java.backend;

import main.java.backend.IMainController;

public class MainControllerFactory {

	public IMainController createMainController() {
		//TODO: FIXME! FIXME! FIXME!
		/**
		  		Hi Dr. E,
		  		I have a few questions about the semester project.
		  	    First, regarding the createMainController() method, inside the MainControlerFactory class;
		  	    What I had imagined is that I will have a controller class that accepts the requests from
		  	    the front end. I am not sure why I need different controllers to handle incoming requests
		  	    that carry the same parametric information in them. My understanding is that I should have
		  	    one controller class and then delegate all the information down to the service level.
		  	    From the exercise specification though, and from the code given, I see that we need a separate
		  	    controller class for each use case. Though I am not sure what we should classify as a use case.
		  	    What I would do, is that I would use the file name to extract the file contents into a list of
		  	    Strings, each entry being a file line, and then I would tokenize each line and pass it into a Task
		  	    object. The presence of the SimpleTableModel though, makes me questioning where Task objects come into play.
		  	    Do we initially feed all the information into a SimpleTableModel and then create Tasks from there?
		  	    What I have done, is to create a MainController class that implements IMainController interface, and I
		  	    return that (a MainController object) from the createMainController() method inside MainControllerFactory.java.
		  	    I understand that you would like to see more controller classes implementing the IMainController interface,
		  	    but at the moment I am not sure why more than one controllers are needed.
		  	    I would really appreciate your input

		  	    Thank you

		  	    Kind Regards,
		  	    fM
		 */
		return new MainController();
	}

}
