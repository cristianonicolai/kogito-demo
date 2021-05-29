package org.kie.kogito.shipping;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShippingService {

    public Shipping createShipping(Prize prize, Quote quote1, Quote quote2) {
        Quote quote = quote1.getApproved() ? quote1 : quote2;
        return new Shipping(quote, prize);
    }
}
