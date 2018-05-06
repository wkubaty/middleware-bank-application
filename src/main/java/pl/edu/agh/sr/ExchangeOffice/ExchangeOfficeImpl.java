package pl.edu.agh.sr.ExchangeOffice;

import io.grpc.stub.StreamObserver;
import sr.grpc.gen.Currency;
import sr.grpc.gen.CurrencyCode;
import sr.grpc.gen.ExchangeOfficeGrpc.ExchangeOfficeImplBase;
import sr.grpc.gen.ExchangeRateRequest;
import sr.grpc.gen.ExchangeRateResponse;

import java.util.*;
import java.util.stream.Collectors;


public class ExchangeOfficeImpl extends ExchangeOfficeImplBase {
    @Override
    public void subscribeToExchangeRatesChanges(ExchangeRateRequest request, StreamObserver<ExchangeRateResponse> responseObserver) {
        System.out.println("New bank subscribed...");
        List<CurrencyCode> supportedCurrencies = request.getSupportedCurrenciesList();
        CurrencyCode defaultCurrency = request.getDefaultCurrency();
        while (true){
            ExchangeRateResponse response = createResponseWithUpdatedCurrencies(supportedCurrencies, defaultCurrency);
            responseObserver.onNext(response);
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //responseObserver.onCompleted();
    }
    private ExchangeRateResponse createResponseWithUpdatedCurrencies(List<CurrencyCode> supportedCurrencies, CurrencyCode defaultCurrency){
        Map<CurrencyCode, CurrencyValue> updatedExchangeRates =
                ExchangeRatesMonitor.getExchangeRates()
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue().isUpdated())
                        .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));

        List<Currency> currenciesToSend = new LinkedList<>();
        supportedCurrencies.forEach(currencyCode -> {
            if(updatedExchangeRates.containsKey(currencyCode)){
                currenciesToSend.add(
                        Currency.newBuilder()
                                .setCurrencyCode(currencyCode)
                                .setValue(ExchangeRatesMonitor.convertExchangeRatesToRequestedCurrency(currencyCode, defaultCurrency).toString())
                                .build());
            }
        });
        return ExchangeRateResponse.newBuilder()
                .addAllCurrencies(currenciesToSend)
                .build();

    }
}
