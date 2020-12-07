package guarantee.backend.service;

import guarantee.backend.model.TypeStation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITypeStationService {
    List<TypeStation> getAll();
}
