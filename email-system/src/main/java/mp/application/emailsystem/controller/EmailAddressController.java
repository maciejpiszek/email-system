package mp.application.emailsystem.controller;

import mp.application.emailsystem.exception.ResourceNotFoundException;
import mp.application.emailsystem.model.EmailAddress;
import mp.application.emailsystem.repository.EmailAddressRepository;
import mp.application.emailsystem.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmailAddressController {

    @Autowired
    private EmailAddressRepository emailAddressRepository;

    @Autowired
    private CitizenRepository citizenRepository;
    
    
    /**
     * 
     * @param citizenId
     * @param pageable
     * @return
     */
    @GetMapping("/citizens/{citizenId}/emailaddresses")
    public Page<EmailAddress> getAllEmailAddressesByCitizenId(@PathVariable (value = "citizenId") Long citizenId,
                                                Pageable pageable) {
        return emailAddressRepository.findByCitizenId(citizenId, pageable);
    }
    
    
    /**
     * 
     * @param citizenId
     * @param emailAddress
     * @return
     */
    @PostMapping("/citizens/{citizenId}/emailaddresses")
    public EmailAddress createEmailAddress(@PathVariable (value = "CitizenId") Long citizenId,
                                 		  @Valid @RequestBody EmailAddress emailAddress) {
        return citizenRepository.findById(citizenId).map(citizen -> {
            emailAddress.setCitizen(citizen);
            return emailAddressRepository.save(emailAddress);
        }).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
    }
    
    
    /**
     * 
     * @param citizenId
     * @param emailAddressId
     * @param emailAddressRequest
     * @return
     */
    @PutMapping("/citizens/{citizenId}/emailaddresses/{emailAddressId}")
    public EmailAddress updateEmailAddress(@PathVariable (value = "citizenId") Long citizenId,
                                 		  @PathVariable (value = "emailAddressId") Long emailAddressId,
                                 		  @Valid @RequestBody EmailAddress emailAddressRequest) {
        if(!citizenRepository.existsById(citizenId)) {
            throw new ResourceNotFoundException("CitizenId " + citizenId + " not found!");
        }

        return emailAddressRepository.findById(emailAddressId).map(emailAddress -> {
            emailAddress.setEmailAddress(emailAddressRequest.getEmailAddress());
            return emailAddressRepository.save(emailAddress);
        }).orElseThrow(() -> new ResourceNotFoundException("EmailAddressId " + emailAddressId + "not found!"));
    }
    
    
    /**
     * 
     * @param citizenId
     * @param emailAddressId
     * @return
     */
    @DeleteMapping("/citizens/{citizenId}/emailaddresses/{emailAddressId}")
    public ResponseEntity<?> deleteEmailAddress(@PathVariable (value = "citizenId") Long citizenId,
                              				   @PathVariable (value = "emailAddressId") Long emailAddressId) {
        if(!citizenRepository.existsById(citizenId)) {
            throw new ResourceNotFoundException("CitizenId " + citizenId + " not found!");
        }

        return emailAddressRepository.findById(emailAddressId).map(emailAddress -> {
             emailAddressRepository.delete(emailAddress);
             return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("EmailAddressId " + emailAddressId + "not found!"));
    }
}