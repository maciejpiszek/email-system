package mp.application.emailsystem.controller;

import mp.application.emailsystem.dto.EmailAddressDTO;
import mp.application.emailsystem.dto.EmailAddressMapper;
import mp.application.emailsystem.exception.ResourceNotFoundException;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.model.EmailAddress;
import mp.application.emailsystem.repository.EmailAddressRepository;
import mp.application.emailsystem.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class EmailAddressController {

	@Autowired
	private EmailAddressRepository emailAddressRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	/**
	 * 
	 * @return Getting email addresses using citizen ID as a parameter
	 */
	@GetMapping("/citizens/{citizenId}/emailaddresses")
	public Page<EmailAddressDTO> getAllEmailAddressesByCitizenId(@PathVariable(value = "citizenId") Long citizenId,
			Pageable pageable) {
		Page<EmailAddress> emailAddresses = emailAddressRepository.findByCitizenId(citizenId, pageable);
		Page<EmailAddressDTO> emailAddressDTOS = EmailAddressMapper.mapping(emailAddresses);
		return emailAddressDTOS;
	}

	/**
	 * 
	 * @return Adding email address using citizen ID as a parameter
	 */
	@PostMapping("/citizens/{citizenId}/emailaddresses")
	public EmailAddress createEmailAddress(@PathVariable(value = "citizenId") Long citizenId,
			@Valid @RequestBody EmailAddressDTO emailAddressDTO) {
		return citizenRepository.findById(citizenId).map(citizen -> {
			EmailAddress emailAddress=EmailAddressMapper.mapping(emailAddressDTO);
			emailAddress.setCitizen(citizen);
			return emailAddressRepository.save(emailAddress);
		}).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
	}

	/**
	 * 
	 * @return Updating list of email addresses using citizen ID checking if given
	 *         address already exists
	 */
	@PutMapping("/citizens/{citizenId}/emailaddresses/{emailAddressId}")
	public EmailAddress updateEmailAddress(@PathVariable(value = "citizenId") Long citizenId,
			@PathVariable(value = "emailAddressId") Long emailAddressId,
			@Valid @RequestBody EmailAddressDTO emailAddressDTORequest) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("CitizenId " + citizenId + " not found!");
		}

		return emailAddressRepository.findById(emailAddressId).map(emailAddress -> {
			emailAddress.setEmailAddress(emailAddressDTORequest.getEmailAddress());
			return emailAddressRepository.save(emailAddress);
		}).orElseThrow(() -> new ResourceNotFoundException("EmailAddressId " + emailAddressId + " not found!"));
	}

	/**
	 * 
	 * @return Deleting given address checking if given address already exists
	 */
	@DeleteMapping("/citizens/{citizenId}/emailaddresses/{emailAddressId}")
	public void deleteEmailAddress(@PathVariable(value = "citizenId") Long citizenId,
			@PathVariable(value = "emailAddressId") Long emailAddressId) {
		if (!citizenRepository.existsById(citizenId)) {
			throw new ResourceNotFoundException("CitizenId " + citizenId + " not found!");
		}
			Citizen citizen = citizenRepository.getOne(citizenId);
			Optional<EmailAddress> optionalEmailAddress =emailAddressRepository.findById(emailAddressId);
			EmailAddress foundEmailAddress = optionalEmailAddress.get();			
		if (citizen.getEmailAddresses().contains(foundEmailAddress)) {
			emailAddressRepository.findById(emailAddressId).map(emailAddress -> {
			emailAddressRepository.delete(emailAddress);
			return ResponseEntity.ok().build();
		});
		} else throw new ResourceNotFoundException("EmailAddressId " + emailAddressId + " in CitizenId " + citizenId + " repository not found!");
	}
}