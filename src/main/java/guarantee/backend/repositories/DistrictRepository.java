package guarantee.backend.repositories;

import guarantee.backend.model.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends CrudRepository<District, Long> {
    List<District> getAllByProvinceIdEquals(Long idProvince);
}
