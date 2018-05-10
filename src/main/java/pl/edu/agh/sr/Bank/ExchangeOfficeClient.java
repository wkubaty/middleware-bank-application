package pl.edu.agh.sr.Bank;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sr.grpc.gen.*;
import sr.grpc.gen.Currency;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ExchangeOfficeClient implements Runnable{
    private final static Logger logger = Logger.getLogger(ExchangeOfficeClient.class.getName());
    private final ExchangeOfficeGrpc.ExchangeOfficeBlockingStub exchangeOfficeBlockingStub;
    private final ManagedChannel channel;
    private static ConcurrentHashMap<CurrencyCode, BigDecimal> exchangeRates = new ConcurrentHashMap<>();
    final static CurrencyCode DEFAULT_CURRENCY = CurrencyCode.PLN;
    private Set<CurrencyCode> supportedCurrencies;
    public ExchangeOfficeClient(String host, int port, Set<CurrencyCode> supportedCurrencies) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        this.supportedCurrencies = supportedCurrencies;
        this.exchangeOfficeBlockingStub = ExchangeOfficeGrpc.newBlockingStub(channel);

    }

    public void run() {
        ExchangeRateRequest request = ExchangeRateRequest.newBuilder()
                .setDefaultCurrency(DEFAULT_CURRENCY)
                .addAllSupportedCurrencies(supportedCurrencies)
                .build();
        Iterator<ExchangeRateResponse> response;
        response = exchangeOfficeBlockingStub.subscribeToExchangeRatesChanges(request);
        while (response.hasNext()) {
            ExchangeRateResponse exchangeRateResponse = response.next();
            List<Currency> currenciesList = exchangeRateResponse.getCurrenciesList();
            System.out.println("updated currencies: ");
            currenciesList.forEach(currency -> {
                System.out.println(currency.getCurrencyCode());
                exchangeRates.put(currency.getCurrencyCode(), new BigDecimal(currency.getValue()));
            });
            System.out.println("Current exchange rates: ");

            exchangeRates.forEach((currencyCode, value) ->
                    System.out.println(currencyCode.name() + ": " + value));

        }
    }

    public static ConcurrentHashMap<CurrencyCode, BigDecimal> getExchangeRates() {
        return exchangeRates;
    }


}
