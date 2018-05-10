#ifndef BANK_ICE
#define BANK_ICE

module Bank{

    exception WrongGUIDException {}
    exception UnsupportedCurrencyException {}

    enum CurrencyCode {
        PLN,
        USD,
        EUR,
        GBP,
        CHF
    };

    struct Person{
            long PESEL;
            string firstName;
            string lastName;
            double monthlyIncome;
            string GUID;
        };

    struct LoanInfo {
        double defaultCurrencyCost;
        double chosenCurrencyCost;
    };

    interface BankAccount {
        double getBalanceInfo(string GUID) throws WrongGUIDException;
        Person getOwnerInfo();
    };

    interface StandardBankAccount extends BankAccount {

    };

    interface PremiumBankAccount extends BankAccount{
        LoanInfo getLoanInfo(string GUID, double amount, int months, CurrencyCode currencyCode) throws WrongGUIDException, UnsupportedCurrencyException;
    };

    interface BankAccountFactory {
        BankAccount* createAccount(Person person);
    }
};

#endif
