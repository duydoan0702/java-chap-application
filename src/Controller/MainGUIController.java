package Controller;

import View.Login;
import View.MainGUI;

public class MainGUIController {
	
	public static void handleExit(MainGUI mainGUI) {
		mainGUI.dispose();
		new Login();
	}

}
