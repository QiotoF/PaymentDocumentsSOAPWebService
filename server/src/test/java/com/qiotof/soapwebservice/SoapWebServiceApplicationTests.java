package com.qiotof.soapwebservice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class SoapWebServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private WebTestClient webClient;

	@Test
	public void testWSDL() throws Exception {

		webClient.get().uri("/ws/PaymentDocuments.wsdl")
				.exchange().expectStatus().isOk();

	}

}
