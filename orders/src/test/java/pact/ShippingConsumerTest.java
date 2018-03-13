package pact;

import au.com.dius.pact.consumer.ConsumerPactTestMk2;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import au.com.dius.pact.model.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShippingConsumerTest extends ConsumerPactTestMk2 {

    @Rule
    public PactProviderRuleMk2 rule = new PactProviderRuleMk2("shipping-service", "localhost", 9999, this);

    private   PactDslJsonBody  requestBody = new PactDslJsonBody()
                .stringValue("name", "shipment1")
                .stringValue("amount", "8.0");

    private PactDslJsonBody responseBody = new PactDslJsonBody()
            .stringValue("id", "d331083b-a1f4-4d61-ab59-14eed5e3fc7f")
            .stringValue("name", "shipment1")
            .stringValue("amount", "8.0");

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder.given("shippment of order sent.")
                .uponReceiving("shippment request from order service")
                .path("/shipping?username=consumerA")
//                .query("username=consumerA")
                .method("POST")
                .body(requestBody)
                .willRespondWith()
                .status(201)
                .body(responseBody)
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
        assertEquals(new ConsumerClient(mockServer.getUrl())
                .postBody("/shipping?username=consumerA", requestBody.toString(), ContentType.APPLICATION_JSON), responseBody.toString());
    }
}
