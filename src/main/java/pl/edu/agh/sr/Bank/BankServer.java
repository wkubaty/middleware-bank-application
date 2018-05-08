package pl.edu.agh.sr.Bank;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class BankServer {

    public void start(String[] args) {
        try(Communicator communicator = Util.initialize(args)) {

            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h localhost -p 10006:udp -h localhost -p 10006");

            BankAccountFactoryImpl accountsServant = new BankAccountFactoryImpl();

            adapter.add(accountsServant, new Identity("accounts", "accounts"));

            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BankServer app = new BankServer();
        app.start(args);
    }
}
