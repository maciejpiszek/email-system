package mp.application.emailsystem.controller;

import mp.application.emailsystem.dto.CitizenDTO;
import mp.application.emailsystem.dto.CitizenMapper;
import mp.application.emailsystem.exception.ResourceNotFoundException;
import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.repository.CitizenRepository;
import mp.application.emailsystem.repository.EmailAddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import javax.validation.Valid;

@RestController
public class CitizenController {

	@Autowired
	CitizenRepository citizenRepository;

	@Autowired
	EmailAddressRepository emailAddressRepository;

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
	 * @return pageable collection of citizens sorted
	 */
	@GetMapping("/citizens/{pageNumber}/{pageSize}/{sortDirection}/{orderBy}")
	public Page<CitizenDTO> getCitizensSorted(@PathVariable int pageNumber, @PathVariable int pageSize,
			@PathVariable String sortDirection, @PathVariable String... orderBy) {

		Sort.Direction direction = null;
		if (sortDirection.equals("ASC")) {
			direction = Sort.Direction.ASC;
		} else if (sortDirection.equals("DESC")) {
			direction = Sort.Direction.DESC;
		}
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, direction, orderBy);
		Page<Citizen> citizens = citizenRepository.findAll(pageRequest);
		Page<CitizenDTO> citizenDTOS = CitizenMapper.mapping(citizens);
		return citizenDTOS;
	}

	/**
	 * 
	 * @param citizenId
	 * @return citizen data on citizen id number given as a parameter
	 */
	@GetMapping("/citizens/{citizenId}")
	public CitizenDTO getCitizen(@PathVariable(value = "citizenId") Long citizenId) {
		Optional<Citizen> optionalCitizen = citizenRepository.findById(citizenId);
		Citizen citizen = optionalCitizen.get();
		CitizenDTO citizenDTO = CitizenMapper.mapping(citizen);
		return citizenDTO;

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
	 * @return updating citizen data on citizen id number given as a parameter
	 */
	@PutMapping("/citizens/{citizenId}")
	public Citizen updateCitizen(@PathVariable Long citizenId, @Valid @RequestBody CitizenDTO citizenDTORequest) {

		Optional<Citizen> optionalCitizen = citizenRepository.findById(citizenId);
		CitizenDTO citizenDTO = CitizenMapper.mapping(optionalCitizen.get());
		citizenDTO.setFirstname(citizenDTORequest.getFirstname());
		citizenDTO.setSurname(citizenDTORequest.getSurname());
		return citizenRepository.save(CitizenMapper.mapping(citizenDTO));

	}

	/**
	 * 
	 * @param citizenId
	 * @return deleting citizen
	 */
	@DeleteMapping("/citizens/{citizenId}")
	public ResponseEntity<?> deleteCitizen(Pageable pageable, @PathVariable Long citizenId) {
		if (citizenRepository.existsById(citizenId)) {

			emailAddressRepository.findByCitizenId(citizenId, pageable);
			emailAddressRepository.deleteAll();
		}
		return citizenRepository.findById(citizenId).map(citizen -> {
			citizenRepository.delete(citizen);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("CitizenId " + citizenId + " not found!"));
	}

}