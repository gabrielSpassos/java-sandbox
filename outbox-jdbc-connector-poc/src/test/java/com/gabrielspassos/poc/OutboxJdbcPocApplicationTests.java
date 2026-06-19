package com.gabrielspassos.poc;

import com.gabrielspassos.poc.controller.request.OrderRequest;
import com.gabrielspassos.poc.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OutboxJdbcPocApplicationTests {

	@Autowired
	private OrderService orderService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void shouldSaveOutboxEventWithJsonbPayload() {
		Long beforeCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM outbox", Long.class);

		orderService.save(new OrderRequest("test order"));

		Long afterCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM outbox", Long.class);

		assertEquals(beforeCount + 1, afterCount);
	}
}
