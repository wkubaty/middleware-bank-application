package pl.edu.agh.sr.Client;

import Bank.*;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Guard;


public class Client {
    private BankAccountPrx bankAccountPrx;

    public void start(String[] args){
        try(Communicator communicator = Util.initialize(args)){

            ObjectPrx base = communicator.stringToProxy("accounts/accounts:tcp -h localhost -p 10006:udp -h localhost -p 10006");

            BankAccountFactoryPrx obj = BankAccountFactoryPrx.checkedCast(base);
            if (obj == null){
                throw new Error("Invalid proxy");
            }

            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type: 'new', 'balance' or 'loan'");
            do {
                System.out.print("==> ");
                System.out.flush();
                line = in.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("new")) {
                    System.out.print("Enter PESEL:");
                    long PESEL = Long.parseLong(in.readLine());
                    System.out.print("Enter first name:");
                    String firstName = in.readLine();
                    System.out.print("Enter last name:");
                    String lastName = in.readLine();
                    System.out.print("Enter monthly income:");
                    long monthlyIncome = Long.parseLong(in.readLine());
                    bankAccountPrx = obj.createAccount(new Person(PESEL, firstName, lastName, monthlyIncome, ""));

                    System.out.println("YOUR GUID = " + bankAccountPrx.getOwnerInfo().GUID);
                }
                else if(line.equals("balance")){
                    System.out.print("Enter GUID:");
                    String GUID = in.readLine().trim();
                    try{
                        System.out.println(bankAccountPrx.getBalanceInfo(GUID));
                    }
                    catch (WrongGUIDException e){
                        System.out.println("Wrong GUID!");
                    }
                }
                else if(line.equals("loan")){
                    System.out.print("Enter GUID:");
                    String GUID = in.readLine();
                    System.out.print("Enter amount:");
                    long amount = Long.parseLong(in.readLine());
                    System.out.print("Enter number of months:");
                    int months = Integer.parseInt(in.readLine());
                    System.out.print("Enter currency code:");
                    String currencyCode = in.readLine();

                    PremiumBankAccountPrx premiumBankAccount = PremiumBankAccountPrx.checkedCast(bankAccountPrx);
                    try{
                        System.out.println(premiumBankAccount.getLoanInfo(GUID, amount, months, CurrencyCode.valueOf(currencyCode)));//TODO: enum exception
                    }
                    catch (WrongGUIDException e){
                        System.out.println("Wrong GUID!");
                    }
                }
            }
            while (!line.equals("exit"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start(args);
    }
}
