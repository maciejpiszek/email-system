package mp.application.emailsystem.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import mp.application.emailsystem.model.Citizen;

public class CitizenMapper {

	/**
	 * 
	 * @param citizen
	 * @return citizenDTO object copied from given citizen
	 */
	public static CitizenDTO mapping(Citizen citizen) {

		CitizenDTO citizenDTO = new CitizenDTO();
		citizenDTO.setId(citizen.getId());
		citizenDTO.setFirstname(citizen.getFirstname());
		citizenDTO.setSurname(citizen.getSurname());
		citizenDTO.setCreatedAt(citizen.getCreatedAt());
		citizenDTO.setUpdatedAt(citizen.getUpdatedAt());
		return citizenDTO;
	}

	/**
	 * 
	 * @param citizens
	 * @return DTO copy of citizens list
	 */
	public static List<CitizenDTO> mapping(List<Citizen> citizens) {

		List<CitizenDTO> citizenDTOS = new ArrayList<CitizenDTO>();
		for (Citizen citizen : citizens) {
			citizenDTOS.add(mapping(citizen));
		}
		return citizenDTOS;
	}

	/**
	 * 
	 * @param citizens
	 * @return DTO copy of citizens list pageable
	 */
	public static Page<CitizenDTO> mapping(Page<Citizen> citizens) {

		return citizens.map(citizen -> mapping(citizen));
	}

	/**
	 * 
	 * @param citizenDTO
	 * @return citizen object copied from given citizenDTO
	 */
	public static Citizen mapping(CitizenDTO citizenDTO) {

		Citizen citizen = new Citizen();
		citizen.setId(citizenDTO.getId());
		citizen.setFirstname(citizenDTO.getFirstname());
		citizen.setSurname(citizenDTO.getSurname());
		citizen.setCreatedAt(citizenDTO.getCreatedAt());
		citizen.setUpdatedAt(citizenDTO.getUpdatedAt());
		return citizen;
	}
}
