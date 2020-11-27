package guarantee.backend.service.Impl;

import guarantee.backend.model.Ward;
import guarantee.backend.repositories.WardRepository;
import guarantee.backend.service.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("WardService")
public class WardServiceImpl implements IWardService {

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> getByDistrictId(Long idDistrict) {
        List<Ward> list = wardRepository.getAllByDistrictIdEquals(idDistrict);
        return list;
    }
}
