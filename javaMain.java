//import GUI.FileIO.mainFrame;
//import GUI.JDBC.mainFrame;

public class javaMain {
    public static void main(String[] args) {
        try {
            //mainFrame for FIleIO
            //GUI.FileIO.mainFrame mFObjFileIO = new GUI.FileIO.mainFrame();

            //mainFrame for JDBC
            GUI.JDBC.mainFrame mFObjJdbc = new GUI.JDBC.mainFrame();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}