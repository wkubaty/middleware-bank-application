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

    class Person;

    interface BankAccount {
        double getBalanceInfo(string GUID) throws WrongGUIDException;
        Person getOwnerInfo();
    };

    interface StandardBankAccount extends BankAccount {

    };

    interface PremiumBankAccount extends BankAccount{
        string getLoanInfo(string GUID, long amount, int months, CurrencyCode currencyCode) throws WrongGUIDException;
    };

    class Person{
        long PESEL;
        string firstName;
        string lastName;
        long monthlyIncome;
        string GUID;
    };

    interface BankAccountFactory {
        BankAccount* createAccount(Person person);
    }

};

#endif
