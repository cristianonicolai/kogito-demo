package org.kie.kogito.shipping;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;

public class KafkaTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final String KOGITO_INCOMING_STREAM = "kogito_incoming_stream";
    public static final String KOGITO_OUTGOING_STREAM = "kogito_outgoing_stream";

    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        env.putAll(InMemoryConnector.switchIncomingChannelsToInMemory(KOGITO_INCOMING_STREAM));
        env.putAll(InMemoryConnector.switchOutgoingChannelsToInMemory(KOGITO_OUTGOING_STREAM));
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
