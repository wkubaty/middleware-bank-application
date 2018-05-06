package pl.edu.agh.sr.Bank;

import org.junit.Test;
import pl.edu.agh.sr.ExchangeOffice.ExchangeRatesMonitor;
import sr.grpc.gen.CurrencyCode;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ExchangeRatesMonitorTest {
    @Test
    public void shouldExchangeRateChange(){
        ExchangeRatesMonitor exchangeRatesMonitor = new ExchangeRatesMonitor();
        BigDecimal PLNBeforeUpdate = exchangeRatesMonitor.getExchangeRates().get(CurrencyCode.PLN).getValue();
        exchangeRatesMonitor.updateExchangeRates();
        BigDecimal PLNAfterUpdate = exchangeRatesMonitor.getExchangeRates().get(CurrencyCode.PLN).getValue();
        assertEquals(PLNBeforeUpdate.doubleValue(), PLNAfterUpdate.doubleValue(), 0.1);
    }

}
