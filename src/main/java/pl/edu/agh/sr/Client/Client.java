package pl.edu.agh.sr.Client;


import Bank.*;
import com.zeroc.Ice.AlreadyRegisteredException;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    private static final String DEFAULT_CURRENCY = "PLN";
    private BankAccountPrx bankAccountPrx;
    BufferedReader in;

    public void start(String[] args, int port){
        try(Communicator communicator = Util.initialize(args)){

            ObjectPrx base = communicator.stringToProxy("accounts/accounts:tcp -h localhost -p " + port + ":udp -h localhost -p " + port);

            BankAccountFactoryPrx obj = BankAccountFactoryPrx.checkedCast(base);
            if (obj == null){
                throw new Error("Invalid proxy");
            }

            String line = "";
            in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type: 'new', 'balance', 'loan' or 'switch'");
            do {
                try{
                    System.out.print("==> ");
                    System.out.flush();
                    line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    if(line.equals("new")) {
                        runNewCommand(obj);
                    }
                    else if(line.equals("balance")){
                        runBalanceCommand();
                    }
                    else if(line.equals("loan")){
                        runLoanCommand();

                    }
                    else if(line.equals("switch")){
                        runSwitchCommand(communicator, port);
                    }
                    else {
                        System.out.println("Type: 'new', 'balance', 'loan' or 'switch");
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Check typing...");
                }
            }
            while (!line.equals("exit"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void runNewCommand(BankAccountFactoryPrx obj) throws IOException {
        System.out.print("Enter PESEL: ");
        long PESEL = Long.parseLong(in.readLine());
        System.out.print("Enter first name: ");
        String firstName = in.readLine();
        System.out.print("Enter last name: ");
        String lastName = in.readLine();
        System.out.print("Enter monthly income: ");
        long monthlyIncome = Long.parseLong(in.readLine());
        try {
            bankAccountPrx = obj.createAccount(new Person(PESEL, firstName, lastName, monthlyIncome, ""));

            System.out.println("Your type account: " + bankAccountPrx.ice_getIdentity().category);
            System.out.println("Your GUID: " + bankAccountPrx.getOwnerInfo().GUID);
        }
        catch (AlreadyRegisteredException e){
            System.out.println("There is an account with such identity. Try again...");
        }
    }
    private void runBalanceCommand() throws IOException {
        System.out.print("Enter GUID: ");
        String GUID = in.readLine().trim();
        try{
            System.out.printf("Account balance: %.2f %s\n", bankAccountPrx.getBalanceInfo(GUID), DEFAULT_CURRENCY);
        }
        catch (NullPointerException e){
            System.out.println("Choose account first!");
        }
        catch (WrongGUIDException e){
            System.out.println("Wrong GUID!");
        }
    }
    private void runLoanCommand() throws IOException {
        try{
            if("standard".equals(bankAccountPrx.ice_getIdentity().category)){
                System.out.println("Nice try, but you've only a standard account. Can't take a loan!");
                return;
            }
            System.out.print("Enter GUID: ");
            String GUID = in.readLine().trim();
            System.out.print("Enter amount: ");
            long amount = Long.parseLong(in.readLine());
            System.out.print("Enter number of months: ");
            int months = Integer.parseInt(in.readLine());
            System.out.print("Enter currency code: ");
            CurrencyCode currencyCode;
            try{
                currencyCode = CurrencyCode.valueOf(in.readLine());
            }
            catch (IllegalArgumentException e){
                System.out.println("Wrong currency code");
                return;
            }

            PremiumBankAccountPrx premiumBankAccount = PremiumBankAccountPrx.checkedCast(bankAccountPrx);

            try{
                LoanInfo loanInfo = premiumBankAccount.getLoanInfo(GUID, amount, months, currencyCode);

                System.out.println("Total cost in " + DEFAULT_CURRENCY + ": " + loanInfo.defaultCurrencyCost +
                        " ( " + loanInfo.defaultCurrencyCost / (double) months + " per month)");
                System.out.println("Total cost in " + currencyCode + ": " + loanInfo.chosenCurrencyCost +
                        " ( " + loanInfo.chosenCurrencyCost / (double) months + " per month)");
            }
            catch (WrongGUIDException e){
                System.out.println("Wrong GUID! Try again...");
            }
            catch (UnsupportedCurrencyException e){
                System.out.println("The bank is not supporting this currency");
            }
        }
        catch (NullPointerException e){
            System.out.println("Choose account first!");
        }
    }
    private void runSwitchCommand(Communicator communicator, int port) throws IOException {
        try{
            System.out.print("Enter PESEL: ");
            long PESEL = Long.parseLong(in.readLine());
            System.out.print("Enter account type ('standard' or 'premium'): ");
            String accountType = in.readLine();
            if("standard".equals(accountType) || "premium".equals(accountType)){
                try{
                    ObjectPrx objectPrx = communicator.stringToProxy(accountType + "/" + PESEL + ":tcp -h localhost -p " + port + ":udp -h localhost -p " + port);
                    bankAccountPrx = BankAccountPrx.checkedCast(objectPrx); // switched account
                }
                catch (com.zeroc.Ice.ObjectNotExistException e){
                    System.out.println("No such account!");
                }

            } else {
                System.out.println("Only allowed 'standard' or 'premium'!");
            }
        }
        catch (NullPointerException e){
            System.out.println("Choose account first!");
        }
    }

    public static void main(String[] args) {
        new Client().start(args, 10005);
    }
}
