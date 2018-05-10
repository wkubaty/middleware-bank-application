#ifndef BANK_ICE
#define BANK_ICE

module Bank{
    exception AccountException {
        string reason;
    }
    exception WrongGUIDException {}
    exception WrongArgumentException extends AccountException {}

    enum CurrencyCode {
        PLN,
        USD,
        EUR,
        GBP,
        CHF,
        JPY,
        RUB,
        CNY
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
        LoanInfo getLoanInfo(string GUID, double amount, int months, CurrencyCode currencyCode) throws WrongGUIDException;
    };



    interface BankAccountFactory {
        BankAccount* createAccount(Person person) throws WrongArgumentException;
    }
};

#endif
