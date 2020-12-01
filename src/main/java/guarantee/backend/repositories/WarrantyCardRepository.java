package guarantee.backend.repositories;

import guarantee.backend.model.WarrantyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarrantyCardRepository extends JpaRepository<WarrantyCard, Long> {
    Optional<WarrantyCard> findBySerialNumber(String serialNumber);
}
