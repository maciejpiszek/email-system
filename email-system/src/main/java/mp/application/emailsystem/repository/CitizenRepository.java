package mp.application.emailsystem.repository;

import mp.application.emailsystem.model.Citizen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	Page<Citizen> findById(Long citizenId, Pageable pageable);
}