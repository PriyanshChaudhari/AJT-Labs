import GUI.mainFrame;

import java.io.IOException;

public class javaMain {
    public static void main(String[] args) {
        try {
            mainFrame mFObj = new mainFrame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}