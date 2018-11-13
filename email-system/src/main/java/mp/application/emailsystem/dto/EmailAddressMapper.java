package mp.application.emailsystem.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import mp.application.emailsystem.model.EmailAddress;

public class EmailAddressMapper {
	/**
	 * 
	 * @param emailAddress
	 * @return email address DTO object copied from given email address
	 */
	public static EmailAddressDTO mapping(EmailAddress emailAddress) {

		EmailAddressDTO emailAddressDTO = new EmailAddressDTO();
		emailAddressDTO.setId(emailAddress.getId());
		emailAddressDTO.setEmailAddress(emailAddress.getEmailAddress());
		emailAddressDTO.setCreatedAt(emailAddress.getCreatedAt());
		emailAddressDTO.setUpdatedAt(emailAddress.getUpdatedAt());
		return emailAddressDTO;
	}

	/**
	 * 
	 * @param emailAddresses
	 * @return list of DTO email addresses copied objects
	 */
	public static List<EmailAddressDTO> mapping(List<EmailAddress> emailAddresses) {
		List<EmailAddressDTO> emailAddressDTOS = new ArrayList<EmailAddressDTO>();
		for (EmailAddress emailAddress : emailAddresses) {
			emailAddressDTOS.add(mapping(emailAddress));
		}
		return emailAddressDTOS;
	}

	/**
	 * 
	 * @param emailAddresses
	 * @return list of DTO email addresses copied objects pageable
	 */
	public static Page<EmailAddressDTO> mapping(Page<EmailAddress> emailAddresses) {

		return emailAddresses.map(emailAddress -> mapping(emailAddress));
	}

	/**
	 * 
	 * @param emailAddressDTO
	 * @return email address object copied from given email address DTO
	 */
	public static EmailAddress mapping(EmailAddressDTO emailAddressDTO) {

		EmailAddress emailAddress = new EmailAddress();
		emailAddress.setId(emailAddressDTO.getId());
		emailAddress.setEmailAddress(emailAddressDTO.getEmailAddress());
		emailAddress.setCreatedAt(emailAddressDTO.getCreatedAt());
		emailAddress.setUpdatedAt(emailAddressDTO.getUpdatedAt());

		return emailAddress;
	}
}
