package pl.edu.agh.sr.ExchangeOffice;


import io.grpc.stub.StreamObserver;
import sr.grpc.gen.Currency;
import sr.grpc.gen.CurrencyCode;
import sr.grpc.gen.ExchangeOfficeGrpc.ExchangeOfficeImplBase;
import sr.grpc.gen.ExchangeRateRequest;
import sr.grpc.gen.ExchangeRateResponse;

import java.util.HashSet;
import java.util.Set;

public class ExchangeOfficeImpl extends ExchangeOfficeImplBase {
    @Override
    public void subscribeToExchangeRatesChanges(ExchangeRateRequest request, StreamObserver<ExchangeRateResponse> responseObserver) {
        System.out.println("new bank subscribed");
        Set<Currency> updatedCurrencies = new HashSet<>();
        request.getSupportedCurrenciesList().forEach(currencyCode -> updatedCurrencies.add(
                Currency.newBuilder()
                        .setCurrencyCode(currencyCode)
                        .setValue("10.001").build()));
        while (true){
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            ExchangeRateResponse response = ExchangeRateResponse.newBuilder()
                    .addAllCurrencies(updatedCurrencies)
                    .build();
            responseObserver.onNext(response);
        }
        //responseObserver.onCompleted();
    }
}
