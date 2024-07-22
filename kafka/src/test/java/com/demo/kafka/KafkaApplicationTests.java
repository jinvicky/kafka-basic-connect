package com.demo.kafka;

import com.demo.kafka.service.CustomProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CustomProducer sut;

	@Test
	void test1() {
//		sut.send("this message sent from spring boot application!");
	}

}
