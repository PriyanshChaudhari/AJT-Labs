import javax.swing.*;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.Scanner;

public class javaMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input;

        while (true) {
            System.out.println("Select: \n1. Add a book\n2. View all books\n3. Search for a book\n4. Delete a book\n5. Exit");
            input = sc.nextInt();
            switch (input) {
                case 1:
                    //call addBook()
                    try {
                        guiOne guiObj1 = new guiOne();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    //call viewAllBooks()
                    try {
                        guiTwo guiObj2 = new guiTwo();
                    }catch (IOException | ClassNotFoundException e) {
                       e.printStackTrace();
                    }
                    break;
                case 3:
                    //call searchBook()
                    try {
                        guiThree guiObj3 = new guiThree();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    //call deleteBook()
                    break;
                case 5:
                    //exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}