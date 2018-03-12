package pact;

import au.com.dius.pact.consumer.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.ConsumerPactTest;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslRequestWithoutPath;
import au.com.dius.pact.consumer.dsl.PactDslResponse;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import java.io.IOException;

public class ShippingConsumerTest extends ConsumerPactTestMk2  {

    @Rule
    public PactProviderRuleMk2  rule = new PactProviderRuleMk2 ("shipping-service", "localhost", 9999, this);

    @Pact(provider = "shipping-server", consumer = "orders-service")
    public MessagePact createPact(MessagePactBuilder builder) {
        PactDslJsonBody body = new PactDslJsonBody().stringValue("name", "shipment1").stringValue("amount", "8.0");

//            Map<String, String> metadata = new HashMap<String, String>();
//            metadata.put("contentType", "application/json");

            return builder.given("shippmeng of order sent.")
                    .expectsToReceive("success")
                    .withContent(body)
                    .toPact();
    }

        @Test
        @PactVerification({"test_provider", "SomeProviderState"})
        public void test() throws Exception {
//            Assert.assertNotNull(new String(currentMessage));
        }

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
        return null;
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

    }
//
//        public void setMessage(byte[] messageContents) {
//            currentMessage = messageContents;
//        }
}
