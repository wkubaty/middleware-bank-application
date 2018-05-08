package pl.edu.agh.sr.Bank;

import Bank.BankAccount;
import Bank.BankAccountFactory;
import Bank.BankAccountPrx;
import Bank.Person;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Identity;

import java.util.UUID;

public class BankAccountFactoryImpl implements BankAccountFactory {
    private BankAccount bankAccount;
    private String accountType;

    @Override
    public BankAccountPrx createAccount(Person person, Current current) {
        person.GUID = UUID.randomUUID().toString();

        calculateProperBankAccountType(person);

        Identity identity = new Identity(Long.toString(person.PESEL), accountType);

        return BankAccountPrx.uncheckedCast(current.adapter.add(bankAccount, identity));
    }

    private void calculateProperBankAccountType(Person person) {
        if(person.monthlyIncome < 10000) {
            bankAccount = new StandardBankAccountImpl(person);
            accountType = "standard";
        }
        else {
            bankAccount = new PremiumBankAccountImpl(person);
            accountType = "premium";
        }
    }
}
