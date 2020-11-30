package guarantee.backend.service;

import guarantee.backend.model.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProvinceService {

    List<Province> getAll();
}
