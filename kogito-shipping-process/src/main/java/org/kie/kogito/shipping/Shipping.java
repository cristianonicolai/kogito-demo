package org.kie.kogito.shipping;

public class Shipping {

    private Quote quote;
    private Prize prize;

    public Shipping(Quote quote, Prize prize) {
        this.quote = quote;
        this.prize = prize;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "quote=" + quote +
                ", prize=" + prize +
                '}';
    }
}
