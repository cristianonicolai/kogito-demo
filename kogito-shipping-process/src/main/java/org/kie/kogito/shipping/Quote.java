package org.kie.kogito.shipping;

import java.text.NumberFormat;

public class Quote {

    private Double price;
    private String fmtPrice;
    private Integer days;
    private String courier;
    private Boolean approved = false;

    public Quote() {
    }

    public Quote(Double price, Integer days, String courier) {
        setPrice(price);
        this.days = days;
        this.courier = courier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.fmtPrice = NumberFormat.getCurrencyInstance().format(price);
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getFmtPrice() {
        return fmtPrice;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "price=" + price +
                ", fmtPrice='" + fmtPrice + '\'' +
                ", days=" + days +
                ", courier='" + courier + '\'' +
                ", approved=" + approved +
                '}';
    }
}
