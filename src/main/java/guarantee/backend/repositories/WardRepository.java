package guarantee.backend.repositories;

import guarantee.backend.model.Ward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends CrudRepository<Ward, Long> {
    List<Ward> getAllByDistrictIdEquals(Long idDistrict);
    List<Ward> getAllByProvince_NameEqualsAndDistrict_NameEquals(String provinceName, String districtName);

}
