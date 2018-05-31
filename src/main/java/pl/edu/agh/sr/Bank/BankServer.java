package pl.edu.agh.sr.Bank;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import sr.grpc.gen.CurrencyCode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class BankServer {

    public void start(String[] args, int port) {
        try(Communicator communicator = Util.initialize(args)) {

            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h localhost -p " + port + ":udp -h localhost -p " + port);

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
    public static void main(String[] args) throws IOException {
        Set<CurrencyCode> supportedCurrencies = new HashSet<>();
        supportedCurrencies.add(CurrencyCode.USD);
        supportedCurrencies.add(CurrencyCode.GBP);
        Thread exchangeRatesMonitorThread = new Thread(new ExchangeOfficeClient("localhost", 55555, supportedCurrencies));
        exchangeRatesMonitorThread.start();
        BankServer app = new BankServer();
        app.start(args, 10006);
    }

}
