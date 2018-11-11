package mp.application.emailsystem.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import mp.application.emailsystem.model.Citizen;
import mp.application.emailsystem.model.EmailAddress;

public class CitizenMapper {

	
	public static CitizenDTO mapping(Citizen citizen) {
		
		CitizenDTO citizenDTO = new CitizenDTO();
		citizenDTO.setId(citizen.getId());
		citizenDTO.setFirstname(citizen.getFirstname());
		citizenDTO.setSurname(citizen.getSurname());
		return citizenDTO;		
	}
	
	public static List<CitizenDTO> mapping (List<Citizen> citizens) {
		
		List<CitizenDTO> citizenDTOS = new ArrayList<CitizenDTO>();
		for (Citizen citizen: citizens) {
			citizenDTOS.add(mapping(citizen));
		}
		return citizenDTOS;
	}
	
	public static Page<CitizenDTO> mapping (Page<Citizen> citizens) {
		
		return citizens.map(citizen -> mapping(citizen));
	}
	
	public static Citizen mapping (CitizenDTO citizenDTO) {
		
		Citizen citizen = new Citizen();
		citizen.setId(citizenDTO.getId());
		citizen.setFirstname(citizenDTO.getFirstname());
		citizen.setSurname(citizenDTO.getSurname());
		return citizen;
	}	
}
