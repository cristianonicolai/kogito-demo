package org.kie.kogito.shipping;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.v03.CloudEventV03;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.reactive.messaging.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.connectors.InMemorySink;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kie.kogito.shipping.KafkaTestResourceLifecycleManager.KOGITO_INCOMING_STREAM;
import static org.kie.kogito.shipping.KafkaTestResourceLifecycleManager.KOGITO_OUTGOING_STREAM;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
public class ShippingIT {

    @Inject
    @Any
    InMemoryConnector connector;
    
    @Inject
    ObjectMapper mapper;

    @Test
    public void testProcess() throws Exception {
        Prize prize = new Prize("admin", "details", "lego"); 
        
        CloudEventV03 ce = CloudEventBuilder.v03().withData(mapper.writeValueAsBytes(prize)).withSource(URI.create("http://localhost")).withType("shipping").withId(UUID.randomUUID().toString()).build();
        
        connector.source(KOGITO_INCOMING_STREAM).send(mapper.writeValueAsString(ce));
        InMemorySink received = connector.sink(KOGITO_OUTGOING_STREAM);

        String id = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/shipping")
                .then()
                .statusCode(200)
                .log().ifValidationFails()
                .body("$.size()", is(1))
                .body("[0].id", not(emptyOrNullString()))
                .extract()
                .path("[0].id");

        String taskId = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/shipping/{processId}/tasks", id)
                .then()
                .statusCode(200)
                .log().ifValidationFails()
                .body("$.size()", is(1))
                .body("[0].id", not(emptyOrNullString()))
                .body("[0].parameters.shipping.prize.username", is(prize.getUsername()))
                .body("[0].parameters.shipping.prize.details", is(prize.getDetails()))
                .body("[0].parameters.shipping.prize.prizename", is(prize.getPrizename()))
                .body("[0].parameters.shipping.quote.courier", not(emptyOrNullString()))
                .body("[0].parameters.shipping.quote.approved", is(true))
                .extract()
                .path("[0].id");

        given()
                .contentType(ContentType.JSON)
                .body(new HashMap<>())
                .when()
                .post("/shipping/{id}/HandleShipping/{taskId}", id, taskId)
                .then()
                .statusCode(200);
        
        assertEquals(1, received.received().size());
    }
}
