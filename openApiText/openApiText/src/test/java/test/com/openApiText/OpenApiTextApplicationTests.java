package test.com.openApiText;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT /*seta uma porta aletoria */)
class OpenApiTextApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
		String test = new RestTemplate().exchange("http://localhost:" + port + "/test", 
		HttpMethod.GET, null, String.class)
		.getBody();

		Assertions.assertEquals("test", test);
	}

}
