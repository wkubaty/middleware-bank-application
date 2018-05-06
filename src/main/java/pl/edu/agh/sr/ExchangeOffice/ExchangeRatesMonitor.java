package pl.edu.agh.sr.ExchangeOffice;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sr.grpc.gen.CurrencyCode;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ExchangeRatesMonitor implements Runnable{
    private static final double PROBABILITY_OF_CURRENCY_EXCHANGE_RATE_CHANGE = 0.5;
    private static final CurrencyCode DEFAULT_CURRENCY = CurrencyCode.USD;
    private static ConcurrentHashMap<CurrencyCode, CurrencyValue> exchangeRates;

    public ExchangeRatesMonitor() {
        exchangeRates = new ConcurrentHashMap<>();
        initializeExchangeRates();
    }

    private static void initializeExchangeRates() {
        exchangeRates.put(CurrencyCode.PLN, new CurrencyValue(new BigDecimal("0.2811"), true));
        exchangeRates.put(CurrencyCode.USD, new CurrencyValue(new BigDecimal("0.3811"), true));
        exchangeRates.put(CurrencyCode.EUR, new CurrencyValue(new BigDecimal("0.4811"), true));
//        exchangeRates.put(new CurrencyValue(CurrencyCode.USD, new BigDecimal("1.0000")), true);
//        exchangeRates.put(new CurrencyValue(CurrencyCode.EUR, new BigDecimal("1.1977")), true);
//        exchangeRates.put(new CurrencyValue(CurrencyCode.GBP, new BigDecimal("1.3535")), true);
//        exchangeRates.put(new CurrencyValue(CurrencyCode.CHF, new BigDecimal("1.0001")), true);
//        exchangeRates.put(new CurrencyValue(CurrencyCode.JPY, new BigDecimal("0.0091")), true);
//        exchangeRates.put(new CurrencyValue(CurrencyCode.RUB, new BigDecimal("0.0160")), true);
//        exchangeRates.put(new CurrencyValue(CurrencyCode.CNY, new BigDecimal("0.1571")), true);
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
        exchangeRates.forEach((k, v) -> exchangeRates.replace(k, simulateExchangeRateChanges(v)));
    }

    private static CurrencyValue simulateExchangeRateChanges(CurrencyValue presentCurrencyValue){
        if(shouldCurrencyExchangeRateChange()){
            double multiplicand = 0.9 + Math.random()/5.0; // between 0.9 and 1.1
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

}

