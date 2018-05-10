package pl.edu.agh.sr.Bank;

import Bank.BankAccount;
import Bank.Person;
import Bank.StandardBankAccount;
import Bank.WrongGUIDException;
import com.zeroc.Ice.Current;

public class StandardBankAccountImpl implements BankAccount {
    private double balance;
    protected Person person;

    public StandardBankAccountImpl(Person person) {
        this.person = person;
        balance = Math.random();
    }

    @Override
    public double getBalanceInfo(String GUID, Current current) throws WrongGUIDException {
        if(person.GUID.equals(GUID)) {
            return balance;
        }
        else {
            throw new WrongGUIDException();
        }

    }

    @Override
    public Person getOwnerInfo(Current current) {
        return person;
    }
}
