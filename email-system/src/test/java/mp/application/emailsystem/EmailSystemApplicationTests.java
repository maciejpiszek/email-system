package mp.application.emailsystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mp.application.emailsystem.dto.CitizenDTO;
import mp.application.emailsystem.dto.CitizenMapper;
import mp.application.emailsystem.dto.EmailAddressDTO;
import mp.application.emailsystem.dto.EmailAddressMapper;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.model.EmailAddress;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSystemApplicationTests {

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testCitizenMapper() {
		
		CitizenMapper citizenMapper = new CitizenMapper();
		Citizen citizen = new Citizen();
		citizen.setFirstname("TestJanusz");
		citizen.setSurname("TestKowalski");		
		citizen.setId(123456L);				
		CitizenDTO citizenDTO = citizenMapper.mapping(citizen);		
		assertTrue(citizen.getFirstname().equals(citizenDTO.getFirstname()));
		assertTrue(citizen.getSurname().equals(citizenDTO.getSurname()));
		assertTrue(citizen.getId().equals(citizenDTO.getId()));
	}

	@Test
	public void testEmailAddressMapper() {
		
		EmailAddressMapper emailAddressMapper = new EmailAddressMapper();
		EmailAddress emailAddress = new EmailAddress();	
		emailAddress.setId(123456L);
		emailAddress.setEmailAddress("test@test.pl");
		EmailAddressDTO emailAddressDTO = emailAddressMapper.mapping(emailAddress);		
		assertTrue(emailAddress.getId().equals(emailAddress.getId()));
		assertTrue(emailAddress.getEmailAddress().equals(emailAddress.getEmailAddress()));
	}
}
