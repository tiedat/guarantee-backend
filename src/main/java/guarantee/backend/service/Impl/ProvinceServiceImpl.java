package guarantee.backend.service.Impl;

import guarantee.backend.model.Province;
import guarantee.backend.repositories.ProvinceRepository;
import guarantee.backend.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@Component("ProvinceService")
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
//    @Autowired
//    private EntityManager entityManager;

    @Override
    public List<Province> getAll() {
        List<Province> list = (List<Province>) provinceRepository.findAll();
        return list;
    }

    @Override
    public String getAreaByName(String name) {
        String area = provinceRepository.showAreaByName(name);
        return area;
    }

}
