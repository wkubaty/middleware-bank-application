package pl.edu.agh.sr.ExchangeOffice;

import sr.grpc.gen.CurrencyCode;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ConcurrentHashMap;

public class ExchangeRatesMonitor implements Runnable{
    private static final double PROBABILITY_OF_CURRENCY_EXCHANGE_RATE_CHANGE = 0.5;
    private static final CurrencyCode DEFAULT_CURRENCY = CurrencyCode.USD;
    private static ConcurrentHashMap<CurrencyCode, CurrencyValue> exchangeRates;

    public ExchangeRatesMonitor() {
        exchangeRates = new ConcurrentHashMap<>();
        initializeExchangeRates();
    }

    private static void initializeExchangeRates() {
        exchangeRates.put(DEFAULT_CURRENCY, new CurrencyValue(new BigDecimal("1.0000"), true));
        exchangeRates.put(CurrencyCode.PLN, new CurrencyValue(new BigDecimal("0.28"), true));
        exchangeRates.put(CurrencyCode.EUR, new CurrencyValue(new BigDecimal("1.1977"), true));
        exchangeRates.put(CurrencyCode.GBP, new CurrencyValue(new BigDecimal("1.3582"), true));
        exchangeRates.put(CurrencyCode.CHF, new CurrencyValue(new BigDecimal("0.9967"), true));
    }

    @Override
    public void run() {
        while(true){
            updateExchangeRates();
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void updateExchangeRates() {
        exchangeRates.forEach((k, v) -> exchangeRates.replace(k, simulateExchangeRateChanges(k, v)));
    }

    private static CurrencyValue simulateExchangeRateChanges(CurrencyCode currencyCode, CurrencyValue presentCurrencyValue){
        if(shouldCurrencyExchangeRateChange()){
            if(currencyCode.equals(DEFAULT_CURRENCY)){
                return new CurrencyValue(presentCurrencyValue.getValue(), true);
            }
            double multiplicand = 0.99 + Math.random() / 50.0; // between 0.99 and 1.01
            BigDecimal newValue = presentCurrencyValue.getValue().multiply(new BigDecimal(multiplicand)).round(new MathContext(5));
            return new CurrencyValue(newValue, true);
        }
        return new CurrencyValue(presentCurrencyValue.getValue(), false);
    }


    private static boolean shouldCurrencyExchangeRateChange() {
        return Math.random() < PROBABILITY_OF_CURRENCY_EXCHANGE_RATE_CHANGE;
    }

    public static ConcurrentHashMap<CurrencyCode, CurrencyValue> getExchangeRates() {
        return exchangeRates;
    }

    public static BigDecimal convertExchangeRatesToRequestedCurrency(CurrencyCode requestedCurrencyCode, CurrencyCode clientDefaultCurrencyCode) {
        BigDecimal clientCurrencyValue = exchangeRates.get(clientDefaultCurrencyCode).getValue();
        return exchangeRates.get(requestedCurrencyCode).getValue().divide(clientCurrencyValue, new MathContext(5));

    }

}

