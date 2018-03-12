package pact;

import au.com.dius.pact.consumer.ConsumerPactTestMk2;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShippingConsumerTest extends ConsumerPactTestMk2  {

    @Rule
    public PactProviderRuleMk2  rule = new PactProviderRuleMk2 ("shipping-service", "localhost", 9999, this);

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {

        PactDslJsonBody body = new PactDslJsonBody().stringValue("name", "shipment1").stringValue("amount", "8.0");

        return builder.given("shippment of order sent.")
                .uponReceiving("shippment request from order service")
                .path("/")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"responsetest\": true, \"name\": \"harry\"}")
                .given("test state 2") // NOTE: Using provider states are optional, you can leave it out
                .uponReceiving("ExampleJavaConsumerPactTest second test interaction")
                .method("OPTIONS")
                .path("/second")
                .body("")
                .willRespondWith()
                .status(200)
                .body("")
                .toPact();
    }

    @Override
    protected String providerName() {
        return "shipping-service";
    }

    @Override
    protected String consumerName() {
        return "orders-service";
    }

    @Override
    protected void runTest(MockServer mockServer) throws IOException {
        assertEquals(new ConsumerClient(mockServer.getUrl()).options("/second"), 200);
        Map expectedResponse = new HashMap();
        expectedResponse.put("responsetest", true);
        expectedResponse.put("name", "harry");
        assertEquals(new ConsumerClient(mockServer.getUrl()).getAsMap("/", ""), expectedResponse);
        assertEquals(new ConsumerClient(mockServer.getUrl()).options("/second"), 200);
    }
}
