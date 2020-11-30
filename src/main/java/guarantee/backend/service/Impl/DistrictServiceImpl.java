package guarantee.backend.service.Impl;

import guarantee.backend.model.District;
import guarantee.backend.repositories.DistrictRepository;
import guarantee.backend.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Component("DistrictService")
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getByProvinceId(Long idProvince) {
        List<District> list = districtRepository.getAllByProvinceIdEquals(idProvince);
        return list;
    }

    @Override
    public List<District> getByProvinceName(String provinceName) {
        List<District> list = districtRepository.getAllByProvince_NameEquals(provinceName);
        return list;
    }
}
