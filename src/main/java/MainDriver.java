public class MainDriver {
    public static void main(String[] args) {

    	//CAT SPLASH SCREEN!!
    	//Comment out for now for testing/time purposes
    	SplashScreen s = new SplashScreen();

        //Now create main window of classes with list of classes just loaded
        ClassMainFrame mainWindow = new ClassMainFrame("CU-IN-CLASS");//CU�N�CLASS

        //Now set the visibility of the window to true
        mainWindow.setVisible(true);

        //And set resizable to false
        mainWindow.setResizable(false);
    }
}
