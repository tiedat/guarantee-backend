package guarantee.backend.repositories;

import guarantee.backend.model.RequestGuarantee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestGuaranteeRepository extends CrudRepository<RequestGuarantee, Long> {
    List<RequestGuarantee> findBySerial(String serial);
}
