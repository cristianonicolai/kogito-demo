package org.kie.kogito.demo.process;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.kie.kogito.demo.process.rest.ScoresRemoteService;

@ApplicationScoped
public class ScoreService {
    
    @Inject
    @RestClient
    ScoresRemoteService scoresRemoteService;

    public boolean addScore() {
        return scoresRemoteService.addScore("");
    }
    
    public boolean addScore(String multiplier) {
        return scoresRemoteService.addScore(multiplier);
    }
    
}
