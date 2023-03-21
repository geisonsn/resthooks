package bosh.resthooks;

import bosh.resthooks.model.Subscription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class ResthooksApplicationTests {

	@Autowired
	private WebTestClient client;

	@Test
	void getAllSubscriptions() throws Exception{
		this.client.get()
			.uri("/subscriptions")
			.exchange()
			.expectStatus()
			.isOk();
	}

	@Test
	void getSubscriptionById_success() throws Exception{
		this.client.get()
			.uri(String.format("/subscriptions/%d", 1))
			.exchange()
			.expectStatus()
			.isOk();
	}

	@Test
	void getSubscriptionById_notfound() throws Exception{
		this.client.get()
			.uri(String.format("/subscriptions/%d", 9))
			.exchange()
			.expectStatus()
			.isNotFound();
	}

	@Test
 	void createSubscription_success() throws Exception {
		Subscription sub = Subscription.builder()
			.url("subscriptions")
			.topicId("abc123")
			.hookUri("http://localhost:8080/subscriptions")
			.topicName("hook.create")
			.topicDescription("Topic to create hooks");

		this.client.post()
			.uri("/subscriptions")
			.bodyValue(sub)
			.exchange()
			.expectStatus()
			.isCreated();
	}

	@Test
	void updateSubscription_success() throws Exception {
		Subscription sub = Subscription.builder()
			.url("subscriptions")
			.topicId("abc123")
			.hookUri("http://localhost:8080/subscriptions")
			.topicName("hook.create")
			.topicDescription("Topic updated");

		this.client.put()
			.uri(String.format("/subscriptions/%d", 1))
			.bodyValue(sub)
			.exchange()
			.expectStatus()
			.isOk();
	}

	@Test
	void updateSubscription_notfound() throws Exception {
		Subscription sub = Subscription.builder()
			.url("subscriptions")
			.topicId("abc123")
			.hookUri("http://localhost:8080/subscriptions")
			.topicName("hook.create")
			.topicDescription("Topic updated");

		this.client.put()
			.uri(String.format("/subscriptions/%d", 9))
			.bodyValue(sub)
			.exchange()
			.expectStatus()
			.isNotFound();
	}

	@Test
	void deleteSubscription_success() throws Exception {
		this.client.delete()
			.uri(String.format("/subscriptions/%d", 3))
			.exchange()
			.expectStatus()
			.isOk();
	}

	@Test
	void deleteSubscription_notfound() throws Exception {
		this.client.delete()
			.uri(String.format("/subscriptions/%d", 9))
			.exchange()
			.expectStatus()
			.isNotFound();
	}

}
