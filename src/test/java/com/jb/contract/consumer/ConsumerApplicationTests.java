package com.jb.contract.consumer;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
@AutoConfigureStubRunner (
		ids = "com.jb.contract:favourite-service-producer",
		stubsMode = StubRunnerProperties.StubsMode.LOCAL,
		stubsPerConsumer = true,
		consumerName = "consumer"
)
class ConsumerApplicationTests {

	@StubRunnerPort("favourite-service-producer")
	int port;

	@Test
	void should_return_true_if_favourite() {
		FavouriteRequest request = new FavouriteRequest("CONSUMER", "12345", "INVENTORYITEM", "67890");

		ResponseEntity<Boolean> exchange = new RestTemplate().exchange(
				RequestEntity.post(URI.create("http://localhost:" + port + "/consumer/hasFavourite"))
						.body(request), Boolean.class);

		BDDAssertions.then(exchange.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(exchange.getBody()).isEqualTo(Boolean.TRUE);
	}

	class FavouriteRequest {
		private String userKey;
		private String userValue;
		private String itemKey;
		private String itemValue;

		public FavouriteRequest() {
		}

		public FavouriteRequest(String userKey, String userValue, String itemKey, String itemValue) {
			this.userKey = userKey;
			this.userValue = userValue;
			this.itemKey = itemKey;
			this.itemValue = itemValue;
		}

		public String getUserKey() {
			return userKey;
		}

		public void setUserKey(String userKey) {
			this.userKey = userKey;
		}

		public String getUserValue() {
			return userValue;
		}

		public void setUserValue(String userValue) {
			this.userValue = userValue;
		}

		public String getItemKey() {
			return itemKey;
		}

		public void setItemKey(String itemKey) {
			this.itemKey = itemKey;
		}

		public String getItemValue() {
			return itemValue;
		}

		public void setItemValue(String itemValue) {
			this.itemValue = itemValue;
		}
	}

}
