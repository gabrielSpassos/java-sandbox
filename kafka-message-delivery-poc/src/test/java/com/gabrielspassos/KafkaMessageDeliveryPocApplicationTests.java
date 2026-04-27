package com.gabrielspassos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "app.mode=AT_LEAST_ONCE")
class KafkaMessageDeliveryPocApplicationTests extends BaseIntegrationTest {

	@Test
	void contextLoads() {
	}

}
