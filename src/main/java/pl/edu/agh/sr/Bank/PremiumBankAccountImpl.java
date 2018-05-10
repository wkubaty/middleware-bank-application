package pl.edu.agh.sr.Bank;


import Bank.*;
import com.zeroc.Ice.Current;

import static pl.edu.agh.sr.Bank.ExchangeOfficeClient.DEFAULT_CURRENCY;

public class PremiumBankAccountImpl extends StandardBankAccountImpl implements PremiumBankAccount {
    private final static double LOAN_MONTHLY_COST = 1.01;
    public PremiumBankAccountImpl(Person person) {
        super(person);
    }

    @Override
    public LoanInfo getLoanInfo(String GUID, double amount, int months, CurrencyCode currencyCode, Current current) throws WrongGUIDException {
        if(super.person.GUID.equals(GUID)) {
            double defaultCurrencyCost = amount *  LOAN_MONTHLY_COST;
            double chosenCurrencyCost = getLoanCostInChosenCurrency(amount, currencyCode) *  LOAN_MONTHLY_COST;
            return new LoanInfo(defaultCurrencyCost,chosenCurrencyCost);
        }
        else {
            throw new WrongGUIDException();
        }
    }
    private double getLoanCostInChosenCurrency(double amount, CurrencyCode currencyCode){
        if(currencyCode.equals(DEFAULT_CURRENCY.toString())){
            return amount;
        }
        return amount / ExchangeOfficeClient.getExchangeRates().get(sr.grpc.gen.CurrencyCode.valueOf(currencyCode.value())).doubleValue();
    }
}
