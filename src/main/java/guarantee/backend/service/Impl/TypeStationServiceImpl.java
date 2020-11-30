package guarantee.backend.service.Impl;

import guarantee.backend.model.TypeStation;
import guarantee.backend.repositories.TypeStationRepository;
import guarantee.backend.service.ITypeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("TypeStationService")
public class TypeStationServiceImpl implements ITypeStationService {
    @Autowired
    private TypeStationRepository repository;

    @Override
    public List<TypeStation> getAll() {
        return (List<TypeStation>) repository.findAll();
    }
}
