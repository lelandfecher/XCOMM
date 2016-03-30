import java.awt.*;


public class MainDriver {
    public static void main(String[] args) {

    	//CAT SPLASH SCREEN!!
    	//Comment out for now for testing/time purposes
    	//SplashScreen s = new SplashScreen();
    	
        //First use Singleton to load classes from serialized file
        InstructorDataStore table = InstructorDataStore.getInstance();
        
        //No more log in for now...
        
        //Then have Dialog Box prompting to sign in
//        Frame f = new Frame();
//        LoginDlg ld = new LoginDlg(f, "Please Login/Sign-Up");
//
//        while (ld.signedIn() != true) {
//            ld.setVisible(true);
//        }
//        int whichInstructor = ld.getWhichInstructor();


        //Now create main window of classes with list of classes just loaded
        ClassMainFrame mainWindow = new ClassMainFrame("CU·N·CLASS", table, 0);

        //Now set the visibility of the window to true
        mainWindow.setVisible(true);

        //And set resizable to false
        mainWindow.setResizable(false);
    }
}
