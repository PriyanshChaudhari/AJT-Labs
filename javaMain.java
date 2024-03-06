import GUI.mainFrame;
import RMI.RMIFileClient;
import RMI.RMIFileServer;

public class javaMain {
    public static void main(String[] args) {
        try {
            mainFrame mFObj = new mainFrame();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}