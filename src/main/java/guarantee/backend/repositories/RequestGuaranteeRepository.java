package guarantee.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestGuaranteeRepository<RequestGuarantee, Long> extends CrudRepository<RequestGuarantee, Long> {
}
