package mp.application.emailsystem.controller;

import mp.application.emailsystem.dto.CitizenDTO;
import mp.application.emailsystem.dto.CitizenMapper;
import mp.application.emailsystem.exception.ResourceNotFoundException;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
public class CitizenController {

    @Autowired
    CitizenRepository citizenRepository;

    
    /**
     * 
     * @return pageable collection of citizens 
     */
    @GetMapping("/citizens")
    public Page<CitizenDTO> getAllCitizens(Pageable pageable) {
    	Page<Citizen> citizens = citizenRepository.findAll(pageable);
    	Page<CitizenDTO> citizenDTOS = CitizenMapper.mapping(citizens);
        
    	return citizenDTOS;
    }

    
    /**
     * 
     * @return creating and saving citizens into database
     */ 
    @PostMapping("/citizens")
    public Citizen createCitizen(@Valid @RequestBody CitizenDTO citizenDTO) {
        return citizenRepository.save(CitizenMapper.mapping(citizenDTO));
    }

    
    /**
     * 
     * @param citizenId
     * @param citizenRequest
     * @return updating citizens data 
     */
    @PutMapping("/citizens/{citizenId}")
    public Citizen updateCitizen(@PathVariable Long citizenId, 
    						  @Valid @RequestBody Citizen citizenRequest) {
        return citizenRepository.findById(citizenId).map(citizen -> {
            CitizenDTO citizenDTO = CitizenMapper.mapping(citizen);
        	citizenDTO.setFirstname(citizen.getFirstname());
            citizenDTO.setSurname(citizen.getSurname());
            return citizenRepository.save(CitizenMapper.mapping(citizenDTO));
        }).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
    }

    
    /**
     * 
     * @param citizenId
     * @return deleting citizen
     */
    @DeleteMapping("/citizens/{citizenId}")
    public ResponseEntity<?> deleteCitizen(@PathVariable Long citizenId) {
        return citizenRepository.findById(citizenId).map(citizen -> {
            citizenRepository.delete(citizen);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
    }

}