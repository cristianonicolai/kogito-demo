package org.kie.kogito.demo.process;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonService {
    
    public String getDetails(String name) {
        if ("krisv".equals(name)) {
            return "Castle 12, Fortress, Belgium";
        } else {
            return "Unknown";
        }
    }
}
