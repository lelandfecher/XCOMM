import java.awt.Frame;


public class MainDriver {
	public static void main(String[] args) {
		//First use Singleton to load classes from serialized file
		InstructorDataStore table = InstructorDataStore.getInstance();
		
		//Then have Dialog Box prompting to sign in
		Frame f = new Frame();
		LoginDlg ld = new LoginDlg(f, "Please Login/Sign-Up");
		
		while (ld.signedIn() != true) {
			ld.setVisible(true);
		}
		int whichInstructor = ld.getWhichInstructor();
		

		
		//Now create main window of classes with list of classes just loaded
		ClassMainFrame mainWindow = new ClassMainFrame("List of all Classes", table, whichInstructor);
		
		//Now set the visibility of the window to true
		mainWindow.setVisible(true);
		
		//And set resizable to false
		mainWindow.setResizable(false);
	}
}
