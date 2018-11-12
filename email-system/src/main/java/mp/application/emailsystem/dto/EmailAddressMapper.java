package mp.application.emailsystem.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import mp.application.emailsystem.model.EmailAddress;

public class EmailAddressMapper {

	
public static EmailAddressDTO mapping(EmailAddress emailAddress) {
		
		EmailAddressDTO emailAddressDTO = new EmailAddressDTO();
		emailAddressDTO.setId(emailAddress.getId());
		emailAddressDTO.setEmailAddress(emailAddress.getEmailAddress());
		emailAddressDTO.setCitizen(emailAddress.getCitizen());
		return emailAddressDTO;		
	}
	
	
	public static List<EmailAddressDTO> mapping (List<EmailAddress> emailAddresses) {
		List<EmailAddressDTO> emailAddressDTOS = new ArrayList<EmailAddressDTO>();
		for (EmailAddress emailAddress: emailAddresses) {
			emailAddressDTOS.add(mapping(emailAddress));
		}
		return emailAddressDTOS;
	}
	
	public static Page<EmailAddressDTO> mapping (Page<EmailAddress> emailAddresses) {
		
		return emailAddresses.map(emailAddress -> mapping(emailAddress));
	}
}
