import GUI.mainFrame;
import IO.*;

import java.io.IOException;

public class javaMain {
    public static void main(String[] args) {
//        jdbcCreateTableQuery  ctq = new jdbcCreateTableQuery();
//        jdbcPriceChangeQuery pcq = new jdbcPriceChangeQuery();

        try {
            mainFrame mFObj = new mainFrame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}