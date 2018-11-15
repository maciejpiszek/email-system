package mp.application.emailsystem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import mp.application.emailsystem.controller.CitizenController;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.model.EmailAddress;
import mp.application.emailsystem.repository.CitizenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailSystemApplicationBeforeTestsFlywayDisabled {

	/**
	 * 
	 * 
	 * RUN ONCE WITH BEFORE FIRST FLYWAY USAGE
	 * 
	 */

	@Mock
	private CitizenRepository citizenRepository;

	@InjectMocks
	private CitizenController citizenController;

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	int serverPort;

	/**
	 * Verifying POST Citizen request succeed
	 */
	@Test
	public void setUpCitizenBeforeTests() throws Exception {

		Citizen citizen = new Citizen("TEST1", "TEST1");
		mockMvc.perform(post("/citizens").contentType(MediaType.APPLICATION_JSON).content(asJsonString(citizen)))
				.andExpect(status().isOk());
	}

	/**
	 * Verifying POST EmailAddress request succeed
	 */
	@Test
	public void setUpEmailBeforeTests() throws Exception {

		EmailAddress emailAddress = new EmailAddress("test@test.pl");
		mockMvc.perform(post("/citizens/1/emailaddresses").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(emailAddress))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}