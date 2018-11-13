package mp.application.emailsystem;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import mp.application.emailsystem.EmailSystemApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MockingTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	/**
	 * Testing GET REST Services from CitizenController on databsae V2 script
	 */
	@Test
	public void testRetrieveCitizens() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> responseCitizens = restTemplate.exchange(createURLWithPort("/citizens"), HttpMethod.GET,
				entity, String.class);
		ResponseEntity<String> responseCitizensSorted = restTemplate
				.exchange(createURLWithPort("/citizens/1/10/ASC/Surname"), HttpMethod.GET, entity, String.class);
		ResponseEntity<String> responseCitizen = restTemplate.exchange(createURLWithPort("/citizens/2"), HttpMethod.GET,
				entity, String.class);
		/*
		 * String expectedCitizens =
		 * "{\"content\":[{\"createdAt\":\"2018-11-12T16:48:03.956\",\"updatedAt\":\"2018-11-12T16:48:03.956\",\"id\":1,\"firstname\":\"WIESLAW\",\"surname\":\"BATYSKAF\",\"emailAddresses\":null},{\"createdAt\":\"2018-11-12T16:48:03.975\",\"updatedAt\":\"2018-11-12T16:48:03.975\",\"id\":2,\"firstname\":\"MARIUSZ\",\"surname\":\"PYSZCZUK\",\"emailAddresses\":null},{\"createdAt\":\"2018-11-12T16:48:03.975\",\"updatedAt\":\"2018-11-12T16:48:03.975\",\"id\":3,\"firstname\":\"LESZEK\",\"surname\":\"BUBICKI\",\"emailAddresses\":null}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true},\"pageNumber\":0,\"pageSize\":20,\"offset\":0,\"unpaged\":false,\"paged\":true},\"last\":true,\"totalElements\":3,\"totalPages\":1,\"size\":20,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true},\"numberOfElements\":3,\"first\":true}";
		 * String expectedCitizensSorted =
		 * "{\"content\":[{\"createdAt\":\"2018-11-12T16:48:03.956\",\"updatedAt\":\"2018-11-12T16:48:03.956\",\"id\":1,\"firstname\":\"WIESLAW\",\"surname\":\"BATYSKAF\",\"emailAddresses\":null},{\"createdAt\":\"2018-11-12T16:48:03.975\",\"updatedAt\":\"2018-11-12T16:48:03.975\",\"id\":3,\"firstname\":\"LESZEK\",\"surname\":\"BUBICKI\",\"emailAddresses\":null},{\"createdAt\":\"2018-11-12T16:48:03.975\",\"updatedAt\":\"2018-11-12T16:48:03.975\",\"id\":2,\"firstname\":\"MARIUSZ\",\"surname\":\"PYSZCZUK\",\"emailAddresses\":null}],\"pageable\":{\"sort\":{\"sorted\":true,\"unsorted\":false},\"offset\":0,\"pageNumber\":0,\"pageSize\":10,\"paged\":true,\"unpaged\":false},\"totalPages\":1,\"totalElements\":3,\"last\":true,\"size\":10,\"number\":0,\"sort\":{\"sorted\":true,\"unsorted\":false},\"numberOfElements\":3,\"first\":true}";
		 * String expectedCitizen =
		 * "{\"createdAt\":\"2018-11-12T16:48:03.975\",\"updatedAt\":\"2018-11-12T16:48:03.975\",\"id\":2,\"firstname\":\"MARIUSZ\",\"surname\":\"PYSZCZUK\",\"emailAddresses\":null}";
		 * JSONAssert.assertEquals(expectedCitizens, responseCitizens.getBody(), false);
		 * JSONAssert.assertEquals(expectedCitizensSorted,
		 * responseCitizensSorted.getBody(), false);
		 * JSONAssert.assertEquals(expectedCitizen, responseCitizen.getBody(), false);
		 */
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
