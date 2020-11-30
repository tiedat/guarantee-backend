package guarantee.backend.service;

import guarantee.backend.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IWardService {
    List<Ward> getByDistrictId(Long idDistrict);

    List<Ward> getByProNameDistrictName(String proName, String districtName);
}
