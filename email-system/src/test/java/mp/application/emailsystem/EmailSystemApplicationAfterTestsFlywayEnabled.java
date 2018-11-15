package mp.application.emailsystem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import mp.application.emailsystem.controller.CitizenController;
import mp.application.emailsystem.repository.CitizenRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailSystemApplicationAfterTestsFlywayEnabled {

	/**
	 * 
	 * 
	 * RUN ONCE WITH FLYWAY ENABLED
	 * 
	 */

	@Mock
	private CitizenRepository citizenRepository;

	@InjectMocks
	private CitizenController citizenController;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Verifying DELETE EmailAddress request succeed
	 */
	@Test
	public void aDeleteEmailAddress() throws Exception {

		mockMvc.perform(delete("/citizens/4/emailaddresses/3")).andExpect(status().isOk());
	}

	/**
	 * Verifying DELETE Citizen request succeed
	 */
	@Test
	public void bDeleteCitizenAfterTests() throws Exception {

		mockMvc.perform(delete("/citizens/4")).andExpect(status().isOk());
	}
}