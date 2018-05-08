package pl.edu.agh.sr.Bank;


import Bank.CurrencyCode;
import Bank.Person;
import Bank.PremiumBankAccount;
import Bank.WrongGUIDException;
import com.zeroc.Ice.Current;

public class PremiumBankAccountImpl extends StandardBankAccountImpl implements PremiumBankAccount {

    public PremiumBankAccountImpl(Person person) {
        super(person);
    }

    @Override
    public String getLoanInfo(String GUID, long amount, int months, CurrencyCode currencyCode, Current current) throws WrongGUIDException {
        return null;
    }
}
