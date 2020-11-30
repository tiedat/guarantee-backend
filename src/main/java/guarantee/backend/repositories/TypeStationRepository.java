package guarantee.backend.repositories;

import guarantee.backend.model.TypeStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeStationRepository extends CrudRepository<TypeStation, Long> {
}
