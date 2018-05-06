package pl.edu.agh.sr.ExchangeOffice;


import io.grpc.stub.StreamObserver;
import sr.grpc.gen.Currency;
import sr.grpc.gen.CurrencyCode;
import sr.grpc.gen.ExchangeOfficeGrpc.ExchangeOfficeImplBase;
import sr.grpc.gen.ExchangeRateRequest;
import sr.grpc.gen.ExchangeRateResponse;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ExchangeOfficeImpl extends ExchangeOfficeImplBase {
    private int counter = 0;
    @Override
    public void subscribeToExchangeRatesChanges(ExchangeRateRequest request, StreamObserver<ExchangeRateResponse> responseObserver) {
        System.out.println("New bank subscribed...");
        List<CurrencyCode> supportedCurrencies = request.getSupportedCurrenciesList();
        while (true){
            Map<CurrencyCode, CurrencyValue> updatedCurrencies = ExchangeRatesMonitor.getExchangeRates();
            updatedCurrencies.forEach((k, v) -> System.out.println(v.getValue()));
            List<Currency> currenciesToSend = new LinkedList<>();
            supportedCurrencies.forEach(currencyCode -> {
                if(updatedCurrencies.get(currencyCode).isUpdated()){
                    currenciesToSend.add(
                            Currency.newBuilder()
                                    .setCurrencyCode(currencyCode)
                                    .setValue(ExchangeRatesMonitor.getExchangeRates().get(currencyCode).getValue().toString())
                                    .build());
                }
            });
            System.out.println("size: " + currenciesToSend.size());
            ExchangeRateResponse response = ExchangeRateResponse.newBuilder()
                    .addAllCurrencies(currenciesToSend)
                    .build();
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
    private int getNextValue(){
        counter++;
        return counter;
    }
}
