package RMI.SearchBook;

import java.rmi.Naming;

public class RMISearchServer {
    public RMISearchServer(){
        SearchBookRemote stub =  null;
        try {
            stub = new SearchBookRemote();
            System.out.println("Search Server ready.");
            Naming.rebind("rmi://localhost:5000/searchBook", stub);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        RMISearchServer searchServer = new RMISearchServer();
    }
}
