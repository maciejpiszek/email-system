package mp.application.emailsystem.controller;

import mp.application.emailsystem.exception.ResourceNotFoundException;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class CitizenController {

    @Autowired
    CitizenRepository citizenRepository;

    
    /**
     * 
     * @param pageable
     * @return 
     */
    @GetMapping("/citizens")
    public Page<Citizen> getAllCitizens(Pageable pageable) {
        return citizenRepository.findAll(pageable);
    }

    
    /**
     * 
     * @param citizen
     * @return
     */
    @PostMapping("/citizens")
    public Citizen createCitizen(@Valid @RequestBody Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    
    /**
     * 
     * @param citizenId
     * @param citizenRequest
     * @return
     */
    @PutMapping("/citizens/{citizenId}")
    public Citizen updateCitizen(@PathVariable Long citizenId, 
    						  @Valid @RequestBody Citizen citizenRequest) {
        return citizenRepository.findById(citizenId).map(citizen -> {
            citizen.setFirstname(citizenRequest.getFirstname());
            citizen.setSurname(citizenRequest.getSurname());
            return citizenRepository.save(citizen);
        }).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
    }

    
    /**
     * 
     * @param citizenId
     * @return
     */
    @DeleteMapping("/citizens/{citizenId}")
    public ResponseEntity<?> deleteCitizen(@PathVariable Long citizenId) {
        return citizenRepository.findById(citizenId).map(citizen -> {
            citizenRepository.delete(citizen);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
    }

}