package guarantee.backend.repositories;

import guarantee.backend.model.RequestGuarantee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestGuaranteeRepository extends CrudRepository<RequestGuarantee, Long> {
    RequestGuarantee findBySerial(String serial);
}
