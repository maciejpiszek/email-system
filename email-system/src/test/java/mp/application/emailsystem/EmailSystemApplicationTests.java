package mp.application.emailsystem;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import mp.application.emailsystem.controller.CitizenController;
import mp.application.emailsystem.dto.CitizenDTO;
import mp.application.emailsystem.dto.CitizenMapper;
import mp.application.emailsystem.dto.EmailAddressDTO;
import mp.application.emailsystem.dto.EmailAddressMapper;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.model.EmailAddress;
import mp.application.emailsystem.repository.CitizenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmailSystemApplicationTests {
	
	
	/**
	 * RUNS WITH FLYWAY V2 SCRPIT
	 */

	@Mock
	private CitizenRepository citizenRepository;

	@InjectMocks
	private CitizenController citizenController;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	int serverPort;
	
	Citizen citizen = new Citizen("TEST", "TEST");

	@BeforeAll
	public void setUpBeforeClass() throws Exception {

	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void contextLoads() {

	}

	/**
	 * Verifying GET request succeed
	 */
	
	  @Test public void testGetCitizensRestTemplate() throws URISyntaxException {
	  
	  final String baseURI = "http://localhost:" + serverPort + "/citizens"; URI
	  uri = new URI(baseURI); ResponseEntity<String> result =
	  testRestTemplate.getForEntity(uri, String.class);
	  
	  Assert.assertEquals(200, result.getStatusCodeValue());
	  
	  }
	
	/**
	 * Verifying GET request succeed
	 */
	@Test
	public void testGetCitizens() throws Exception {

		mockMvc.perform(get("/citizens")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	/**
	 * Verifying GET request succeed
	 * Operating on citizen ID no 1 - given in Flyway script
	 */
	@Test
	public void tesGetCitizen() throws Exception {

		mockMvc.perform(get("/citizens/1")).andExpect(status().isOk()) 
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	/**
	 * Verifying POST request succeed
	 */
	@Test
	public void testCreateCitizen() throws Exception {

		
		Citizen citizen = new Citizen("TEST", "TEST");
		mockMvc.perform(post("/citizens").contentType(MediaType.APPLICATION_JSON).content(asJsonString(citizen)))
				.andExpect(status().isOk());
		
	}

	/**
	 * Verifying PUT request succeed
	 * Operating on citizen ID no 2 - given in Flyway script
	 */
	@Test
	public void testUpdateCitizen() throws Exception {

		Citizen citizen = new Citizen("DASTARDLEY", "KOWALSKI");
		mockMvc.perform(put("/citizens/2")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(citizen))).andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmailAddresses() throws Exception {

		mockMvc.perform(get("/citizens/3/emailaddresses")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	
	@Test
	public void testCitizenMapper() {

		LocalDateTime now1 = LocalDateTime.now();
		LocalDateTime now2 = LocalDateTime.now();
		Citizen citizen = new Citizen();
		citizen.setFirstname("TestJanusz");
		citizen.setSurname("TestKowalski");
		citizen.setId(123456L);
		citizen.setCreatedAt(now1);
		citizen.setUpdatedAt(now2);
		CitizenDTO citizenDTO = CitizenMapper.mapping(citizen);

		assertTrue(citizen.getFirstname().equals(citizenDTO.getFirstname()));
		assertTrue(citizen.getSurname().equals(citizenDTO.getSurname()));
		assertTrue(citizen.getId().equals(citizenDTO.getId()));
		assertTrue(citizen.getCreatedAt().equals(citizenDTO.getCreatedAt()));
		assertTrue(citizen.getUpdatedAt().equals(citizenDTO.getUpdatedAt()));

	}

	@Test
	public void testEmailAddressMapper() {

		LocalDateTime now1 = LocalDateTime.now();
		LocalDateTime now2 = LocalDateTime.now();
		EmailAddress emailAddress = new EmailAddress();
		emailAddress.setId(123456L);
		emailAddress.setEmailAddress("test@test.pl");
		emailAddress.setCreatedAt(now1);
		emailAddress.setUpdatedAt(now2);
		EmailAddressDTO emailAddressDTO = EmailAddressMapper.mapping(emailAddress);
		assertTrue(emailAddress.getId().equals(emailAddressDTO.getId()));
		assertTrue(emailAddress.getEmailAddress().equals(emailAddressDTO.getEmailAddress()));
		assertTrue(emailAddress.getCreatedAt().equals(emailAddressDTO.getCreatedAt()));
		assertTrue(emailAddress.getUpdatedAt().equals(emailAddressDTO.getUpdatedAt()));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
