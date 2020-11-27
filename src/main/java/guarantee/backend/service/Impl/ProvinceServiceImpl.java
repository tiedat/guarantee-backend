package guarantee.backend.service.Impl;

import guarantee.backend.model.Province;
import guarantee.backend.repositories.ProvinceRepository;
import guarantee.backend.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("ProvinceService")
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAll() {
        List<Province> list = (List<Province>) provinceRepository.findAll();
        return list;
    }
}
