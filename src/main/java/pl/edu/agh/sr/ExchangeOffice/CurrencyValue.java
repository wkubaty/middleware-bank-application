package pl.edu.agh.sr.ExchangeOffice;

import sr.grpc.gen.CurrencyCode;

import java.math.BigDecimal;

public class CurrencyValue {
    private BigDecimal value;
    private Boolean updated;

    public CurrencyValue(BigDecimal value, Boolean updated) {
        this.value = value;
        this.updated = updated;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Boolean isUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }
}
