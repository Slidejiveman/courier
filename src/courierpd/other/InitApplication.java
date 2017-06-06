package courierpd.other;

import courierdm.CourierEntityManager;
import courierui.CourierMainFrame;

/**
 * The initialization class will be used to handle things
 * that should be taken care of before the main application 
 * opens up. This could include database activities.
 * 
 * The Main method is where we should probably read in stuff from the
 * database so it will be available while the application. This way it
 * isn't read in each time we access a particular panel.
 * 
 * @author rdnot
 *
 */
public class InitApplication {

	public static void main(String[] args) {
		CourierEntityManager.initEM();
		// In here, we can read something out of the database and then pass
		// it on to the main frame here. There's an example in the League code.
		CourierMainFrame.startGUI(args);

	}

}
