package org.kie.kogito.shipping;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourierService {

    public Quote retrieveQuote(Prize prize, String courier) {
        Random random = new Random();
        double price = random.nextDouble();
        return new Quote(price, random.nextInt(30), courier);
    }
}
