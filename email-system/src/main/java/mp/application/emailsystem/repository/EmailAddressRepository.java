package mp.application.emailsystem.repository;

import mp.application.emailsystem.model.EmailAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAddressRepository extends JpaRepository<EmailAddress, Long> {
    Page<EmailAddress> findByCitizenId(Long citizenId, Pageable pageable);
}
