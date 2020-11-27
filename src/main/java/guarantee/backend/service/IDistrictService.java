package guarantee.backend.service;

import guarantee.backend.model.District;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDistrictService {
    List<District> getByProvinceId(Long idProvince);
}
